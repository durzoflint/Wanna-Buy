package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class FlooringActivity extends AppCompatActivity {

    Button nextBtn;
    CheckBox showroomGroundFloor;
    ConstraintLayout flooringLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flooring);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        showroomGroundFloor = findViewById(R.id.ground_check_showroom);//show only for showroom
        flooringLayout = findViewById(R.id.flooring_layout);

        if(Requirements.getInstance().subType.equals(getString(R.string.showroom))){
            showroomGroundFloor.setVisibility(View.VISIBLE);
            flooringLayout.setVisibility(View.GONE);

        }else{
            showroomGroundFloor.setVisibility(View.GONE);
            flooringLayout.setVisibility(View.VISIBLE);

        }

        nextBtn = findViewById(R.id.flooring_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FlooringActivity.this,FurnishedOrNot.class));
            }
        });
    }

    private void setData(CheckBox checkBox,String data){
        if(checkBox.isChecked()){
            Requirements.getInstance().floorList.add(data);
        }else{
            Requirements.getInstance().floorList.remove(data);
        }
    }
    public void onCheckBoxClicked(View v){
        switch(v.getId()){

            case R.id.check_ground:
                setData((CheckBox)v,"Ground");
                break;
            case R.id.check_1:
                setData((CheckBox)v,"1");
                break;
            case R.id.check_2:
                setData((CheckBox)v,"2");
                break;
            case R.id.check_3:
                setData((CheckBox)v,"3");
                break;
            case R.id.check_4:
                setData((CheckBox)v,"4");
                break;
            case R.id.check_5:
                setData((CheckBox)v,"5");
                break;
            case R.id.check_6:
                setData((CheckBox)v,"6");
                break;
            case R.id.check_7:
                setData((CheckBox)v,"7");
                break;
            case R.id.check_8:
                setData((CheckBox)v,"8");
                break;
            case R.id.check_9:
                setData((CheckBox)v,"9");
                break;
            case R.id.check_10:
                setData((CheckBox)v,"10");
                break;
            case R.id.check_11:
                setData((CheckBox)v,"11");
                break;
            case R.id.check_12:
                setData((CheckBox)v,"12");
                break;
            case R.id.check_13:
                setData((CheckBox)v,"13");
                break;
            case R.id.check_14:
                setData((CheckBox)v,"14");
                break;
            case R.id.check_15:
                setData((CheckBox)v,"15");
                break;
            case R.id.check_16:
                setData((CheckBox)v,"16");
                break;
            case R.id.check_17:
                setData((CheckBox)v,"17");
                break;
            case R.id.check_18:
                setData((CheckBox)v,"18");
                break;
            case R.id.check_19:
                setData((CheckBox)v,"19");
                break;
            case R.id.check_20:
                setData((CheckBox)v,"20");
                break;
            case R.id.check_20_plus:
                setData((CheckBox)v,"21");
                break;
        }
    }
}
