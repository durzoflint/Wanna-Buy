package com.nyxwolves.wannabuy.activities;

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

    int PAYMENT_CODE = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement_name);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requirementInput = findViewById(R.id.req_name_input);

        nextButton = findViewById(R.id.req_name_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    UserPaymentCheck helper = new UserPaymentCheck(RequirementName.this);
                    CallbackInterface callbackInterface = RequirementName.this;
                    helper.getUserStatus(callbackInterface);
                }
            }
        });
    }
    private void startIntentToHome(){
        Requirements.getInstance().reqName = requirementInput.getText().toString().trim();
        Intent i = new Intent(RequirementName.this,HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setAction(getString(R.string.POST_REQUIREMENT));
        startActivity(i);
        finish();
    }

    private boolean checkInput(){
        if(requirementInput.getText().toString().length() > 0){
            return true;
        }else{
            return false;
        }
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

    @Override
    public void setData(JSONObject data) {
        try{
            if(Integer.valueOf(data.getString("REQUIREMENTS_NUM")) > 0){
                //needs to pay
                intiatePayment();
            }else{
                //free
                startIntentToHome();
            }
        }catch(Exception e){}
    }

    private  void intiatePayment(){
        Intent paymentIntent = new Intent(RequirementName.this,PaymentActivity.class);

        if(Requirements.getInstance().buyorRent.equals(getString(R.string.BUY))){
            paymentIntent.putExtra(PaymentActivity.AMOUNT,4999);
            paymentIntent.putExtra(PaymentActivity.DESCRIPTION,getString(R.string.pay_buy_requirement));
        }else{
            paymentIntent.putExtra(PaymentActivity.AMOUNT,2499);
            paymentIntent.putExtra(PaymentActivity.DESCRIPTION,getString(R.string.pay_rent_requirement));
        }
        startActivityForResult(paymentIntent,PAYMENT_CODE);
    }
    @Override
    public void isSuccess(boolean isSuccess) {

    }
}
