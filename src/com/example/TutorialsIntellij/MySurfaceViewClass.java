package com.example.TutorialsIntellij;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceViewClass extends SurfaceView implements Runnable {

    SurfaceHolder myHolder;
    Thread myThread = null;
    private boolean isRunning = true;

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
            myHolder.unlockCanvasAndPost(canvas);
        }
    }
}
