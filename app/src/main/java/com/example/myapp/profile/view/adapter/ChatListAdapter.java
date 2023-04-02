package com.example.myapp.profile.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapp.profile.model.ChatModel;

import java.util.List;

public class ChatListAdapter extends ArrayAdapter<ChatModel> {

    public ChatListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ChatListAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ChatListAdapter(@NonNull Context context, int resource, @NonNull ChatModel[] objects) {
        super(context, resource, objects);

    }

    public ChatListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull ChatModel[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ChatListAdapter(@NonNull Context context, int resource, @NonNull List<ChatModel> objects) {
        super(context, resource, objects);

    }

    public ChatListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ChatModel> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Nullable
    @Override
    public ChatModel getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable ChatModel item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
