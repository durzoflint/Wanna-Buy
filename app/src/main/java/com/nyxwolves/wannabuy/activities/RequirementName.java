package com.nyxwolves.wannabuy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nyxwolves.wannabuy.CustomDialog.MessageDialog;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.UserPaymentCheck;

import org.json.JSONObject;

public class RequirementName extends AppCompatActivity implements CallbackInterface, MessageDialog.CustomDialogListener {

    Button nextButton;
    EditText requirementInput, brokerageInput;
    ProgressDialog progressDialog;
    LinearLayout brokerageLayout;
    LinearLayout brokerageInputLayout;

    int PAYMENT_CODE = 1200;
    String buyOrPostRequirement;
    String userMode; //owner or dealer
    String ownerOrDealer;
    int buyReqNum, rentReqNum, dealerReqCredits;
    boolean isBrokerageSelected;

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
                if (checkInput() && checkBrokerage()) {
                    MessageDialog msgDialog = new MessageDialog();
                    Bundle bundle = new Bundle();
                    bundle.putInt("OPTION", MessageDialog.REQ_DIALOG);
                    msgDialog.setArguments(bundle);
                    msgDialog.show(getSupportFragmentManager(), "MSG_DIALOG");
                } else {
                    Toast.makeText(RequirementName.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //layout visible for dealers only
        brokerageLayout = findViewById(R.id.brokerage_layout);//visible only for dealers
        brokerageInputLayout = findViewById(R.id.brokerage_input_layout);//visible only if user selects yes for brokerage
        brokerageInput = findViewById(R.id.req_brokerage_per_cent_input);//brokerage amount in %
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
        return requirementInput.getText().toString().length() > 0;
    }

    private boolean checkBrokerage(){
        if(isBrokerageSelected){
            return brokerageInput.getText().toString().trim().length() > 0;
        }else{
            return true;
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

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.req_brokerage_yes:
                isBrokerageSelected = true;
                brokerageInputLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.req_brokerage_no:
                isBrokerageSelected = false;
                brokerageInputLayout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void setData(JSONObject data) {
        progressDialog.cancel();
        try {
            ownerOrDealer = data.getString("TYPE");
            buyReqNum = data.getInt("BUY_REQ_NUM");
            rentReqNum = data.getInt("RENT_REQ_NUM");
            dealerReqCredits = data.getInt("DEALER_REQ_CREDITS");

            if (ownerOrDealer.equals(getString(R.string.individual))) {
                //hide brokerage layout
                brokerageLayout.setVisibility(View.GONE);
                if (buyReqNum + rentReqNum >= 2) {
                    nextButton.setVisibility(View.VISIBLE);
                    //nextButton.setText(getString(R.string.submit));
                    nextButton.setText(getString(R.string.free));
                } else {
                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setText(getString(R.string.free));
                }
            } else {
                //make brokerage layout visible
                brokerageLayout.setVisibility(View.VISIBLE);

                if (rentReqNum+buyReqNum == 0) {
                    nextButton.setVisibility(View.VISIBLE);
                    //nextButton.setText(getString(R.string.submit));
                    nextButton.setText(getString(R.string.free));
                } else {
                    nextButton.setVisibility(View.VISIBLE);
                    //nextButton.setText(getString(R.string.post_now));
                    nextButton.setText(getString(R.string.free));
                }
            }

        } catch (Exception e) {
        }
    }

    private void fixAmount() {
        if (ownerOrDealer.equals(getString(R.string.individual))) {//if user is owner
            userMode = getString(R.string.individual);
            if (Requirements.getInstance().buyorRent.equals(getString(R.string.BUY))) {
                if (buyReqNum + rentReqNum >= 2) {
                    //needs to pay
                    buyOrPostRequirement = getString(R.string.pay_buy_requirement);
                    //intiatePayment(4999, getString(R.string.pay_buy_requirement));
                    startIntentToHome();
                } else {
                    //free
                    buyOrPostRequirement = getString(R.string.pay_buy_requirement);
                    startIntentToHome();
                }
            } else {
                if (buyReqNum + rentReqNum >= 2) {
                    //needs to pay
                    buyOrPostRequirement = getString(R.string.pay_rent_requirement);
                    //intiatePayment(1999, getString(R.string.pay_rent_requirement));
                    startIntentToHome();
                } else {
                    //free
                    buyOrPostRequirement = getString(R.string.pay_rent_requirement);
                    startIntentToHome();
                }
            }
        } else if (ownerOrDealer.equals(getString(R.string.dealer))) {//if user is dealer
            userMode = getString(R.string.dealer);
            if (Requirements.getInstance().buyorRent.equals(getString(R.string.BUY))) {
                if (dealerReqCredits == 0) {
                    if (buyReqNum+rentReqNum <=0) {
                        //needs to pay
                        Log.d("TEST", "REACHED");
                        buyOrPostRequirement = getString(R.string.pay_buy_requirement);
                        //intiatePayment(10000, getString(R.string.pay_buy_requirement));
                        startIntentToHome();

                    } else {
                        //intial free req left
                        buyOrPostRequirement = getString(R.string.pay_buy_requirement);
                        startIntentToHome();
                    }

                } else if (dealerReqCredits > 0) {
                    //still has credits to post requirements
                    buyOrPostRequirement = getString(R.string.pay_buy_requirement);
                    startIntentToHome();
                }
            } else {
                if (dealerReqCredits == 0) {
                    if (buyReqNum+rentReqNum <= 0) {
                        //needs to pay
                        buyOrPostRequirement = getString(R.string.pay_rent_requirement);
                        //intiatePayment(10000, getString(R.string.pay_rent_requirement));
                        startIntentToHome();

                    } else {
                        //intial free req left
                        //still has some credits to post requirements
                        buyOrPostRequirement = getString(R.string.pay_rent_requirement);
                        startIntentToHome();
                    }

                } else if (dealerReqCredits > 0) {
                    //still has some credits to post requirements
                    buyOrPostRequirement = getString(R.string.pay_rent_requirement);
                    startIntentToHome();
                }
            }
        }
        startIntentToHome();
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

    @Override
    public void dealerOrUser(boolean isDealer) {

    }

    //callback from ok button in dialog box
    @Override
    public void onOkButtonClicked() {
        fixAmount();
    }
}
