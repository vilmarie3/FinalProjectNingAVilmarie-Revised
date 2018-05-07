package com.example.android.finalproject_ningavimarie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LogIn extends MainActivity implements View.OnClickListener {

    private static final String TAG = "Activity";


    private FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;


    public FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        mAuth = FirebaseAuth.getInstance();

        //findViewById(R.id.sign_in_button).setOnClickListener(this);

        //mStatusTextView = findViewById(R.id.info);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d(TAG, "onAuthStateChanged: signed in" + user.getUid());

                    Toast.makeText(LogIn.this, "Successfully signed in with " + user.getEmail(), Toast.LENGTH_SHORT).show();

                } else {
                    Log.d(TAG, "onAuthStateChanged: signed out");

                }
            }
        };

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onStop() {

        super.onStop();

        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    public void goToSignIn(View view) {

        Intent intent = new Intent(LogIn.this, EmailPassword.class);
        startActivity(intent);
    }

    public void goToSignUP(View view) {

        Intent intent = new Intent(LogIn.this, NewUserLogIn.class);
        startActivity(intent);
    }
}

