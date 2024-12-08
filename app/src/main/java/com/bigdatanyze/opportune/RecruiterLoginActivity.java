package com.bigdatanyze.opportune;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecruiterLoginActivity extends AppCompatActivity {

	private EditText emailEditText, passwordEditText;
	private Button loginButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recruiter_login);

		// Initialize the views
		emailEditText = findViewById(R.id.email);
		passwordEditText = findViewById(R.id.password);
		loginButton = findViewById(R.id.login_button);

		// Login button click listener
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loginRecruiter();
			}
		});
	}

	private void loginRecruiter() {
		// Example login logic
		String email = emailEditText.getText().toString();
		String password = passwordEditText.getText().toString();

		if (email.equals("hr@fin.com") && password.equals("password")) {
			// Redirect to PostJobActivity after successful login
			Intent intent = new Intent(RecruiterLoginActivity.this, PostJobActivity.class);
			startActivity(intent);
			finish(); // Close the login activity
		} else {
			Toast.makeText(RecruiterLoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
		}
	}
}
