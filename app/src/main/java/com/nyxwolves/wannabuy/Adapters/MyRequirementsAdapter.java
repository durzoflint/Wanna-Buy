package com.nyxwolves.wannabuy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

import java.util.List;

public class MyRequirementsAdapter extends RecyclerView.Adapter<MyRequirementsHolder> {

    List<Requirements> dataList;

    public MyRequirementsAdapter(){

    }

    public void setData(List<Requirements>dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyRequirementsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_requirements_item,viewGroup,false);
        return new MyRequirementsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRequirementsHolder myRequirementsHolder, int i) {
        Requirements obj = dataList.get(i);
        myRequirementsHolder.locationName.setText(obj.locationOne);
        myRequirementsHolder.price.setText(obj.budget);
        myRequirementsHolder.bhkOrLand.setText(obj.bhk);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
