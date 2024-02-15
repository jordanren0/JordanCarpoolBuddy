package com.example.jordancarpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
/**
 * @author Jordan Ren
 * @version 1.0
 */
public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView userEmail;

    /**
     * This method navigates the user to the UserProfileActivity page depending on the theme
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ThemeHolder.getCurrentTheme().equals("dark")){
            String layoutName = ThemeHolder.getCurrentTheme() + "_activity_user_profile";
            int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
            setContentView(layoutId);
        }
        else{
            setContentView(R.layout.activity_user_profile);
        }


        mAuth = FirebaseAuth.getInstance();
        userEmail = findViewById(R.id.EmailInfo);

        String userEmailText = mAuth.getCurrentUser().getEmail();
        userEmail.setText(userEmailText);
    }

    /**
     * This method signs the user out
     * @param v
     */
    public void signOut(View v) {
        mAuth.signOut();
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        System.out.println("Signed Out");
        finish();
    }

    /**
     * This method creates the intent that navigates the user to the VehicleInfoActivity class
     * @param v
     */
    public void seeVehicles(View v) {
        startActivity(new Intent(this, VehicleInfoActivity.class));
        System.out.println("Here:");
    }

    /**
     * This method creates the intent that navigates the user to the UpdateUserProfile class
     * @param v
     */
    public void goEditProf(View v) {
        startActivity(new Intent(this, UpdateUserProfile.class));
        System.out.println("Here:");
    }

    /**
     * This method changes the theme
     * @param v
     */
    public void changeTheme(View v){
        if(ThemeHolder.getCurrentTheme().equals("dark"))  ThemeHolder.setCurrentTheme("light");
        else  ThemeHolder.setCurrentTheme("dark");
        startActivity(new Intent(this, UserProfileActivity.class));
    }

    /**
     * This method creates the intent that navigates the user to the WhyCarpool class
     * @param v
     */
    public void whyCarpool(View v){
        startActivity(new Intent(this, WhyCarpool.class));
    }
}