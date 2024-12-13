package com.bigdatanyze.opportune;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashbord);

		jobListRecyclerView = findViewById(R.id.job_list);
		loadingProgress = findViewById(R.id.loading_progress);
		swipeRefreshLayout = findViewById(R.id.swipe_refresh);

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

				if (response.isSuccessful() && response.body() != null) {
					List<Job> jobs = response.body();
					if (jobAdapter == null) {
						jobAdapter = new JobAdapter(jobs);
						jobListRecyclerView.setAdapter(jobAdapter);
					} else {
						jobAdapter.updateData(jobs);
					}
				} else {
					showError("Error: " + response.message());
				}
			}

			@Override
			public void onFailure(Call<List<Job>> call, Throwable t) {
				showLoading(false);
				showError("Failure: " + t.getMessage());
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
}
