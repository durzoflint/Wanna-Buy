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
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdHelper {

    private Context ctx;
    private JSONObject createParams = new JSONObject();
    public static List<SellerAd> adDataList = new ArrayList<>();

    public AdHelper(Context ctx){
        this.ctx = ctx;
    }

    public void createAd(){
        String URL = "http://www.wannabuy.in/api/Ads/create_ad.php";
        getJson();
        Log.d("ADS_JSON",createParams.toString());
        JsonObjectRequest createAdRequest = new JsonObjectRequest(Request.Method.POST, URL, createParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ADS_RESPONSE_CREATED",response.toString());

                SharedPreferences sharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.shared_pref),Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(ctx.getString(R.string.shared_first_ad),false);
                editor.apply();

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
            createParams.put("CORNER_PLOT",SellerAd.getInstance().cornerPlot.toUpperCase());
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


    public void readAd(){
        String URL = "http://www.wannabuy.in/api/Ads/read_ads.php?id="+FirebaseAuth.getInstance().getCurrentUser().getEmail();

        StringRequest getRequirementRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("READ_RESPONSE", response.toString());
                adDataList = getAdData(response);
                Log.d("TEST_SIZE",""+adDataList.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("READ_RESPONSE", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(getRequirementRequest);
    }

    private List<SellerAd> getAdData(String readResponse) {
        Log.d("RESPONSE_CHECK", readResponse);
        List<SellerAd> tempList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(readResponse);
            JSONArray responseArray = jsonObject.getJSONArray("ads");

            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject object = responseArray.getJSONObject(i);

                SellerAd tempData = new SellerAd();
                tempData.area = object.getString("PROPERTY_LOCATION");
                tempData.size = object.getString("PROPERTY_SIZE");
                tempData.type = object.getString("PROPERTY_TYPE");
                tempData.bhk = object.getString("BHK");
                tempData.floor = object.getString("FLOOR");
                tempData.facing = object.getString("FACING");
                tempData.isNew = object.getString("NEW");
                tempData.furnished = object.getString("ADDITIONAL");
                tempData.budget = object.getString("BUDGET");
                tempData.buyOrRent = object.getString("MODE");

                tempList.add(tempData);
            }
            return tempList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
