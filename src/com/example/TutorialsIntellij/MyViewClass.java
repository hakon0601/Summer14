package com.example.TutorialsIntellij;

import android.content.Context;
import android.graphics.*;
import android.view.View;


public class MyViewClass extends View{

    Bitmap launcherIcon;
    float changingY;
    Typeface font;


    public MyViewClass(Context context) {
        super(context);

        launcherIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        changingY = 0;
        font = Typeface.createFromAsset(context.getAssets(), "assassin.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint textPaint = new Paint();
        textPaint.setARGB(50, 254, 10, 50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        textPaint.setTypeface(font);
        canvas.drawText("Hawkon", (canvas.getWidth()/2), 200, textPaint);

        canvas.drawBitmap(launcherIcon, (canvas.getWidth()/2), changingY, null);
        if (changingY < canvas.getHeight()) {
            changingY += 10;
        }
        else {
            changingY = 0;
        }
        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 550);
        Paint myBlue = new Paint();
        myBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, myBlue);
        invalidate();
    }
}
