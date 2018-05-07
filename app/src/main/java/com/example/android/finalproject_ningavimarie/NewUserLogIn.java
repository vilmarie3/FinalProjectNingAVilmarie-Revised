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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class NewUserLogIn extends AppCompatActivity {
    public EditText nameText;
    public EditText emailText;
    public EditText passwordText;
    private Button signUpButton;
    private TextView mStatusTextView;
   // public FirebaseDatabase database = FirebaseDatabase.getInstance();
    //public DatabaseReference userRef = database.getReference("User Information");
   // public DatabaseReference newReservation = database.getReference("Reservations");
    private FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG3 = "EmailPassword";
    private ArrayList<Order> itemsInOrder = new ArrayList<>();
    public DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_log_in);
        nameText = findViewById(R.id.new_user_name_edit_text);
        emailText = findViewById(R.id.new_user_email_edit_text);
        passwordText = findViewById(R.id.new_user_password_edit_text);
        signUpButton = findViewById(R.id.new_user_signup_button);

        mAuth = FirebaseAuth.getInstance();



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(emailText.getText().toString(), passwordText.getText().toString());
                String userName = nameText.getText().toString();


                mDatabase = FirebaseDatabase.getInstance().getReference();
                Order obj = new Order();

                HashMap<String, String> dataMap = obj.firebaseMap();
                dataMap.put("User Name", userName);
                mDatabase.child("Reservation").child("User Name").setValue(userName);


                Intent logInIntent = new Intent(NewUserLogIn.this, PreOrder.class);
                startActivity(logInIntent);
            }
        });
        mStatusTextView = findViewById(R.id.sign_up_info);
    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void createAccount(String email, String password) {

        Log.d(TAG3, "createAccount: " + email);
        if (!validateForm()) {
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG3, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);

                } else {
                    Log.w(TAG3, "createUserWithEmail:failure", task.getException());
                    //Toast.makeText(NewUserLogIn.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }


            }
        });
    }
    private void updateUI(FirebaseUser user) {

        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));


            /*findViewById(R.id.user_email_edit_text).setVisibility(View.GONE);
            findViewById(R.id.user_password_edit_text).setVisibility(View.GONE);
            findViewById(R.id.new_user_signup_button).setVisibility(View.VISIBLE);*/

            // findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
        } else {
            //mStatusTextView.setText(R.string.signed_out);


            //findViewById(R.id.user_password_edit_text).setVisibility(View.VISIBLE);
            //findViewById(R.id.user_email_edit_text).setVisibility(View.VISIBLE);
            //findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailText.setError("Required.");
            valid = false;
        } else {
            emailText.setError(null);
        }

        String password = passwordText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordText.setError("Required.");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
    private void signIn(String email, String password) {
        Log.d(TAG3, "signIn:" + email);
        if (!validateForm()) {
            return;

        }



        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Log.d(TAG3, "signInWithEmail: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.w(TAG3, "signInWithEmail:failure", task.getException());
                    //Toast.makeText(NewUserLogIn.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

               /* if (!task.isSuccessful()){

                }*/
            }

        });

    }
    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.new_user_signup_button) {

            signIn(emailText.getText().toString(), passwordText.getText().toString());


        }


    }


}

