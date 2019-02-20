package com.nyxwolves.wannabuy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nyxwolves.wannabuy.Fragments.MyMatchesFragment;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.Matches;
import com.nyxwolves.wannabuy.activities.AdsDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolder> implements CallbackInterface {

    Context ctx;
    JSONArray data = new JSONArray();
    CardView noMatchesCard;

    public MatchesAdapter(Context ctx,CardView noMatchesCard){
        this.ctx = ctx;
        this.noMatchesCard = noMatchesCard;

        CallbackInterface matchesCallback = this;
        Matches matches = new Matches(ctx);
        matches.getMatches(matchesCallback);
    }

    @Override
    public void setData(JSONObject data) {
        try{
            if(data.getString("message").equals("matches not found")) {
                Log.d("MATCHES", "NOT_FOUND");
                noMatchesCard.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            try{
                this.data = data.getJSONArray("matches");
                notifyDataSetChanged();
            }catch(JSONException error){
                Log.d("MATCH_ADAPTER","ERROR");
            }
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
            matchesViewHolder.cityName.setText(jsonObject.getString("PROPERTY_LOCATION"));
            String propType = jsonObject.getString("PROPERTY_TYPE");
            matchesViewHolder.propertyType.setText(propType);
            matchesViewHolder.bhkText.setText(jsonObject.getString("BHK"));
            matchesViewHolder.priceText.setText(jsonObject.getString("BUDGET"));
            matchesViewHolder.landSize.setText(jsonObject.getString("LAND_AREA"));
            matchesViewHolder.builtUpSize.setText(jsonObject.getString("BUILT_UP_AREA"));

            //set image of property
            String imageURL = "http://wannabuy.in/api/images/"+jsonObject.getString("IMAGE");
            Glide.with(ctx).load(imageURL).into(matchesViewHolder.propertyImage);

            //for land
            if (propType.equals(ctx.getString(R.string.residential_land)) ||
                    propType.equals(ctx.getString(R.string.commercial_land)) ||
                    propType.equals(ctx.getString(R.string.industrial_land)) ||
                    propType.equals(ctx.getString(R.string.institutional_land)) ||
                    propType.equals(ctx.getString(R.string.rental_residential_land)) ||
                    propType.equals(ctx.getString(R.string.rental_commercial_land)) ||
                    propType.equals(ctx.getString(R.string.rental_industrial_land)) ||
                    propType.equals(ctx.getString(R.string.rental_industrial_land))) {
                matchesViewHolder.bhkText.setVisibility(View.GONE);
                matchesViewHolder.builtUpSize.setVisibility(View.GONE);
            }
            //for floorspace
            if(propType.equals(ctx.getString(R.string.commercial_floorspace))){
                matchesViewHolder.bhkText.setVisibility(View.GONE);
                matchesViewHolder.landSize.setVisibility(View.GONE);
            }
            //for budget
            if(jsonObject.getInt("BUDGET") >= 100000){

                String displayText = jsonObject.getDouble("BUDGET")/100000+" Lakhs";
                matchesViewHolder.priceText.setText(displayText);
            }else if(jsonObject.getInt("BUDGET") >= 10000000){
                String displayText = jsonObject.getDouble("BUDGET")/10000000+" Crores";
                matchesViewHolder.priceText.setText(displayText);
            }

            matchesViewHolder.bhkText.setText(jsonObject.getString("BHK")+" Bhk");

            matchesViewHolder.landSize.setText(jsonObject.getString("LAND_AREA")+" Sq.Ft");
            matchesViewHolder.builtUpSize.setText(jsonObject.getString("BUILT_UP_AREA")+" Sq.Ft");

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
