// JobApi.java
package com.bigdatanyze.opportune;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JobApi {
	@GET("jobs")
	Call<List<Job>> getAllJobs();

	@POST("jobs")  // This matches the POST endpoint on the server
	Call<Job> postJob(@Body Job job);  // @Body sends the job object as the POST data

	@GET("jobs")
	Call<List<Job>> getJobs();
}
