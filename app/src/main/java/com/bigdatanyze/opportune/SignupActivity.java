package com.bigdatanyze.opportune;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {

	private EditText nameEditText, emailEditText, passwordEditText;
	private Button signupButton;
	private TextView backToLogin;
	private ImageView logo;
	private ProgressBar progressBar;

	private static final String BASE_URL = "https://server-opportune-1.onrender.com/";

	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		// Initialize the views
		logo = findViewById(R.id.logo);
		nameEditText = findViewById(R.id.name);
		emailEditText = findViewById(R.id.email);
		passwordEditText = findViewById(R.id.password);
		signupButton = findViewById(R.id.signup_button);
		backToLogin = findViewById(R.id.back_to_login);
		progressBar = findViewById(R.id.progressBarsignup);  // ProgressBar

		// Load animations
		Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		Animation slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
		Animation slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
		Animation slideInUp = AnimationUtils.loadAnimation(this, R.anim.slide_in_up);

		// Apply animations
		logo.startAnimation(fadeIn);
		nameEditText.startAnimation(slideInLeft);
		emailEditText.startAnimation(slideInRight);
		passwordEditText.startAnimation(slideInUp);
		signupButton.startAnimation(slideInUp);
		backToLogin.startAnimation(fadeIn);

		// Sign Up button click listener
		signupButton.setOnClickListener(v -> signupUser());

		// Back to login click listener
		backToLogin.setOnClickListener(v -> {
			Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		});
	}

	private void signupUser() {
		String name = nameEditText.getText().toString().trim();
		String email = emailEditText.getText().toString().trim();
		String password = passwordEditText.getText().toString().trim();

		// Validate input fields
		if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
			Toast.makeText(SignupActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
			return;
		}

		// Validate email format
		if (!email.contains("@") || !email.contains(".")) {
			Toast.makeText(SignupActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
			return;
		}

		// Validate password strength (at least 6 characters)
		if (password.length() < 8) {
			Toast.makeText(SignupActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
			return;
		}

		// Show progress bar
		progressBar.setVisibility(View.VISIBLE);

		// Check if the email already exists
		checkIfUserExists(email, name, password);
	}

	private void checkIfUserExists(String email, String name, String password) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())) // Gson with lenient parsing
				.build();

		UsersApi usersApi = retrofit.create(UsersApi.class);
		Call<Users> call = usersApi.getUserByEmail(email);  // Call expecting a Users object

		call.enqueue(new Callback<Users>() {
			@Override
			public void onResponse(Call<Users> call, Response<Users> response) {
				progressBar.setVisibility(View.GONE);  // Hide progress bar

				// Log the response for debugging
				try {
					String rawResponse = response.errorBody() != null ? response.errorBody().string() : "No error body";
					Log.d("SignupActivity", "Raw response: " + rawResponse);
				} catch (Exception e) {
					Log.e("SignupActivity", "Error logging response body: " + e.getMessage());
				}

				if (response.isSuccessful() && response.body() != null) {
					// User already exists, navigate to login page
					Users existingUser = response.body();
					Toast.makeText(SignupActivity.this, "Email already registered. Please login.", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
					startActivity(intent);
					finish();
				} else {
					// User not found, proceed with registration
					registerUser(name, email, password);

				}
			}

			@Override
			public void onFailure(Call<Users> call, Throwable t) {
				progressBar.setVisibility(View.GONE);  // Hide progress bar
				Log.e("SignupActivity", "Error checking if user exists: " + t.getMessage());
				Toast.makeText(SignupActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void registerUser(String name, String email, String password) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())) // Gson with lenient parsing
				.build();

		UsersApi usersApi = retrofit.create(UsersApi.class);

		// Create a new User object with name, email, and password
		Users newUser = new Users(name, email, password);  // User with necessary fields
		Call<Users> call = usersApi.registerUser(newUser); // Call expecting a Users object

		call.enqueue(new Callback<Users>() {
			@Override
			public void onResponse(Call<Users> call, Response<Users> response) {
				progressBar.setVisibility(View.GONE);  // Hide progress bar

				if (response.isSuccessful()) {
					// Registration successful, check for success message
					if (response.body() != null) {
						// Assuming server returns user data or status on success
						Toast.makeText(SignupActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();

						// Redirect to dashboard after successful signup
						Intent intent = new Intent(SignupActivity.this, DashboardActivity.class);
						startActivity(intent);
						finish();
					} else {
						// Handle cases where the response is empty or doesn't contain expected data
						Toast.makeText(SignupActivity.this, "Signup failed. Unexpected response from server.", Toast.LENGTH_SHORT).show();
						Log.e("SignupActivity", "Empty response body.");
					}
				} else {
					// Handle unsuccessful responses (e.g., 4xx, 5xx errors)
					Log.e("SignupActivity", "Signup failed with code: " + response.code() + ", message: " + response.message());
					Toast.makeText(SignupActivity.this, "Signup failed. Server error: " + response.code(), Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<Users> call, Throwable t) {
				progressBar.setVisibility(View.GONE);  // Hide progress bar
				Log.e("SignupActivity", "Error during registration: " + t.getMessage());
				Toast.makeText(SignupActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
