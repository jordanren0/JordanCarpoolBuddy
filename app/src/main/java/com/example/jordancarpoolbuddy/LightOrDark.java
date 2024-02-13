package com.example.jordancarpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LightOrDark extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_or_dark);

        // Find the light theme button and set a click listener
        Button lightThemeButton = findViewById(R.id.lightTheme);
        lightThemeButton.setOnClickListener(new View.OnClickListener() {
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
