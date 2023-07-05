package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton addMeasurement;
    private FloatingActionButton logout;
    private RecyclerView cardiacMeasurements;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        } else {
            RecyclerView cardiacMeasurements = findViewById(R.id.cardiacMeasurements);
            cardiacMeasurements = findViewById(R.id.cardiacMeasurements);
            addMeasurement = findViewById(R.id.addMeasurement);
            logout = findViewById(R.id.logout);

            ArrayList<CardiacMeasurement> cardiacMeasurementArrayList = new ArrayList<>();
            CardiacMeasurementsAdapter cardiacMeasurementsAdapter = new CardiacMeasurementsAdapter(HomeActivity.this, cardiacMeasurementArrayList);
            cardiacMeasurements.setAdapter(cardiacMeasurementsAdapter);

            cardiacMeasurements.setLayoutManager(new LinearLayoutManager(this));

            databaseReference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid()).child("measurements");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    cardiacMeasurementArrayList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        CardiacMeasurement cardiacMeasurement = dataSnapshot.getValue(CardiacMeasurement.class);
                        cardiacMeasurementArrayList.add(cardiacMeasurement);
                    }
                    Collections.reverse(cardiacMeasurementArrayList);
                    cardiacMeasurementsAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            addMeasurement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, AddCardiacMeasurementActivity.class);
                    startActivity(intent);
                }
            });

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
            });
        }
    }
}