package com.nyxwolves.wannabuy.RestApiHelper;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequirementHelper {
    private Context ctx;
    private JSONObject params = new JSONObject();
    public static List<Requirements> reqDataList = new ArrayList<>();


    public RequirementHelper(Context ctx) {
        this.ctx = ctx;
    }

    public void createRequirement() {
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

                Toast.makeText(ctx, "Requirement is Posted", Toast.LENGTH_SHORT).show();
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
            params.put("USER_ID",  FirebaseAuth.getInstance().getCurrentUser().getEmail().toUpperCase());
            params.put("REQ_NAME",  Requirements.getInstance().reqName.toUpperCase());
            params.put("STATE",  Requirements.getInstance().state.toUpperCase());
            params.put("CITY",  Requirements.getInstance().city.toUpperCase());
            params.put("PROPERTY_LOCATION_ONE",  Requirements.getInstance().locationOne.toUpperCase());
            params.put("PROPERTY_LOCATION_TWO",  Requirements.getInstance().locationTwo.toUpperCase());
            params.put("PROPERTY_LOCATION_THREE",  Requirements.getInstance().locationThree.toUpperCase());
            params.put("PROPERTY_LOCATION_FOUR",  Requirements.getInstance().locationFour.toUpperCase());
            params.put("PROPERTY_LOCATION_FIVE",  Requirements.getInstance().locationFive.toUpperCase());
            params.put("PROPERTY_TYPE",  Requirements.getInstance().subType.toUpperCase());
            params.put("PROPERTY_SIZE_LAND_MIN",  Requirements.getInstance().minSizeLand.toUpperCase());
            params.put("PROPERTY_SIZE_LAND_MAX",  Requirements.getInstance().maxSizeLand.toUpperCase());
            params.put("PROPERTY_SIZE_BUILT_UP_MIN",  Requirements.getInstance().minSizeBuilding.toUpperCase());
            params.put("PROPERTY_SIZE_BUILT_UP_MAX",  Requirements.getInstance().maxSizeBuilding.toUpperCase());
            params.put("BHK",getBhkList());
            params.put("FLOOR",getFloorJson());
            params.put("BUDGET_MIN",  Requirements.getInstance().minBudget.toUpperCase());
            params.put("BUDGET_MAX",  Requirements.getInstance().maxBudget.toUpperCase());
            params.put("BUDGET_MAX_UNIT",  Requirements.getInstance().maxBudgetUnit.toUpperCase());
            params.put("BUDGET_MIN_UNIT",  Requirements.getInstance().minBudgetUnit.toUpperCase());
            params.put("AGE_MIN",  Requirements.getInstance().minAge.toUpperCase());
            params.put("AGE_MAX",  Requirements.getInstance().maxAge.toUpperCase());
            params.put("NEW_RESALE",  Requirements.getInstance().isNew.toUpperCase());
            params.put("BUY_OR_RENT",  Requirements.getInstance().buyorRent.toUpperCase());
            params.put("FURNISHED",  Requirements.getInstance().furnished.toUpperCase());
            params.put("FACING_EAST",  Requirements.getInstance().facingEast.toUpperCase());
            params.put("FACING_WEST",  Requirements.getInstance().facingWest.toUpperCase());
            params.put("FACING_SOUTH",  Requirements.getInstance().facingSouth.toUpperCase());
            params.put("FACING_NORTH",  Requirements.getInstance().facingNorth.toUpperCase());
            params.put("CDMA_APPROVED",  Requirements.getInstance().cdmaApproved.toUpperCase());
            params.put("DTCP_APPROVED",  Requirements.getInstance().dtcpApproved.toUpperCase());
            params.put("CORP_APPROVED",  Requirements.getInstance().corporationApproved.toUpperCase());
            params.put("PANCH_APPROVED",  Requirements.getInstance().panchayatApproved.toUpperCase());
            params.put("COMM_APPROVED",  Requirements.getInstance().commercialApproved.toUpperCase());
            params.put("INDUS_APPROVED",  Requirements.getInstance().industrialApproved.toUpperCase());
            params.put("RERA_APPROVED",  Requirements.getInstance().reraApproved.toUpperCase());
            params.put("GYM",  Requirements.getInstance().gym.toUpperCase());
            params.put("POWER_BACKUP",  Requirements.getInstance().powerBackup.toUpperCase());
            params.put("SECURITY_GUARD",  Requirements.getInstance().securityGuard.toUpperCase());
            params.put("LIFT",  Requirements.getInstance().lift.toUpperCase());
            params.put("SWIMMING_POOL",  Requirements.getInstance().swimmingPool.toUpperCase());
            params.put("CAFETERIA",  Requirements.getInstance().cafetria.toUpperCase());
            params.put("GARDEN",  Requirements.getInstance().garden.toUpperCase());
            params.put("WATER",  Requirements.getInstance().water.toUpperCase());
            params.put("PLAY_AREA",  Requirements.getInstance().playArea.toUpperCase());
            params.put("GROUND_WATER",  Requirements.getInstance().groundWater.toUpperCase());
            params.put("CORP_WATER",  Requirements.getInstance().corporationWater.toUpperCase());
            params.put("DRAINAGE_CONNECTION",  Requirements.getInstance().drainageConnection.toUpperCase());
            params.put("COV_CAR_PARKING",  Requirements.getInstance().isCovparking.toUpperCase());
            params.put("UN_COV_CAR_PARKING",  Requirements.getInstance().isUnCovParking.toUpperCase());
            params.put("COV_PARKING_NUM",  Requirements.getInstance().noOfCovParking.toUpperCase());
            params.put("UN_COV_PARKING_NUM",  Requirements.getInstance().noOfUnCovParking.toUpperCase());
            params.put("PG_RENT_BOYS",  Requirements.getInstance().pgRentBoys.toUpperCase());
            params.put("PG_RENT_GIRLS",  Requirements.getInstance().pgRentGirls.toUpperCase());
            params.put("PG_RENT_SHORT_STAY",  Requirements.getInstance().pgRentShortStay.toUpperCase());
            params.put("PG_RENT_FAMILY",  Requirements.getInstance().pgRentFamily.toUpperCase());
            params.put("PETS_ALLOWED",  Requirements.getInstance().petsAllowed.toUpperCase());
            params.put("ROAD_WIDTH_MIN",Requirements.getInstance().minRoadWidth.toUpperCase());
            params.put("ROAD_WIDTH_MAX",Requirements.getInstance().maxRoadWidth.toUpperCase());
            params.put("MAINTENANCE_FEE",Requirements.getInstance().maintenanceFee.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONArray getFloorJson(){
        JSONArray floorArray = new JSONArray();
        for(String floorNum : Requirements.getInstance().floorList){
            floorArray.put(floorNum);
        }
        return floorArray;
    }

    private JSONArray getBhkList(){
        JSONArray bhkArray = new JSONArray();
        for(String bhk : Requirements.getInstance().bhkList){
            bhkArray.put(bhk);
        }
        return bhkArray;
    }

    public void getRequirement(String option, String query) {
        String URL = "http://www.wannabuy.in/api/Requirements/read_requirement.php";
        //showDialog();
        if (option.equals(ctx.getString(R.string.USER_ID_QUERY))) {
            URL = URL + "?id=" + query;
        } else if (option.equals(ctx.getString(R.string.LOCATION_QUERY))) {
            URL = URL + "?location=" + query;
        } else if (option.equals(ctx.getString(R.string.BHK_QUERY))) {
            URL = URL + "?bhk=" + query;
        } else if (option.equals(ctx.getString(R.string.TYPE_QUERY))) {
            URL = URL + "?type=" + query;
        } else if (option.equals(ctx.getString(R.string.BUDGET_QUERY))) {
            URL = URL + "?budget=" + query;
        } else if (option.equals(ctx.getString(R.string.ANY_QUERY))) {
            URL = URL + "?query=" + query;
        }

        StringRequest getRequirementRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("READ_RESPONSE", response.toString());
                reqDataList = getReqData(response);
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

    private List<Requirements> getReqData(String readResponse) {
        Log.d("RESPONSE_CHECK", readResponse);
        try {
            JSONObject jsonObject = new JSONObject(readResponse);
            JSONArray responseArray = jsonObject.getJSONArray("requirements");

            List<Requirements> tempList = new ArrayList<>();
            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject object = responseArray.getJSONObject(i);
                Requirements tempData = new Requirements();
                tempData.locationOne = object.getString("PROPERTY_LOCATION_ONE");
                tempData.minSizeLand = object.getString("PROPERTY_SIZE");
                tempData.type = object.getString("PROPERTY_SUB_TYPE");
                //tempData.bhk = object.getString("BHK");
                //tempData.floor = object.getString("FLOOR");
                //tempData.facing = object.getString("FACING");
                tempData.isNew = object.getString("NEW");
                tempData.furnished = object.getString("ADDITIONAL");
                //tempData.budget = object.getString("BUDGET");
                tempData.buyorRent = object.getString("MODE");

                tempList.add(tempData);
            }
            return tempList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
    /*getJson();
        Log.d("REQ_JSON",params.toString());
    JsonObjectRequest createRequest = new JsonObjectRequest(Request.Method.POST, URL, params, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d("REQ_RESPONSE_CREATED", response.toString());
            //closeDialog();
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.shared_pref),Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(ctx.getString(R.string.shared_first_req),false);
            editor.apply();

            Toast.makeText(ctx, "Requirement is Posted", Toast.LENGTH_SHORT).show();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            //closeDialog();
            Log.d("REQ_ERROR_CREATED", error.toString());
        }
    });*/


