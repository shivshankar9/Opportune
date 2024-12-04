package com.bigdatanyze.opportune;

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

public class SignupActivity extends AppCompatActivity {

	private EditText nameEditText, emailEditText, passwordEditText;
	private Button signupButton;
	private TextView backToLogin;
	private ImageView logo;

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
		signupButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signupUser();
			}
		});

		// Back to login click listener
		backToLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private void signupUser() {
		// Example signup logic
		String name = nameEditText.getText().toString();
		String email = emailEditText.getText().toString();
		String password = passwordEditText.getText().toString();

		if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
			Toast.makeText(SignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();
			// Redirect to HomeActivity after successful signup
			Intent intent = new Intent(SignupActivity.this, DashboardActivity.class);
			startActivity(intent);
			finish(); // Close the signup activity
		} else {
			Toast.makeText(SignupActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
		}
	}
}
