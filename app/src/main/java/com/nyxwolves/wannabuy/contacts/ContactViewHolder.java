package com.nyxwolves.wannabuy.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nyxwolves.wannabuy.R;

import java.lang.ref.WeakReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    CircleImageView image;
    TextView name;
    LinearLayout linearLayout;

    private WeakReference<ContactClickListener> listenerWeakReference;

    public ContactViewHolder(View itemView, ContactClickListener contactClickListener) {
        super(itemView);

        linearLayout = itemView.findViewById(R.id.linearLayout);
        image = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);

        listenerWeakReference = new WeakReference<>(contactClickListener);

        linearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listenerWeakReference.get().onClick(getAdapterPosition());
    }
}