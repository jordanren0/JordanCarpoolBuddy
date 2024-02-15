package com.example.jordancarpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
public class BookVehicleProfile extends AppCompatActivity {
    TextView modelTextView;
    TextView typeTextView;
    TextView priceTextView;
    TextView IDTextView;
    TextView openTextView;

    TextView capacityView;

    Vehicle chosen;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fb;

    /**
     * This method navigates the user to the BookVehicleProfile page depending on the theme
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ThemeHolder.getCurrentTheme().equals("dark")){
            String layoutName = ThemeHolder.getCurrentTheme() + "_activity_book_vehicle_profile";
            int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
            setContentView(layoutId);
        }
        else{
            setContentView(R.layout.activity_book_vehicle_profile);
        }


        mAuth = FirebaseAuth.getInstance();
        fb = FirebaseFirestore.getInstance();

        modelTextView = findViewById(R.id.Modeltext);
        typeTextView = findViewById(R.id.Typetext);
        IDTextView = findViewById(R.id.IDtext);
        priceTextView = findViewById(R.id.Pricetext);
        openTextView = findViewById(R.id.Opentext);
        capacityView = findViewById(R.id.CapacityText);

        chosen = BookVehicleInfo.vehicle;

        String nameText = chosen.getModel();
        String typeText = chosen.getVehicleType();
        String IdText = chosen.getVehicleID();
        String capacity = String.valueOf(chosen.getCapacity());
//        findViewById(R.id.CapacityText) = capacity;
//        String openText = chosen.isOpen().toString();
        String openText;
        if(chosen.isOpen().toString().equals("true")) openText = "Avaliable";
        else openText = "Not Avaliable";
        String priceText = chosen.getBasePrice().toString();

        capacityView.setText(capacity);
        modelTextView.setText(nameText);
        typeTextView.setText(typeText);
        IDTextView.setText(IdText);
        priceTextView.setText(priceText);
        openTextView.setText(openText);
    }

    /**
     * This method allows the user to book a vehicle, and displays a Toast message if the vehicle is closed or is full
     * @param v
     */
    public void bookV(View v) {
        if (chosen.isOpen()) {
            fb.collection("Vehicle").whereEqualTo("model", chosen.getModel())
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                        String ID = ds.getId();
                        fb.collection("Vehicle").document(ID).update("capacity", chosen.getCapacity() - 1);
                    }
                }
            });
        }
        else if(chosen.getCapacity() == 0) Toast.makeText(getApplicationContext(), "Vehicle is full, book another one", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(getApplicationContext(), "Vehicle is not avaliable, book another one", Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(), "Vehicle is full, book another one", Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(this, BookVehicleInfo.class));
    }
}