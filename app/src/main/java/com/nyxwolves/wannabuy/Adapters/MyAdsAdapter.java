package com.nyxwolves.wannabuy.Adapters;

import android.app.ProgressDialog;
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
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;

import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;
import com.nyxwolves.wannabuy.activities.AdsDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyAdsAdapter extends RecyclerView.Adapter<MyAdsViewHolder> implements CallbackInterface {

    public JSONArray data = new JSONArray();
    Context ctx;
    CardView noAdsCard;
    ProgressDialog progressDialog;

    public MyAdsAdapter(Context ctx, CardView noAdsCard) {
        this.ctx = ctx;
        this.noAdsCard = noAdsCard;

        CallbackInterface callback = this;
        AdHelper helper = new AdHelper(ctx);
        helper.getUserAds(callback);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void setData(JSONObject jsonObject) {
        try {
            if (jsonObject.getString("message").equals(ctx.getString(R.string.no_ads_found))) {
                Log.d("ADS_ADAPTER","NOT_FOUND");
                progressDialog.cancel();
                noAdsCard.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            try {
                progressDialog.cancel();
                data = jsonObject.getJSONArray("ads");
                notifyDataSetChanged();
            } catch (JSONException error) {
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

            //set image of property
            String imageURL = "http://wannabuy.in/api/images/"+jsonObject.getString("IMAGE");
            Glide.with(ctx).load(imageURL).into(myAdsViewHolder.propertyImage);

            //for land
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
            //for floorspace
            if(propType.equals(ctx.getString(R.string.commercial_floorspace))){
                myAdsViewHolder.bhkText.setVisibility(View.GONE);
                myAdsViewHolder.landSize.setVisibility(View.GONE);
            }
            //for budget
            if(jsonObject.getInt("BUDGET") >= 100000){

                String displayText = jsonObject.getDouble("BUDGET")/100000+" Lakhs";
                myAdsViewHolder.priceText.setText(displayText);
            }else if(jsonObject.getInt("BUDGET") >= 10000000){
                String displayText = jsonObject.getDouble("BUDGET")/10000000+" Crores";
                myAdsViewHolder.priceText.setText(displayText);
            }

            myAdsViewHolder.bhkText.setText(jsonObject.getString("BHK")+" Bhk");

            myAdsViewHolder.landSize.setText(jsonObject.getString("LAND_AREA")+" Sq.Ft");
            myAdsViewHolder.builtUpSize.setText(jsonObject.getString("BUILT_UP_AREA")+" Sq.Ft");
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
        } catch (Exception e) {}

    }

    @Override
    public int getItemCount() {
        return data.length();
    }
}
