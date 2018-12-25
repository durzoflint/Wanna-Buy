package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nyxwolves.wannabuy.R;

public class RentalTenantName extends AppCompatActivity {

    EditText tenantNameInput;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_tenant_name);

        tenantNameInput = findViewById(R.id.tenant_name_input);

        nextBtn = findViewById(R.id.tenant_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RentalTenantName.this,ROIRentalActivity.class));
            }
        });
    }
}
