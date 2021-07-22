package com.ihsan946.sholatq.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.ihsan946.sholatq.R;

import java.util.Calendar;

public class BroadcastReceiverSholat extends BroadcastReceiver {
    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 50;
    private static final String EXTRA_JADWAL_SHOLAT = "jadwal_sholat";
    public static final String SHOLAT_CHANNEL_ID = "SHOLATQ.JADWALSHOLAT";
    public static final String SHOLAT_CHANNEL_NAME = "SHOLATQ";
    Uri alarmSound = null;
    Uri alarmSoundShubuh = null;





    @Override
    public void onReceive(Context context, Intent intent) {

        String jadwal_sholat = intent.getStringExtra(EXTRA_JADWAL_SHOLAT);
        kirimNotif(context,jadwal_sholat);
    }


    public void kirimNotif(Context context, String jadwal_sholat){
        String name = "SholatQ";


//
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, SHOLAT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notify)
                .setContentTitle(name)
                .setContentText("Saatnya Waktu Sholat "+ jadwal_sholat)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
//
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .build();

        alarmSoundShubuh = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://"+ context.getPackageName()
                + "/" + R.raw.adzan_shubuh);

        alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://"+ context.getPackageName()
                + "/" + R.raw.adzan_bosnia);


// create android channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel sholatChannel = new NotificationChannel(SHOLAT_CHANNEL_ID,
                    SHOLAT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            sholatChannel.enableLights(true);
            sholatChannel.enableVibration(true);
            sholatChannel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            sholatChannel.setSound(alarmSoundShubuh, audioAttributes);
            //
            NotificationChannel shubuhChannel = new NotificationChannel(SHOLAT_CHANNEL_ID,
                    SHOLAT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            sholatChannel.enableLights(true);
            sholatChannel.enableVibration(true);
            sholatChannel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            sholatChannel.setSound(alarmSound, audioAttributes);


            if(jadwal_sholat.equals("Shubuh")){
                if (notificationManagerCompat != null) {
                    notificationManagerCompat.createNotificationChannel(shubuhChannel);
                }
            }else{
                if (notificationManagerCompat != null) {
                    notificationManagerCompat.createNotificationChannel(sholatChannel);
                }
            }

            Notification notification = builder.build();

            if (notificationManagerCompat != null) {
                notificationManagerCompat.notify(ALARM_REQUEST_CODE, notification);
            }
        }

//

    }

    public void setRepeatingAlarm(Context context, int ALARM_REQUEST_CODE, String time_sholat, String jadwal_sholat, String tanggal_kini) {

        Intent alarmintent = new Intent(context,BroadcastReceiverSholat.class);
        alarmintent.putExtra(EXTRA_JADWAL_SHOLAT,jadwal_sholat);

//
        String [] time_split;
        time_split = time_sholat.split("\\:");
//        String [] date = tanggal_kini.split("-");
        int jam = Integer.parseInt(time_split[0]);
        int menit = Integer.parseInt(time_split[1]);
//        int tahun = Integer.parseInt(date[0]);
//        int bulan = Integer.parseInt(date[1]);
//        int tanggal = Integer.parseInt(date[2]);

//
        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR,tahun);
//        calendar.set(Calendar.MONTH,bulan - 1);
//        calendar.set(Calendar.DAY_OF_MONTH,tanggal);
        calendar.set(Calendar.HOUR_OF_DAY,jam);
        calendar.set(Calendar.MINUTE,menit);
        calendar.set(Calendar.SECOND,0);

//

        pendingIntent = PendingIntent.getBroadcast(context,ALARM_REQUEST_CODE,alarmintent,0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        }
        else
        {
            Log.d("ALARM", "SET ALARM FAILURE");
        }

    }

    public boolean isAlarmSet(Context context) {
        Intent intent = new Intent(context, BroadcastReceiverSholat.class);

        return PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_NO_CREATE) != null;
    }

    public void cancelAlarm(Context context, int ALARM_REQUEST_CODE) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadcastReceiverSholat.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
        else{
            Log.d("ALARM", "CANCEL FAILURE");
        }

    }


}
