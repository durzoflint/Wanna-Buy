package com.nyxwolves.wannabuy.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.contacts.ContactActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ChildEventListener childEventListener;
    List<Message> messageList;
    MessageAdapter messageAdapter;
    ProgressBar progressBar;
    String name, email, source, userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        name = intent.getStringExtra(ContactActivity.NAME);
        email = intent.getStringExtra(ContactActivity.EMAIL);
        source = intent.getStringExtra(ContactActivity.SOURCE);

        if (!source.equals(ContactActivity.CONTACT))
            addContact();

        String node = (email.compareTo(userEmail) > 0) ? (userEmail + " " + email) : (email + " "
                + userEmail);
        node = node.replaceAll("\\.", "");

        setTitle(name);

        databaseReference = firebaseDatabase.getReference()
                .child("messages")
                .child(node);

        messageList = new ArrayList<>();
        setupRecyclerView();
        attachDatabaseListener();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ChatActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context
                        .INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                EditText msg = findViewById(R.id.msg);
                String time = new Date().toString();
                databaseReference.push().setValue(new Message(time, msg.getText().toString(), userEmail));
                msg.setText("");
            }
        });
    }

    private void addContact() {
        final DatabaseReference contactReference = firebaseDatabase.getReference()
                .child("messages").child("users").child(userEmail.replaceAll("\\.", ""));
        contactReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    boolean found = false;
                    Iterable<DataSnapshot> emails = dataSnapshot.getChildren();
                    for (DataSnapshot node : emails) {
                        if (node.getValue().toString().equals(email))
                            found = true;
                    }
                    if (!found)
                        contactReference.push().setValue(email);
                } else {
                    contactReference.push().setValue(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ChatActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        messageAdapter = new MessageAdapter(messageList, this);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void attachDatabaseListener() {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                messageList.add(message);
                messageAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ChatActivity.this, "An Error Occurred", Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.addChildEventListener(childEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(childEventListener);
    }
}