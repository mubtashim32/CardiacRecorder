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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Updates and Deletes data from fireabse records
 */
public class UpdateDeleteCardiacMeasurementActivtiy extends AppCompatActivity {

    private EditText heartRateText;
    private EditText systolicPressureText;
    private EditText diastolicPressureText;
    private EditText commentText;
    private EditText dateText;
    private EditText timeText;
    private Button updateBtn, deleteBtn;
    private DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_update_delete_cardiac_measurement_activtiy);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        int date = intent.getIntExtra("date", 0);
        int month = intent.getIntExtra("month", 0);
        int year = intent.getIntExtra("year", 0);

        int minute = intent.getIntExtra("minute", 0);
        int hour = intent.getIntExtra("hour", 0);
        String diastolicPressure = intent.getStringExtra("diastolicPressure");
        String systolicPressure = intent.getStringExtra("systolicPressure");
        String heartRate = intent.getStringExtra("heartRate");
        String comment = intent.getStringExtra("comment");

        Toast.makeText(UpdateDeleteCardiacMeasurementActivtiy.this, id, Toast.LENGTH_LONG).show();

        heartRateText = findViewById(R.id.heartRate);
        systolicPressureText = findViewById(R.id.systolicPressure);
        diastolicPressureText = findViewById(R.id.diastolicPressure);
        commentText = findViewById(R.id.comment);
        updateBtn = findViewById(R.id.update);
        deleteBtn = findViewById(R.id.delete);
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);

        String hourStr = Integer.toString(hour);
        String minuteStr = Integer.toString(minute);

        if (minuteStr.length() == 1) minuteStr = "0" + minuteStr;
        if (hourStr.length() == 1) hourStr = "0" + hourStr;

        dateText.setText(date + "-" + month + "-" + year);
        timeText.setText(minuteStr + "-" + hourStr);
        heartRateText.setText(heartRate);
        systolicPressureText.setText(systolicPressure);
        diastolicPressureText.setText(diastolicPressure);
        commentText.setText(comment);

         mdatabase = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("measurements");

         updateBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int heartRate = -1;
                 try {
                     heartRate = Integer.valueOf(heartRateText.getText().toString());
                 } catch (Exception e) {
                     heartRateText.setError("HR must be a non-negative integer");
                 }
                 if (heartRate < 0) heartRateText.setError("HR must be a non-negative integer");

                 int systolic = -1;
                 try {
                     systolic = Integer.valueOf(systolicPressureText.getText().toString());
                 } catch (Exception e) {
                     systolicPressureText.setError("DBP must be a non-negative integer");
                 }
                 if (systolic < 0) systolicPressureText.setError("DBP must be a non-negative integer");

                 int diastolic = -1;
                 try {
                     diastolic = Integer.valueOf(diastolicPressureText.getText().toString());
                 } catch (Exception e) {
                     diastolicPressureText.setError("DBP must be a non-negative integer");
                 }
                 if (diastolic < 0) diastolicPressureText.setError("DBP must be a non-negative integer");

                 String dateView = dateText.getText().toString();
                 String time = timeText.getText().toString();
                 String comment = commentText.getText().toString();

                 if (dateView.length() == 0) dateText.setError("Enter Date!");
                 if (time.length() == 0) dateText.setError("Enter Time!");


                 if (dateView.length() > 0 && time.length() > 0 && heartRate >= 0 && systolic >= 0 && diastolic >= 0) {
                     String key = mdatabase.push().getKey();
                     CardiacMeasurement cardiacMeasurement = new CardiacMeasurement(key, year, month, date, hour, minute, systolic, diastolic, heartRate, comment);
                     mdatabase.child(id).setValue(cardiacMeasurement).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void unused) {
                             Toast.makeText(UpdateDeleteCardiacMeasurementActivtiy.this, "Data Updated", Toast.LENGTH_LONG).show();
                             Intent intent = new Intent(UpdateDeleteCardiacMeasurementActivtiy.this, HomeActivity.class);
                             startActivity(intent);
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Toast.makeText(UpdateDeleteCardiacMeasurementActivtiy.this, "Error", Toast.LENGTH_LONG).show();
                         }
                     });
                 }
             }
         });

         deleteBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mdatabase.child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void unused) {
                         Toast.makeText(UpdateDeleteCardiacMeasurementActivtiy.this, "Delete successful", Toast.LENGTH_LONG).show();
                         Intent intent = new Intent(UpdateDeleteCardiacMeasurementActivtiy.this, HomeActivity.class);
                         startActivity(intent);
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(UpdateDeleteCardiacMeasurementActivtiy.this, "Delete failed", Toast.LENGTH_LONG).show();
                     }
                 });
             }
         });
    }
}