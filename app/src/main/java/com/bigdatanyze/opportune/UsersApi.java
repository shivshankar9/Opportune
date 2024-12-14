package com.bigdatanyze.opportune;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UsersApi {
//	@GET("api/users")
//	Call<List<Users>> getAllUserss();
//
//	@POST("api/register")  // This matches the POST endpoint on the server
//	Call<Users> postUsers(@Body Users users);  // @Body sends the job object as the POST data
//
//	@GET("api/users/{id}")
//	Call<List<Users>> getUserss();
//	// Endpoint to fetch user data by email or phone
	@GET("api/users")
	Call<Users> getUserDetails();
	// Endpoint to fetch user data by email
	@GET("api/users/email/{emailid}")
	Call<Users> getUserByEmail(@Path("emailid") String email);
//	@GET("api/users")
//	Call<Users> getUserByEmailOrPhone(@Query("email") String email, @Query("phoneNo") String phoneNo);

}
