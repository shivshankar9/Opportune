package com.bigdatanyze.opportune;

import com.bigdatanyze.opportune.Job;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import java.util.List;

public interface JobApi {
	@POST("/jobs")
	Call<Job> postJob(@Body Job job);

	@GET("/jobs")
	Call<List<Job>> getAllJobs();
}
