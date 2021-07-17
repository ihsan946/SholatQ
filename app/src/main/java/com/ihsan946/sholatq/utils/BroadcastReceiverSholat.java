package com.ihsan946.sholatq.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BroadcastReceiverSholat extends BroadcastReceiver {
    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 100;
    String jam_sekarang;
    private NotificationUtils mNotificationUtils;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent alarmintent = new Intent(context,BroadcastReceiverSholat.class);
        pendingIntent = PendingIntent.getBroadcast(context,ALARM_REQUEST_CODE,alarmintent,0);

        Date jam_kini = new Date();
        SimpleDateFormat formatjam = new SimpleDateFormat("HH:mm", Locale.UK);
        jam_sekarang = formatjam.format(jam_kini);
        Date hasil1 = null;

//        get jadwal sholat

        String time_shubuh = "12.05";
        String [] time_split;

        time_split = time_shubuh.split("\\.");
        int jam = Integer.valueOf(time_split[0]);
        int menit = Integer.valueOf(time_split[1]);

//
//        if(jam_sekarang.equals(time_shubuh)){
//
//        }

        Calendar calendar = Calendar.getInstance();
        Calendar callset = (Calendar) calendar.clone();
        callset.set(Calendar.HOUR_OF_DAY,jam);
        callset.set(Calendar.MINUTE,menit);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, callset.getTimeInMillis(), pendingIntent);

        kirimNotif(context);

    }

    private void kirimNotif(Context context){
        String name = "SholatQ";
        String description = "Yuk Sholat";

        mNotificationUtils = new NotificationUtils(context);
        Notification.Builder nb = mNotificationUtils.
                getAndroidChannelNotification(name,description);
        mNotificationUtils.getManager().notify(101, nb.build());

    }


}
