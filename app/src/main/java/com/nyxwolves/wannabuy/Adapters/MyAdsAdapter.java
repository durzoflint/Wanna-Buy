package com.nyxwolves.wannabuy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;

import java.util.ArrayList;
import java.util.List;

public class MyAdsAdapter extends RecyclerView.Adapter<MyAdsViewHolder> {

    private List<SellerAd> dataList;
    Context ctx;

    public MyAdsAdapter(Context ctx){
        this.ctx = ctx;
    }

    public void setData(List<SellerAd> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyAdsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_ads_item,viewGroup,false);

        return new MyAdsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdsViewHolder myAdsViewHolder, int i) {
        SellerAd obj = dataList.get(i);
        myAdsViewHolder.areaName.setText(obj.area);
        myAdsViewHolder.cityName.setText("Chennai");
        myAdsViewHolder.priceText.setText("1000000");
        myAdsViewHolder.sizeText.setText(obj.size);
        myAdsViewHolder.bhkText.setText(obj.bhk+" BHK");
        Log.d("RECYCLE",""+i);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}