package com.example.hoangpeo.alarm2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String state = intent.getExtras().getString("extra");
        Log.e("wake up ","wake up");
        // create a intent to the ringtone sevice
        Intent serviceIntent = new Intent(context,RingtonePlayingService.class);

        serviceIntent.putExtra("extra", state);

        context.startService(serviceIntent);
    }

}
