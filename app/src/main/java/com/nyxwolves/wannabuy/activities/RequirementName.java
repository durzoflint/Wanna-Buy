package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class RequirementName extends AppCompatActivity {

    Button nextButton;
    EditText requirementInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement_name);

        requirementInput = findViewById(R.id.req_name_input);

        nextButton = findViewById(R.id.req_name_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    Requirements.getInstance().reqName = requirementInput.getText().toString().trim();
                    Intent i = new Intent(RequirementName.this,HomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setAction(getString(R.string.POST_REQUIREMENT));
                    startActivity(i);
                }
            }
        });
    }

    private boolean checkInput(){
        if(requirementInput.getText().toString().length() > 0){
            return true;
        }else{
            return false;
        }
    }
}
