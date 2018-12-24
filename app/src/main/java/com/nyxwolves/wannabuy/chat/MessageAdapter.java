package com.nyxwolves.wannabuy.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int INCOMING = 1;
    private static final int OUTGOING = 2;
    private List<Message> list;
    private Context context;

    public MessageAdapter(List<Message> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        if (viewType == OUTGOING) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                    .view_message_outgoing, viewGroup, false);
            return new OutgoingMessageViewHolder(v);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                    .view_message_incoming, viewGroup, false);
            return new IncomingMessageViewHolder(v);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case INCOMING:
                IncomingMessageViewHolder holder = (IncomingMessageViewHolder) viewHolder;
                holder.text.setText(list.get(position).message);
                break;
            case OUTGOING:
                OutgoingMessageViewHolder holder1 = (OutgoingMessageViewHolder) viewHolder;
                holder1.text.setText(list.get(position).message);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().trim();
        if (list.get(position).email.equals(email))
            return OUTGOING;
        else
            return INCOMING;
    }
}