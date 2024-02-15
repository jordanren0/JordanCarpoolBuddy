package com.example.jordancarpoolbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/**
 * @author Jordan Ren
 * @version 1.0
 */
public class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView modelText;
    protected TextView typeText;
    protected TextView statusText;
    protected TextView capacityText;
    VehicleAdapter.vehicleListener listener;

    /**
     * This method is a constructor that initializes all properties
     * @param itemView
     * @param listener1
     */
    public VehicleViewHolder(@NonNull View itemView, VehicleAdapter.vehicleListener listener1) {
        super(itemView);

        modelText = itemView.findViewById(R.id.modelTextView);
        typeText = itemView.findViewById(R.id.typeTextView);
        statusText = itemView.findViewById(R.id.statusTextView);
        capacityText = itemView.findViewById(R.id.capacityView);
        listener = listener1;

        itemView.setOnClickListener(this);

    }

    /**
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        listener.vehicleOnClick(getAdapterPosition());
    }
}
