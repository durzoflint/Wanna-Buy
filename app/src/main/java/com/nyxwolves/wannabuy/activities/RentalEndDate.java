package com.nyxwolves.wannabuy.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.nyxwolves.wannabuy.R;

public class RentalEndDate extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    ImageView dateIcon;
    EditText dateInput;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_end_date);

        dateIcon = findViewById(R.id.date_icon);
        dateInput = findViewById(R.id.date_input_text);

        nextBtn = findViewById(R.id.end_date_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    startActivity(new Intent(RentalEndDate.this,RentalPropertyType.class));
                }
            }
        });

        dateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog  datePickerDialog  = new DatePickerDialog(RentalEndDate.this,RentalEndDate.this,2018,12,12);
                datePickerDialog.show();
            }
        });
    }

    private boolean checkInput(){
        if(dateInput.getText().toString().trim().length() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateInput.setText(dayOfMonth+" /"+month+"/"+year);
    }
}

