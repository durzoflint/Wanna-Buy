package com.nyxwolves.wannabuy.RestApiHelper;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;

import org.json.JSONException;
import org.json.JSONObject;

public class UserPaymentCheck {
    public static String UPDATE_BUY_REQ =  "UPDATE_BUY_REQ";
    public static String UPDATE_RENT_REQ =  "UPDATE_RENT_REQ";
    public static String UPDATE_AD = "UPDATE_AD";
    public static String INCREASE_DEALER_CREDITS = "INCREASE_DEALER_CREDITS";
    public static String DECREASE_DEALER_RENT_REQ = "DECREASE_DEALER_RENT_REQ";
    public static String DECREASE_DEALER_BUY_REQ = "DECREASE_DEALER_BUY_REQ";
    public static String DECREASE_DEALER_ADS = "DECREASE_DEALER_ADS";

    private Context ctx;

    public  UserPaymentCheck(Context ctx){
        this.ctx = ctx;
    }

    public void getUserStatus(final CallbackInterface callBack){
        String URL = "http://www.wannabuy.in/api/User/user_status.php?USER_ID="+ FirebaseAuth.getInstance().getCurrentUser().getEmail();

        StringRequest getRequirementRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("JSON_RESPONSE", response);
                try{
                    callBack.setData(new JSONObject(response));
                }catch(JSONException e){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("READ_RESPONSE", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(getRequirementRequest);
    }

    public void updateUserStatus(String type,final CallbackInterface callBack){
        String URL = "http://www.wannabuy.in/api/User/user_transaction.php?USER_ID="+ FirebaseAuth.getInstance().getCurrentUser().getEmail()+"&TYPE="+type;
        StringRequest getRequirementRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("JSON_RESPONSE", response);
                try{
                    if(callBack != null){
                        callBack.setData(new JSONObject(response));
                    }

                }catch(JSONException e){}

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
