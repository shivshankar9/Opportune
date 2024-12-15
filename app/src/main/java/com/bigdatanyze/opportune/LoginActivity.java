package com.bigdatanyze.opportune;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;  // Import the SignInButton class
import com.google.android.gms.tasks.Task;
import com.google.android.material.textview.MaterialTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

	private static final String TAG = "LoginActivity";
	private static final String BASE_URL = "https://server-opportune-1.onrender.com/";

	private EditText usernameEditText, passwordEditText;
	private Button loginButton;
	private SignInButton googleSignInButton; // Change the type to SignInButton
	private MaterialTextView recruiterLoginPageButton;
	private TextView signUp;
	private ImageView logo;

	private GoogleSignInClient googleSignInClient;
	private static final int RC_SIGN_IN = 100;

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// Initialize the views
		logo = findViewById(R.id.logo);
		usernameEditText = findViewById(R.id.email);
		passwordEditText = findViewById(R.id.password);
		loginButton = findViewById(R.id.login_button);
		googleSignInButton = findViewById(R.id.google_sign_in_button); // No cast needed
		signUp = findViewById(R.id.sign_up);
		recruiterLoginPageButton = findViewById(R.id.recruiter_login_page_button);

		// Configure Google Sign-In
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestEmail()
				.build();
		googleSignInClient = GoogleSignIn.getClient(this, gso);

		// Login button click listener
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loginUser();
			}
		});

		// Google Sign-In button click listener
		googleSignInButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signInWithGoogle();
			}
		});

		// Signup button click listener
		signUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signupUser();
			}
		});

		// Recruiter login page button click listener
		recruiterLoginPageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				recruiterLoginPage();
			}
		});
	}

	private void recruiterLoginPage() {
		Intent intent = new Intent(LoginActivity.this, RecruiterLoginActivity.class);
		startActivity(intent);
		finish();
	}

	private void loginUser() {
		String username = usernameEditText.getText().toString();
		String password = passwordEditText.getText().toString();

		if (username.isEmpty() || password.isEmpty()) {
			Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
			return;
		}

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		UsersApi usersApi = retrofit.create(UsersApi.class);
		Call<Users> call = usersApi.getUserByEmail(username);

		call.enqueue(new Callback<Users>() {
			@Override
			public void onResponse(Call<Users> call, Response<Users> response) {
				if (response.isSuccessful() && response.body() != null) {
					Users user = response.body();
					if (user.getPassword().equals(password)) {
						saveUserSession(user);
						navigateToDashboard();
					} else {
						Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<Users> call, Throwable t) {
				Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void signupUser() {
		Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
		startActivity(intent);
		finish();
	}

	private void signInWithGoogle() {
		Intent signInIntent = googleSignInClient.getSignInIntent();
		startActivityForResult(signInIntent, RC_SIGN_IN);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RC_SIGN_IN) {
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
			handleGoogleSignInResult(task);
		}
	}

	private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
		try {
			GoogleSignInAccount account = completedTask.getResult(Exception.class);
			if (account != null) {
				String name = account.getDisplayName();
				String email = account.getEmail();
				String phone = "Unknown"; // Placeholder for phone number

				registerUserOnServer(name, email, phone);
			}
		} catch (Exception e) {
			Log.e(TAG, "Google sign-in failed", e);
		}
	}

	private void registerUserOnServer(String name, String email, String phone) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		UsersApi usersApi = retrofit.create(UsersApi.class);

		Users newUser = new Users(name, email, phone, true, "12345678");
		Call<Users> call = usersApi.registerUser(newUser);

		call.enqueue(new Callback<Users>() {
			@Override
			public void onResponse(Call<Users> call, Response<Users> response) {
				if (response.isSuccessful()) {
					Toast.makeText(LoginActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
					navigateToDashboard();
				} else {
					Toast.makeText(LoginActivity.this, "Failed to register user", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<Users> call, Throwable t) {
				Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void saveUserSession(Users user) {
		SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean("isLoggedIn", true);
		editor.putString("userId", user.getId());
		editor.putString("userName", user.getName());
		editor.apply();
	}

	private void navigateToDashboard() {
		Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
		startActivity(intent);
		finish();
	}
}
