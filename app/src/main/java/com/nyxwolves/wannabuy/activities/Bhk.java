package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class Bhk extends AppCompatActivity {

    Button nextBtn;
    TextView modeHeader;
    EditText minRestRoomInput;

    boolean oneSelected = false;
    boolean twoSelected = false;
    boolean threeSelected = false;
    boolean fourSelected = false;
    boolean fiveSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhk);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        modeHeader = findViewById(R.id.bhk_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        minRestRoomInput = findViewById(R.id.min_rest_room_input);


        nextBtn = findViewById(R.id.bhk_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oneSelected || twoSelected || threeSelected || fourSelected || fiveSelected) {
                    if (checkInput()) {
                        if (Requirements.getInstance().subType.equals(getString(R.string.residential_independent)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.pg_rent_independent))) {
                            startActivity(new Intent(Bhk.this, CarParking.class));

                        } else if (Requirements.getInstance().subType.equals(getString(R.string.residential_apartments)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.pg_rent_apartment)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.commercial_floorspace)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace))) {
                            startActivity(new Intent(Bhk.this, FlooringActivity.class));

                        } else {
                            startActivity(new Intent(Bhk.this, FacingActivity.class));

                        }
                    } else {
                        Toast.makeText(Bhk.this, "Enter number of Rest Room.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Bhk.this, "Choose number of rooms", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkInput(){
        if(minRestRoomInput.getText().toString().trim().length() > 0){
            if(Integer.parseInt(minRestRoomInput.getText().toString()) > 0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    private boolean setData(CheckBox checkBox, String data) {
        if (checkBox.isChecked()) {
            Requirements.getInstance().bhkList.add(data);
            return true;
        } else {
            Requirements.getInstance().bhkList.remove(data);
            return false;
        }
    }

    public void onCheckBoxClicked(View v) {
        switch (v.getId()) {
            case R.id.bhk_one:
                oneSelected = setData((CheckBox) v, "1");
                break;
            case R.id.bhk_two:
                twoSelected = setData((CheckBox) v, "2");
                break;
            case R.id.bhk_three:
                threeSelected = setData((CheckBox) v, "3");
                break;
            case R.id.bhk_four:
                fourSelected = setData((CheckBox) v, "4");
                break;
            case R.id.bhk_five:
                fiveSelected = setData((CheckBox) v, "5");
                break;
        }
    }
}
