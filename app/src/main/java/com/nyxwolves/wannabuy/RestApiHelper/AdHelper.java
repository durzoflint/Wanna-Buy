package com.nyxwolves.wannabuy.RestApiHelper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.Interfaces.AdInterface;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.Interfaces.ImageRecieved;
import com.nyxwolves.wannabuy.POJO.SellerAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdHelper implements CallbackInterface {

    private Context ctx;
    private JSONObject createParams = new JSONObject();


    public AdHelper(Context ctx) {
        this.ctx = ctx;
    }

    public void createAd(final String type, final AdInterface callback) {
        String URL = "http://www.wannabuy.in/api/Ads/create_ad.php";
        Log.d("CREATED","AD");
        getJson();
        Log.d("CREATED_ADS_JSON", createParams.toString());
        JsonObjectRequest createAdRequest = new JsonObjectRequest(Request.Method.POST, URL, createParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ADS_RESPONSE_CREATED", response.toString());
                callback.adCreated(true, response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AD_ERROR",error.toString());
                callback.adCreated(false, null);
                Toast.makeText(ctx, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
        CustomRequestQueue.getInstance(ctx).addRequest(createAdRequest);
    }

    private void getJson() {

        try {
            createParams.put("USER_ID", FirebaseAuth.getInstance().getCurrentUser().getEmail());
            createParams.put("DOOR_NO", SellerAd.getInstance().adsDoorNo);
            createParams.put("PROPERTY_STREET", SellerAd.getInstance().adsStreet);
            createParams.put("PROPERTY_AREA", SellerAd.getInstance().adsArea);
            createParams.put("PROPERTY_DISTRICT", SellerAd.getInstance().adsDistrict);
            createParams.put("PROPERTY_STATE", SellerAd.getInstance().adsState);
            createParams.put("PINCODE",SellerAd.getInstance().adsPinCode);
            createParams.put("PROPERTY_TYPE", SellerAd.getInstance().adsPropertyType);
            createParams.put("LAND_AREA", SellerAd.getInstance().adsLandArea);
            createParams.put("BUILT_UP_AREA", SellerAd.getInstance().adsBuiltUpArea);
            createParams.put("AGE", SellerAd.getInstance().adsAge);
            createParams.put("AGE_TYPE", SellerAd.getInstance().adsAgeType);
            createParams.put("BHK", SellerAd.getInstance().adsBhk);
            createParams.put("NO_OF_APARTMENTS", SellerAd.getInstance().adsNoOfApartments);
            createParams.put("TOTAL_FLOORS", SellerAd.getInstance().adsTotalFloors);
            createParams.put("FLOOR", SellerAd.getInstance().adsFloor);
            createParams.put("BUDGET", SellerAd.getInstance().adsBudget);
            createParams.put("NEW", SellerAd.getInstance().adsIsNew);
            createParams.put("MODE", SellerAd.getInstance().adsSellOrRent);
            createParams.put("FURNISHED", SellerAd.getInstance().adsFurnished);
            createParams.put("FACING", SellerAd.getInstance().adsFacing);
            createParams.put("APPROVAL", getApprovalList());
            createParams.put("FACILITIES", getFacilitiesList());
            createParams.put("MAINTENANCE", SellerAd.getInstance().adsMaintance);
            createParams.put("COV_CAR_PARKING", SellerAd.getInstance().adsCovCarParking);
            createParams.put("UN_COV_CAR_PARKING", SellerAd.getInstance().adsUnCovParking);
            createParams.put("COV_PARKING_NUM", SellerAd.getInstance().adsCovCarParkingNum);
            createParams.put("UN_COV_PARKING_NUM", SellerAd.getInstance().adsUnCovCarParkingNum);
            createParams.put("ROAD_WIDTH", SellerAd.getInstance().adsRoadWidth);
            createParams.put("ADVANCE_DEPOSIT", SellerAd.getInstance().adsAdvanceDeposit);
            createParams.put("PETS_ALLOWED", SellerAd.getInstance().adsPetsAllowed);
            createParams.put("NEGOTIABLE", SellerAd.getInstance().adsBudgetNegotiable);
            createParams.put("PG_ROOMS", SellerAd.getInstance().noOfRooms);
            createParams.put("PG_PERSON_PER_ROOM", SellerAd.getInstance().personPerRoom);
            createParams.put("PG_WITH_FOOD", SellerAd.getInstance().withFood);
            createParams.put("PG_RENT_MONTH", SellerAd.getInstance().pgRentPerMonth);
            createParams.put("GUEST_PRICE", SellerAd.getInstance().guestHousePrice);
            createParams.put("BOYS_HOSTEL_PRICE", SellerAd.getInstance().boysHostelPrice);
            createParams.put("GIRLS_HOSTEL_PRICE", SellerAd.getInstance().girlsHostelPrice);
            createParams.put("WORKING_MEN_HOSTEL_PRICE", SellerAd.getInstance().workingMenHostelPrice);
            createParams.put("WORKING_WOMEN_HOSTEL_PRICE", SellerAd.getInstance().workingWomenHostelPrice);
            createParams.put("VEG", SellerAd.getInstance().veg);
            createParams.put("NON_VEG",SellerAd.getInstance().nonVeg);
            createParams.put("ROI_VALUE", SellerAd.getInstance().adsRoi);
            createParams.put("ROI_INCREMENT_PERIOD", SellerAd.getInstance().adsRoiIncrementPeriod);
            createParams.put("ROI_INCREMENT_VALUE", SellerAd.getInstance().roiIncrementalValue);
            createParams.put("RENT_START_DATE", SellerAd.getInstance().rentStartDate);
            createParams.put("RENT_END_DATE", SellerAd.getInstance().rentEndDate);
            createParams.put("LATITUDE", SellerAd.getInstance().locationLatitude);
            createParams.put("LONGITUDE", SellerAd.getInstance().locationLongitude);
            createParams.put("BROKERAGE", SellerAd.getInstance().brokerageAmount);
            createParams.put("RENT_DEPOSIT", SellerAd.getInstance().rentDeposit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONArray getFacilitiesList() {
        JSONArray facilitiesList = new JSONArray();
        for (String facilities : SellerAd.getInstance().facilitiesList) {
            facilitiesList.put(facilities);
        }
        return facilitiesList;
    }

    private JSONArray getApprovalList() {
        JSONArray approvalList = new JSONArray();
        for (String approval : SellerAd.getInstance().approvalList) {
            approvalList.put(approval);
        }
        return approvalList;
    }


    public void readAd(String Ad_id, final CallbackInterface callbackInterface) {
        String URL = "http://www.wannabuy.in/api/Ads/read_ad_complete_info.php?AD_ID=" + Ad_id;

        StringRequest getRequirementRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("COMPLETE_AD", response);
                    callbackInterface.setData(new JSONObject(response));
                } catch (JSONException e) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("READ_RESPONSE", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(getRequirementRequest);
    }


    public void getUserAds(final CallbackInterface callbackInterface) {
        String URL = "http://www.wannabuy.in/api/Ads/read_ad_short_info.php?USER_ID=" + FirebaseAuth.getInstance().getCurrentUser().getEmail();

        StringRequest getRequirementRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("USER_AD_RESPONSE", response);
                    callbackInterface.setData(new JSONObject(response));
                } catch (Exception e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("READ_RESPONSE", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(getRequirementRequest);
    }

    public void makeAdVisible(String adId) {
        String URL = "http://www.wannabuy.in/api/Ads/make_ad_visible.php?AD_ID=" + adId;

        StringRequest getRequirementRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("VISIBLE", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("READ_RESPONSE", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(getRequirementRequest);
    }

    //callback from updating user ad status
    @Override
    public void setData(JSONObject data) {
        try {
            Log.d("TEST_JSON", data.toString());
            if (data.getString("message").equals("success")) {
                Toast.makeText(ctx, "Ad is Posted", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void isSuccess(boolean isSuccess) {

    }

    @Override
    public void doesUserExits(boolean isExists) {

    }

    @Override
    public void dealerOrUser(boolean isDealer) {

    }


    public void getAdImage(String adId, final ImageRecieved callback){
        String URL = "http://www.wannabuy.in/api/Ads/get_ad_images.php?AD_ID=" + adId;

        StringRequest getRequirementRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    callback.imageRecieved(new JSONObject(response));
                }catch (Exception e){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("IMAGE_READ_RESPONSE", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(getRequirementRequest);
    }

}
