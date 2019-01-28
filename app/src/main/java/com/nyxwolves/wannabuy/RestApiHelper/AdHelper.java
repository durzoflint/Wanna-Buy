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
import com.nyxwolves.wannabuy.Interfaces.RecyclerInterface;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdHelper{

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
                Toast.makeText(ctx, "Error Occurred", Toast.LENGTH_SHORT).show();
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
            createParams.put("APPROVAL",getApprovalList());
            createParams.put("FACILITIES",getFacilitiesList());
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
            createParams.put("VEG_NON_VEG", SellerAd.getInstance().vegNonVeg.toUpperCase());
            createParams.put("ROI_VALUE", SellerAd.getInstance().adsRoi.toUpperCase());
            createParams.put("ROI_INCREMENT_PERIOD", SellerAd.getInstance().adsRoiIncrementPeriod.toUpperCase());
            createParams.put("ROI_INCREMENT_VALUE",SellerAd.getInstance().roiIncrementalValue);
            createParams.put("RENT_START_DATE",SellerAd.getInstance().rentStartDate);
            createParams.put("RENT_END_DATE",SellerAd.getInstance().rentEndDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONArray getFacilitiesList(){
        JSONArray facilitiesList = new JSONArray();
        for(String facilities : SellerAd.getInstance().facilitiesList){
            facilitiesList.put(facilities);
        }
        return facilitiesList;
    }

    private JSONArray getApprovalList(){
        JSONArray approvalList = new JSONArray();
        for(String approval : SellerAd.getInstance().approvalList){
            approvalList.put(approval);
        }
        return approvalList;
    }


    public void readAd(String Ad_id) {
        String URL = "http://www.wannabuy.in/api/Ads/read_ad_complete_info.php?AD_ID=" + Ad_id;

        StringRequest getRequirementRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("READ_RESPONSE", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(getRequirementRequest);
    }


    public void getUserAds(final RecyclerInterface recyclerInterface){
        String URL = "http://www.wannabuy.in/api/Ads/read_ad_short_info.php?USER_ID="+FirebaseAuth.getInstance().getCurrentUser().getEmail();

        StringRequest getRequirementRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Log.d("USER_AD_RESPONSE",response);
                    recyclerInterface.setData(new JSONObject(response));
                }catch (Exception e){}
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
