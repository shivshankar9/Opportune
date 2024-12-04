package com.bigdatanyze.opportune;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

	private ImageView profilePicture;
	private TextView profileName, profileEmail, profileBio;
	private TextView editProfile, accountSettings, notificationSettings, logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		// Initialize the views
		profilePicture = findViewById(R.id.profile_picture);
		profileName = findViewById(R.id.profile_name);
		profileEmail = findViewById(R.id.profile_email);
		profileBio = findViewById(R.id.profile_bio);
		editProfile = findViewById(R.id.settings_option_1);
		accountSettings = findViewById(R.id.settings_option_2);
		notificationSettings = findViewById(R.id.settings_option_3);
		logout = findViewById(R.id.settings_option_4);

		// Set initial values (this can be replaced with real user data)
		profileName.setText("John Doe");
		profileEmail.setText("john.doe@example.com");
		profileBio.setText("A passionate developer with experience in building mobile applications.");

		// Edit Profile click listener
		editProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Handle edit profile action
				Toast.makeText(ProfileActivity.this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show();
				// Example: Redirect to EditProfileActivity
				Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
				startActivity(intent);
			}
		});

		// Account Settings click listener
		accountSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Handle account settings action
				Toast.makeText(ProfileActivity.this, "Account Settings Clicked", Toast.LENGTH_SHORT).show();
				// Example: Redirect to AccountSettingsActivity
				Intent intent = new Intent(ProfileActivity.this, AccountSettingsActivity.class);
				startActivity(intent);
			}
		});

		// Notification Settings click listener
		notificationSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Handle notification settings action
				Toast.makeText(ProfileActivity.this, "Notification Settings Clicked", Toast.LENGTH_SHORT).show();
				// Example: Redirect to NotificationSettingsActivity
				Intent intent = new Intent(ProfileActivity.this, NotificationSettingsActivity.class);
				startActivity(intent);
			}
		});

		// Logout click listener
		logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Handle logout action
				Toast.makeText(ProfileActivity.this, "Logout Clicked", Toast.LENGTH_SHORT).show();
				// Example: Redirect to LoginActivity after logout
				Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
