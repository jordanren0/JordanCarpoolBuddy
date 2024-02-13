package com.example.jordancarpoolbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllVehicleAdapter extends RecyclerView.Adapter<AllVehicleHolder> {
    ArrayList<String> vmodelData;
    ArrayList<String> vtypeData;
    ArrayList<String> vstatusData;
    ArrayList<String> vcapacityData;

    private vehicleListener listener;

    public AllVehicleAdapter(ArrayList modelData, ArrayList typeData, ArrayList statusData, ArrayList capacityData, vehicleListener listener1) {
        vmodelData = modelData;
        vtypeData = typeData;
        vstatusData = statusData;
        vcapacityData = capacityData;
        listener = listener1;
    }

    @NonNull
    @Override
    public AllVehicleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_row_view, parent, false);
//        AllVHolder holder = new AllVHolder(myView, listener);
//        return holder;

        View myView;
        if(ThemeHolder.getCurrentTheme().equals("dark")) {
            String layoutName = ThemeHolder.getCurrentTheme() + "_vehicle_row_view";
            int layoutId = parent.getContext().getResources().getIdentifier(layoutName, "layout", parent.getContext().getPackageName());
            myView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        } else {
            myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_row_view, parent, false);
        }
        return new AllVehicleHolder(myView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AllVehicleHolder holder, int position) {
        holder.modelText.setText(vmodelData.get(position));
        holder.statusText.setText(vstatusData.get(position));
        holder.typeText.setText(vtypeData.get(position));
        holder.capacityText.setText(vcapacityData.get(position));
    }


    @Override
    public int getItemCount() {
        return vmodelData.size();
    }

    public void newData(ArrayList modelData, ArrayList typeData, ArrayList statusData, ArrayList capacityData) {
        vmodelData = modelData;
        vtypeData = typeData;
        vstatusData = statusData;
        vcapacityData = capacityData;
    }

    public interface vehicleListener {
        void vehicleOnClick(int p);

    }
}
