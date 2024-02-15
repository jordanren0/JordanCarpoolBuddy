package com.example.jordancarpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
/**
 * @author Jordan Ren
 * @version 1.0
 */
public class AuthActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public FirebaseFirestore firestore;
    private EditText emailField;
    private EditText passwordField;

    /**
     * This method navigates the user to the AuthActivity page depending on the theme
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ThemeHolder.getCurrentTheme().equals("dark")){
            String layoutName = ThemeHolder.getCurrentTheme() + "_activity_auth";
            int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
            setContentView(layoutId);
        }
        else{
            setContentView(R.layout.activity_auth);
        }


        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        emailField = findViewById(R.id.EditTextEmail);
        passwordField = findViewById(R.id.EditTextPassword);

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
    }

    /**
     * This method signs the user in to the program, and displays a Toast error if the sign in fails
     * @param v
     */
    public void signIn(View v) {
        System.out.println("Log in");
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();
        System.out.printf("Email: %s and Password: %s%n", emailString, passwordString);
        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("SIGN IN", "Successfully signed in the user");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                    updateUI(user);

                }
                else {
                    Log.w("SIGN IN", "SignInUserWithEmailFailure", task.getException());
                    Toast.makeText(getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                    System.out.println(emailString + passwordString);
                }
            }
        });


//        firestore.collection("users").document("1").set(emailString);
    }

    /**
     * This method sets an intent to navigate the user to the Sign Up page
     * @param v
     */
    public void gotoSignUp(View v) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    /**
     * This method sets an intent to navigate the user to the User Profile page
     * @param currentUser
     */
    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
    }

    /**
     * This method changes the theme
     * @param v
     */
    public void changeTheme(View v){
        if(ThemeHolder.getCurrentTheme().equals("dark"))  ThemeHolder.setCurrentTheme("light");
        else  ThemeHolder.setCurrentTheme("dark");
        startActivity(new Intent(this, AuthActivity.class));
    }
}