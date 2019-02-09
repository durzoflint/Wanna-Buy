package com.nyxwolves.wannabuy.RestApiHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserHelper {

    private Context ctx;
    private SharedPreferences sharedPreferences;

    public UserHelper(Context ctx){
        this.ctx = ctx;
        sharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.shared_pref),Context.MODE_PRIVATE);
    }

    public void createUser(final CallbackInterface callback, String ownerOrDealer, String phone){
        String URL = "http://www.wannabuy.in/api/User/create_user.php";
        JSONObject params = new JSONObject();

        try{
            Log.d("USER_ID",FirebaseAuth.getInstance().getCurrentUser().getEmail());
            params.put("USER_ID",FirebaseAuth.getInstance().getCurrentUser().getEmail());
            params.put("PHONE_NUMBER",phone);
            params.put("OWNER_OR_DEALER",ownerOrDealer);
        }catch(Exception e){}

        JsonObjectRequest createRequest = new JsonObjectRequest(Request.Method.POST, URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("USER_RESPONSE", response.toString());
                callback.isSuccess(true);
                Toast.makeText(ctx, "Welcome", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //closeDialog();
                callback.isSuccess(false);
                Toast.makeText(ctx, "Error Occured.Please try again", Toast.LENGTH_SHORT).show();
                Log.d("USER_ERROR", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(createRequest);
    }

    public void isUserExists(final CallbackInterface callbackInterface){
        String URL = "http://www.wannabuy.in/api/User/user_exists.php?USER_ID="+ FirebaseAuth.getInstance().getCurrentUser().getEmail();

        StringRequest getRequirementRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("USER_JSON_RESPONSE", response);
                try{
                    JSONObject object = new JSONObject(response);
                    if(object.getString("message").equals("user_found")){
                        Log.d("TEST_USER","EXISTS");
                        callbackInterface.doesUserExits(true);
                    }else {
                        Log.d("TEST_USER","NOT EXISTS");
                        callbackInterface.doesUserExits(false);
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
