package com.bigdatanyze.opportune;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

	private EditText usernameEditText, passwordEditText;
	private Button loginButton;
	private MaterialTextView recruiterLoginPageButton;	private TextView signUp;
	private ImageView logo;

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
		// Example login logic
		String username = usernameEditText.getText().toString();
		String password = passwordEditText.getText().toString();

		if (username.equals("admin") && password.equals("admin")) {
			// Redirect to DashboardActivity after successful login
			Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
			startActivity(intent);
			finish(); // Close the login activity
		} else {
			Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
		}
	}

	private void signupUser() {
		// Handle user signup (you can implement signup logic here)
		Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
		startActivity(intent);
		finish();
	}
}
