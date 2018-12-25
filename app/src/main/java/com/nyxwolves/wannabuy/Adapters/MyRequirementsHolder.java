package com.nyxwolves.wannabuy.Adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

public class MyRequirementsHolder extends RecyclerView.ViewHolder {

    TextView locationName,bhkOrLand,price;
    ConstraintLayout rootLayout;

    public MyRequirementsHolder(@NonNull View itemView) {
        super(itemView);
        locationName = itemView.findViewById(R.id.location_name);
        bhkOrLand = itemView.findViewById(R.id.bhk_or_land);
        price = itemView.findViewById(R.id.price);
        rootLayout = itemView.findViewById(R.id.rootLayout);
    }
}
