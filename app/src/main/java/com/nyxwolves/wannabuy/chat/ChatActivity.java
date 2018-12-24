package com.nyxwolves.wannabuy.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyxwolves.wannabuy.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ChildEventListener childEventListener;
    List<Message> messageList;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //Remove the dot(.) from the email ids
        databaseReference = firebaseDatabase.getReference()
                .child("messages")
                .child("aaaorabhinav@gmailcom abhinavu1201@gmailcom");

        messageList = new ArrayList<>();
        setupRecyclerView();
        attachDatabaseListener();

        //Todo: To send a msg
        String time = new Date().toString();
        //databaseReference.push().setValue(new Message(time, "Test Message 101",
        // "aaaorabhinav@gmail.com"));
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