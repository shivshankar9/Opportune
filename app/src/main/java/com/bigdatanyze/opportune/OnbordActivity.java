package com.bigdatanyze.opportune;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.SignInButton;

public class OnbordActivity extends AppCompatActivity {
	private Button loginButton;
	private Button googleSignInButton;
	private Button signupButton;

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onbord);

		// Find buttons
		loginButton = findViewById(R.id.btnLogIn);
		googleSignInButton = findViewById(R.id.btnGoogleSignIn);
		signupButton = findViewById(R.id.btnSignUp);

		// Find the TextView where the text will appear
		final TextView chatText = findViewById(R.id.tvAppName);
		final String text = "Get Hired With Opportune"; // The text you want to animate

		// Set up the typing animation (text appearing letter by letter)
		chatText.setText(""); // Clear any previous text

		ValueAnimator animator = ValueAnimator.ofInt(0, text.length());
		animator.setDuration(3000); // Duration of the typing effect
		animator.setRepeatMode(ValueAnimator.RESTART); // Repeat the animation infinitely
		animator.setRepeatCount(ValueAnimator.INFINITE); // Infinite repetitions
		animator.addUpdateListener(animation -> {
			int index = (int) animation.getAnimatedValue();
			chatText.setText(text.substring(0, index)); // Update the text view with each letter
		});
		animator.start();

		// Set up click listeners for buttons
		loginButton.setOnClickListener(v -> {
			// Handle log in button click
			// You can start a new activity here or show a login dialog
			Intent intent = new Intent(OnbordActivity.this, LoginActivity.class);
			startActivity(intent);
		});

		googleSignInButton.setOnClickListener(v -> {
			// Handle Google Sign-In button click
			// You can integrate Google Sign-In here
		});

		signupButton.setOnClickListener(v -> {
			// Handle sign up button click
			// You can start a new activity for signing up
			Intent intent = new Intent(OnbordActivity.this, SignupActivity.class);
			startActivity(intent);
		});
	}
}
