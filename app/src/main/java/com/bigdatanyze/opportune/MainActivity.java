package com.bigdatanyze.opportune;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Redirect to LoginActivity when MainActivity is opened
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		finish(); // Close the MainActivity
	}
}
