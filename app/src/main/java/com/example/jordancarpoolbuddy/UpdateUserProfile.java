package com.example.jordancarpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * @author Jordan Ren
 * @version 1.0
 */
public class UpdateUserProfile extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public FirebaseFirestore firebase;
    private EditText name;
    private EditText type;
    private EditText email;
    private EditText password;
    private EditText confirmedPass;

    /**
     * This method navigates the user to the UpdateUserProfile page depending on the theme
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ThemeHolder.getCurrentTheme().equals("dark")){
            String layoutName = ThemeHolder.getCurrentTheme() + "_activity_update_user_profile";
            int layoutId = getResources().getIdentifier(layoutName, "layout", getPackageName());
            setContentView(layoutId);
        }
        else{
            setContentView(R.layout.activity_update_user_profile);
        }

        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();
        name = (EditText) findViewById(R.id.resetName);
        type = (EditText) findViewById(R.id.resetType);
        email = (EditText) findViewById(R.id.resetEmail);
        password = (EditText) findViewById(R.id.resetPassword);
        confirmedPass = (EditText) findViewById(R.id.confirmPassword);

    }

    /**
     * This method allows the user to reset their email and password
     * A Toast error is displayed if the user does not sign up with a CIS email or if the two passwords do not match
     * @param v
     */
    public void reset(View v) {
        String nameString = name.getText().toString();
        String typeString = type.getText().toString();
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String confirmString = confirmedPass.getText().toString();

        if(!emailString.contains("@cis.edu.hk")){
            Toast.makeText(getApplicationContext(), "Must sign up with CIS Email", Toast.LENGTH_SHORT).show();
        }
        else if(!passwordString.equals(confirmString)){
            Toast.makeText(getApplicationContext(), "Your passwords must match", Toast.LENGTH_SHORT).show();
        }
        else{
            firebase.collection("User").whereEqualTo("email", mAuth.getCurrentUser().getEmail())
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                                String ID = ds.getId();
                                firebase.collection("User").document(ID).update("name", nameString);
                                firebase.collection("User").document(ID).update("userType", typeString);
                                firebase.collection("User").document(ID).update("email", emailString);
                                firebase.collection("User").document(ID).update("password", passwordString);
//                               mAuth.getCurrentUser().updatePassword(passwordString);
//                               mAuth.getCurrentUser().updateEmail(emailString);
                            }
                        }
                    });
        }
        startActivity(new Intent(this, UserProfileActivity.class));
    }
}