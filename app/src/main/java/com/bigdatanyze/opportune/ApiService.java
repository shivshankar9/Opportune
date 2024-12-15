package com.bigdatanyze.opportune;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
	@GET("users") // Use the correct endpoint path
	Call<Users> getUser();

	@POST("/api/users/login")
	Call<String> loginUser(@Body Users user);
}
