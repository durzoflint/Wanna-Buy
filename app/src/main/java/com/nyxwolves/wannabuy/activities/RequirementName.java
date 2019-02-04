package com.nyxwolves.wannabuy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.UserPaymentCheck;

import org.json.JSONObject;

public class RequirementName extends AppCompatActivity implements CallbackInterface {

    Button nextButton;
    EditText requirementInput;
    ProgressDialog progressDialog;

    int PAYMENT_CODE = 1200;
    String buyOrPostRequirement;
    String userMode; //owner or dealer
    String ownerOrDealer;
    int buyReqNum, rentReqNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement_name);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        requirementInput = findViewById(R.id.req_name_input);

        nextButton = findViewById(R.id.req_name_next_btn);

        //api call to get curent status of the user
        //can find whether the user is elgibile for first free req
        UserPaymentCheck helper = new UserPaymentCheck(RequirementName.this);
        CallbackInterface callbackInterface = RequirementName.this;
        helper.getUserStatus(callbackInterface);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    fixAmount();
                }
            }
        });
    }

    private void startIntentToHome() {
        Requirements.getInstance().reqName = requirementInput.getText().toString().trim();

        Intent i = new Intent(RequirementName.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setAction(getString(R.string.POST_REQUIREMENT));
        i.putExtra(getString(R.string.buy_rent_flag), buyOrPostRequirement);
        i.putExtra(getString(R.string.owner_dealer_flag), userMode);
        startActivity(i);
        finish();
    }

    private boolean checkInput() {
        if (requirementInput.getText().toString().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYMENT_CODE) {
            if (resultCode == RESULT_OK) {
                startIntentToHome();
            }
        }
    }

    @Override
    public void setData(JSONObject data) {
        progressDialog.cancel();
        try {
            ownerOrDealer = data.getString("TYPE");
            buyReqNum = data.getInt("BUY_REQ_NUM");
            rentReqNum = data.getInt("RENT_REQ_NUM");

            if(ownerOrDealer.equals(getString(R.string.individual))){
                if (rentReqNum == 0 && Requirements.getInstance().buyorRent.equals(getString(R.string.RENT))) {
                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setText(getString(R.string.free));
                } else if (buyReqNum == 0 && Requirements.getInstance().buyorRent.equals(getString(R.string.BUY))) {
                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setText(getString(R.string.free));
                }else{
                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setText(getString(R.string.make_payment));
                }
            }else{
                if (rentReqNum == 0 && Requirements.getInstance().buyorRent.equals(getString(R.string.RENT))) {
                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setText(getString(R.string.make_payment));
                } else if (buyReqNum == 0 && Requirements.getInstance().buyorRent.equals(getString(R.string.BUY))) {
                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setText(getString(R.string.make_payment));
                }else{
                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setText(getString(R.string.post_now));
                }
            }

        } catch (Exception e) {
        }
    }

    private void fixAmount() {
        if (ownerOrDealer.equals(getString(R.string.individual))) {//if user is owner
            userMode = getString(R.string.individual);
            if (Requirements.getInstance().buyorRent.equals(getString(R.string.BUY))) {
                if (buyReqNum > 0) {
                    //needs to pay
                    buyOrPostRequirement = getString(R.string.pay_buy_requirement);
                    intiatePayment(4999, getString(R.string.pay_buy_requirement));
                } else {
                    //free
                    buyOrPostRequirement = getString(R.string.pay_buy_requirement);
                    startIntentToHome();
                }
            } else {
                if (rentReqNum > 0) {
                    //needs to pay
                    buyOrPostRequirement = getString(R.string.pay_rent_requirement);
                    intiatePayment(1999, getString(R.string.pay_rent_requirement));
                } else {
                    //free
                    buyOrPostRequirement = getString(R.string.pay_rent_requirement);
                    startIntentToHome();
                }
            }
        } else if (ownerOrDealer.equals(getString(R.string.dealer))) {//if user is dealer
            userMode = getString(R.string.dealer);
            if (Requirements.getInstance().buyorRent.equals(getString(R.string.BUY))) {
                if (buyReqNum == 0) {
                    //needs to pay
                    buyOrPostRequirement = getString(R.string.pay_buy_requirement);
                    intiatePayment(10000, getString(R.string.pay_buy_requirement));
                } else {
                    //free
                    buyOrPostRequirement = getString(R.string.pay_buy_requirement);
                    startIntentToHome();
                }
            } else {
                if (rentReqNum == 0) {
                    //needs to pay
                    intiatePayment(10000, getString(R.string.pay_rent_requirement));
                } else {
                    //free
                    intiatePayment(10000, getString(R.string.pay_rent_requirement));
                    startIntentToHome();
                }
            }
        }

    }

    private void intiatePayment(int amount, String buyOrRent) {
        Intent paymentIntent = new Intent(RequirementName.this, PaymentActivity.class);
        paymentIntent.putExtra(PaymentActivity.AMOUNT, amount);
        paymentIntent.putExtra(PaymentActivity.DESCRIPTION, buyOrRent);
        paymentIntent.putExtra(PaymentActivity.USER_TYPE, userMode);
        startActivityForResult(paymentIntent, PAYMENT_CODE);
    }

    @Override
    public void isSuccess(boolean isSuccess) {

    }

    @Override
    public void doesUserExits(boolean isExists) {

    }
}
