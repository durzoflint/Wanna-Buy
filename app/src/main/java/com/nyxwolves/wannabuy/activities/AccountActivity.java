package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.contacts.ContactActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ImageView addBtn = findViewById(R.id.nav_account_btn);
        addBtn.setImageDrawable(getDrawable(R.drawable.acc_orange));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        CircleImageView profilePicture = findViewById(R.id.profile_picture);
        if (user.getPhotoUrl() != null)
            Glide.with(this).load(user.getPhotoUrl()).into(profilePicture);

        TextView name = findViewById(R.id.name);
        name.setText(user.getDisplayName());

        TextView settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, SettingsActivity.class));
            }
        });

        TextView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(AccountActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });

        ImageView home = findViewById(R.id.nav_home_btn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, HomeActivity.class));
                finish();
            }
        });
        ImageView msg = findViewById(R.id.nav_msg_btn);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, ContactActivity.class));
                finish();
            }
        });
        ImageView ads = findViewById(R.id.nav_ads_btn);
        ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, AdsActivity.class));
                finish();
            }
        });
        ImageView wannaBuy = findViewById(R.id.wanna_buy);
        wannaBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, BuyOrRent.class));
            }
        });
    }
}
