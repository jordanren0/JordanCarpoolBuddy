package com.example.jordancarpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public FirebaseFirestore firebase;
    private EditText name;
    private EditText type;
    private EditText email;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ThemeHolder.getCurrentTheme().equals("dark")){
            String layoutName = ThemeHolder.getCurrentTheme() + "_activity_sign_up";
            int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
            setContentView(layoutId);
        }
        else{
            setContentView(R.layout.activity_sign_up);
        }

        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();
        name = (EditText) findViewById(R.id.userNameInput);
        type = (EditText) findViewById(R.id.userTypeInput);
        email = (EditText) findViewById(R.id.userEmailInput);
        password = (EditText) findViewById(R.id.userPasswordInput);

    }

    public void signUp(View v) {
        String nameString = name.getText().toString();
        String typeString = type.getText().toString();
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        ArrayList<String> vehicles = new ArrayList<>();

        System.out.println(emailString + passwordString);

        if(!emailString.contains("@cis.edu.hk")){
            Toast.makeText(getApplicationContext(), "Must sign up with CIS Email", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        System.out.println("dub");
                        User newUser = new User(nameString, emailString, typeString, passwordString, 300.0, vehicles);
                        firebase.collection("User").add(newUser);
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println("L");
                        Toast.makeText(getApplicationContext(), "Something's wrong", Toast.LENGTH_SHORT).show();
                        Log.w("SIGN UP", "SignUpUserWithEmailFailure", task.getException());
                    }
                }
            });
        }


    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
        }
    }
}