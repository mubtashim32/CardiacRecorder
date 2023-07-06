package com.example.cardiacrecorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Populates data in the recycler view
 */
public class CardiacMeasurementsAdapter extends
        RecyclerView.Adapter<CardiacMeasurementsAdapter.ViewHolder> {

    /**
     * Provides a direct references of each view in a measurement item
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView systolicPressure;
        public TextView diastolicPressure;
        public TextView heartRate;
        public TextView date;
        public TextView time;

        /**
         * Binds the views with widgets by id
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            date = itemView.findViewById(R.id.date);
            systolicPressure = itemView.findViewById(R.id.systolicPressure);
            diastolicPressure = itemView.findViewById(R.id.diastolicPressure);
            heartRate = itemView.findViewById(R.id.heartRate);
            time = itemView.findViewById(R.id.time);
        }

        /**
         * On click listener for each item
         * @param view measurement item view
         */
        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();

            CardiacMeasurement cardiacMeasurement = cardiacMeasurementArrayList.get(position);

            String id = cardiacMeasurement.getId();
            int year = cardiacMeasurement.getYear();
            int month = cardiacMeasurement.getMonth();
            int date = cardiacMeasurement.getDate();
            int hour = cardiacMeasurement.getHour();
            int minute = cardiacMeasurement.getMinute();

            int sysloticPressure = cardiacMeasurement.getSystolicPressure();
            int diastolicPressure = cardiacMeasurement.getDiastolicPressure();
            int heartRate = cardiacMeasurement.getHeartRate();
            String comment = cardiacMeasurement.getComment();

            Intent intent = new Intent(context, UpdateDeleteCardiacMeasurementActivtiy.class);

            intent.putExtra("id", id);
            intent.putExtra("date", date);
            intent.putExtra("month", month);
            intent.putExtra("year", year);
            intent.putExtra("hour", hour);
            intent.putExtra("minute", minute);

            intent.putExtra("systolicPressure", Integer.toString(sysloticPressure));
            intent.putExtra("diastolicPressure", Integer.toString(diastolicPressure));
            intent.putExtra("heartRate", Integer.toString(heartRate));
            intent.putExtra("comment", comment);

            context.startActivity(intent);
        }
    }

    private ArrayList<CardiacMeasurement> cardiacMeasurementArrayList;
    private Context context;

    /**
     * Initializes the adapter
     * @param context state of the application
     * @param cardiacMeasurementArrayList list that contains the measurement items
     */
    public CardiacMeasurementsAdapter(Context context, ArrayList<CardiacMeasurement> cardiacMeasurementArrayList) {
        this.context = context;
        this.cardiacMeasurementArrayList = cardiacMeasurementArrayList;
    }

    /**
     * Binding layout to view holder and returning the holder
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return the binded holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View cardiacMeasurementView = layoutInflater.inflate(R.layout.cardiac_measurement, parent, false);

        ViewHolder viewHolder = new ViewHolder(cardiacMeasurementView);
        return viewHolder;
    }

    /**
     * Populating the item view through holder
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CardiacMeasurementsAdapter.ViewHolder holder, int position) {
        CardiacMeasurement cardiacMeasurement = cardiacMeasurementArrayList.get(position);

        TextView date = holder.date;
        TextView time = holder.time;
        TextView systolicPressure = holder.systolicPressure;
        TextView diastolicPressure = holder.diastolicPressure;
        TextView heartRate = holder.heartRate;

        String minute = Integer.toString(cardiacMeasurement.getMinute());
        if (minute.length() == 1) minute = "0" + minute;
        String hour = Integer.toString(cardiacMeasurement.getHour());
        if (hour.length() == 1) hour = "0" + hour;

        date.setText("Date: " + cardiacMeasurement.getDate() + "-" + cardiacMeasurement.getMonth() + "-" + cardiacMeasurement.getYear());
        time.setText("Time: " + hour + ":" + minute);
        systolicPressure.setText(Integer.toString(cardiacMeasurement.getSystolicPressure()));
        diastolicPressure.setText(Integer.toString(cardiacMeasurement.getDiastolicPressure()));
        heartRate.setText("Heart Rate: " + cardiacMeasurement.getHeartRate());
        int sbp = cardiacMeasurement.getSystolicPressure(), dbp = cardiacMeasurement.getDiastolicPressure();
        if (sbp < 90 || sbp > 140) {
            systolicPressure.setTextColor(Color.parseColor("#D32F2F"));
        }
        if (dbp < 60 || dbp > 90) {
            diastolicPressure.setTextColor(Color.parseColor("#D32F2F"));
        }

    }

    /**
     * Counts the number of items in the list
     * @return the count of items
     */
    @Override
    public int getItemCount() {
        return cardiacMeasurementArrayList.size();
    }
}
