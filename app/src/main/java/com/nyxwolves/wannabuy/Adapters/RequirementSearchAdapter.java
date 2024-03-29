package com.nyxwolves.wannabuy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.RestApiHelper.RequirementHelper;
import com.nyxwolves.wannabuy.activities.AdsActivity;
import com.nyxwolves.wannabuy.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequirementSearchAdapter extends RecyclerView.Adapter<RequirementSearchHolder> implements CallbackInterface {

    private JSONArray data = new JSONArray();
    private Context ctx;
    private CardView noReqCard;

    public RequirementSearchAdapter(Context ctx, CardView noReqCard){
        this.ctx = ctx;
        this.noReqCard = noReqCard;

        CallbackInterface callBack = this;
        RequirementHelper helper = new RequirementHelper(ctx);
        helper.getUserRequirementShortInfo(callBack);
    }
    public RequirementSearchAdapter(Context ctx, String location){
        this.ctx = ctx;
        RequirementHelper searchHelper = new RequirementHelper(ctx);
        CallbackInterface callBack = this;
        searchHelper.getRequirementShortByLocation(location,callBack);
    }

    @NonNull
    @Override
    public RequirementSearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.requirements_search,viewGroup,false);
        return new RequirementSearchHolder(v);
    }

    public void setData(JSONObject jsonObject){
        try {
           if(jsonObject.getString("message").equals(ctx.getString(R.string.no_req_found))){
               noReqCard.setVisibility(View.VISIBLE);
           }
        }catch (Exception e){
            try{
                this.data = jsonObject.getJSONArray("requirements");
                notifyDataSetChanged();
            }catch (Exception error){}
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

    @Override
    public void onBindViewHolder(@NonNull RequirementSearchHolder requirementSearchHolder, int i) {

        try{
            JSONObject singleObject = data.getJSONObject(i);
            requirementSearchHolder.cityName.setText(singleObject.get("CITY").toString());
            requirementSearchHolder.propertyType.setText(singleObject.get("PROPERTY_TYPE").toString());
            requirementSearchHolder.minBudget.setText(singleObject.get("BUDGET_MIN").toString());
            requirementSearchHolder.maxBudget.setText(singleObject.get("BUDGET_MAX").toString());
            requirementSearchHolder.minBudgetUnit.setText(singleObject.get("BUDGET_MIN_UNIT").toString());
            requirementSearchHolder.maxBudgetUnit.setText(singleObject.get("BUDGET_MAX_UNIT").toString());
            requirementSearchHolder.buyOrSell.setText(singleObject.get("BUY_OR_RENT").toString());

            requirementSearchHolder.postAd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ctx.startActivity(new Intent(ctx, AdsActivity.class));
                }
            });
        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return data.length();
    }
}
