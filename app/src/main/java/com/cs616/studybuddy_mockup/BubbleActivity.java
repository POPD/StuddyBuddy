package com.cs616.studybuddy_mockup;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class BubbleActivity extends Service {

    private final static int FOREGROUND_ID = 999;

    private HeadLayer mHeadLayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        initHeadLayer();

        PendingIntent pendingIntent = createPendingIntent();
        Notification notification = createNotification(pendingIntent);

        startForeground(FOREGROUND_ID, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        destroyHeadLayer();
        stopForeground(true);

    }

    private void initHeadLayer() {
        mHeadLayer = new HeadLayer(this);
    }

    private void destroyHeadLayer() {
        mHeadLayer.destroy();
        mHeadLayer = null;
    }

    private PendingIntent createPendingIntent() {
        Intent bringToForegroundIntent = new Intent(this, SessionActivity.class);
        bringToForegroundIntent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        return PendingIntent.getActivity(this, 0, bringToForegroundIntent, 0);
    }

    private Notification createNotification(PendingIntent intent) {
        return new Notification.Builder(this)
                .setContentTitle(getText(R.string.notificationTitle))
                .setContentText(getText(R.string.notificationText))
                .setSmallIcon(R.mipmap.book)
                .build();
    }

}
