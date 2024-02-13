package com.example.jordancarpoolbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllVehicleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView modelText;
    protected TextView typeText;
    protected TextView statusText;

    protected TextView capacityText;
    AllVehicleAdapter.vehicleListener listener;

    public AllVehicleHolder(@NonNull View itemView, AllVehicleAdapter.vehicleListener listener1) {
        super(itemView);

        modelText = itemView.findViewById(R.id.modelTextView);
        typeText = itemView.findViewById(R.id.typeTextView);
        statusText = itemView.findViewById(R.id.statusTextView);
        capacityText = itemView.findViewById(R.id.capacityView);
        listener = listener1;

        itemView.setOnClickListener(this);
    }

    public void onClick(View view) {
        listener.vehicleOnClick(getAdapterPosition());
    }
}
