package com.bigdatanyze.opportune;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Check if the user is logged in
		SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
		boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

		// Redirect to DashboardActivity if already logged in
		if (isLoggedIn) {
			Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
			startActivity(intent);
		} else {
			// Redirect to LoginActivity if not logged in
			Intent intent = new Intent(MainActivity.this, OnbordActivity.class);
			startActivity(intent);
		}
		finish(); // Close MainActivity
	}
}
