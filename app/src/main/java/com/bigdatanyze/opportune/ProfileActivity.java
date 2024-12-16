package com.bigdatanyze.opportune;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

	private ImageView profilePicture;
	private TextView profileName, profileEmail, profileBio;
	private TextView editProfile, accountSettings, notificationSettings, logout;
	private BottomNavigationView bottomNavigationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		bottomNavigationView = findViewById(R.id.bottom_navigation);
		bottomNavigationView.setSelectedItemId(R.id.nav_profile); // Set Profile as selected item by default
		bottomNavigationView.setOnItemSelectedListener(item -> {
			int id = item.getItemId();

			if (id == R.id.nav_home) {
				Intent profileIntent = new Intent(ProfileActivity.this, DashboardActivity.class);
				startActivity(profileIntent);
				overridePendingTransition(0, 0); // Disable animation
				return true;
			} else if (id == R.id.nav_profile) {
				// Already on ProfileActivity, so no navigation needed
				return true;
			} else if (id == R.id.nav_notifications) {
				Intent notificationIntent = new Intent(ProfileActivity.this, NotificationsActivity.class);
				startActivity(notificationIntent);
				overridePendingTransition(0, 0); // Disable animation
				return true;
			} else {
				return false; // Default behavior
			}
		});

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
		editProfile.setOnClickListener(v -> {
			Toast.makeText(ProfileActivity.this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
			startActivity(intent);
		});

		// Account Settings click listener
		accountSettings.setOnClickListener(v -> {
			Toast.makeText(ProfileActivity.this, "Account Settings Clicked", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(ProfileActivity.this, AccountSettingsActivity.class);
			startActivity(intent);
		});

		// Notification Settings click listener
		notificationSettings.setOnClickListener(v -> {
			Toast.makeText(ProfileActivity.this, "Notification Settings Clicked", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(ProfileActivity.this, NotificationSettingsActivity.class);
			startActivity(intent);
		});

		// Logout click listener
		logout.setOnClickListener(v -> {
			Toast.makeText(ProfileActivity.this, "Logout Clicked", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		});
	}
}
