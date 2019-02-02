package com.nyxwolves.wannabuy.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nyxwolves.wannabuy.Helpers.FirebaseHelper;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.activities.AccountActivity;
import com.nyxwolves.wannabuy.activities.AdsActivity;
import com.nyxwolves.wannabuy.activities.AreaLocality;
import com.nyxwolves.wannabuy.activities.BuyOrRent;
import com.nyxwolves.wannabuy.activities.MyAdsActivity;
import com.nyxwolves.wannabuy.activities.PaymentInformationActivity;
import com.nyxwolves.wannabuy.chat.ChatActivity;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String SOURCE = "source";
    ContactAdapter contactAdapter;
    List<Contact> contactList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String email;
    ProgressBar progressBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Messages");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView addBtn = findViewById(R.id.nav_msg_btn);
        addBtn.setImageDrawable(getDrawable(R.drawable.msg_orange));

        contactList = new ArrayList<>();
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("messages").child("users")
                .child(email.replaceAll("\\.", ""));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Toast.makeText(ContactActivity.this, "No Contacts so far. Chat with someone " +
                            "to add contacts", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Iterable<DataSnapshot> contacts = dataSnapshot.getChildren();
                    for (DataSnapshot node : contacts) {
                        Contact contact = node.getValue(Contact.class);
                        contactList.add(contact);
                    }
                    setupRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ContactActivity.this, "An Error Occurred", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        ImageView home = findViewById(R.id.nav_home_btn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        ImageView msg = findViewById(R.id.nav_account_btn);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactActivity.this, AccountActivity.class));
                finish();
            }
        });
        ImageView ads = findViewById(R.id.nav_ads_btn);
        ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactActivity.this, AdsActivity.class));
                finish();
            }
        });
        ImageView wannaBuy = findViewById(R.id.wanna_buy);
        wannaBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactActivity.this, BuyOrRent.class));
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        contactAdapter = new ContactAdapter(contactList, this, new ContactClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(ContactActivity.this, ChatActivity.class);
                intent.putExtra(EMAIL, contactList.get(position).email);
                intent.putExtra(NAME, contactList.get(position).name);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.GONE);
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
                Intent myReq = new Intent(ContactActivity.this, MyAdsActivity.class);
                myReq.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                myReq.setAction(getString(R.string.show_req));
                startActivity(myReq);
                break;

            case R.id.menu_my_matches:
                Intent myMatches = new Intent(ContactActivity.this, MyAdsActivity.class);
                myMatches.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                myMatches.setAction(getString(R.string.show_match));
                startActivity(myMatches);
                break;

            case R.id.menu_wanna_buy:
                Requirements.getInstance().buyorRent = getString(R.string.BUY);
                startActivity(new Intent(ContactActivity.this, AreaLocality.class));
                break;

            case R.id.menu_wanna_rent:
                Requirements.getInstance().buyorRent = getString(R.string.RENT);
                startActivity(new Intent(ContactActivity.this, AreaLocality.class));
                break;

            case R.id.menu_wanna_sell:
                startActivity(new Intent(ContactActivity.this, AdsActivity.class));
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
                startActivity(new Intent(ContactActivity.this, AdsActivity.class));
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
}