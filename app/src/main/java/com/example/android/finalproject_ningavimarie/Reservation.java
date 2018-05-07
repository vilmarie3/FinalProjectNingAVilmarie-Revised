package com.example.android.finalproject_ningavimarie;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Reservation extends AppCompatActivity {
    static LocalTime Startime;
    static LocalTime Endtime;
//    int hour1=getText(R.id.)

    SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"12:00", "12:30", "13:00","14:30","15:00","15:30",
                "16:00","16:30","17:00","17:30","18:00",
                "18:30","19:00","19:30","20:00","20:30","21:00"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);


        dropdown.setAdapter(adapter);

        Spinner r = findViewById(R.id.spinner2);

        String[] oo = new String[]{"12:00", "12:30", "13:00","14:30","15:00","15:30",
                "16:00","16:30","17:00","17:30","18:00",
                "18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00"};

        ArrayAdapter<String> pp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, oo);
        r.setAdapter(pp);

    }

    public void startTableActivity(View view){


        //Spinner 1 and it's String
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner1);
        String text1 = mySpinner.getSelectedItem().toString();
        //Spinner 2 and it's String
        Spinner mySpinnerr=(Spinner) findViewById(R.id.spinner2);
        String text2 = mySpinnerr.getSelectedItem().toString();



        try {
            Date date1 = formatter.parse(text1);
            Date date2 = formatter.parse(text2);
            Intent i=new Intent(this,TableActivity.class);
            //Data
            //i.putExtra("MyClass",date1);
            //i.putExtra("MyClass",date2);
            Data.sendDate1(date1);
            Data.sendDate2(date2);




            if (date1.compareTo(date2) >= 0) {
                //Error
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                startActivity(i);
            }

        }catch (ParseException e1){
        }

    }

}


