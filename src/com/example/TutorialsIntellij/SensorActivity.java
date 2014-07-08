package com.example.TutorialsIntellij;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class SensorActivity extends Activity implements SensorEventListener, AdapterView.OnItemClickListener {

    List<Sensor> sensorList;
    List<String> arrayListOfSensorNames;
    private static final String TAG = "#SensorActivity";
    private ArrayList<String> messageArray;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    private int selectedDialog = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors);
        ListView sensorListView = (ListView) findViewById(R.id.lvSensors);

        builder = new AlertDialog.Builder(this);
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = manager.getSensorList(Sensor.TYPE_ALL);


        messageArray = new ArrayList<String>();
        arrayListOfSensorNames = new ArrayList<String>();
        for (int i = 0; i < sensorList.size(); i++) {
            arrayListOfSensorNames.add(sensorList.get(i).getName());
            sensorList.get(i);
            messageArray.add(arrayListOfSensorNames.get(i) + " \nPower: " + sensorList.get(i).getPower() + " mAh \n");

            manager.registerListener(this, sensorList.get(i), SensorManager.SENSOR_DELAY_NORMAL);
        }


        String[] values = new String[arrayListOfSensorNames.size()];
        arrayListOfSensorNames.toArray(values);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        sensorListView.setAdapter(adapter);
        sensorListView.setOnItemClickListener(this);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.i(TAG, sensorEvent.sensor.getName() + "changed");
        String newStringValues = "";
        for (int i = 0; i < sensorEvent.values.length; i++) {
            newStringValues += " value " + i + ": " + sensorEvent.values[i] + "\n";
        }
        messageArray.set(sensorList.indexOf(sensorEvent.sensor), sensorEvent.sensor.getName() + "\n" + "Power: " + sensorEvent.sensor.getPower() + " mAh \n" + newStringValues);
        builder.setMessage(messageArray.get(selectedDialog));
        if (alertDialog != null) {
            Log.i(TAG, "alertDialog is not null");
            alertDialog.setMessage(messageArray.get(selectedDialog));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectedDialog = i;
        builder.setCancelable(true);
        builder.setTitle(arrayListOfSensorNames.get(i));
        builder.setMessage(messageArray.get(i));


        alertDialog = builder.create();
        alertDialog.show();

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                alertDialog = null;
            }
        });


    }
}
