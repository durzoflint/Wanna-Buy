package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;

public class ImageUpload extends AppCompatActivity {

    Button submitButton;

    String ownerOrDealer;
    int adsNum;
    int PAYMENT_CODE = 120;
    int adId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        ownerOrDealer = getIntent().getStringExtra(getString(R.string.owner_dealer_flag));
        try{
            adsNum = Integer.parseInt(getIntent().getStringExtra(getString(R.string.ad_num)));
            adId = Integer.parseInt(getIntent().getStringExtra(getString(R.string.ad_id)));
        }catch (NumberFormatException e){
            e.toString();
        }


        submitButton = findViewById(R.id.ad_submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPayment();
            }
        });
    }

    private void checkPayment(){
        if (ownerOrDealer.equals(getString(R.string.individual))) {//if user is owner

            if (SellerAd.getInstance().adsSellOrRent.equals(getString(R.string.SELL))) {
                intiatePayment(2999, getString(R.string.pay_sell_ad));
            } else {
                intiatePayment(999, getString(R.string.pay_rent_ad));
            }

        } else if (ownerOrDealer.equals(getString(R.string.dealer))) {//if user is dealer

            if (adsNum == 0) {
                //needs to pay
                if (SellerAd.getInstance().adsSellOrRent.equals(getString(R.string.SELL))) {
                    intiatePayment(10000, getString(R.string.pay_sell_ad));
                } else {
                    intiatePayment(10000, getString(R.string.pay_rent_ad));
                }
            } else if (adsNum != 0) {
                //still has credits left
                startIntentToHome();

            }
        }
    }

    private void startIntentToHome() {
        Intent i = new Intent(ImageUpload.this, HomeActivity.class);
        i.setAction(getString(R.string.POST_AD));
        i.putExtra(getString(R.string.ad_id),adId);
        startActivity(i);
    }

    private void intiatePayment(int amount, String sellOrRent) {
        Intent paymentIntent = new Intent(ImageUpload.this, PaymentActivity.class);
        paymentIntent.putExtra(PaymentActivity.AMOUNT, amount);
        paymentIntent.putExtra(PaymentActivity.DESCRIPTION, sellOrRent);
        startActivityForResult(paymentIntent, PAYMENT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PAYMENT_CODE){
            if(resultCode == RESULT_OK){
                startIntentToHome();
            }
        }
    }
}
