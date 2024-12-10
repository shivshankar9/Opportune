package com.bigdatanyze.opportune;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigdatanyze.opportune.R;
import kotlinx.coroutines.Job;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

	private RecyclerView jobList;
	private JobAdapter jobAdapter;
	private List<Job> jobs;
	private static final String BASE_URL = "https://your-backend-url.com"; // Replace with your API URL

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashbord);

		jobList = findViewById(R.id.job_list);

		// Initialize job list
		jobs = new ArrayList<>();

		// Set up RecyclerView
		jobAdapter = new JobAdapter(this, Collections.singletonList((com.bigdatanyze.opportune.Job) jobs));
		jobList.setLayoutManager(new LinearLayoutManager(this));
		jobList.setAdapter(jobAdapter);

//		fetchJobs();
	}

//	private void fetchJobs() {
//		Retrofit retrofit = new Retrofit.Builder()
//				.baseUrl(BASE_URL)
//				.addConverterFactory(GsonConverterFactory.create())
//				.build();
//
//		JobApi jobApi = retrofit.create(JobApi.class);
//		Call<List<Job>> call = jobApi.getAllJobs();
//
//		call.enqueue(new Callback<List<Job>>() {
//			@Override
//			public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
//				if (response.isSuccessful()) {
//					jobs.clear();
//					jobs.addAll(response.body());
//					jobAdapter.notifyDataSetChanged();
//				} else {
//					Toast.makeText(DashboardActivity.this, "Failed to load jobs", Toast.LENGTH_SHORT).show();
//				}
//			}
//
//			@Override
//			public void onFailure(Call<List<Job>> call, Throwable t) {
//				Toast.makeText(DashboardActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//			}
//		});
//	}
}
