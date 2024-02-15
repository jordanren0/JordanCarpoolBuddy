package com.example.jordancarpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
/**
 * @author Jordan Ren
 * @version 1.0
 */
public class VehicleProfileActivity extends AppCompatActivity {
    TextView modelTextView;
    TextView typeTextView;
    TextView priceTextView;
    TextView IDTextView;
    TextView openTextView;

    Vehicle chosen;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fb;

    /**
     * This method navigates the user to the VehicleProfile page depending on the theme
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ThemeHolder.getCurrentTheme().equals("dark")){
            String layoutName = ThemeHolder.getCurrentTheme() + "_activity_vehicle_profile";
            int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
            setContentView(layoutId);
        }
        else{
            setContentView(R.layout.activity_vehicle_profile);
        }

        mAuth = FirebaseAuth.getInstance();
        fb = FirebaseFirestore.getInstance();

        modelTextView = findViewById(R.id.ModelQ);
        typeTextView = findViewById(R.id.TypeQ);
        IDTextView = findViewById(R.id.IDQ);
        priceTextView = findViewById(R.id.PriceQ);
        openTextView = findViewById(R.id.OpenQ);

        chosen = VehicleInfoActivity.vehicle;

        String nameText = chosen.getModel();
        String typeText = chosen.getVehicleType();
        String IdText = chosen.getVehicleID();
        String openText;
        if(chosen.isOpen().toString().equals("true")) openText = "Avaliable";
        else openText = "Not Avaliable";
//        String openText = chosen.isOpen().toString();
        String priceText = chosen.getBasePrice().toString();

        modelTextView.setText(nameText);
        typeTextView.setText(typeText);
        IDTextView.setText(IdText);
        priceTextView.setText(priceText);
        openTextView.setText(openText);

    }

    /**
     * This method sets the current vehicle to not avaliable
     * @param v
     */
    public void closeV(View v) {
        fb.collection("Vehicle").whereEqualTo("model", chosen.getModel())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                    String ID = ds.getId();
                    fb.collection("Vehicle").document(ID).update("open", false);
                }
            }
        });
        startActivity(new Intent(this, VehicleInfoActivity.class));

    }

    /**
     * This method sets the current vehicle to avaliable
     * @param v
     */
    public void openV(View v) {
        fb.collection("Vehicle").whereEqualTo("model", chosen.getModel())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                    String ID = ds.getId();
                    fb.collection("Vehicle").document(ID).update("open", true);
                }
            }
        });
        startActivity(new Intent(this, VehicleInfoActivity.class));
    }
}