package com.nyxwolves.wannabuy.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.UserHelper;
import com.nyxwolves.wannabuy.RestApiHelper.UserPaymentCheck;

import org.json.JSONObject;

public class PaymentInformationActivity extends AppCompatActivity implements CallbackInterface {

    TextView textView;
    String userMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        textView = findViewById(R.id.payment_info_text);
        UserPaymentCheck userHelper = new UserPaymentCheck(this);
        CallbackInterface callbackInterface = this;
        userHelper.getUserStatus(callbackInterface);

    }

    private void setText(int option){
        if(option == 1){
            textView.setText("WannaBuy  Pricing Launch  OFFER:\n\n2 Requirement(Buy/Rent)(Free For Three months) \n\nPOST Sale : ₹ 2999 ( For Three months ) \n\nPOST Rent : ₹ 999 ( For Three months )\n\nAll the price  is valid for one post only. \n\nFor Extra Ad sale Post  additional ₹2999 have to be charged for three months . \n\nFor extra Rent post ₹999 have to be charged for three months. \n\nFor Buyers/ rentals totally 2 requirements is free for three months (The location choice is 5 locations in single post ) For additional requirements Buyer has to pay for ₹4999 for three months for sale for rental ₹1999 for three months.");
        }else if(option == 2){
            textView.setText("Dealer package :\n\n ₹10000 for three months to a Dealer will be given 10 listings to Post (sale / Rent out) Ad and 10 listings on Buying requirement  Buy Or Rent requirement.\n\n Launch offer: Two Requirement is free for three months .\n\n Total 22 listings : 10 listings requirement (WannaBuy /WannaRent)+ 2 free listings in Buy or Rent  and 10 listings posting (Wanna sell /Wanna Rentout).");
        }
    }


    @Override
    public void setData(JSONObject data) {
        try{
            userMode = data.getString("TYPE");
            if (userMode.equals(getString(R.string.individual))){
                setTitle("Individual's Pay");
                setText(1);
            }else{
                setTitle("Dealer's Pay");
                setText(2);
            }
        }catch (Exception e){}
    }

    @Override
    public void isSuccess(boolean isSuccess) {

    }

    @Override
    public void doesUserExits(boolean isExists) {

    }
}