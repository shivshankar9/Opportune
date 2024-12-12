package com.bigdatanyze.opportune;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigdatanyze.opportune.Job;
import com.bigdatanyze.opportune.JobAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

	private RecyclerView jobListRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashbord);

		jobListRecyclerView = findViewById(R.id.job_list);

		if (jobListRecyclerView != null) {
			jobListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

			// Initialize Retrofit
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl("https://server-opportune-1.onrender.com/")
					.addConverterFactory(GsonConverterFactory.create())
					.build();

			JobApi jobApi = retrofit.create(JobApi.class);

			// Fetch jobs data
			jobApi.getJobs().enqueue(new Callback<List<Job>>() {
				@Override
				public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
					if (response.isSuccessful()) {
						List<Job> jobs = response.body();
						if (jobs != null) {
							// Set the adapter with the fetched jobs
							JobAdapter adapter = new JobAdapter(jobs);
							jobListRecyclerView.setAdapter(adapter);
						}
					} else {
						Log.e("DashboardActivity", "Error: " + response.message());
					}
				}

				@Override
				public void onFailure(Call<List<Job>> call, Throwable t) {
					Log.e("DashboardActivity", "Failure: " + t.getMessage());
				}
			});
		}
	}
}
