package com.nyxwolves.wannabuy.activities;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.nyxwolves.wannabuy.Helpers.FirebaseHelper;
import com.nyxwolves.wannabuy.R;

public class EmailLogin extends AppCompatActivity {

    TextInputEditText emailInput,passwordInput;
    TextInputLayout emailLayout,passwordLayout;

    Button continueBtn;
    ConstraintLayout rootLayout;

    FirebaseHelper loginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginHelper = new FirebaseHelper(this);

        rootLayout = findViewById(R.id.email_login_root_layout);

        emailLayout = findViewById(R.id.login_email_layout);
        passwordLayout = findViewById(R.id.login_pass_layout);

        emailInput = findViewById(R.id.login_email_input);
        passwordInput = findViewById(R.id.login_pass_input);

        continueBtn = findViewById(R.id.login_continue_btn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    loginHelper.loginUser(emailInput.getText().toString().trim(),passwordInput.getText().toString().trim(),rootLayout);
                }
            }
        });
    }

    private boolean checkInput(){
        if(emailInput.getText().toString().trim().length() == 0){
           emailLayout.setError("Cannot be empty");
           passwordLayout.setErrorEnabled(false);
           return false;
        }else if(passwordInput.getText().toString().trim().length() == 0){
            passwordLayout.setError("Cannot be empty");
            emailLayout.setErrorEnabled(false);
            return false;
        }else{
            emailLayout.setErrorEnabled(false);
            passwordLayout.setErrorEnabled(false);
            return true;
        }
    }
}
