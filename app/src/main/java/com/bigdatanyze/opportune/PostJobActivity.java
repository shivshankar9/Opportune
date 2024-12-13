package com.bigdatanyze.opportune;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostJobActivity extends AppCompatActivity {

	private EditText jobTitleEditText, companyNameEditText, locationEditText, jobDescriptionEditText, salaryEditText, applyLinkEditText;
	private Button postJobButton;
	private ProgressBar progressBar;  // ProgressBar to show posting animation

	// URL for your backend (change this to the production URL)
	private static final String BASE_URL = "https://server-opportune-1.onrender.com/";

	private Retrofit retrofit;

	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_job);  // Use your layout XML file

		// Initialize the views
		jobTitleEditText = findViewById(R.id.job_title);
		companyNameEditText = findViewById(R.id.company_name);
		locationEditText = findViewById(R.id.location);
		jobDescriptionEditText = findViewById(R.id.job_description);
		salaryEditText = findViewById(R.id.salary);
		applyLinkEditText = findViewById(R.id.ApplyLink);
		postJobButton = findViewById(R.id.post_job_button);
		progressBar = findViewById(R.id.progress_bar);  // ProgressBar view

		// Initialize Retrofit
		retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)  // Set the backend URL
				.addConverterFactory(GsonConverterFactory.create())  // Gson converter for parsing JSON
				.build();

		// Set up the button click listener to trigger the job posting
		postJobButton.setOnClickListener(v -> postJob());
	}

	// Method to handle job posting
	private void postJob() {
		String jobTitle = jobTitleEditText.getText().toString().trim();
		String companyName = companyNameEditText.getText().toString().trim();
		String location = locationEditText.getText().toString().trim();
		String jobDescription = jobDescriptionEditText.getText().toString().trim();
		String salaryText = salaryEditText.getText().toString().trim();
		String applyLink = applyLinkEditText.getText().toString().trim();

		// Check if all fields are filled out
		if (!jobTitle.isEmpty() && !companyName.isEmpty() && !location.isEmpty() && !jobDescription.isEmpty() && !salaryText.isEmpty()) {
			try {
				// Show progress bar and disable button while posting
				progressBar.setVisibility(View.VISIBLE);
				postJobButton.setEnabled(false);

				int salary = Integer.parseInt(salaryText);  // Convert salary to integer

				// Create a Job object to send to the backend
				Job job = new Job(jobTitle, jobDescription, companyName, location, salary, "2024-12-12T00:00:00", applyLink);

				// Create the API service
				JobApi jobApi = retrofit.create(JobApi.class);

				// Make the API call asynchronously
				Call<Job> call = jobApi.postJob(job);
				call.enqueue(new Callback<Job>() {
					@Override
					public void onResponse(Call<Job> call, Response<Job> response) {
						// Hide progress bar and enable button
						progressBar.setVisibility(View.GONE);
						postJobButton.setEnabled(true);

						if (response.isSuccessful()) {
							// Successfully posted the job
							Toast.makeText(PostJobActivity.this, "Job posted successfully!", Toast.LENGTH_SHORT).show();

							// Clear the input fields
							jobTitleEditText.setText("");
							companyNameEditText.setText("");
							locationEditText.setText("");
							jobDescriptionEditText.setText("");
							salaryEditText.setText("");
							applyLinkEditText.setText("");
						} else {
							// Failed to post the job (e.g., server error)
							Toast.makeText(PostJobActivity.this, "Failed to post job. Try again.", Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onFailure(Call<Job> call, Throwable t) {
						// Hide progress bar and enable button
						progressBar.setVisibility(View.GONE);
						postJobButton.setEnabled(true);

						// Handle failure (e.g., no network connection)
						String errorMessage = t.getMessage();
						if (errorMessage == null || errorMessage.isEmpty()) {
							errorMessage = "An unknown error occurred.";
						}
						Toast.makeText(PostJobActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
					}
				});
			} catch (NumberFormatException e) {
				// If salary is not a valid number, show an error
				progressBar.setVisibility(View.GONE);
				postJobButton.setEnabled(true);
				Toast.makeText(PostJobActivity.this, "Please enter a valid salary.", Toast.LENGTH_SHORT).show();
			}
		} else {
			// If any of the fields are empty
			Toast.makeText(PostJobActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
		}
	}
}
