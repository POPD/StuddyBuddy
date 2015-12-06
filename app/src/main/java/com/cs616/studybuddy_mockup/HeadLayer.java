package com.cs616.studybuddy_mockup;

/**
 * Created by Alex on 12/5/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Creates the head layer view which is displayed directly on window manager.
 * It means that the view is above every application's view on your phone -
 * until another application does the same.
 */
public class HeadLayer extends View {

    // Variables that control drag
    private int startDragX;
    private int startDragY;
    //private int startDragY; // Unused as yet
    private int prevDragX;
    private int prevDragY;

    private Context context;
    private WindowManager windowManager;
    private LinearLayout rootLayout;			// Root layout
    private WindowManager.LayoutParams rootLayoutParams;		// Parameters of the root layout

    public HeadLayer(Context context) {
        super(context);

        this.context = context;

        addToWindowManager();
    }

    private void addToWindowManager() {

        rootLayout = (LinearLayout) LayoutInflater.from(context).
                inflate(R.layout.activity_bubble, null);

        rootLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        rootLayoutParams.gravity = Gravity.LEFT;

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(rootLayout, rootLayoutParams);

        ImageView image= (ImageView) rootLayout.findViewById(R.id.img_logo);
        image.setOnTouchListener(new TrayTouchListener());
    }

    /**
     * Removes the view from window manager.
     */
    public void destroy() {
        windowManager.removeView(rootLayout);
    }

    // Drags the tray as per touch info
    private void dragTray(int action, int x, int y){
        switch (action){
            case MotionEvent.ACTION_DOWN:

                // Store the start points
                startDragX = x;
                startDragY = y;
                //startDragY = y;
                prevDragX = x;
                prevDragY = y;
                break;

            case MotionEvent.ACTION_MOVE:

                // Calculate position of the whole tray according to the drag, and update layout.
                float deltaX = x- prevDragX;
                float deltaY = y- prevDragY;
                rootLayoutParams.x += deltaX;
                rootLayoutParams.y += deltaY;
                prevDragX = x;
                prevDragY = y;
                windowManager.updateViewLayout(rootLayout, rootLayoutParams);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                    //User clicked instead of moving
                    if(startDragX == x && startDragY == y) {

                        //go back to the session activity
                        Context context = getContext();

                        Intent bringToForegroundIntent = new Intent(context, SessionActivity.class);
                        bringToForegroundIntent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.context.startActivity(bringToForegroundIntent);
                        this.context.stopService(new Intent(this.context, BubbleActivity.class));
                    }
                break;
        }
    }

    // Listens to the touch events on the tray.
    private class TrayTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final int action = event.getActionMasked();

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    // Filter and redirect the events to dragTray()
                    dragTray(action, (int)event.getRawX(), (int)event.getRawY());
                    break;
                default:
                    return false;
            }
            return true;

        }
    }

}