package com.example.TutorialsIntellij;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;


public class MyClass extends View{

    Bitmap launcherIcon;


    public MyClass(Context context) {
        super(context);

        launcherIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(launcherIcon, (canvas.getWidth()/2), 0, null);
    }
}
