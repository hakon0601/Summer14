package com.example.TutorialsIntellij;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

    MySurfaceViewClass mySurfaceView;
    float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new MySurfaceViewClass(this);
        mySurfaceView.setOnTouchListener(this);
        x = 0;
        y = 0;
        setContentView(mySurfaceView);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mySurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySurfaceView.resume();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        x = motionEvent.getX();
        y = motionEvent.getY();
        // returns true to allow for continous updates
        return true;
    }

    public class MySurfaceViewClass extends SurfaceView implements Runnable {

        SurfaceHolder myHolder;
        Thread myThread = null;
        private boolean isRunning = false;

        public MySurfaceViewClass(Context context) {
            super(context);
            myHolder = getHolder();

        }

        public void pause() {
            isRunning = false;
            while (true) {
                try {
                    myThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            myThread = null;
        }

        public void resume() {
            isRunning = true;
            myThread = new Thread(this);
            myThread.start();
        }

        @Override
        public void run() {
            while (isRunning) {
                if (!myHolder.getSurface().isValid()) {
                    continue;
                }
                Canvas canvas = myHolder.lockCanvas();
                canvas.drawRGB(2, 2, 150);
                if (x != 0 && y != 0) {
                    Bitmap test = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                    canvas.drawBitmap(test, x - (test.getWidth()/2), y - (test.getHeight()/2), null);
                }
                myHolder.unlockCanvasAndPost(canvas);
            }
        }
    }


}
