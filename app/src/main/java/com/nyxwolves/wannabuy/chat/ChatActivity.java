package com.nyxwolves.wannabuy.chat;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.contacts.Contact;
import com.nyxwolves.wannabuy.contacts.ContactActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    String name, email, userEmail, userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = user.getEmail();
        userName = user.getDisplayName();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        name = intent.getStringExtra(ContactActivity.NAME);
        email = intent.getStringExtra(ContactActivity.EMAIL);

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
                new SendNotification().execute(email, name, "You have a new Message");
            }
        });
    }

    private void addContact() {
        final DatabaseReference contactReference = firebaseDatabase.getReference()
                .child("messages").child("users").child(userEmail.replaceAll("\\.", ""));
        final DatabaseReference contactReference2 = firebaseDatabase.getReference()
                .child("messages").child("users").child(email.replaceAll("\\.", ""));
        contactReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    boolean found = false;
                    Iterable<DataSnapshot> contacts = dataSnapshot.getChildren();
                    for (DataSnapshot node : contacts) {
                        Contact contact = node.getValue(Contact.class);
                        if (contact.email.equals(email))
                            found = true;
                    }
                    if (!found) {
                        contactReference.push().setValue(new Contact(name, email));
                        contactReference2.push().setValue(new Contact(userName, userEmail));
                    }
                } else {
                    contactReference.push().setValue(new Contact(name, email));
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

    private class SendNotification extends AsyncTask<String, Void, Void> {
        String baseUrl = "http://www.wannabuy.in/api/notifications/";
        String webPage = "";

        @Override
        protected Void doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String myURL = baseUrl + "sendNotification.php?email=" + strings[0] + "&title="
                        + strings[1] + "&body=" + strings[2];
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
    }
}