package com.example.android.finalproject_ningavimarie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EmailPassword extends AppCompatActivity {

    private static final String TAG2 = "EmailPassword";


    private FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;
    public EditText userEmailText;
    public EditText userPasswordText;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference mDatabase;

    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);

        userEmailText = findViewById(R.id.user_email_edit_text);
        userPasswordText = findViewById(R.id.user_password_edit_text);
        signInButton = findViewById(R.id.submitUserLogIn);

        mStatusTextView = findViewById(R.id.user_sign_in_info);

        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d(TAG2, "onAuthStateChanged: signed in" + user.getUid());
                    Toast.makeText(EmailPassword.this, "Successfully signed in with " + user.getEmail(), Toast.LENGTH_SHORT).show();

                } else {
                    Log.d(TAG2, "onAuthStateChanged: signed out");

                }
            }
        };

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = userEmailText.getText().toString();
                String password = userPasswordText.getText().toString();
                if (!email.equals("") && !password.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, password);
                } else {
                    Toast.makeText(EmailPassword.this, "You did not fill the fields correctly", Toast.LENGTH_SHORT).show();
                }


                mDatabase = FirebaseDatabase.getInstance().getReference();
                Order obj = new Order();

                HashMap<String, String> dataMap = obj.firebaseMap();
                dataMap.put("User Name", email);
                mDatabase.child("Reservation").child("User Name").setValue(email);

                Intent signInIntent = new Intent(EmailPassword.this, PreOrder.class);
                startActivity(signInIntent);


            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG2, "signIn:" + email);
        if (!validateForm()) {
            return;

        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Log.d(TAG2, "signInWithEmail: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.w(TAG2, "signInWithEmail:failure", task.getException());
                    Toast.makeText(EmailPassword.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

               /* if (!task.isSuccessful()){

                }*/
            }

        });

    }


    private void updateUI(FirebaseUser user) {

        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));


            // findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText(R.string.signed_out);


            findViewById(R.id.user_password_edit_text).setVisibility(View.VISIBLE);
            findViewById(R.id.user_email_edit_text).setVisibility(View.VISIBLE);
            //findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = userEmailText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            userEmailText.setError("Required.");
            valid = false;
        } else {
            userEmailText.setError(null);
        }

        String password = userPasswordText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            userPasswordText.setError("Required.");
            valid = false;
        } else {
            userPasswordText.setError(null);
        }

        return valid;
    }

    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.submitUserLogIn) {
            signIn(userEmailText.getText().toString(), userPasswordText.getText().toString());


        }


    }
}
