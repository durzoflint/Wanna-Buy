package com.nyxwolves.wannabuy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchesViewHolder extends RecyclerView.ViewHolder {
    TextView locationText,priceText,sizeText;
    Button detailsBtn;
    CircleImageView propPic;

    public MatchesViewHolder(@NonNull View itemView) {
        super(itemView);
        locationText = itemView.findViewById(R.id.location_text);
        priceText = itemView.findViewById(R.id.price_text);
        sizeText = itemView.findViewById(R.id.size_text);
        detailsBtn = itemView.findViewById(R.id.details_btn);
        propPic = itemView.findViewById(R.id.prop_pic);
    }
}
