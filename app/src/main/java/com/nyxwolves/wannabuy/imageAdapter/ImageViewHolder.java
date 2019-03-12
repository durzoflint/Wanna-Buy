package com.nyxwolves.wannabuy.imageAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nyxwolves.wannabuy.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }
}
