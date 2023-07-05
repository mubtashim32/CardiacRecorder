package com.example.cardiacrecorder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardiacMeasurementsAdapter extends
        RecyclerView.Adapter<CardiacMeasurementsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView systolicPressure;
        public TextView diastolicPressure;
        public TextView heartRate;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            date = itemView.findViewById(R.id.date);
            systolicPressure = itemView.findViewById(R.id.systolicPressure);
            diastolicPressure = itemView.findViewById(R.id.diastolicPressure);
            heartRate = itemView.findViewById(R.id.heartRate);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();

            CardiacMeasurement cardiacMeasurement = cardiacMeasurementArrayList.get(position);

            String id = cardiacMeasurement.getId();
            String date = cardiacMeasurement.getMeasuredDate();
            int sysloticPressure = cardiacMeasurement.getSystolicPressure();
            int diastolicPressure = cardiacMeasurement.getDiastolicPressure();
            int heartRate = cardiacMeasurement.getHeartRate();

            Intent intent = new Intent(context, UpdateDeleteCardiacMeasurementActivtiy.class);

            intent.putExtra("id", id);
            intent.putExtra("date", date);
            intent.putExtra("systolicPressure", Integer.toString(sysloticPressure));
            intent.putExtra("diastolicPressure", Integer.toString(diastolicPressure));
            intent.putExtra("heartRate", Integer.toString(heartRate));

            context.startActivity(intent);
        }
    }

    private ArrayList<CardiacMeasurement> cardiacMeasurementArrayList;
    private Context context;

    public CardiacMeasurementsAdapter(Context context, ArrayList<CardiacMeasurement> cardiacMeasurementArrayList) {
        this.context = context;
        this.cardiacMeasurementArrayList = cardiacMeasurementArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View cardiacMeasurementView = layoutInflater.inflate(R.layout.cardiac_measurement, parent, false);

        ViewHolder viewHolder = new ViewHolder(cardiacMeasurementView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardiacMeasurementsAdapter.ViewHolder holder, int position) {
        CardiacMeasurement cardiacMeasurement = cardiacMeasurementArrayList.get(position);

        TextView date = holder.date;
        TextView systolicPressure = holder.systolicPressure;
        TextView diastolicPressure = holder.diastolicPressure;
        TextView heartRate = holder.heartRate;

        date.setText(cardiacMeasurement.getMeasuredDate());
        systolicPressure.setText(Integer.toString(cardiacMeasurement.getSystolicPressure()));
        diastolicPressure.setText(Integer.toString(cardiacMeasurement.getDiastolicPressure()));
        heartRate.setText("Heart Rate: " + Integer.toString(cardiacMeasurement.getHeartRate()));
    }

    @Override
    public int getItemCount() {
        return cardiacMeasurementArrayList.size();
    }
}
