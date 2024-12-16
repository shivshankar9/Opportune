package com.bigdatanyze.opportune;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

	private RecyclerView jobListRecyclerView;
	private ProgressBar loadingProgress;
	private SwipeRefreshLayout swipeRefreshLayout;
	private JobAdapter jobAdapter;
	private TextView emptyStateTextView;
	private BottomNavigationView bottomNavigationView;

	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Check subscription status
		boolean isSubscribed = getSharedPreferences("user_prefs", MODE_PRIVATE)
				.getBoolean("is_subscribed", false);

		if (!isSubscribed) {
			// Redirect to Payment Page
			Intent paymentIntent = new Intent(DashboardActivity.this, PaymentActivity.class);
			startActivity(paymentIntent);
			finish();
			return;
		}

		// Load Dashboard layout
		setContentView(R.layout.activity_dashbord);
		bottomNavigationView = findViewById(R.id.bottom_navigation);
		bottomNavigationView.setOnItemSelectedListener(item -> {
			int id = item.getItemId();

			if (id == R.id.nav_home) {
				// Display a Toast for "Home" without navigating
				Toast.makeText(DashboardActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
				return true; // No navigation as we're already on Home
			} else if (id == R.id.nav_profile) {
				// Navigate to ProfileActivity only if not already there
				Intent profileIntent = new Intent(DashboardActivity.this, ProfileActivity.class);
				startActivity(profileIntent);
				overridePendingTransition(0, 0); // Disable animation
				return true;
			} else if (id == R.id.nav_notifications) {
				// Navigate to NotificationsActivity only if not already there
				Intent notificationIntent = new Intent(DashboardActivity.this, NotificationsActivity.class);
				startActivity(notificationIntent);
				overridePendingTransition(0, 0); // Disable animation
				return true;
			} else {
				return false; // Default behavior
			}
		});

		// Initialize views
		jobListRecyclerView = findViewById(R.id.job_list);
		loadingProgress = findViewById(R.id.loading_progress);
		swipeRefreshLayout = findViewById(R.id.swipe_refresh);
		emptyStateTextView = findViewById(R.id.empty_state_text);

		if (jobListRecyclerView != null) {
			jobListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		}

		// Swipe-to-refresh listener
		swipeRefreshLayout.setOnRefreshListener(this::fetchJobs);

		// Initial data fetch
		fetchJobs();
	}

	private void fetchJobs() {
		showLoading(true);

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://server-opportune-1.onrender.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		JobApi jobApi = retrofit.create(JobApi.class);

		jobApi.getJobs().enqueue(new Callback<List<Job>>() {
			@Override
			public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
				showLoading(false);

				if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
					List<Job> jobs = response.body();
					emptyStateTextView.setVisibility(View.GONE);
					jobListRecyclerView.setVisibility(View.VISIBLE);

					if (jobAdapter == null) {
						jobAdapter = new JobAdapter(jobs);
						jobListRecyclerView.setAdapter(jobAdapter);
					} else {
						jobAdapter.updateData(jobs); // Use this to update job data
					}
				} else {
					showError("No jobs found.");
					showEmptyState();
				}
			}

			@Override
			public void onFailure(Call<List<Job>> call, Throwable t) {
				showLoading(false);
				showError("Failed to load jobs. Please swipe down to retry.");
				showEmptyState();
			}
		});
	}

	private void showLoading(boolean isLoading) {
		loadingProgress.setVisibility(isLoading ? View.VISIBLE : View.GONE);
		if (!isLoading) {
			swipeRefreshLayout.setRefreshing(false);
		}
	}

	private void showError(String message) {
		Toast.makeText(DashboardActivity.this, message, Toast.LENGTH_SHORT).show();
	}

	private void showEmptyState() {
		jobListRecyclerView.setVisibility(View.GONE);
		emptyStateTextView.setVisibility(View.VISIBLE);
		emptyStateTextView.setText("No data available. Swipe down to refresh.");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_dashboard, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.nav_home) {
			Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == R.id.nav_profile) {
			Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == R.id.nav_notifications) {
			Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == R.id.action_logout) {
			// Clear subscription status on logout
			getSharedPreferences("user_prefs", MODE_PRIVATE).edit().clear().apply();
			Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		} else {
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
