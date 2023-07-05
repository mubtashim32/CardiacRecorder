package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Intent intent = new Intent(HomeActivity.this, UpdateDeleteCardiacMeasurementActivtiy.class);
//        startActivity(intent);

        RecyclerView cardiacMeasurements = findViewById(R.id.cardiacMeasurements);

        ArrayList<CardiacMeasurement> cardiacMeasurementArrayList = new ArrayList<>();
        CardiacMeasurementsAdapter cardiacMeasurementsAdapter = new CardiacMeasurementsAdapter(cardiacMeasurementArrayList);
        cardiacMeasurements.setAdapter(cardiacMeasurementsAdapter);

        cardiacMeasurements.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("measurements");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CardiacMeasurement cardiacMeasurement = dataSnapshot.getValue(CardiacMeasurement.class);
                    cardiacMeasurementArrayList.add(cardiacMeasurement);
                }
                cardiacMeasurementsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
//        Date date = Calendar.getInstance().getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//        String formattedDate = simpleDateFormat.format(date);

//        Intent intent = new Intent(HomeActivity.this, AddCardiacMeasurementActivity.class);
//        startActivity(intent);