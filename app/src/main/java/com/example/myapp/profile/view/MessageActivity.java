package com.example.myapp.profile.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.profile.broadcastReceiver.IncomingSMSReceiver;
import com.example.myapp.profile.broadcastReceiver.MyReceiver;
import com.example.myapp.profile.model.ChatModel;
import com.example.myapp.profile.view.adapter.MessageAdapter;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;


public class MessageActivity extends AppCompatActivity {

    private ArrayList<ChatModel> chatModels;

    private MyReceiver receiver;
    private IntentFilter filter;
    public static final String EMITTER = "com.example.myapp.BC_EVENT";


    private EditText messageField;
    private RecyclerView recyclerView;
    private MessageAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initializeView();
        initializeLogic();
    }

    void initializeView(){
        ImageButton sendBtn = findViewById(R.id.send);
        messageField = findViewById(R.id.message);
        recyclerView = findViewById(R.id.conversationView);

        sendBtn.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.SEND_SMS}, 1);
            }else {

                sendMessage(messageField.getText().toString());
                messageField.setText("");
            }
        });
    }

    void initializeLogic(){
        chatModels = new ArrayList<>();
        receiver = new  MyReceiver();

        filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(EMITTER);

        adapter = new MessageAdapter(chatModels);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(new IncomingSMSReceiver(),intentFilter);


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
    }



    private void sendMessage(String message){

        Intent intent_sent = new Intent();
        intent_sent.setAction("MSG_SENT");

        PendingIntent sentMsgPI = PendingIntent.getBroadcast(this,1,intent_sent,PendingIntent.FLAG_ONE_SHOT);


        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+959793168992",null,message,sentMsgPI,null);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch ( getResultCode()){
                    case Activity.RESULT_OK:
                        Calendar c = Calendar.getInstance();
                        String d = c.getTime().toString();
                        chatModels.add(new ChatModel("Pyae Phyo",message,d));
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "Sent!!!", Toast.LENGTH_SHORT).show();
                    break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE: Toast.makeText(context, "No Service!!!", Toast.LENGTH_SHORT).show();break;
                    case SmsManager.RESULT_ERROR_NULL_PDU: Toast.makeText(context, "No PDU!!!", Toast.LENGTH_SHORT).show();break;


                }
            }
        },new IntentFilter("MSG_SENT"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendMessage(messageField.getText().toString());
                messageField.setText("");
            } else {
                Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
