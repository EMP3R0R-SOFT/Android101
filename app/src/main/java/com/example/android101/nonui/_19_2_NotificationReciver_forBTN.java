package com.example.android101.nonui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class _19_2_NotificationReciver_forBTN extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("toastMessage");
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }



}
