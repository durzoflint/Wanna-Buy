package com.nyxwolves.wannabuy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;

import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;
import com.nyxwolves.wannabuy.activities.AdsDetailActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyAdsAdapter extends RecyclerView.Adapter<MyAdsViewHolder> implements CallbackInterface {

    public JSONArray data = new JSONArray();
    Context ctx;

    public MyAdsAdapter(Context ctx) {
        this.ctx = ctx;
        CallbackInterface callback = this;
        AdHelper helper = new AdHelper(ctx);
        helper.getUserAds(callback);
    }

    public void setData(JSONObject jsonObject) {
        try {
            data = jsonObject.getJSONArray("ads");
            notifyDataSetChanged();
        } catch (Exception e) {
        }
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_ads_item, viewGroup, false);

        return new MyAdsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdsViewHolder myAdsViewHolder, int i) {
        try {
            final JSONObject jsonObject = data.getJSONObject(i);
            myAdsViewHolder.cityName.setText(jsonObject.getString("PROPERTY_LOCATION"));
            String propType = jsonObject.getString("PROPERTY_TYPE");
            myAdsViewHolder.propertyType.setText(propType);
            if (propType.equals(ctx.getString(R.string.residential_land)) ||
                    propType.equals(ctx.getString(R.string.commercial_land)) ||
                    propType.equals(ctx.getString(R.string.industrial_land)) ||
                    propType.equals(ctx.getString(R.string.institutional_land)) ||
                    propType.equals(ctx.getString(R.string.rental_residential_land)) ||
                    propType.equals(ctx.getString(R.string.rental_commercial_land)) ||
                    propType.equals(ctx.getString(R.string.rental_industrial_land)) ||
                    propType.equals(ctx.getString(R.string.rental_industrial_land))) {
                myAdsViewHolder.bhkText.setVisibility(View.GONE);
                myAdsViewHolder.builtUpSize.setVisibility(View.GONE);
            }

            myAdsViewHolder.bhkText.setText(jsonObject.getString("BHK"));
            myAdsViewHolder.priceText.setText(jsonObject.getString("BUDGET"));
            myAdsViewHolder.landSize.setText(jsonObject.getString("LAND_AREA"));
            myAdsViewHolder.builtUpSize.setText(jsonObject.getString("BUILT_UP_AREA"));
            myAdsViewHolder.detailsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent(ctx, AdsDetailActivity.class);
                        i.putExtra(ctx.getString(R.string.AD_ID), jsonObject.getString("AD_ID"));
                        ctx.startActivity(i);
                    } catch (Exception e) {
                    }
                }
            });
        } catch (Exception e) {
        }

    }

    @Override
    public int getItemCount() {
        return data.length();
    }
}
