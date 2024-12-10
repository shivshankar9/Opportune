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

	// Replace with your actual backend URL
	private static final String BASE_URL = "http://localhost:8080"; // Change to your backend URL (e.g., localhost for local testing)

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_job);  // Use your layout XML file

		// Initialize the views
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

			// Set up Retrofit to interact with the backend API
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)  // Set the backend URL
					.addConverterFactory(GsonConverterFactory.create())  // Gson converter for parsing JSON
					.build();

			// Create the API service
			JobApi jobApi = retrofit.create(JobApi.class);

			// Make the API call asynchronously
			Call<Job> call = jobApi.postJob(job);
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
						// Failed to post the job (e.g., server error)
						Toast.makeText(PostJobActivity.this, "Failed to post job. Try again.", Toast.LENGTH_SHORT).show();
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
