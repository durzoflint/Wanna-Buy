package com.nyxwolves.wannabuy.Adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

public class MyRequirementsHolder extends RecyclerView.ViewHolder {

    TextView reqName,propertyType,minBudget,maxBudget,minBudgetUnit,maxBudgetUnit,buyOrSell,propertyLocations;

    public MyRequirementsHolder(@NonNull View itemView) {
        super(itemView);
        reqName = itemView.findViewById(R.id.req_name);
        propertyType = itemView.findViewById(R.id.property_type_item);
        minBudget = itemView.findViewById(R.id.min_budget_value);
        maxBudget = itemView.findViewById(R.id.max_budget_value);
        buyOrSell = itemView.findViewById(R.id.buy_or_rent);
        minBudgetUnit = itemView.findViewById(R.id.min_budget_unit);
        maxBudgetUnit = itemView.findViewById(R.id.max_budget_unit);
        propertyLocations = itemView.findViewById(R.id.prop_location);
    }
}
