package com.bigdatanyze.opportune;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

	private RecyclerView notificationsList;
	private NotificationsAdapter notificationsAdapter;
	private List<Notification> notifications;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);

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
