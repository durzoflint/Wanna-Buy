package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.RequirementHelper;

public class Budget extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar budgetSeekBar;
    Button nextButton;
    TextView selectedPriceView;

    int budget=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        selectedPriceView = findViewById(R.id.selected_price_view);

        budgetSeekBar = findViewById(R.id.budget_seekbar);
        budgetSeekBar.setOnSeekBarChangeListener(this);
        budgetSeekBar.setMax(1000);

        nextButton = findViewById(R.id.budget_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(budget != 0){
                    Requirements.getInstance().budget = String.valueOf(budget);
                    Intent i = new Intent(Budget.this,HomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setAction(getString(R.string.POST_REQUIREMENT));
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        budget = progress;
        selectedPriceView.setText(""+progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
