package com.nyxwolves.wannabuy.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

public class PaymentInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        String userMode = sharedPreferences.getString(getString(R.string.user_mode), "NOT_SET");
        if (userMode.equals(getString(R.string.owner)))
            setTitle("Owner's Pay");
        else
            setTitle("Dealer's Pay");

        TextView text = findViewById(R.id.text);
        text.setText("WANNA BUY : FREE( For three months )\n" +
                "WANNA RENT : FREE( For Three months ) \n" +
                "POST Sale : ₹ 2999 ( For Three months ) \n" +
                "POST Rent : ₹ 999 ( For Three months ) \n" +
                "\n" +
                "All the price  is valid for one post only . For Extra Ad sale Post  additional " +
                "₹2999 have to be charged for three months . For extra Rent post ₹999 have to be " +
                "charged for three months . \n" +
                "\n" +
                "For Buyers/ rentals  one post is free on sale and also for rental totally 2 " +
                "requirements is free for three months ( make the location choice as 5 locations " +
                "in single post ) \n" +
                "For additional requirements buyer has to pay for ₹4999 for three months for sale" +
                " for rental ₹1999 for three months .");

        //if (userMode.equals(getString(R.string.dealer))){
        text.setText("For dealers @  ₹10000 for three months and 10 posts on requirement and 10 " +
                "posts on Ad posts . Apart from the basic free one post\n\n" + text.getText());
        //}
    }
}