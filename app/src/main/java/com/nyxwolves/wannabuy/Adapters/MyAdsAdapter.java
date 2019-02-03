package com.nyxwolves.wannabuy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;

import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyAdsAdapter extends RecyclerView.Adapter<MyAdsViewHolder> implements CallbackInterface {

    public JSONArray data = new JSONArray();
    Context ctx;

    public MyAdsAdapter(Context ctx){
        this.ctx = ctx;
        CallbackInterface callback = this;
        AdHelper helper = new AdHelper(ctx);
        helper.getUserAds(callback);
    }

    public void setData(JSONObject jsonObject){
        try{
            data = jsonObject.getJSONArray("ads");
            notifyDataSetChanged();
        }catch(Exception e){}
    }

    @Override
    public void isSuccess(boolean isSuccess) {

    }

    @Override
    public void doesUserExits(boolean isExists) {

    }

    @NonNull
    @Override
    public MyAdsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_ads_item,viewGroup,false);

        return new MyAdsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdsViewHolder myAdsViewHolder, int i) {
        try{
            JSONObject jsonObject = data.getJSONObject(i);
            myAdsViewHolder.areaName.setText(jsonObject.get("PROPERTY_LOCATION").toString());
            myAdsViewHolder.cityName.setText(jsonObject.get("PROPERTY_TYPE").toString());
            myAdsViewHolder.bhkText.setText(jsonObject.get("BHK").toString());
            myAdsViewHolder.priceText.setText(jsonObject.get("BUDGET").toString());
            myAdsViewHolder.landSize.setText(jsonObject.get("LAND_AREA").toString());
            myAdsViewHolder.builtUpSize.setText(jsonObject.get("BUILT_UP_AREA").toString());
        }catch (Exception e){}

    }

    @Override
    public int getItemCount() {
        return data.length();
    }
}
