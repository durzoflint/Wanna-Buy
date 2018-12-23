package com.nyxwolves.wannabuy.RestApiHelper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.POJO.SellerAd;

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
                Toast.makeText(ctx,"Ad Posted",Toast.LENGTH_SHORT).show();
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
            createParams.put("user_id",""+FirebaseAuth.getInstance().getCurrentUser().getEmail());
            createParams.put("PROPERTY_LOCATION",""+SellerAd.getInstance().area.toUpperCase());
            createParams.put("PROPERTY_TYPE",""+SellerAd.getInstance().subType.toUpperCase());
            createParams.put("PROPERTY_SIZE",""+SellerAd.getInstance().size.toUpperCase());
            createParams.put("PROPERTY_ADDRESS",""+SellerAd.getInstance().propertyAddress.toUpperCase());
            createParams.put("BHK",""+SellerAd.getInstance().bhk.toUpperCase());
            createParams.put("FACING",""+SellerAd.getInstance().facing.toUpperCase());
            createParams.put("NEW",""+SellerAd.getInstance().isNew.toUpperCase());
            createParams.put("MODE",""+SellerAd.getInstance().sellOrRent.toUpperCase());
            createParams.put("ADDITIONAL",""+SellerAd.getInstance().furnished.toUpperCase());
            createParams.put("BUDGET","650000");
            createParams.put("FLOOR","2");
            createParams.put("NEGOTIABLE",""+SellerAd.getInstance().budgetNegotiable.toUpperCase());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
