package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nyxwolves.wannabuy.R;

public class WelcomeScreen extends AppCompatActivity {

    int TIME_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);



    }

    @Override
    protected void onStart() {
        super.onStart();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeScreen.this,HomeActivity.class));
            }
        },TIME_DELAY);
    }
}
