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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

/**
 * Adds a cardiac measurement into the database
 */
public class AddCardiacMeasurementActivity extends AppCompatActivity {

    /**
     *
     */
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
        /**
         * Hides the TopActionBar
         */
        getSupportActionBar().hide();
        /**
         * Binds the layout file for this activiyt
         */
        setContentView(R.layout.activity_add_cardiac_measurement);

        /**
         * Links view to widget by given id
         */
        heartRateText = findViewById(R.id.heartRate);
        systolicPressureText = findViewById(R.id.systolicPressure);
        diastolicPressureText = findViewById(R.id.diastolicPressure);
        commentText = findViewById(R.id.comment);
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        addBtn = findViewById(R.id.addData);
        addDate = findViewById(R.id.addDate);
        addTime = findViewById(R.id.addTime);

        /**
         * Defines the operation performed by clicking addDate button
         */
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDateDialog();
            }
        });
        /**
         * Defines the operation performed by clicking addTime button
         */
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimeDialog();
            }
        });
        /**
         * Referencing Realtime database to a variable
         */
        mdatabase = FirebaseDatabase.getInstance().getReference();

        /**
         * Defines the operation performed by clicking addBtn button
         */
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Checking if the value of heart rate is only non-negative number
                 */
                int heartRate = -1;
                try {
                    heartRate = Integer.valueOf(heartRateText.getText().toString());
                } catch (Exception e) {
                    heartRateText.setError("HR must be a non-negative integer");
                }
                if (heartRate < 0) heartRateText.setError("HR must be a non-negative integer");

                /**
                 * Checking if the value of systolic blood pressure is only non-negative number
                 */
                int systolic = -1;
                try {
                    systolic = Integer.valueOf(systolicPressureText.getText().toString());
                } catch (Exception e) {
                    systolicPressureText.setError("DBP must be a non-negative integer");
                }
                if (systolic < 0) systolicPressureText.setError("DBP must be a non-negative integer");

                /**
                 * Checking if the value of diastolic blood pressure is only non-negative number
                 */
                int diastolic = -1;
                try {
                    diastolic = Integer.valueOf(diastolicPressureText.getText().toString());
                } catch (Exception e) {
                    diastolicPressureText.setError("DBP must be a non-negative integer");
                }
                if (diastolic < 0) diastolicPressureText.setError("DBP must be a non-negative integer");

                /**
                 * Reads data from widget view
                 */
                String date = dateText.getText().toString();
                String time = timeText.getText().toString();
                String comment = commentText.getText().toString();

                /**
                 * Checking if date or time field is empty
                 */
                if (date.length() == 0) dateText.setError("Enter Date!");
                if (time.length() == 0) dateText.setError("Enter Time!");

                /**
                 * Saving information to database
                 */
                if (date.length() > 0 && time.length() > 0 && heartRate >= 0 && systolic >= 0 && diastolic >= 0) {
                    /**
                     * Generates a unique key
                     */
                    String key = mdatabase.push().getKey();
                    /**
                     * Creates a new instance
                     */
                    CardiacMeasurement cardiacMeasurement = new CardiacMeasurement(key, yearVar, monthVar, dateVar, hourVar, minuteVar, systolic, diastolic, heartRate, comment);
                    addData(cardiacMeasurement);
                }
            }
        });
    }

    /**
     * Opens a Date picker to pick date
     */
    private void openDateDialog() {
        /**
         * Creating a new date picker
         */
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            /**
             * Provides the date selected as integers
             * @param datePicker reference to the picker
             * @param year year selected
             * @param month month selected
             * @param date date selected
             */
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                yearVar = year;
                monthVar = month;
                dateVar = date;
                dateText.setText(date + "-" + month + "-" + year);
            }
        }, 2023, 7, 5);

        /**
         * Shwoing the dialog
         */
        datePickerDialog.show();
    }

    /**
     * Opens a Time picker to pick date
     */
    private void openTimeDialog() {
        /**
         * Creating a new time picker
         */
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            /**
             * Provides the time selected
             * @param timePicker the timepicker itself
             * @param hour the hour selected
             * @param minute the minute seledted
             */
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                hourVar = hour;
                minuteVar = minute;
                timeText.setText(hour + ":" + minute);
            }
        }, 12, 0, true);
        /**
         * Shwoing the dialog
         */
        timePickerDialog.show();
    }

    /**
     * Adds measurement data to database
     * @param cardiacMeasurement the measurement data inputted
     * @return a boolean variable indicating successful data addition
     */
    public boolean addData(CardiacMeasurement cardiacMeasurement) {
        String key = cardiacMeasurement.getId();
        mdatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("measurements").child(key).setValue(cardiacMeasurement).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(AddCardiacMeasurementActivity.this, "Data Added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddCardiacMeasurementActivity.this, HomeActivity.class);
                startActivity(intent);
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