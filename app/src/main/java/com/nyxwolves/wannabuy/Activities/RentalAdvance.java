package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nyxwolves.wannabuy.R;

public class RentalAdvance extends AppCompatActivity {

    EditText advanceInput;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_advance);

        advanceInput = findViewById(R.id.advance_input);

        nextBtn = findViewById(R.id.rental_advance_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    startActivity(new Intent(RentalAdvance.this,RentalTenantName.class));
                }
            }
        });
    }

    private boolean checkInput(){
        if(advanceInput.getText().toString().trim().length() > 0){
            return true;
        }else{
            return false;
        }
    }
}
