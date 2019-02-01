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
}
