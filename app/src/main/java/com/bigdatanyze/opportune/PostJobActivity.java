package com.bigdatanyze.opportune;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostJobActivity extends AppCompatActivity {

	private EditText jobTitleEditText, companyNameEditText, locationEditText, jobDescriptionEditText;
	private Button postJobButton;
	private static final String BASE_URL = "https://your-backend-url.com"; // Replace with your API URL

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_job);

		jobTitleEditText = findViewById(R.id.job_title);
		companyNameEditText = findViewById(R.id.company_name);
		locationEditText = findViewById(R.id.location);
		jobDescriptionEditText = findViewById(R.id.job_description);
		postJobButton = findViewById(R.id.post_job_button);

		postJobButton.setOnClickListener(v -> postJob());
	}

	private void postJob() {
		String jobTitle = jobTitleEditText.getText().toString();
		String companyName = companyNameEditText.getText().toString();
		String location = locationEditText.getText().toString();
		String jobDescription = jobDescriptionEditText.getText().toString();

		if (!jobTitle.isEmpty() && !companyName.isEmpty() && !location.isEmpty() && !jobDescription.isEmpty()) {
			Job job = new Job(jobTitle, companyName, location, jobDescription);

			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();

			JobApi jobApi = retrofit.create(JobApi.class);
			Call<Job> call = jobApi.postJob(job);

			call.enqueue(new Callback<Job>() {
				@Override
				public void onResponse(Call<Job> call, Response<Job> response) {
					if (response.isSuccessful()) {
						Toast.makeText(PostJobActivity.this, "Job posted successfully!", Toast.LENGTH_SHORT).show();
						jobTitleEditText.setText("");
						companyNameEditText.setText("");
						locationEditText.setText("");
						jobDescriptionEditText.setText("");
					} else {
						Toast.makeText(PostJobActivity.this, "Failed to post job", Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<Job> call, Throwable t) {
					Toast.makeText(PostJobActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
				}
			});
		} else {
			Toast.makeText(PostJobActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
		}
	}
}
