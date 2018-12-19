package com.nyxwolves.wannabuy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;

import java.util.ArrayList;
import java.util.List;

public class RequirementSearchAdapter extends RecyclerView.Adapter<RequirementSearchHolder> {

    private List<Requirements>dataList = new ArrayList<>();
    private Context ctx;

    public RequirementSearchAdapter(Context ctx){
        this.ctx = ctx;
    }

    public void setData(List<Requirements>dataList){
        this.dataList.clear();
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RequirementSearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.requirements_search,viewGroup,false);
        return new RequirementSearchHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RequirementSearchHolder requirementSearchHolder, int i) {
        Requirements obj = dataList.get(i);
        requirementSearchHolder.cityName.setText("Chennai");
        requirementSearchHolder.propertyBhk.setText(obj.bhk+" Bhk");
        requirementSearchHolder.propertyType.setText(obj.type);
        requirementSearchHolder.propertyLccation.setText(obj.area);
        requirementSearchHolder.propertySize.setText(obj.size);
        requirementSearchHolder.propertyBudget.setText(obj.budget);
        requirementSearchHolder.postAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdHelper helper = new AdHelper(ctx);
                helper.createAd();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
