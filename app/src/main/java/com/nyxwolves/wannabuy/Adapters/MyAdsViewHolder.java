package com.nyxwolves.wannabuy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdsViewHolder extends RecyclerView.ViewHolder {

    CircleImageView propertyImage;
    TextView areaName,cityName,bhkText,priceText,landSize,builtUpSize;
    Button detailsBtn;

    public MyAdsViewHolder(@NonNull View itemView) {
        super(itemView);

        propertyImage = itemView.findViewById(R.id.property_image);
        areaName = itemView.findViewById(R.id.area_name);
        cityName = itemView.findViewById(R.id.city_name);
        bhkText = itemView.findViewById(R.id.ads_bhk);
        priceText = itemView.findViewById(R.id.ads_price);
        landSize = itemView.findViewById(R.id.land_area);
        builtUpSize = itemView.findViewById(R.id.built_up_area);
        detailsBtn = itemView.findViewById(R.id.ads_details_btn);
    }
}
