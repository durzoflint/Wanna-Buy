package com.nyxwolves.wannabuy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nyxwolves.wannabuy.Fragments.FragmentToActivity;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;
import com.nyxwolves.wannabuy.RestApiHelper.RequirementHelper;
import com.nyxwolves.wannabuy.contacts.ContactActivity;
import com.nyxwolves.wannabuy.notifications.MyFirebaseMessagingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, FragmentToActivity, NavigationView.OnNavigationItemSelectedListener{


    ImageView homeButton,msgButton,adsButton,profileButton,postRequirement;
    Button rentButton,postAdBtn,builderBtn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        if(getIntent().getAction() != null) {
            if (getIntent().getAction().equals(getString(R.string.POST_REQUIREMENT))) {
                RequirementHelper createHelper = new RequirementHelper(HomeActivity.this);
                createHelper.createRequirement();
            }

            if(getIntent().getAction().equals(getString(R.string.POST_AD))){
                AdHelper adHelper = new AdHelper(HomeActivity.this);
                adHelper.createAd();
            }
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        homeButton = findViewById(R.id.nav_home_btn);
        homeButton.setImageDrawable(getDrawable(R.drawable.home_orange));
        homeButton.setOnClickListener(this);

        msgButton = findViewById(R.id.nav_msg_btn);
        msgButton.setOnClickListener(this);

        adsButton = findViewById(R.id.nav_ads_btn);
        adsButton.setOnClickListener(this);

        profileButton = findViewById(R.id.nav_account_btn);
        profileButton.setOnClickListener(this);

        postRequirement = findViewById(R.id.wanna_buy);
        postRequirement.setOnClickListener(this);

        postAdBtn = findViewById(R.id.post_property_btn);
        postAdBtn.setOnClickListener(this);

        rentButton = findViewById(R.id.home_rent_btn);
        rentButton.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean(getString(R.string.shared_first_req),true)){
            editor.putBoolean(getString(R.string.shared_first_req),true);
        }else{
            editor.putBoolean(getString(R.string.shared_first_req),false);
        }

        if(sharedPreferences.getBoolean(getString(R.string.shared_first_ad),true)){
            editor.putBoolean(getString(R.string.shared_first_ad),true);
        }else{
            editor.putBoolean(getString(R.string.shared_first_ad),false);
        }
        editor.apply();

        SharedPreferences firebasePreferences = getSharedPreferences(MyFirebaseMessagingService
                .FIREBASEPREFS, Context.MODE_PRIVATE);
        String token = firebasePreferences.getString(MyFirebaseMessagingService.TOKEN, "");
        if (!token.isEmpty()) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            new AddToken().execute(token, user.getEmail(), user.getDisplayName());
            firebasePreferences.edit().clear().apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                startActivity(new Intent(HomeActivity.this,RequirementsSearchActivity.class));
                break;
            case R.id.notify_icon:
                Toast.makeText(HomeActivity.this, "Notify", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.nav_msg_btn:
                startActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.nav_ads_btn:
                Intent i = new Intent(HomeActivity.this,MyAdsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                //startActivity(new Intent(HomeActivity.this,AdsActivity.class));
                break;
            case R.id.nav_account_btn:
                startActivity(new Intent(this, AccountActivity.class));
                break;
            case R.id.wanna_buy:
                startActivity(new Intent(HomeActivity.this,BuyOrRent.class));
                break;

            case R.id.home_rent_btn:
                //startActivity(new Intent(HomeActivity.this,RentalEndDate.class));
                Requirements.getInstance().buyorRent = getString(R.string.RENT);
                startActivity(new Intent(HomeActivity.this,AreaLocality.class));
                break;
            case R.id.post_property_btn:
                startActivity(new Intent(HomeActivity.this,AdsActivity.class));
                break;
        }
    }

    @Override
    public void changeIcon(String option) {
        if(option.equals(getString(R.string.HOME_FRAGMENT))){
            homeButton.setImageDrawable(getDrawable(R.drawable.home_orange));
            adsButton.setImageDrawable(getDrawable(R.drawable.ads_blue));
            profileButton.setImageDrawable(getDrawable(R.drawable.acc_blue));
            msgButton.setImageDrawable(getDrawable(R.drawable.msg_blue));
        }
        else if(option.equals(getString(R.string.ADS_FRAGMENT))){
            adsButton.setImageDrawable(getDrawable(R.drawable.ads_orange));
            homeButton.setImageDrawable(getDrawable(R.drawable.home_blue));
            profileButton.setImageDrawable(getDrawable(R.drawable.acc_blue));
            msgButton.setImageDrawable(getDrawable(R.drawable.msg_blue));
        }
        else if(option.equals(getString(R.string.PROF_FRAGMENT))){
            profileButton.setImageDrawable(getDrawable(R.drawable.acc_orange));
            adsButton.setImageDrawable(getDrawable(R.drawable.ads_blue));
            homeButton.setImageDrawable(getDrawable(R.drawable.home_blue));
            msgButton.setImageDrawable(getDrawable(R.drawable.msg_blue));
        }
        else{
            msgButton.setImageDrawable(getDrawable(R.drawable.msg_orange));
            adsButton.setImageDrawable(getDrawable(R.drawable.ads_blue));
            homeButton.setImageDrawable(getDrawable(R.drawable.home_blue));
            profileButton.setImageDrawable(getDrawable(R.drawable.acc_blue));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.log_out:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this,LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
                break;

            case R.id.my_matches:
                startActivity(new Intent(HomeActivity.this,MyAdsActivity.class));
                break;
            case R.id.user_account:
                startActivity(new Intent(HomeActivity.this,AccountActivity.class));
                break;
        }
        return false;
    }

    private class AddToken extends AsyncTask<String, Void, Void> {
        String baseUrl = "http://www.wannabuy.in/api/notifications/";
        String webPage = "";

        @Override
        protected Void doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String myURL = baseUrl + "addToken.php?firebase_token=" + strings[0] + "&email="
                        + strings[1] + "&name=" + strings[2];
                myURL = myURL.replaceAll(" ", "%20");
                myURL = myURL.replaceAll("\'", "%27");
                myURL = myURL.replaceAll("\'", "%22");
                myURL = myURL.replaceAll("\\+'", "%2B");
                myURL = myURL.replaceAll("\\(", "%28");
                myURL = myURL.replaceAll("\\)", "%29");
                myURL = myURL.replaceAll("\\{", "%7B");
                myURL = myURL.replaceAll("\\}", "%7B");
                myURL = myURL.replaceAll("\\]", "%22");
                myURL = myURL.replaceAll("\\[", "%22");
                url = new URL(myURL);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection
                        .getInputStream()));
                String data;
                while ((data = br.readLine()) != null)
                    webPage = webPage + data;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!webPage.equals("success")) {
                Toast.makeText(HomeActivity.this, "Error Occurred while adding user", Toast
                        .LENGTH_SHORT).show();
            }
        }
    }
}
