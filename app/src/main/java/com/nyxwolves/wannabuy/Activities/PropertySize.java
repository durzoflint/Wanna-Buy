package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class PropertySize extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener{

    TextView minSize,maxSize;
    SeekBar sizeSeekBar;
    Button nextButton;

    int sizeOfProperty;
    String propertyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_size);

        propertyType = getIntent().getStringExtra(getString(R.string.PROPERTY_TYPE));

        sizeSeekBar = findViewById(R.id.area_seekbar);
        sizeSeekBar.setOnSeekBarChangeListener(this);

        nextButton = findViewById(R.id.size_next_btn);
        nextButton.setOnClickListener(this);

        minSize = findViewById(R.id.min_size);
        maxSize  = findViewById(R.id.max_size);

        String propertyType = getIntent().getStringExtra(getString(R.string.PROPERTY_TYPE));
        setLimits(propertyType);
    }

    private void setLimits(String option){
        minSize.setText("0 sq. Ft");
        if(option.equals(getString(R.string.residential))){
            maxSize.setText("10000 sq. Ft");
            sizeSeekBar.setMax(10000);
        }else if(option.equals(getString(R.string.commercial))){
            maxSize.setText("100000+ sq. Ft");
            sizeSeekBar.setMax(100000);
        }else if(option.equals(getString(R.string.industrial))){
            maxSize.setText("100000+ sq. Ft");
            sizeSeekBar.setMax(100000);
        }else if(option.equals(getString(R.string.instutional))){
            maxSize.setText("100000+ sq. Ft");
            sizeSeekBar.setMax(100000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.size_next_btn:
                if(sizeOfProperty != 0) {
                    Requirements.getInstance().size = String.valueOf(sizeOfProperty);
                    Intent i = new Intent(PropertySize.this, PropertySubType.class);
                    i.putExtra(getString(R.string.PROPERTY_TYPE),propertyType);
                    startActivity(i);
                }else{
                    Toast.makeText(PropertySize.this,"Choose the size of property",Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        sizeOfProperty = progress;
        maxSize.setText(""+progress);
        Log.d("PROGRESS",""+sizeOfProperty);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
