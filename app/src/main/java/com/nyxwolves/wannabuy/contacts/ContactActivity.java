package com.nyxwolves.wannabuy.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.nyxwolves.wannabuy.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Messages");

        ImageView addBtn = findViewById(R.id.nav_msg_btn);
        addBtn.setImageDrawable(getDrawable(R.drawable.msg_orange));

        contactList = new ArrayList<>();
        contactList.add(new Contact("Abhinav", "facebookidlog@gmail.com"));
        contactList.add(new Contact("Upadhyay", "abhinavu1201@gmail.com"));
        setupRecyclerView();
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
    }
}