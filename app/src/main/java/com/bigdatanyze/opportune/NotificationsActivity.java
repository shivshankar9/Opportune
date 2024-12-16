package com.bigdatanyze.opportune;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

	private RecyclerView notificationsList;
	private NotificationsAdapter notificationsAdapter;
	private List<Notification> notifications;
	private BottomNavigationView bottomNavigationView;


	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);
		bottomNavigationView = findViewById(R.id.bottom_navigation);
		bottomNavigationView.setOnItemSelectedListener(item -> {
			int id = item.getItemId();

			if (id == R.id.nav_home) {
				// Navigate to DashboardActivity without slide animation
				Intent homeIntent = new Intent(NotificationsActivity.this, DashboardActivity.class);
				startActivity(homeIntent);
				overridePendingTransition(0, 0); // Disable animation
				return true;
			} else if (id == R.id.nav_profile) {
				// Navigate to ProfileActivity without slide animation
				Intent profileIntent = new Intent(NotificationsActivity.this, ProfileActivity.class);
				startActivity(profileIntent);
				overridePendingTransition(0, 0); // Disable animation
				return true;
			} else if (id == R.id.nav_notifications) {
				// Stay on NotificationsActivity without reloading
				// No need to start the same activity
				return true;
			} else {
				return false; // Default behavior
			}
		});


		// Initialize RecyclerView
		notificationsList = findViewById(R.id.notifications_list);
		notificationsList.setLayoutManager(new LinearLayoutManager(this));

		// Initialize notifications list
		notifications = new ArrayList<>();
		notifications.add(new Notification("New Job Alert", "A new job has been posted that matches your profile."));
		notifications.add(new Notification("Application Status", "Your application for Software Engineer at Google has been viewed."));
		// Add more notifications as needed

		// Set up adapter
		notificationsAdapter = new NotificationsAdapter(this, notifications);
		notificationsList.setAdapter(notificationsAdapter);
	}
}
