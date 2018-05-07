package com.example.android.finalproject_ningavimarie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PreOrder extends AppCompatActivity {
    public RadioButton orderFoodRadioB, foodAtRestRadioB;
    public Button confirmLogIN;
    public DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_order);

        orderFoodRadioB = findViewById(R.id.pre_order_food_radio_button);
        foodAtRestRadioB = findViewById(R.id.order_food_at_rest_radio_button);

        confirmLogIN = findViewById(R.id.confirm_login_button);

        confirmLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderFoodRadioB.isChecked()) {

                    Intent intent = new Intent(PreOrder.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Reservation").child("Order");
                    mDatabase.setValue("");
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Reservation").child("Time Food");
                    mDatabase.setValue("");
                    Intent intent = new Intent(PreOrder.this, CheckOut.class);
                    startActivity(intent);
                }


            }
        });
    }


}
