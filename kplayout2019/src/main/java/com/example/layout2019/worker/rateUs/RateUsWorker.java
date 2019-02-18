package com.example.layout2019.worker.rateUs;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;


import com.example.layout2019.screen.mainscreen.MainScreenLibrary;
import com.kplayout2019.R;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RateUsWorker extends Worker {

    public RateUsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        //bug fix for workmanager ; double check that this notification will not trigger second time
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (!mPrefs.getBoolean("rate_us_set_complete", false)) {
            sendNotification(getApplicationContext());
        }
        return Result.success();
    }

    private void sendNotification(Context mContext) {


        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEditor = mPrefs.edit();


        //bug fix for workmanager ; set true and make sure this notification will not trigger again
        mEditor.putBoolean("rate_us_set_complete", true).apply();


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext.getApplicationContext(), "Rate Us");
        Intent mRedirect = new Intent(mContext.getApplicationContext(), MainScreenLibrary.class);
        mRedirect.putExtra("rate_us_flag", true);
        mRedirect.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 2000, mRedirect, PendingIntent.FLAG_UPDATE_CURRENT);


        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        mBuilder.setAutoCancel(true);
        mBuilder.setContentTitle("Your opinion matters!");
        mBuilder.setContentText("Please give us a rate.");
        mBuilder.setTimeoutAfter(-1);

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Rate Us";
            NotificationChannel channel = new NotificationChannel(channelId, "Rate Us", NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(2001, mBuilder.build());
    }
}
