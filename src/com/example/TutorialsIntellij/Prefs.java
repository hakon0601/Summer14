package com.example.TutorialsIntellij;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Prefs extends PreferenceActivity {

    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        addPreferencesFromResource(R.xml.prefs);
    }

}
