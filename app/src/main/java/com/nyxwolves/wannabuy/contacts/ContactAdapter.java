package com.nyxwolves.wannabuy.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    private List<Contact> list;
    private Context context;
    private ContactClickListener listener;

    public ContactAdapter(List<Contact> list, Context context, ContactClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ContactViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_contact, viewGroup, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.name.setText(list.get(position).name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
