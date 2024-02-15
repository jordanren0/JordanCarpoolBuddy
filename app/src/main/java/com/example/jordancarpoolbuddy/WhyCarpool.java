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
public class WhyCarpool extends AppCompatActivity{
    /**
     * This method navigates the user to the WhyCarpool page depending on the theme
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ThemeHolder.getCurrentTheme().equals("dark")){
            String layoutName = ThemeHolder.getCurrentTheme() + "_why_carpool";
            int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
            setContentView(layoutId);
        }
        else{
            setContentView(R.layout.why_carpool);
        }
    }

    /**
     * This method creates an intent that navigates the user to the UserProfileActivity class
     */
    public void goBack(){
        startActivity(new Intent(this, UserProfileActivity.class));
    }
}
