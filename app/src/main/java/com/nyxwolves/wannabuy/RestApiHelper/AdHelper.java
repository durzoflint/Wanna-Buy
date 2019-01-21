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
import java.util.HashMap;
import java.util.List;

public class AdHelper {

    private Context ctx;
    private JSONObject createParams = new JSONObject();
    public static List<SellerAd> adDataList = new ArrayList<>();

    public AdHelper(Context ctx) {
        this.ctx = ctx;
    }

    public void createAd() {
        String URL = "http://www.wannabuy.in/api/Ads/create_ad.php";
        getJson();
        //Log.d("ADS_JSON",new JSONObject(createParams).toString());
        Log.d("ADS_JSON",createParams.toString());
        JsonObjectRequest createAdRequest = new JsonObjectRequest(Request.Method.POST, URL, createParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ADS_RESPONSE_CREATED", response.toString());

                Toast.makeText(ctx, "Ad Posted", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.shared_pref), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(ctx.getString(R.string.shared_first_ad), false);
                editor.apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ADS_ERROR_CREATED", error.toString());
            }
        });
        CustomRequestQueue.getInstance(ctx).addRequest(createAdRequest);
    }

    private void getJson() {

        try {
            createParams.put("USER_ID", FirebaseAuth.getInstance().getCurrentUser().getEmail());
            createParams.put("DOOR_NO", SellerAd.getInstance().adsDoorNo.toUpperCase());
            createParams.put("PROPERTY_LOCATION", SellerAd.getInstance().adsLocation.toUpperCase());
            createParams.put("PROPERTY_TYPE", SellerAd.getInstance().adsPropertyType.toUpperCase());
            createParams.put("PROPERTY_ADDRESS", SellerAd.getInstance().adsPropertyAddress.toUpperCase());
            createParams.put("LAND_AREA", SellerAd.getInstance().adsLandArea.toUpperCase());
            createParams.put("BUILT_UP_AREA", SellerAd.getInstance().adsBuiltUpArea.toUpperCase());
            createParams.put("AGE", SellerAd.getInstance().adsAge.toUpperCase());
            createParams.put("BHK", SellerAd.getInstance().adsBhk.toUpperCase());
            createParams.put("NO_OF_APARTMENTS", SellerAd.getInstance().adsNoOfApartments.toUpperCase());
            createParams.put("TOTAL_FLOORS", SellerAd.getInstance().adsTotalFloors.toUpperCase());
            createParams.put("FLOOR", SellerAd.getInstance().adsFloor.toUpperCase());
            createParams.put("BUDGET", SellerAd.getInstance().adsBudget);
            createParams.put("NEW", SellerAd.getInstance().adsIsNew.toUpperCase());
            createParams.put("MODE", SellerAd.getInstance().adsSellOrRent.toUpperCase());
            createParams.put("FURNISHED", SellerAd.getInstance().adsFurnished.toUpperCase());
            createParams.put("FACING", SellerAd.getInstance().adsFacing.toUpperCase());
            createParams.put("CDMA_APPROVED", SellerAd.getInstance().adsCdmaApproved.toUpperCase());
            createParams.put("DTCP_APPROVED", SellerAd.getInstance().adsDtcpApproved.toUpperCase());
            createParams.put("CORP_APPROVED", SellerAd.getInstance().adsCorpApproved.toUpperCase());
            createParams.put("PANCH_APPROVED", SellerAd.getInstance().adsPanchApproved.toUpperCase());
            createParams.put("COMM_APPROVED", SellerAd.getInstance().adsCommApproved.toUpperCase());
            createParams.put("INDUS_APPROVED", SellerAd.getInstance().adsIndusApproved.toUpperCase());
            createParams.put("RERA_APPROVED", SellerAd.getInstance().adsReraApproved.toUpperCase());
            createParams.put("GYM", SellerAd.getInstance().adsGym.toUpperCase());
            createParams.put("POWER_BACKUP", SellerAd.getInstance().adsPowerBackup.toUpperCase());
            createParams.put("SECURITY_GUARD", SellerAd.getInstance().adsSecurityGuard.toUpperCase());
            createParams.put("LIFT", SellerAd.getInstance().adsLift.toUpperCase());
            createParams.put("SWIMMING_POOL", SellerAd.getInstance().adsSwimmingPool.toUpperCase());
            createParams.put("CAFETRIA", SellerAd.getInstance().adsCafetria.toUpperCase());
            createParams.put("GARDEN", SellerAd.getInstance().adsGarden.toUpperCase());
            createParams.put("WATER", SellerAd.getInstance().adsWater.toUpperCase());
            createParams.put("PLAY_AREA", SellerAd.getInstance().adsPlayArea.toUpperCase());
            createParams.put("METRO_WATER", SellerAd.getInstance().adsMetroWater.toUpperCase());
            createParams.put("DRAINAGE_CONNECTION", SellerAd.getInstance().adsDrainageConnection.toUpperCase());
            createParams.put("MAINTENANCE", SellerAd.getInstance().adsMaintance.toUpperCase());
            createParams.put("COV_CAR_PARKING", SellerAd.getInstance().adsCovCarParking.toUpperCase());
            createParams.put("UN_COV_CAR_PARKING", SellerAd.getInstance().adsUnCovParking.toUpperCase());
            createParams.put("COV_PARKING_NUM", SellerAd.getInstance().adsCovCarParkingNum.toUpperCase());
            createParams.put("UN_COV_PARKING_NUM", SellerAd.getInstance().adsUnCovCarParkingNum.toUpperCase());
            createParams.put("ROAD_WIDTH", SellerAd.getInstance().adsRoadWidth.toUpperCase());
            createParams.put("ADVANCE_DEPOSIT",SellerAd.getInstance().adsAdvanceDeposit.toUpperCase());
            createParams.put("PETS_ALLOWED", SellerAd.getInstance().adsPetsAllowed.toUpperCase());
            createParams.put("NEGOTIABLE", SellerAd.getInstance().adsBudgetNegotiable.toUpperCase());
            createParams.put("PG_ROOMS", SellerAd.getInstance().noOfRooms.toUpperCase());
            createParams.put("PG_PERSON_PER_ROOM", SellerAd.getInstance().personPerRoom.toUpperCase());
            createParams.put("PG_WITH_FOOD", SellerAd.getInstance().withFood.toUpperCase());
            createParams.put("PG_RENT_MONTH", SellerAd.getInstance().pgRentPerMonth.toUpperCase());
            createParams.put("GUEST_PRICE", SellerAd.getInstance().guestHousePrice.toUpperCase());
            createParams.put("BOYS_HOSTEL_PRICE", SellerAd.getInstance().boysHostelPrice.toUpperCase());
            createParams.put("GIRLS_HOSTEL_PRICE", SellerAd.getInstance().girlsHostelPrice.toUpperCase());
            createParams.put("WORKING_MEN_HOSTEL_PRICE", SellerAd.getInstance().workingMenHostelPrice.toUpperCase());
            createParams.put("WORKING_WOMEN_HOSTEL_PRICE", SellerAd.getInstance().workingWomenHostelPrice.toUpperCase());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readAd() {
        String URL = "http://www.wannabuy.in/api/Ads/read_ads.php?id=" + FirebaseAuth.getInstance().getCurrentUser().getEmail();

        StringRequest getRequirementRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("READ_RESPONSE", response.toString());
                adDataList = getAdData(response);
                Log.d("TEST_SIZE", "" + adDataList.size());
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
                /*tempData.area = object.getString("PROPERTY_LOCATION");
                tempData.size = object.getString("PROPERTY_SIZE");
                tempData.type = object.getString("PROPERTY_TYPE");
                tempData.bhk = object.getString("BHK");
                tempData.floor = object.getString("FLOOR");
                tempData.facing = object.getString("FACING");
                tempData.isNew = object.getString("NEW");
                tempData.furnished = object.getString("ADDITIONAL");
                tempData.budget = object.getString("BUDGET");
                tempData.buyOrRent = object.getString("MODE");*/

                tempList.add(tempData);
            }
            return tempList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
