package com.nyxwolves.wannabuy.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

public class OutgoingMessageViewHolder extends RecyclerView.ViewHolder {

    TextView text;

    public OutgoingMessageViewHolder(View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.text);
    }
}