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

import java.util.ArrayList;
import java.util.List;

public class RequirementHelper implements CallbackInterface{
    private Context ctx;
    private JSONObject params = new JSONObject();
    public static List<Requirements> reqDataList = new ArrayList<>();

    public RequirementHelper(Context ctx) {
        this.ctx = ctx;
    }

    public void createRequirement(final String type,final String buyOrRent) {
        String URL = "http://www.wannabuy.in/api/Requirements/create_requirement.php";
        getJson();

        Log.d("REQ_JSON", params.toString());
        JsonObjectRequest createRequest = new JsonObjectRequest(Request.Method.POST, URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("REQ_RESPONSE_CREATED", response.toString());
                //closeDialog();
                SharedPreferences sharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.shared_pref), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(ctx.getString(R.string.shared_first_req), false);
                editor.apply();

                UserPaymentCheck userPaymentCheck = new UserPaymentCheck(ctx);
                CallbackInterface callbackInterface = RequirementHelper.this;
                    if(buyOrRent.equals(ctx.getString(R.string.pay_buy_requirement))){
                        if(type.equals(ctx.getString(R.string.dealer))){
                            userPaymentCheck.updateUserStatus(UserPaymentCheck.DECREASE_DEALER_BUY_REQ,callbackInterface);
                        }else{
                            userPaymentCheck.updateUserStatus(UserPaymentCheck.UPDATE_BUY_REQ,callbackInterface);
                        }
                    }else{
                        if(type.equals(ctx.getString(R.string.dealer))){
                            userPaymentCheck.updateUserStatus(UserPaymentCheck.DECREASE_DEALER_RENT_REQ,callbackInterface);
                        }else{
                            userPaymentCheck.updateUserStatus(UserPaymentCheck.UPDATE_RENT_REQ,callbackInterface);
                        }
                    }

                Requirements.getInstance().clearState();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //closeDialog();
                Toast.makeText(ctx, "Error Occured.Please try again", Toast.LENGTH_SHORT).show();
                Requirements.getInstance().clearState();
                Log.d("REQ_ERROR_CREATED", error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(createRequest);
    }

    private void getJson() {

        try {
            params.put("USER_ID", FirebaseAuth.getInstance().getCurrentUser().getEmail());
            params.put("REQ_NAME", Requirements.getInstance().reqName);
            params.put("STATE", Requirements.getInstance().state);
            params.put("CITY", Requirements.getInstance().city);
            params.put("PROPERTY_LOCATION_ONE", Requirements.getInstance().locationOne);
            params.put("PROPERTY_LOCATION_TWO", Requirements.getInstance().locationTwo);
            params.put("PROPERTY_LOCATION_THREE", Requirements.getInstance().locationThree);
            params.put("PROPERTY_LOCATION_FOUR", Requirements.getInstance().locationFour);
            params.put("PROPERTY_LOCATION_FIVE", Requirements.getInstance().locationFive);
            params.put("PROPERTY_TYPE", Requirements.getInstance().subType);
            params.put("LAND_SIZE_MIN", Requirements.getInstance().minSizeLand);
            params.put("LAND_SIZE_MAX", Requirements.getInstance().maxSizeLand);
            params.put("BUILT_UP_MIN", Requirements.getInstance().minSizeBuilding);
            params.put("BUILT_UP_MAX", Requirements.getInstance().maxSizeBuilding);
            params.put("BHK", getBhkList());
            params.put("FLOOR", getFloorJson());
            params.put("BUDGET_MIN", Requirements.getInstance().minBudget);
            params.put("BUDGET_MAX", Requirements.getInstance().maxBudget);
            params.put("BUDGET_MAX_UNIT", Requirements.getInstance().maxBudgetUnit);
            params.put("BUDGET_MIN_UNIT", Requirements.getInstance().minBudgetUnit);
            params.put("AGE_MIN", Requirements.getInstance().minAge);
            params.put("AGE_MAX", Requirements.getInstance().maxAge);
            params.put("NEW_RESALE", Requirements.getInstance().isNew);
            params.put("BUY_OR_RENT", Requirements.getInstance().buyorRent);
            params.put("FURNISHED", Requirements.getInstance().furnished);
            params.put("FACING", getFacingList());
            params.put("APPROVAL", getApprovalList());
            params.put("FACILITIES", getFacilitiesList());
            params.put("COV_CAR_PARKING", Requirements.getInstance().isCovparking);
            params.put("UN_COV_CAR_PARKING", Requirements.getInstance().isUnCovParking);
            params.put("COV_PARKING_NUM", Requirements.getInstance().noOfCovParking);
            params.put("UN_COV_PARKING_NUM", Requirements.getInstance().noOfUnCovParking);
            params.put("PG_TYPE", getPGList());
            params.put("PETS_ALLOWED", Requirements.getInstance().petsAllowed);
            params.put("ROAD_WIDTH_MIN", Requirements.getInstance().minRoadWidth);
            params.put("ROAD_WIDTH_MAX", Requirements.getInstance().maxRoadWidth);
            params.put("MAINTENANCE_FEE", Requirements.getInstance().maintenanceFee);
            params.put("VEG", Requirements.getInstance().isVeg);
            params.put("NON_VEG", Requirements.getInstance().isNonVeg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONArray getFloorJson() {
        JSONArray floorArray = new JSONArray();
        for (String floorNum : Requirements.getInstance().floorList) {
            floorArray.put(floorNum);
        }
        return floorArray;
    }

    private JSONArray getBhkList() {
        JSONArray bhkArray = new JSONArray();
        for (String bhk : Requirements.getInstance().bhkList) {
            bhkArray.put(bhk);
        }
        return bhkArray;
    }

    private JSONArray getFacilitiesList() {
        JSONArray facilitiesArray = new JSONArray();
        for (String facility : Requirements.getInstance().facilitiesList) {
            facilitiesArray.put(facility);
        }
        return facilitiesArray;
    }

    private JSONArray getApprovalList() {
        JSONArray approvalArray = new JSONArray();
        for (String approval : Requirements.getInstance().approvalList) {
            approvalArray.put(approval);
        }
        return approvalArray;
    }

    private JSONArray getFacingList() {
        JSONArray facingarray = new JSONArray();
        for (String direction : Requirements.getInstance().facingList) {
            facingarray.put(direction);
        }
        return facingarray;
    }

    private JSONArray getPGList() {
        JSONArray pgList = new JSONArray();
        for (String type : Requirements.getInstance().pgTypeList) {
            pgList.put(type);
        }
        return pgList;
    }


    public void getUserRequirementShortInfo(final CallbackInterface callBack) {

        //JSON STRUCTURE
        //{"requirements":[{"USER_ID":"SIBINEHRU99@GMAIL.COM","REQ_NAME":"COMMERCIAL REQ","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_TYPE":"COMMERCIAL_INDEPENDENT","BUDGET_MIN":"42","BUDGET_MAX":"66","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX_UNIT":"NOT_SET","BUY_OR_RENT":"BUY","CREATED_AT":"2019-01-26 19:45:43"},{"USER_ID":"SIBINEHRU99@GMAIL.COM","REQ_NAME":"COMMERCIAL REQ2","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_TYPE":"COMMERCIAL_INDEPENDENT","BUDGET_MIN":"42","BUDGET_MAX":"66","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX_UNIT":"NOT_SET","BUY_OR_RENT":"BUY","CREATED_AT":"2019-01-26 19:47:15"},{"USER_ID":"SIBINEHRU99@GMAIL.COM","REQ_NAME":"COMMERCIAL REQ3","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_TYPE":"COMMERCIAL_INDEPENDENT","BUDGET_MIN":"42","BUDGET_MAX":"66","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX_UNIT":"NOT_SET","BUY_OR_RENT":"BUY","CREATED_AT":"2019-01-26 19:58:41"},{"USER_ID":"SIBINEHRU99@GMAIL.COM","REQ_NAME":"COMMERCIAL REQ4","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_TYPE":"COMMERCIAL_INDEPENDENT","BUDGET_MIN":"42","BUDGET_MAX":"66","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX_UNIT":"NOT_SET","BUY_OR_RENT":"BUY","CREATED_AT":"2019-01-26 20:01:12"},{"USER_ID":"SIBINEHRU99@GMAIL.COM","REQ_NAME":"GOOGLE","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_TYPE":"RESIDENTIAL_INDEPENDENT","BUDGET_MIN":"39","BUDGET_MAX":"80","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX_UNIT":"NOT_SET","BUY_OR_RENT":"BUY","CREATED_AT":"2019-01-26 20:04:39"},{"USER_ID":"SIBINEHRU99@GMAIL.COM","REQ_NAME":"facebook","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_TYPE":"RESIDENTIAL_INDEPENDENT","BUDGET_MIN":"39","BUDGET_MAX":"80","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX_UNIT":"NOT_SET","BUY_OR_RENT":"BUY","CREATED_AT":"2019-01-26 20:07:31"},{"USER_ID":"SIBINEHRU99@GMAIL.COM","REQ_NAME":"amazon","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_TYPE":"RESIDENTIAL_INDEPENDENT","BUDGET_MIN":"39","BUDGET_MAX":"80","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX_UNIT":"NOT_SET","BUY_OR_RENT":"BUY","CREATED_AT":"2019-01-26 20:08:46"},{"USER_ID":"SIBINEHRU99@GMAIL.COM","REQ_NAME":"something","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_TYPE":"RESIDENTIAL_INDEPENDENT","BUDGET_MIN":"39","BUDGET_MAX":"80","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX_UNIT":"NOT_SET","BUY_OR_RENT":"BUY","CREATED_AT":"2019-01-26 20:09:43"},{"USER_ID":"SIBINEHRU99@GMAIL.COM","REQ_NAME":"TEST","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_TYPE":"RESIDENTIAL_LAND","BUDGET_MIN":"32","BUDGET_MAX":"54","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX_UNIT":"NOT_SET","BUY_OR_RENT":"BUY","CREATED_AT":"2019-01-26 20:58:20"}]}

        String URL = "http://www.wannabuy.in/api/Requirements/requirement_short_info.php?USER_ID=" + FirebaseAuth.getInstance().getCurrentUser().getEmail();

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

    public void getRequirementCompleteInfo(String reqId) {

        //JSON STRUCTURE
        //{"REQ_NAME":"COMMERCIAL REQ","USER_ID":"SIBINEHRU99@GMAIL.COM","STATE":"NOT_SET","CITY":"NOT_SET","PROPERTY_LOCATION_QUERY":null,"PROPERTY_LOCATION_ONE":"NOT_SET","PROPERTY_LOCATION_TWO":"NOT_SET","PROPERTY_LOCATION_THREE":"NOT_SET","PROPERTY_LOCATION_FOUR":"NOT_SET","PROPERTY_LOCATION_FIVE":"NOT_SET","PROPERTY_SIZE_MIN":null,"PROPERTY_SIZE_MAX":null,"AGE_MIN":null,"AGE_MAX":null,"PROPERTY_TYPE":"COMMERCIAL_INDEPENDENT","PROPERTY_SUB_TYPE":null,"FURNISHED":null,"BHK":[],"FLOOR":[],"REST_ROOM_NUM":null,"FACING":null,"NEW":null,"RESALE":null,"BUDGET_MIN":"42","BUDGET_MIN_UNIT":"NOT_SET","BUDGET_MAX":"66","BUDGET_MAX_UNIT":"NOT_SET","APPROVAL":[],"BUY_OR_RENT":null,"FACILITIES":[],"VEG":null,"NON_VEG":null,"DRAINAGE_CONNECTION":null,"COV_PARKING":null,"UN_COV_PARKING":null,"COV_PARKING_NUM":null,"UN_COV_PARKING_NUM":null,"PG_TYPE":[],"ROAD_WIDTH_MIN":null,"ROAD_WIDTH_MAX":null,"PETS_ALLOWED":null,"QUERY":null,"MAINTENANCE_FEE":null,"BUT_OR_RENT":"BUY","CREATED_AT":"2019-01-26 19:45:43","DIRECTIONS":[]}

        String URL = "http://www.wannabuy.in/api/Requirements/requirement_complete_info.php?REQ_ID=" + reqId;

        StringRequest getRequirementRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("JSON_RESPONSE", response);
                //reqDataList = getReqData(response);
                //closeDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("READ_RESPONSE", error.toString());
                //closeDialog();
            }
        });

        CustomRequestQueue.getInstance(ctx).addRequest(getRequirementRequest);
    }

    public void getRequirementShortByLocation(String location, final CallbackInterface callbackInterface){
        String URL = "http://www.wannabuy.in/api/Requirements/requirement_location.php?LOCATION=" + location;

        StringRequest getRequirementRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("JSON_RESPONSE", response);
                try{
                    JSONObject jsonResult = new JSONObject(response);
                    callbackInterface.setData(jsonResult);

                }catch (Exception e){
                    Toast.makeText(ctx, "Please Try Again", Toast.LENGTH_SHORT).show();
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



    //callback from updating user status
    @Override
    public void setData(JSONObject data) {
        try{
            if(data.getString("message").equals("success")){
                Toast.makeText(ctx, "Requirement is Posted", Toast.LENGTH_SHORT).show();
                Log.d("REQ_TEST","SUCC");
            }
        }catch (Exception e){
            Toast.makeText(ctx, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void isSuccess(boolean isSuccess) {

    }

    @Override
    public void doesUserExits(boolean isExists) {

    }
}

