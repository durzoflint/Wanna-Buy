package com.nyxwolves.wannabuy.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

public class PaymentInformationActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        String userMode = sharedPreferences.getString(getString(R.string.user_mode), "NOT_SET");
        textView = findViewById(R.id.text);

        if (userMode.equals(getString(R.string.individual))){
            setTitle("Owner's Pay");
            setText(1);
        }else{
            setTitle("Dealer's Pay");
            setText(2);
        }

        /*TextView text = findViewById(R.id.text);
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
                "For additional requirements buyer has to pay for ₹4F999 for three months for sale" +
                " for rental ₹1999 for three months .");

        //if (userMode.equals(getString(R.string.dealer))){
        text.setText("For dealers @  ₹10000 for three months and 10 posts on requirement and 10 " +
                "posts on Ad posts . Apart from the basic free one post\n\n" + text.getText());
        //}*/
    }

    private void setText(int option){
        if(option == 2){
            textView.setText("Dealer package :\n" +
                    "\n" +
                    "₹10000 for three months to a\n" +
                    "Dealer will be given 10 listings to Post sale / Rent out Ad \n" +
                    "and 10 listings on Buying requirement  / Or Rent requirement AD. \n" +
                    "\n" +
                    "\n" +
                    "Launch offer:\n" +
                    "One Requirement on Buying and one Requirement to take on Rent is free for three months . \n" +
                    "\n" +
                    "\n" +
                    "Total 22 listings : 10 listings requirement (WannaBuy /WannaRent)+ 2 free listings in Buy and Wannarent  and 10 listings posting (Wanna sell /Wanna Rentout. )");
        }else{
            textView.setText("WannaBuy  Pricing Launch  OFFER  :\n" +
                    "\n" +
                    "WANNA BUY : FREE( For three months )\n" +
                    "WANNA RENT : FREE( For Three months ) \n" +
                    "POST Sale : ₹ 2999 ( For Three months ) \n" +
                    "POST Rent : ₹ 999 ( For Three months ) \n" +
                    "\n" +
                    "All the price  is valid for one post only . For Extra Ad sale Post  additional ₹2999 have to be charged for three months . For extra Rent post ₹999 have to be charged for three months . \n" +
                    "\n" +
                    "For Buyers/ rentals  one post is free on sale and also for rental totally 2 requirements is free for three months (The location choice is 5 locations in single post ) \n" +
                    "For additional requirements Buyer has to pay for ₹4999 for three months for sale for rental ₹1999 for three months .");
        }
    }
}