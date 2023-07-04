package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    ArrayList<CardiacMeasurement> cardiacMeasurementArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent intent = new Intent(HomeActivity.this, AddCardiacMeasurementActivity.class);
        startActivity(intent);

        RecyclerView cardiacMeasurements = findViewById(R.id.cardiacMeasurements);

//        CardiacMeasurement cardiacMeasurement = new CardiacMeasurement(1, 2, 3, "Hello");
        cardiacMeasurementArrayList.add(cardiacMeasurement);

        CardiacMeasurementsAdapter cardiacMeasurementsAdapter = new CardiacMeasurementsAdapter(cardiacMeasurementArrayList);
        cardiacMeasurements.setAdapter(cardiacMeasurementsAdapter);
        cardiacMeasurements.setLayoutManager(new LinearLayoutManager(this));
    }
}