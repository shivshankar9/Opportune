package com.bigdatanyze.opportune;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bigdatanyze.opportune.Job;
import androidx.appcompat.app.AppCompatActivity;

import com.bigdatanyze.opportune.Job;  // Your job model class
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostJobActivity extends AppCompatActivity {

	private EditText jobTitleEditText, companyNameEditText, locationEditText, jobDescriptionEditText;
	private Button postJobButton;

	private static final String BASE_URL = "https://your-backend-url.com"; // Replace with your actual backend URL

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_job);

		jobTitleEditText = findViewById(R.id.job_title);
		companyNameEditText = findViewById(R.id.company_name);
		locationEditText = findViewById(R.id.location);
		jobDescriptionEditText = findViewById(R.id.job_description);
		postJobButton = findViewById(R.id.post_job_button);

		// Set up the button click listener to trigger the job posting
		postJobButton.setOnClickListener(v -> postJob());
	}

	// Method to handle job posting
	private void postJob() {
		String jobTitle = jobTitleEditText.getText().toString().trim();
		String companyName = companyNameEditText.getText().toString().trim();
		String location = locationEditText.getText().toString().trim();
		String jobDescription = jobDescriptionEditText.getText().toString().trim();

		// Check if all fields are filled out
		if (!jobTitle.isEmpty() && !companyName.isEmpty() && !location.isEmpty() && !jobDescription.isEmpty()) {
			// Create a Job object to send to the backend
			Job job = new Job(jobTitle, companyName, location, jobDescription);

			// Set up Retrofit
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();

			JobApi jobApi = retrofit.create(JobApi.class);
			Call<Job> call = jobApi.postJob(job);

			// Make the API call asynchronously
			call.enqueue(new Callback<Job>() {
				@Override
				public void onResponse(Call<Job> call, Response<Job> response) {
					if (response.isSuccessful()) {
						// Successfully posted the job
						Toast.makeText(PostJobActivity.this, "Job posted successfully!", Toast.LENGTH_SHORT).show();

						// Clear the input fields
						jobTitleEditText.setText("");
						companyNameEditText.setText("");
						locationEditText.setText("");
						jobDescriptionEditText.setText("");
					} else {
						// Failed to post the job
						Toast.makeText(PostJobActivity.this, "Failed to post job", Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFailure(Call<Job> call, Throwable t) {
					// Handle failure (e.g., no network connection)
					Toast.makeText(PostJobActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
				}
			});
		} else {
			// If any of the fields are empty
			Toast.makeText(PostJobActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
		}
	}
}
