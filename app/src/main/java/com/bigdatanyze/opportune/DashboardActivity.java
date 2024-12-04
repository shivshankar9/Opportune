package com.bigdatanyze.opportune;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

	private TextView header;
	private EditText searchJobs;
	private RecyclerView jobList;
	private BottomNavigationView bottomNavigationView;
	private JobAdapter jobAdapter;
	private List<Job> jobs;

	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashbord);

		// Initialize the views
		header = findViewById(R.id.header);
		searchJobs = findViewById(R.id.search_jobs);
		jobList = findViewById(R.id.job_list);
		bottomNavigationView = findViewById(R.id.bottom_navigation);

		// Load animations
		Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		Animation slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
		Animation slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);

		// Apply animations
		header.startAnimation(fadeIn);
		searchJobs.startAnimation(slideInLeft);
		jobList.startAnimation(slideInRight);

		// Initialize job list
		jobs = new ArrayList<>();
		jobs.add(new Job("Software Engineer", "Google", "Mountain View, CA"));
		jobs.add(new Job("Data Analyst", "Facebook", "Menlo Park, CA"));
		jobs.add(new Job("Product Manager", "Amazon", "Seattle, WA"));
		// Add more jobs as needed

		// Set up RecyclerView
		jobAdapter = new JobAdapter(this, jobs);
		jobList.setLayoutManager(new LinearLayoutManager(this));
		jobList.setAdapter(jobAdapter);

		// Handle bottom navigation item clicks
		bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
			int id = item.getItemId();
			if (id == R.id.nav_home) {
				// Handle Home action
				Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
				startActivity(intent);
				return true;
			} else if (id == R.id.nav_profile) {
				// Handle Profile action
				Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
				startActivity(intent);
				return true;
			} else if (id == R.id.nav_notifications) {
				// Handle Notifications action
				Intent intent = new Intent(DashboardActivity.this, NotificationsActivity.class);
				startActivity(intent);
				return true;
			}
			return false;
		});
	}
}
