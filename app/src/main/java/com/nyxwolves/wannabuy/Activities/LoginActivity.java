package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.Helpers.FirebaseHelper;
import com.nyxwolves.wannabuy.R;

public class LoginActivity extends AppCompatActivity {

    //random comment
    Button loginBtn,fbLogin,googleLogin,registerUserBtn;
    LoginButton facebookLogin;
    ConstraintLayout rootLayout;

    FirebaseHelper loginHelper;
    CallbackManager fbCallbackManager;

    int GOOGLE_SIGN = 1;
    int FACEBOOK_SIGN = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginHelper = new FirebaseHelper(this);
        rootLayout = findViewById(R.id.login_root_layout);

        //email password login
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,EmailLogin.class));
            }
        });

        //google login
        googleLogin = findViewById(R.id.google_sign);
        googleLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivityForResult(loginHelper.signInGoogleUser(),GOOGLE_SIGN);
            }
        });

        //facebook login
        facebookLogin = findViewById(R.id.fb_login_btn);
        facebookLogin.setReadPermissions("email","public_profile");
        fbCallbackManager = CallbackManager.Factory.create();
        facebookLogin.registerCallback(fbCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginHelper.facebookLogin(loginResult.getAccessToken(),rootLayout);
            }

            @Override
            public void onCancel() {
                Snackbar.make(rootLayout,"Error Occurred",Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        //custom facebook button
        fbLogin = findViewById(R.id.fb_btn);
        fbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLogin.performClick();
            }
        });

        //register user
        registerUserBtn = findViewById(R.id.register_btn);
        registerUserBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,NameAndEmail.class));
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GOOGLE_SIGN){
            loginHelper.checkAuthentication(data,rootLayout);
        }else{
            fbCallbackManager.onActivityResult(requestCode,resultCode,data);
        }
    }
}
