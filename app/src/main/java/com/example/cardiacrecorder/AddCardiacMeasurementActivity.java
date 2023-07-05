package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddCardiacMeasurementActivity extends AppCompatActivity {

    private EditText heartRateText;
    private EditText systolicPressureText;
    private EditText diastolicPressureText;
    private EditText commentText;
    private EditText dateText;
    private EditText timeText;
    private Button addBtn;
    private ImageButton addDate;
    private ImageButton addTime;
    private DatabaseReference mdatabase;

    private int yearVar, monthVar, dateVar;
    private int hourVar, minuteVar;
    private boolean addDataStatus;
    public ArrayList<CardiacMeasurement> cardiacMeasurementArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_cardiac_measurement);

        heartRateText = findViewById(R.id.heartRate);
        systolicPressureText = findViewById(R.id.systolicPressure);
        diastolicPressureText = findViewById(R.id.diastolicPressure);
        commentText = findViewById(R.id.comment);
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        addBtn = findViewById(R.id.addData);
        addDate = findViewById(R.id.addDate);
        addTime = findViewById(R.id.addTime);

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDateDialog();
            }
        });
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimeDialog();
            }
        });

        mdatabase = FirebaseDatabase.getInstance().getReference();

        addBtn.setOnClickListener(new View.OnClickListener() {
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

                String date = dateText.getText().toString();
                String time = timeText.getText().toString();
                String comment = commentText.getText().toString();

                if (date.length() == 0) dateText.setError("Enter Date!");
                if (time.length() == 0) dateText.setError("Enter Time!");

                if (date.length() > 0 && time.length() > 0 && heartRate >= 0 && systolic >= 0 && diastolic >= 0) {
                    String key = mdatabase.push().getKey();
                    CardiacMeasurement cardiacMeasurement = new CardiacMeasurement(key, yearVar, monthVar, dateVar, hourVar, minuteVar, systolic, diastolic, heartRate, comment);
                    addData(cardiacMeasurement);
                }
            }
        });
    }

    private void openDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                yearVar = year;
                monthVar = month;
                dateVar = date;
                dateText.setText(date + "-" + month + "-" + year);
            }
        }, 2023, 7, 5);

        datePickerDialog.show();
    }

    private void openTimeDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                hourVar = hour;
                minuteVar = minute;
                timeText.setText(hour + ":" + minute);
            }
        }, 12, 0, true);

        timePickerDialog.show();
    }

    public boolean addData(CardiacMeasurement cardiacMeasurement) {
        String key = cardiacMeasurement.getId();
        mdatabase.child("users").child("test").child("measurements").child(key).setValue(cardiacMeasurement).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(AddCardiacMeasurementActivity.this, "Data Added", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(AddCardiacMeasurementActivity.this, HomeActivity.class);
//                startActivity(intent);
                addDataStatus = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddCardiacMeasurementActivity.this, "Error", Toast.LENGTH_LONG).show();
                addDataStatus = false;
            }
        });

        return addDataStatus;
    }
}