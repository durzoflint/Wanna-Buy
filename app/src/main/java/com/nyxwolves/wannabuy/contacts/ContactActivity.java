package com.nyxwolves.wannabuy.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.activities.AccountActivity;
import com.nyxwolves.wannabuy.activities.AdsActivity;
import com.nyxwolves.wannabuy.activities.BuyOrRent;
import com.nyxwolves.wannabuy.chat.ChatActivity;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String SOURCE = "source";
    public static final String CONTACT = "contact";
    ContactAdapter contactAdapter;
    List<Contact> contactList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String email;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Messages");

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
                intent.putExtra(SOURCE, CONTACT);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.GONE);
    }
}