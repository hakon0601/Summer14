package com.example.TutorialsIntellij;

import android.app.Activity;
import android.os.Bundle;

public class GFX extends Activity{


    MyClass myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myView = new MyClass(this);
        setContentView(myView);
    }
}
