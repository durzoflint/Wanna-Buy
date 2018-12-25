package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.nyxwolves.wannabuy.R;

public class NameAndEmail extends AppCompatActivity {

    TextInputEditText nameInput, emailInput;
    TextInputLayout nameLayout,emailLayout;

    Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_and_email);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nameInput = findViewById(R.id.reg_name_input);
        nameInput.setText(null);
        emailInput = findViewById(R.id.reg_email_input);
        emailInput.setText(null);

        nameLayout = findViewById(R.id.reg_name_layout);
        emailLayout = findViewById(R.id.reg_email_layout);

        continueBtn = findViewById(R.id.reg_continue_btn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkInput()) {
                    Intent intent = new Intent(NameAndEmail.this, PasswordActivity.class);
                    intent.putExtra("NAME", nameInput.getText().toString().trim());
                    intent.putExtra("EMAIL", emailInput.getText().toString().trim());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkInput() {
        if (nameInput.getText().toString().trim().length() == 0) {
            nameLayout.setError("Cannot be null");
            emailLayout.setErrorEnabled(false);
            return false;
        } else if(emailInput.getText().toString().trim().length() == 0) {
            emailLayout.setError("Cannot be null");
            nameLayout.setErrorEnabled(false);
            return false;
        }else{
            nameLayout.setErrorEnabled(false);
            emailLayout.setErrorEnabled(false);
            return true;
        }
    }
}
