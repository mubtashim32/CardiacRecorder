package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class UpdateDeleteCardiacMeasurementActivtiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_cardiac_measurement_activtiy);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String date = intent.getStringExtra("date");
        String diastolicPressure = intent.getStringExtra("diastolicPressure");
        String systolicPressure = intent.getStringExtra("systolicPressure");
        String heartRate = intent.getStringExtra("heartRate");

        Toast.makeText(UpdateDeleteCardiacMeasurementActivtiy.this, id, Toast.LENGTH_LONG).show();
    }
}