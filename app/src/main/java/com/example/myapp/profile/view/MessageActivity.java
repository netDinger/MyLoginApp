package com.example.myapp.profile.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.profile.broadcastReceiver.MyReceiver;
import com.example.myapp.profile.service.MessageService;

public class MessageActivity extends AppCompatActivity {

    private MyReceiver receiver;
    private IntentFilter filter;
    public static final String EMITTER = "com.example.myapp.BC_EVENT";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initializeView();
        initializeLogic();
    }

    void initializeView(){
        Button stopService = findViewById(R.id.stopService);

        Intent i = new Intent();
        i.setClass(MessageActivity.this, MessageService.class);

        findViewById(R.id.startService).setOnClickListener(v -> startService(i));

        stopService.setOnClickListener(v -> stopService(i));

        Button broadcast = findViewById(R.id.broadcast);
        broadcast.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(EMITTER);
            intent.putExtra("key","value");
            sendBroadcast(intent);
        });
    }

    void initializeLogic(){
        receiver = new MyReceiver();

        filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(EMITTER);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        unregisterReceiver(receiver);

    }



}