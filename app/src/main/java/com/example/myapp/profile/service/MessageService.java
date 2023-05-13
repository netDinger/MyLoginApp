package com.example.myapp.profile.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MessageService extends Service {
    public MessageService() {
    }

    private boolean running = true;
    private int i = 0;

    private static final String TAG = "MessageService";

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service is Started!", Toast.LENGTH_SHORT).show();
        new Thread(() -> {
            while (running){
                i += 1;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    
    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
        Toast.makeText(this, "value of i "+ i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
  

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    
}