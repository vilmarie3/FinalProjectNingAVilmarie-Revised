package com.example.android.finalproject_ningavimarie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CheckOut extends AppCompatActivity {
    public DatabaseReference mDatabase;
    public Button email, signOut;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    //String uid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        final TextView orderSummary = findViewById(R.id.results_text_view);
        email = findViewById(R.id.email_button);
        signOut = findViewById(R.id.sign_out_app_button);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Reservation");
        mDatabase.child("User Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                String name = dataSnapshot.getValue(String.class);
                orderSummary.setText(getString(R.string.end_message) + " " + name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Reservation");
        mDatabase.child("Order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String order = dataSnapshot.getValue(String.class);
                orderSummary.setText(orderSummary.getText() + "\n \nYour order: " + order);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Reservation");
        mDatabase.child("Time Food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String time = dataSnapshot.getValue(String.class);
                orderSummary.setText(orderSummary.getText() + "\n \n" + time);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("Table Number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String table = dataSnapshot.getValue(String.class);
                orderSummary.setText(orderSummary.getText() + "\n \nTable Number: " + table);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: "));
                intent.putExtra(Intent.EXTRA_EMAIL, user.getEmail());
                intent.putExtra(Intent.EXTRA_SUBJECT, "Your TC Easy reservation");
                intent.putExtra(Intent.EXTRA_TEXT, orderSummary.toString());

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                fAuth.signOut();
            }
        });

    }
}
