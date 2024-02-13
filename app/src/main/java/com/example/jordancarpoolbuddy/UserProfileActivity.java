package com.example.jordancarpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView userEmail;

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

    public void signOut(View v) {
        mAuth.signOut();
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        System.out.println("Signed Out");
        finish();
    }

    public void seeVehicles(View v) {
        startActivity(new Intent(this, VehicleInfoActivity.class));
        System.out.println("Here:");
    }

    public void goEditProf(View v) {
        startActivity(new Intent(this, UpdateUserProfile.class));
        System.out.println("Here:");
    }

    public void changeTheme(View v){
        if(ThemeHolder.getCurrentTheme().equals("dark"))  ThemeHolder.setCurrentTheme("light");
        else  ThemeHolder.setCurrentTheme("dark");
        startActivity(new Intent(this, UserProfileActivity.class));
    }

    public void whyCarpool(View v){
        startActivity(new Intent(this, WhyCarpool.class));
    }
}