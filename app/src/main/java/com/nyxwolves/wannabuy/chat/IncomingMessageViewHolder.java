package com.nyxwolves.wannabuy.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

public class IncomingMessageViewHolder extends RecyclerView.ViewHolder {
    TextView text;

    public IncomingMessageViewHolder(View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.text);
    }
}