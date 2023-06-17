package com.example.myapp.profile.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

public class MessageViewHolder extends RecyclerView.ViewHolder{

    public TextView name,message,date;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        message = itemView.findViewById(R.id.message);
        date = itemView.findViewById(R.id.date);
    }
}