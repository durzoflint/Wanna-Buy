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

    ProgressDialog progressDialog;

    public RequirementHelper(Context ctx) {
        this.ctx = ctx;
        progressDialog = new ProgressDialog(ctx);
    }

    public void createRequirement() {
        String URL = "http://www.wannabuy.in/api/Requirements/create_requirement.php";
        //showDialog();
        getJson();
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
        });

        CustomRequestQueue.getInstance(ctx).addRequest(createRequest);
    }

    private void getJson() {

        try {
            params.put("user_id", FirebaseAuth.getInstance().getCurrentUser().getEmail().toUpperCase());
            params.put("PROPERTY_LOCATION_ONE", Requirements.getInstance().locationOne.toUpperCase());
            params.put("PROPERTY_LOCATION_TWO", Requirements.getInstance().locationTwo.toUpperCase());
            params.put("PROPERTY_LOCATION_THREE", Requirements.getInstance().locationThree.toUpperCase());
            params.put("PROPERTY_LOCATION_FOUR", Requirements.getInstance().locationFour.toUpperCase());
            params.put("PROPERTY_LOCATION_FIVE", Requirements.getInstance().locationFive.toUpperCase());
            params.put("PROPERTY_TYPE", Requirements.getInstance().type.toUpperCase());
            params.put("PROPERTY_SUB_TYPE", Requirements.getInstance().subType.toUpperCase());
            params.put("PROPERTY_SIZE", Requirements.getInstance().minSize.toUpperCase());
            params.put("BHK", Requirements.getInstance().bhk);
            params.put("FLOOR", Requirements.getInstance().floor.toUpperCase());
            params.put("BUDGET", Requirements.getInstance().budget.toUpperCase());
            params.put("NEW", Requirements.getInstance().isNew.toUpperCase());
            params.put("MODE", Requirements.getInstance().buyorRent.toUpperCase());
            params.put("ADDITIONAL", Requirements.getInstance().furnished.toUpperCase());
            params.put("FACING", Requirements.getInstance().facing.toUpperCase());

        } catch (Exception e) {
            e.printStackTrace();
        }
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

            List<Requirements>tempList = new ArrayList<>();
            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject object = responseArray.getJSONObject(i);
                Requirements tempData = new Requirements();
                tempData.locationOne = object.getString("PROPERTY_LOCATION_ONE");
                tempData.minSize = object.getString("PROPERTY_SIZE");
                tempData.type = object.getString("PROPERTY_SUB_TYPE");
                tempData.bhk = object.getString("BHK");
                tempData.floor = object.getString("FLOOR");
                tempData.facing = object.getString("FACING");
                tempData.isNew = object.getString("NEW");
                tempData.furnished = object.getString("ADDITIONAL");
                tempData.budget = object.getString("BUDGET");
                tempData.buyorRent = object.getString("MODE");

                tempList.add(tempData);
            }
            return tempList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private void showDialog() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(ctx.getString(R.string.LOADING));
        progressDialog.setCancelable(false);
        progressDialog.create();
        progressDialog.show();
    }

    private void closeDialog() {
        progressDialog.dismiss();
    }
}


