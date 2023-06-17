package com.example.myapp.profile.view.adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.profile.model.ChatModel;
import com.example.myapp.profile.view.MessageActivity;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private ArrayList<ChatModel> chatModels = new ArrayList<>();


    public MessageAdapter(ArrayList<ChatModel> chatModels){
        this.chatModels = chatModels;

    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
      return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        ChatModel chatModel = chatModels.get(position);
        holder.name.setText(chatModel.getName());
        holder.message.setText(chatModel.getMessage());
        holder.date.setText(chatModel.getDate());

    }

    @Override
    public int getItemCount() {
        return chatModels.size();
    }
}
