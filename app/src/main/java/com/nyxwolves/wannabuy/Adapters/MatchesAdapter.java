package com.nyxwolves.wannabuy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.Matches;
import com.nyxwolves.wannabuy.activities.AdsDetailActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolder> implements CallbackInterface {

    Context ctx;
    JSONArray data = new JSONArray();

    public MatchesAdapter(Context ctx){
        this.ctx = ctx;

        CallbackInterface matchesCallback = this;
        Matches matches = new Matches(ctx);
        matches.getMatches(matchesCallback);
    }

    @Override
    public void setData(JSONObject data) {
        try{
            this.data = data.getJSONArray("matches");
            notifyDataSetChanged();
        }catch (Exception e){}

    }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_ads_item,viewGroup,false);

        return new MatchesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder matchesViewHolder, int i) {
        try{
            final JSONObject jsonObject = data.getJSONObject(i);
            matchesViewHolder.areaName.setText(jsonObject.getString("PROPERTY_LOCATION"));
            matchesViewHolder.cityName.setText(jsonObject.getString("PROPERTY_TYPE"));
            matchesViewHolder.bhkText.setText(jsonObject.getString("BHK"));
            matchesViewHolder.priceText.setText(jsonObject.getString("BUDGET"));
            matchesViewHolder.landSize.setText(jsonObject.getString("LAND_AREA"));
            matchesViewHolder.builtUpSize.setText(jsonObject.getString("BUILT_UP_AREA"));

            final String AD_ID = jsonObject.getString("AD_ID");
            matchesViewHolder.detailsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailedAd = new Intent(ctx, AdsDetailActivity.class);
                    detailedAd.putExtra(ctx.getString(R.string.AD_ID),AD_ID);
                    ctx.startActivity(detailedAd);
                }
            });
        }catch (Exception e){}

    }

    @Override
    public int getItemCount() {
        return data.length();
    }
}
