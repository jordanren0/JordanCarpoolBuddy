package com.example.jordancarpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
/**
 * @author Jordan Ren
 * @version 1.0
 */
public class AddVehicleActivity extends AppCompatActivity {

    EditText model;
    EditText type;
    EditText capacity;
    EditText iD;
    EditText price;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebase;

    /**
     * This method navigates the user to the AddVehicle page depending on the theme
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ThemeHolder.getCurrentTheme().equals("dark")){
            String layoutName = ThemeHolder.getCurrentTheme() + "_activity_add_vehicle";
            int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
            setContentView(layoutId);
        }
        else{
            setContentView(R.layout.activity_add_vehicle);
        }

        model = (EditText) findViewById(R.id.vModel);
        type = (EditText) findViewById(R.id.vType);
        capacity = (EditText) findViewById(R.id.vCapacity);
        iD = (EditText) findViewById(R.id.vID);
        price = (EditText) findViewById(R.id.vPrice);


        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();

    }

    /**
     * This method displays a Toast error if the user does not fill out all the boxes
     * @return boolean
     */
    public boolean formValid() {
        if (model != null && type != null && capacity != null && iD != null && price != null) {
            return true;
        }
        Toast.makeText(getApplicationContext(), "Please fill in all boxes", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * This method allows the user to add a new vehicle, dependant on the user's input of the model, type, capacity and price
     * @param v
     */
    public void addNewVehicle(View v) {
        if (formValid()) {
            String vModel = model.getText().toString();
            String vType = type.getText().toString();
            int vCapa = Integer.parseInt(capacity.getText().toString());
            String vID = iD.getText().toString();
            double vPrice = Double.parseDouble(String.valueOf(price.getText()));

            FirebaseUser addVehicleUser = mAuth.getCurrentUser();
            if (addVehicleUser != null) {
                Boolean open = true;
                String vcOwner = mAuth.getCurrentUser().getEmail();
                Vehicle newVehicle = new Vehicle(vcOwner, vModel, vCapa, vID, open, vType, vPrice);

                firebase.collection("Vehicle").add(newVehicle);
            } else {
                Toast.makeText(getApplicationContext(), "Please Log In First", Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(this, VehicleInfoActivity.class));
        }
    }
}