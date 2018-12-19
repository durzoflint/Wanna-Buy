package com.nyxwolves.wannabuy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

public class RequirementSearchHolder extends RecyclerView.ViewHolder {
    TextView propertyType,propertyBudget,propertySize,cityName,propertyLccation,propertyBhk;
    Button postAd;

    public RequirementSearchHolder(@NonNull View itemView) {
        super(itemView);

        propertyType = itemView.findViewById(R.id.property_type_item);
        propertyBudget = itemView.findViewById(R.id.budget_value);
        propertySize = itemView.findViewById(R.id.size_value);
        cityName = itemView.findViewById(R.id.city_name_item);
        propertyLccation = itemView.findViewById(R.id.property_location_item);
        propertyBhk = itemView.findViewById(R.id.bhk_item);
        postAd = itemView.findViewById(R.id.post_ad_btn);

    }
}
