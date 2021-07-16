package com.ihsan946.sholatq.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.ihsan946.sholatq.R;


public class NotificationUtils extends ContextWrapper {

        private NotificationManager mManager;
        public static final String SHOLAT_CHANNEL_ID = "SHOLATQ.JADWALSHOLAT";
        public static final String SHOLAT_CHANNEL_NAME = "SHOLATQ";


        public NotificationUtils(Context base) {
            super(base);
            createChannels();
        }

        public void createChannels() {

            // create android channel
            NotificationChannel androidChannel = new NotificationChannel(SHOLAT_CHANNEL_ID,
                    SHOLAT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            androidChannel.enableLights(true);
            androidChannel.enableVibration(true);
            androidChannel.setLightColor(Color.WHITE);
            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(androidChannel);


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

