package com.nyxwolves.wannabuy.notifications;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String FIREBASEPREFS = "firebaseprefs";
    public static final String TOKEN = "token";
    String baseUrl = "http://www.wannabuy.in/api/notifications/";
    String webPage = "";

    @Override
    public void onNewToken(String token) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            updateToken(token, FirebaseAuth.getInstance().getCurrentUser().getEmail());
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences(FIREBASEPREFS, Context
                    .MODE_PRIVATE);
            sharedPreferences.edit().putString(TOKEN, token).apply();
        }
    }

    void updateToken(String token, String email) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            String myURL = baseUrl + "updateToken.php?firebase_token=" + token + "&email=" + email;
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
    }
}