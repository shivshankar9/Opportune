package com.bigdatanyze.opportune;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

	private EditText usernameEditText, passwordEditText;
	private Button loginButton;
	private MaterialTextView recruiterLoginPageButton;
	private TextView signUp;
	private ImageView logo;

	private static final String BASE_URL = "https://server-opportune-1.onrender.com/";

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// Initialize the views
		logo = findViewById(R.id.logo);
		usernameEditText = findViewById(R.id.email); // Changed from username to email
		passwordEditText = findViewById(R.id.password);
		loginButton = findViewById(R.id.login_button);
		signUp = findViewById(R.id.sign_up); // Changed from signup_button to sign_up
		recruiterLoginPageButton = findViewById(R.id.recruiter_login_page_button); // Initialize recruiter login button

		// Load animations
		Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		Animation slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
		Animation slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
		Animation slideInUp = AnimationUtils.loadAnimation(this, R.anim.slide_in_up);

		// Apply animations
		logo.startAnimation(fadeIn);
		usernameEditText.startAnimation(slideInLeft);
		passwordEditText.startAnimation(slideInRight);
		loginButton.startAnimation(slideInUp);
		signUp.startAnimation(fadeIn);

		// Login button click listener
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loginUser();
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

		// Validate email and password fields
		if (username.isEmpty() || password.isEmpty()) {
			Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
			return;
		}

		// Retrofit to make API call
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL) // Ensure BASE_URL is correct
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		UsersApi usersApi = retrofit.create(UsersApi.class);

		// Send request to fetch user by email
		Call<Users> call = usersApi.getUserByEmail(username);  // Assuming the method is defined in UsersApi

		call.enqueue(new Callback<Users>() {
			@Override
			public void onResponse(Call<Users> call, Response<Users> response) {
				if (response.isSuccessful() && response.body() != null) {
					Users user = response.body();

					// Compare the password with the one stored on the server
					if (user.getPassword().equals(password)) {
						Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

						// Save user session info (e.g., SharedPreferences)
						SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedPreferences.edit();
						editor.putBoolean("isLoggedIn", true);
						editor.putString("userId", user.getId());
						editor.putString("userName", user.getName());
						editor.apply();

						// Navigate to the main app dashboard
						Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
					}
				} else {
					// If the user is not found (404 or any other error)
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
}
