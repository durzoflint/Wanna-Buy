package com.nyxwolves.wannabuy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.UserPaymentCheck;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener,CallbackInterface{
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";
    public static final String USER_TYPE = "user_type";
    String description, email,type;
    int amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Checkout.preload(getApplicationContext());

        Intent intent = getIntent();
        description = intent.getStringExtra(DESCRIPTION);
        amount = intent.getIntExtra(AMOUNT, 0);
        type = intent.getStringExtra(USER_TYPE);
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
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
        new AddPayment().execute();
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        Checkout.clearUserData(this);

        //only for dealer
        if(type.equals(getString(R.string.dealer))){
            UserPaymentCheck paymentCheck = new UserPaymentCheck(PaymentActivity.this);
            paymentCheck.updateUserStatus(UserPaymentCheck.INCREASE_DEALER_CREDITS,PaymentActivity.this);
        }
    }

    @Override
    public void setData(JSONObject data) {
        try{
            if(data.getString("message").equals("success")){
                setResult(RESULT_OK);
                finish();
            }
        }catch (Exception  e){

        }
    }

    @Override
    public void isSuccess(boolean isSuccess) {

    }

    @Override
    public void doesUserExits(boolean isExists) {

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
        Checkout.clearUserData(this);
        setResult(RESULT_CANCELED);
        finish();
    }

    class AddPayment extends AsyncTask<Void, Void, Void> {
        String baseUrl = "http://www.wannabuy.in/api/payment/";
        String webPage = "";

        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String myURL =
                        baseUrl + "addPayment.php?email=" + email + "&description=" + description + "&amount=" + amount;
                myURL = myURL.replaceAll(" ", "%20");
                myURL = myURL.replaceAll("\'", "%27");
                myURL = myURL.replaceAll("\'", "%22");
                myURL = myURL.replaceAll("\\+'", "%2B");
                myURL = myURL.replaceAll("\\(", "%28");
                myURL = myURL.replaceAll("\\)", "%29");
                myURL = myURL.replaceAll("\\{", "%7B");
                myURL = myURL.replaceAll("\\}", "%7B");
                myURL = myURL.replaceAll("\\]", "%22");
                myURL = myURL.replaceAll("\\[", "%22");
                url = new URL(myURL);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection
                        .getInputStream()));
                String data;
                while ((data = br.readLine()) != null)
                    webPage = webPage + data;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!webPage.equals("success"))
                Toast.makeText(PaymentActivity.this, "Error while adding payment to database",
                        Toast.LENGTH_SHORT).show();
        }
    }
}
