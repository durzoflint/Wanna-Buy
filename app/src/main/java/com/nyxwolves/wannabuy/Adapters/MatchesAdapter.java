package com.nyxwolves.wannabuy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.Interfaces.RecyclerInterface;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.Matches;

import org.json.JSONArray;
import org.json.JSONObject;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolder> implements RecyclerInterface{

    Context ctx;
    JSONArray data = new JSONArray();

    public MatchesAdapter(Context ctx){
        this.ctx = ctx;

        RecyclerInterface matchesCallback = this;
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
            JSONObject jsonObject = data.getJSONObject(i);
            matchesViewHolder.areaName.setText(jsonObject.get("PROPERTY_LOCATION").toString());
            matchesViewHolder.cityName.setText(jsonObject.get("PROPERTY_LOCATION").toString());
            matchesViewHolder.bhkText.setText(jsonObject.get("BHK").toString());
            matchesViewHolder.priceText.setText(jsonObject.get("BUDGET").toString());
            matchesViewHolder.landSize.setText(jsonObject.get("LAND_AREA").toString());
            matchesViewHolder.builtUpSize.setText(jsonObject.get("BUILT_UP_AREA").toString());
        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return data.length();
    }
}
