package com.nyxwolves.wannabuy.Activities;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.nyxwolves.wannabuy.Helpers.FirebaseHelper;
import com.nyxwolves.wannabuy.R;

public class PasswordActivity extends AppCompatActivity {

    TextInputEditText passwordInput,confirmPassInput;
    TextInputLayout passwordLayout,confirmPasswordLayout;
    TextView doneBtn;
    ConstraintLayout rootLayout;

    FirebaseHelper registerHelper;

    final String TAG = "PASSWORD_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        registerHelper = new FirebaseHelper(this);

        final String user_name = getIntent().getStringExtra("NAME");
        Log.d(TAG,user_name);
        final String user_email = getIntent().getStringExtra("EMAIL");
        Log.d(TAG,user_email);

        rootLayout = findViewById(R.id.password_root);
        passwordLayout = findViewById(R.id.reg_pass_layout);
        confirmPasswordLayout = findViewById(R.id.reg_conf_pass_layout);

        passwordInput = findViewById(R.id.reg_pass_input);
        confirmPassInput = findViewById(R.id.reg_conf_pass_input);
        doneBtn = findViewById(R.id.reg_pass_done_btn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPassword();
                if(checkPassword()) {
                    registerHelper.registerUser(user_name,user_email,passwordInput.getText().toString().trim(),rootLayout);
                }
            }
        });

    }


    private boolean checkPassword(){
        if(passwordInput.getText().toString().trim().length() == 0){
            passwordLayout.setError("Cannot be Empty");
            confirmPasswordLayout.setErrorEnabled(false);
            return false;
        }else if(confirmPassInput.getText().toString().trim().length() == 0){
            confirmPasswordLayout.setError("Cannot be Empty");
            passwordLayout.setErrorEnabled(false);
            return false;
        }else if(!passwordInput.getText().toString().trim().equals(confirmPassInput.getText().toString().trim())){
            confirmPasswordLayout.setError("Password should be same");
            return false;
        }else{
            passwordLayout.setErrorEnabled(false);
            confirmPasswordLayout.setErrorEnabled(false);
            return true;
        }
    }

}
