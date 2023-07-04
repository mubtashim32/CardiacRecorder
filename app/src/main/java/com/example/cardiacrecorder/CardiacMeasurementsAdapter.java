package com.example.cardiacrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardiacMeasurementsAdapter extends
        RecyclerView.Adapter<CardiacMeasurementsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView systolicPressure;
        public TextView diastolicPressure;
        public TextView heartRate;

        public ViewHolder(View itemView) {
            super(itemView);
            systolicPressure = itemView.findViewById(R.id.systolicPressure);
            diastolicPressure = itemView.findViewById(R.id.diastolicPressure);
            heartRate = itemView.findViewById(R.id.heartRate);
        }
    }

    private ArrayList<CardiacMeasurement> cardiacMeasurementArrayList;

    public CardiacMeasurementsAdapter(ArrayList<CardiacMeasurement> cardiacMeasurementArrayList) {
        this.cardiacMeasurementArrayList = cardiacMeasurementArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View cardiacMeasurementView = layoutInflater.inflate(R.layout.cardiac_measurement, parent, false);

        ViewHolder viewHolder = new ViewHolder(cardiacMeasurementView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardiacMeasurementsAdapter.ViewHolder holder, int position) {
        CardiacMeasurement cardiacMeasurement = cardiacMeasurementArrayList.get(position);

        TextView systolicPressure = holder.systolicPressure;
        TextView diastolicPressure = holder.diastolicPressure;
        TextView heartRate = holder.heartRate;
        systolicPressure.setText(Integer.toString(cardiacMeasurement.getSystolicPressure()));
        diastolicPressure.setText(Integer.toString(cardiacMeasurement.getDiastolicPressure()));
        heartRate.setText(Integer.toString(cardiacMeasurement.getHeartRate()));
    }

    @Override
    public int getItemCount() {
        return cardiacMeasurementArrayList.size();
    }
}
