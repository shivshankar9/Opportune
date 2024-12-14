package com.bigdatanyze.opportune;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);

		Button subscribeButton = findViewById(R.id.subscribe_button);

		subscribeButton.setOnClickListener(v -> {
			// Simulate successful payment
			Toast.makeText(this, "Subscription successful!", Toast.LENGTH_SHORT).show();

			// Save subscription status
			getSharedPreferences("user_prefs", MODE_PRIVATE)
					.edit()
					.putBoolean("is_subscribed", true)
					.apply();

			// Redirect to Dashboard
			startActivity(new Intent(PaymentActivity.this, DashboardActivity.class));
			finish();
		});
	}
}
