package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nyxwolves.wannabuy.Helpers.FirebaseHelper;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.contacts.ContactActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView addBtn = findViewById(R.id.nav_account_btn);
        addBtn.setImageDrawable(getDrawable(R.drawable.acc_orange));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        CircleImageView profilePicture = findViewById(R.id.profile_picture);
        if (user.getPhotoUrl() != null)
            Glide.with(this).load(user.getPhotoUrl()).into(profilePicture);

        TextView name = findViewById(R.id.name);
        name.setText(user.getDisplayName());

        TextView requirements = findViewById(R.id.requirements);
        requirements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, MyAdsActivity.class);
                intent.setAction(getString(R.string.show_req));
                startActivity(intent);
            }
        });

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
                startActivity(new Intent(AccountActivity.this, MyAdsActivity.class));
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

        TextView contacted = findViewById(R.id.contacted);
        contacted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, ContactActivity.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.menu_user_account:
                startActivity(new Intent(this, AccountActivity.class));
                break;

            case R.id.payment_information:
                startActivity(new Intent(this, PaymentInformationActivity.class));
                break;

            case R.id.menu_my_requirements:
                Intent myReq = new Intent(AccountActivity.this, MyAdsActivity.class);
                myReq.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                myReq.setAction(getString(R.string.show_req));
                startActivity(myReq);
                break;

            case R.id.menu_my_matches:
                Intent myMatches = new Intent(AccountActivity.this, MyAdsActivity.class);
                myMatches.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                myMatches.setAction(getString(R.string.show_match));
                startActivity(myMatches);
                break;

            case R.id.menu_wanna_buy:
                Requirements.getInstance().buyorRent = getString(R.string.BUY);
                startActivity(new Intent(AccountActivity.this, StateInputActivity.class));
                break;

            case R.id.menu_wanna_rent:
                Requirements.getInstance().buyorRent = getString(R.string.RENT);
                startActivity(new Intent(AccountActivity.this, StateInputActivity.class));
                break;

            case R.id.menu_wanna_sell:
                startActivity(new Intent(AccountActivity.this, AdsActivity.class));
                break;

            case R.id.log_out:
                FirebaseHelper logoutHelper = new FirebaseHelper(this);
                logoutHelper.logOutUser();
                finish();
                break;

            case R.id.menu_about_us:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.about_us_url))));
                break;

            case R.id.menu_privacy_policy:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.privacy_policy_url))));
                break;

            case R.id.menu_terms_of_service:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.terms_of_service_url))));
                break;

            case R.id.menu_wanna_rent_out:
                startActivity(new Intent(AccountActivity.this, AdsActivity.class));
                break;

            case R.id.menu_how_we_work:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.how_we_work_url))));
                break;

            case R.id.menu_contact_us:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://wannabuy" +
                        ".in/contactus")));
                break;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
