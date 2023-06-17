package com.example.myapp.profile.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.BreakIterator;

public class IncomingSMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] PDUs = (Object[]) bundle.get("pdus");
        for (Object pdu: PDUs) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
            Toast.makeText(context, smsMessage.getMessageBody(), Toast.LENGTH_SHORT).show();
        }
    }
}
