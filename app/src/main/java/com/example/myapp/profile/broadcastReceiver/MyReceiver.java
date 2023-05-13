package com.example.myapp.profile.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

//activity(fragment),service -> android.app
//BroadcastReceiver, content provider -> android.content

public class MyReceiver extends BroadcastReceiver {

    /**
     * used by {@link com.example.myapp.profile.model.ChatModel}
     * when lottery is selected, tell the view(Activity) to add to bets list
     * @param context user selected bet slip
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        //TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(intent.getAction().equals("com.example.myapp.BC_EVENT"))
            Toast.makeText(context, "BC_EVENT", Toast.LENGTH_LONG).show();
        else {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if (isConnected) {
                try {
                    Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, "Connecting...", Toast.LENGTH_LONG).show();
            }
        }


    }


}
