package com.nyxwolves.wannabuy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.R;

public class SplashScreen extends AppCompatActivity {

    ImageView splashImage;
    final int TIME_DELAY = 2000;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        splashImage = findViewById(R.id.splash_image);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    String userMode = sharedPreferences.getString(getString(R.string.user_mode),"NOT_SET");

                    if(userMode.equals(getString(R.string.individual)) || userMode.equals(getString(R.string.dealer))){
                        startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                    }else{
                        startActivity(new Intent(SplashScreen.this,OwnerOrDealer.class));
                    }

                } else {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                }
            }
        }, TIME_DELAY);

    }
}
