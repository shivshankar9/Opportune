package com.bigdatanyze.opportune;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);

		Button subscribeButton = findViewById(R.id.subscribe_button);

		// Initialize Razorpay Checkout
		Checkout.preload(getApplicationContext());

		subscribeButton.setOnClickListener(v -> startPayment());
	}

	private void startPayment() {
		Checkout checkout = new Checkout();

		// Set your Razorpay Key ID
		checkout.setKeyID("rzp_test_6GkVsJKB0s9HHx");

		// Payment details
		try {
			JSONObject options = new JSONObject();

			options.put("name", "Finverg.Tech");
			options.put("description", "Subscription Payment");
			options.put("currency", "INR");
			options.put("amount", "50000"); // 500.00 INR in paise

			// Pre-fill email and contact information
			JSONObject prefill = new JSONObject();
			prefill.put("email", "user_email@example.com");
			prefill.put("contact", "9999999999");

			options.put("prefill", prefill);

			checkout.open(this, options);
		} catch (Exception e) {
			Toast.makeText(this, "Error in payment: " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onPaymentSuccess(String razorpayPaymentID) {
		Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();

		// Save subscription status
		getSharedPreferences("user_prefs", MODE_PRIVATE)
				.edit()
				.putBoolean("is_subscribed", true)
				.apply();

		// Redirect to Dashboard
		startActivity(new Intent(PaymentActivity.this, DashboardActivity.class));
		finish();
	}

	@Override
	public void onPaymentError(int code, String response) {
		Toast.makeText(this, "Payment Failed: " + response, Toast.LENGTH_SHORT).show();
	}
}
