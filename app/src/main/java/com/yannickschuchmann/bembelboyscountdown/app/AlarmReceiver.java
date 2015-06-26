package com.yannickschuchmann.bembelboyscountdown.app;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by yannick on 25.06.15.
 */
public class AlarmReceiver extends BroadcastReceiver {

    public AlarmManager alarmManager;
    Intent alarmIntent;
    PendingIntent pendingIntent;
    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmIntent = new Intent(context, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

            alarmManager.setRepeating(AlarmManager.RTC, MainActivity.getStartTime().getTimeInMillis(),
                    MainActivity.getInterval(), pendingIntent);

        }

        Intent service1 = new Intent(context, NotificationService.class);
        context.startService(service1);
    }
}