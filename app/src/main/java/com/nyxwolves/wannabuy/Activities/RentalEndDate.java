package com.nyxwolves.wannabuy.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.nyxwolves.wannabuy.R;

public class RentalEndDate extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    ImageView dateIcon;
    EditText dateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_end_date);

        dateIcon = findViewById(R.id.date_icon);
        dateInput = findViewById(R.id.date_input_text);

        dateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog  datePickerDialog  = new DatePickerDialog(RentalEndDate.this,RentalEndDate.this,2018,12,12);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateInput.setText(dayOfMonth+" /"+month+"/"+year);
    }
}

