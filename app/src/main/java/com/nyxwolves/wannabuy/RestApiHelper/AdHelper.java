package com.nyxwolves.wannabuy.RestApiHelper;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

public class AdHelper {

    private Context ctx;
    private JSONObject createParams = new JSONObject();

    public AdHelper(Context ctx){
        this.ctx = ctx;
    }

    public void createAd(){
        String URL = "http://www.wannabuy.in/api/Ads/create_ad.php";
        getJson();

        JsonObjectRequest createAdRequest = new JsonObjectRequest(Request.Method.POST, URL, createParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ADS_RESPONSE_CREATED",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ADS_ERROR_CREATED",error.toString());
            }
        });
        CustomRequestQueue.getInstance(ctx).addRequest(createAdRequest);
    }

    private void getJson(){

        try{
            createParams.put("user_id", FirebaseAuth.getInstance().getCurrentUser().getEmail());
            createParams.put("PROPERTY_LOCATION","VELACHERY");
            createParams.put("PROPERTY_TYPE","APARTMENTS");
            createParams.put("PROPERTY_SIZE","6000");
            createParams.put("PROPERTY_ADDRESS","NO:32,ABCD NAGAR,VELACHERY,CHENNAI");
            createParams.put("BHK","2");
            createParams.put("FACING","EAST");
            createParams.put("NEW","1");
            createParams.put("MODE","BUY");
            createParams.put("ADDITIONAL","UNFURNISHED");
            createParams.put("BUDGET","9500000");
            createParams.put("FLOOR","2");
            createParams.put("NEGOTIABLE","1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
