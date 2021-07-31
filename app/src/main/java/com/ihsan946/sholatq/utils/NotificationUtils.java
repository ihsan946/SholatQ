//NIM : 10118037
//NAMA : MUHAMMAD IHSAN
//KELAS : IF-1 2018
//Tanggal Pembuatan : 28 Juni 2021

package com.ihsan946.sholatq.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;

import com.ihsan946.sholatq.R;


public class NotificationUtils extends ContextWrapper {

        private NotificationManager mManager;
        public static final String SHOLAT_CHANNEL_ID = "SHOLATQ.JADWALSHOLAT";

        public NotificationUtils(Context base) {
            super(base);
        }



        public NotificationManager getManager() {
            if (mManager == null) {
                mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            }
            return mManager;
        }

        public Notification.Builder getAndroidChannelNotification(String title, String body) {
            return new Notification.Builder(getApplicationContext(), SHOLAT_CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(R.drawable.ic_notify)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logo))
                    .setAutoCancel(true);
        }
    }

