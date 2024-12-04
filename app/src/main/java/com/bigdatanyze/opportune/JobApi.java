package com.bigdatanyze.opportune;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

public interface JobApi {
	@POST("/jobs")
	Call<Job> postJob(@Body Job job);

	Call<List<Job>> getAllJobs();
}
