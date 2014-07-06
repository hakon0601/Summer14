package com.example.TutorialsIntellij;


import android.app.Activity;
import android.os.Bundle;

public class GFXSurface extends Activity {

    MySurfaceViewClass mySurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new MySurfaceViewClass(this);
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
}
