package com.bigdatanyze.opportune;

import com.bigdatanyze.opportune.JobApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

	private static final String BASE_URL = "http://localhost:8080"; // Adjust this based on where your backend is hosted

	private static Retrofit retrofit = null;

	public static Retrofit getClient() {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)  // Set the base URL for your API
					.addConverterFactory(GsonConverterFactory.create())  // Add a converter for JSON to Java object mapping
					.build();
		}
		return retrofit;
	}

	public static JobApi getApi() {
		return getClient().create(JobApi.class);
	}
}
