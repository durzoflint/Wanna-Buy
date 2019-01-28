package com.nyxwolves.wannabuy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

public class RequirementSearchHolder extends RecyclerView.ViewHolder {
    TextView propertyType,minBudget,maxBudget,minBudgetUnit,maxBudgetUnit,cityName,buyOrSell;
    Button postAd;

    public RequirementSearchHolder(@NonNull View itemView) {
        super(itemView);
        propertyType = itemView.findViewById(R.id.property_type_item);
        minBudget = itemView.findViewById(R.id.min_budget_value);
        maxBudget = itemView.findViewById(R.id.max_budget_value);
        cityName = itemView.findViewById(R.id.city_name_item);
        buyOrSell = itemView.findViewById(R.id.buy_or_rent);
        minBudgetUnit = itemView.findViewById(R.id.min_budget_unit);
        maxBudgetUnit = itemView.findViewById(R.id.max_budget_unit);
        postAd = itemView.findViewById(R.id.post_ad_btn);


    }
}
