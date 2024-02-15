package com.example.jordancarpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
/**
 * @author Jordan Ren
 * @version 1.0
 */
public class VehicleInfoActivity extends AppCompatActivity implements VehicleAdapter.vehicleListener {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    public FirebaseFirestore firebase;
    private Vehicle vehicleInfo;
    private ArrayList<Vehicle> vehiclesList;

    public static Vehicle vehicle;

    RecyclerView recView;
    private ArrayList<String> mdata;
    private ArrayList<String> tdata;
    private ArrayList<String> sdata;
    private ArrayList<String> cdata;
    private VehicleAdapter myAdapter;


    /**
     * This method navigates the user to the VehicleInfo page depending on the theme
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ThemeHolder.getCurrentTheme().equals("dark")){
            String layoutName = ThemeHolder.getCurrentTheme() + "_activity_vehicle_info";
            int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
            setContentView(layoutId);
        }
        else{
            setContentView(R.layout.activity_vehicle_info);
        }

        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();
        vehiclesList = new ArrayList<>();
        recView = findViewById(R.id.vehiclesRecView);
        user = mAuth.getCurrentUser();

        mdata = new ArrayList();
        tdata = new ArrayList();
        sdata = new ArrayList();
        cdata = new ArrayList();

        myAdapter = new VehicleAdapter(mdata, tdata, sdata, cdata,this);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
        getAndPopulateData();
    }

    /**
     * This method adds all vehicles with their properties to the recycler view
     */
    public void getAndPopulateData() {
        firebase.collection("Vehicle").whereEqualTo("owner", user.getEmail())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                        Vehicle vehicle = ds.toObject(Vehicle.class);
                        vehiclesList.add(vehicle);
                    }

                    for (Vehicle eachVehicle : vehiclesList) {
                        String eachModel = eachVehicle.getModel();
                        mdata.add(eachModel);

                        String eachType = eachVehicle.getVehicleType();
                        tdata.add("Vehicle Type: " + eachType);

                        String eachStatus = eachVehicle.isOpen().toString();
                        if(eachStatus.equals("true")) sdata.add("Vehicle Status: Avaliable");
                        else sdata.add("Vehicle Status: Not Avaliable");
//                        sdata.add(eachStatus);

                        String eachCapacity = String.valueOf(eachVehicle.getCapacity());
                        cdata.add("Vehicle Capacity: " + eachCapacity);
                    }

                    myAdapter.newData(mdata, tdata, sdata, cdata);
                    myAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getApplicationContext(), "you don't have any vehicle yet", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * This method creates an intent that navigates the user to the AddVehicleActivity class
     * @param v
     */
    public void toAddVehicle(View v) {
        startActivity(new Intent(this, AddVehicleActivity.class));
    }

    /**
     * This method creates an intent that navigates the user to the BookVehicleInfo class
     * @param v
     */
    public void toBookVehicle(View v) {
        startActivity(new Intent(this, BookVehicleInfo.class));
    }

    /**
     * This method creates an intent that navigates the user to the VehicleProfileActivity class
     * @param p
     */
    @Override
    public void vehicleOnClick(int p) {
        vehicle = vehiclesList.get(p);
        startActivity(new Intent(this, VehicleProfileActivity.class));
    }
}