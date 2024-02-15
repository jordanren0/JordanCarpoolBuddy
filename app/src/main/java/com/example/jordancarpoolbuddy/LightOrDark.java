package com.example.jordancarpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author Jordan Ren
 * @version 1.0
 */
public class LightOrDark extends AppCompatActivity{

    /**
     * This method sets the theme to light or dark depending on what button the user presses
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_or_dark);

        // Find the light theme button and set a click listener
        Button lightThemeButton = findViewById(R.id.lightTheme);
        lightThemeButton.setOnClickListener(new View.OnClickListener() {

            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                // Start the new activity
                System.out.println("Lightskin");
                ThemeHolder.setCurrentTheme("light");
                startActivity(new Intent(LightOrDark.this, AuthActivity.class));
                finish(); // Close the theme selector activity
            }
        });

        // Find the dark theme button and set a click listener
        Button darkThemeButton = findViewById(R.id.darkTheme);
        darkThemeButton.setOnClickListener(new View.OnClickListener() {

            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                // Start the new activity
                System.out.println("Darkskin");
                ThemeHolder.setCurrentTheme("dark");
                startActivity(new Intent(LightOrDark.this, AuthActivity.class));
                finish(); // Close the theme selector activity
            }
        });
    }
}
