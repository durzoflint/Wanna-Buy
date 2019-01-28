package com.nyxwolves.wannabuy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.nyxwolves.wannabuy.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";
    String description;
    int amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Checkout.preload(getApplicationContext());

        Intent intent = getIntent();
        description = intent.getStringExtra(DESCRIPTION);
        amount = intent.getIntExtra(AMOUNT, 0);

        startPayment();
    }

    public void startPayment() {
        Checkout checkout = new Checkout();

        checkout.setImage(R.mipmap.ic_launcher_round);

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "WannaBuy");

            options.put("description", description);

            options.put("currency", "INR");

            options.put("amount", amount * 100);

            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("RazorPay Payment Error", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        Checkout.clearUserData(this);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
        Checkout.clearUserData(this);
        setResult(RESULT_CANCELED);
        finish();
    }
}
