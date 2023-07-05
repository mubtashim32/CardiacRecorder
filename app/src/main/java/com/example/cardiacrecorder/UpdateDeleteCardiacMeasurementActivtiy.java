package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpdateDeleteCardiacMeasurementActivtiy extends AppCompatActivity {
    private EditText heartRateText;
    private EditText systolicPressureText;
    private EditText diastolicPressureText;
    private EditText commentText;
    private Button updateBtn, deleteBtn;
    private DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_cardiac_measurement_activtiy);

        heartRateText = findViewById(R.id.heartRate);
        systolicPressureText = findViewById(R.id.systolicPressure);
        diastolicPressureText = findViewById(R.id.diastolicPressure);
        commentText = findViewById(R.id.comment);
        updateBtn = findViewById(R.id.update);
        deleteBtn = findViewById(R.id.delete);

//         mdatabase = FirebaseDatabase.getInstance().getReference("measurements");

//         updateBtn.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 int heartRate = Integer.valueOf(heartRateText.getText().toString());
//                 int systolic = Integer.valueOf(systolicPressureText.getText().toString());
//                 int diastolic = Integer.valueOf(diastolicPressureText.getText().toString());
//                 String comment = commentText.getText().toString();
//                 Date date = Calendar.getInstance().getTime();
//                 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//                 String today = simpleDateFormat.format(date);

//                 CardiacMeasurement patient = new CardiacMeasurement(today, systolic, diastolic, heartRate, comment);
//                 mdatabase.child("users").push().setValue(patient).addOnSuccessListener(new OnSuccessListener<Void>() {
//                     @Override
//                     public void onSuccess(Void unused) {
//                         Toast.makeText(UpdateDeleteCardiacMeasurementActivtiy.this, "Data Added", Toast.LENGTH_LONG).show();
//                     }
//                 }).addOnFailureListener(new OnFailureListener() {
//                     @Override
//                     public void onFailure(@NonNull Exception e) {
//                         Toast.makeText(UpdateDeleteCardiacMeasurementActivtiy.this, "Error", Toast.LENGTH_LONG).show();
//                     }
//                 });
//             }
//         });
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String date = intent.getStringExtra("date");
        String diastolicPressure = intent.getStringExtra("diastolicPressure");
        String systolicPressure = intent.getStringExtra("systolicPressure");
        String heartRate = intent.getStringExtra("heartRate");

        Toast.makeText(UpdateDeleteCardiacMeasurementActivtiy.this, id, Toast.LENGTH_LONG).show();

    }
}