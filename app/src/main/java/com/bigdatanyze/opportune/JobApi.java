package com.bigdatanyze.opportune;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import java.util.List;

public interface JobApi {

	// POST request to create a new job
	@POST("/api/jobs/jobpost")  // Update the endpoint to match your Spring Boot backend route
	Call<Job> postJob(@Body Job job);

	// GET request to fetch all jobs
	@GET("/api/jobs")  // Use the correct API path for retrieving jobs
	Call<List<Job>> getAllJobs();
}
