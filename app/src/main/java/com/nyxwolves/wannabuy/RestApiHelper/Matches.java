package com.nyxwolves.wannabuy.RestApiHelper;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.Interfaces.RecyclerInterface;

import org.json.JSONObject;

public class Matches {

    Context ctx;

    public Matches(Context ctx){
        this.ctx = ctx;
    }

    public void getMatches(final RecyclerInterface callbackInterface){

        String URL = "http://www.wannabuy.in/api/Matches/get_matches.php?USER_ID="+ FirebaseAuth.getInstance().getCurrentUser().getEmail();


        StringRequest getRequirementRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    callbackInterface.setData(new JSONObject(response));
                }catch (Exception e){}

                Log.d("MATCHES_RESPONSE", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("READ_RESPONSE", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(getRequirementRequest);
    }
}
