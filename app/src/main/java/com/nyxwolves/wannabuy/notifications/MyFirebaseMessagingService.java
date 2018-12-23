package com.nyxwolves.wannabuy.notifications;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        Log.d("Abhinav", "Refreshed token: " + token);

        //sendRegistrationToServer(token);

        //FirebaseMessaging.getInstance().subscribeToTopic("testing_topic");
    }
}