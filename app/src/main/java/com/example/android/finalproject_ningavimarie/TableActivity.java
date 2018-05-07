package com.example.android.finalproject_ningavimarie;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.widget.ImageViewCompat;
import android.support.v4.widget.TintableImageSourceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import android.util.Log;

import android.view.View.OnClickListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TableActivity extends AppCompatActivity {
    int num;//implements View.OnClickListener {

    Date date1 = Data.receiveDate1();
    Date date2 = Data.receiveDate2();

    public DatabaseReference mDatabase;
    final TableTracker tracker = new TableTracker();

    public void Confirm(View view){
        int Val = Validate();
        if(Val == 1) {
            for (int i = 0; i < tracker.value.length; i++) {
                if (tracker.value[i] == true) {
                    Data.addTime(date1, date2, i);
                 Intent f=new Intent(this,LogIn.class);
                 startActivity(f);
                }
            }
        }
        else if(Val == -1){
            Toast.makeText(TableActivity.this,
                    "Cannot reverve more than two tables!",
                    Toast.LENGTH_LONG).show();
        }
        else if(Val == 0){
            Toast.makeText(TableActivity.this,
                    "Please select at least one table!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public class TableTracker{
        boolean[] value = new boolean[14];

        public TableTracker(){
            Arrays.fill(value, false);
        }

        public void setTracker(int n, boolean flag){
            this.value[n] = flag;
        }

    }

    public int Validate(){
        int count = 0;
        for (int i = 0; i < tracker.value.length; i++) {
            if (tracker.value[i] == true) {
                count++;
            }
        }
        if(count>2){
            return -1;
        }
        else if(count == 0){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent mIntent = getIntent();
        num = mIntent.getIntExtra("numberOfPeople", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Data.Init();

        System.out.println("ChuanBuGuoQuDe" + date1);
        System.out.println("ChuanBuGuoQuDe" + date2);



        //final boolean Table1Track = false;
        //final Integer Table1Track = new Integer(0);


        if(Data.isConflict(date1,date2,0)){
            ImageView table=(ImageView)findViewById(R.id.table1);
            table.setImageResource(R.drawable.rec); //plot block with red
        }
        if(Data.isConflict(date1,date2,1)){
            ImageView table=(ImageView)findViewById(R.id.table2);
            table.setImageResource(R.drawable.rec); //plot block with red
        }
        if(Data.isConflict(date1,date2,2)){
            ImageView table=(ImageView)findViewById(R.id.table3);
            table.setImageResource(R.drawable.rec); //plot block with red
        }
        if(Data.isConflict(date1,date2,3)){
            ImageView table=(ImageView)findViewById(R.id.table4);
            table.setImageResource(R.drawable.rec); //plot block with red
        }
        if(Data.isConflict(date1,date2,4)){
            ImageView table=(ImageView)findViewById(R.id.table5);
            table.setImageResource(R.drawable.rec); //plot block with red
        }
        if(Data.isConflict(date1,date2,5)){
            ImageView table=(ImageView)findViewById(R.id.table6);
            table.setImageResource(R.drawable.rec); //plot block with red
        }
        if(Data.isConflict(date1,date2,6)){
            ImageView table=(ImageView)findViewById(R.id.table7);
            table.setImageResource(R.drawable.rec); //plot block with red
        }
        if(Data.isConflict(date1,date2,7)){
            ImageView table=(ImageView)findViewById(R.id.table8);
            table.setImageResource(R.drawable.rec); //plot block with red
        }
        if(Data.isConflict(date1,date2,8)){
            ImageView table=(ImageView)findViewById(R.id.table9);
            table.setImageResource(R.drawable.rec); //plot block with red
        }
        if(Data.isConflict(date1,date2,9)){
            ImageView table=(ImageView)findViewById(R.id.table10);
            table.setImageResource(R.drawable.rec); //plot block with red
        }

        if(Data.isConflict(date1,date2,10)){
            ImageView table=(ImageView)findViewById(R.id.table11);
            table.setImageResource(R.drawable.heart); //plot block with red
        }

        if(Data.isConflict(date1,date2,11)){
            ImageView table=(ImageView)findViewById(R.id.table12);

            table.setImageResource(R.drawable.heart); //plot block with red
        }

        if(Data.isConflict(date1,date2,12)){
            ImageView table=(ImageView)findViewById(R.id.table13);
            table.setImageResource(R.drawable.heart); //plot block with red
        }

        if(Data.isConflict(date1,date2,13)){
            ImageView table=(ImageView)findViewById(R.id.table14);
            table.setImageResource(R.drawable.circle1); //plot block with red
        }




        final ImageView TABLE1=(ImageView)findViewById(R.id.table1);
        TABLE1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,0) == false) {
                    if( tracker.value[0] == false) {
                        //Data.addTime(date1, date2, 0);
                        TABLE1.setImageResource(R.drawable.test1);
                        tracker.setTracker(0, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "1");


                        mDatabase.child("Reservation").child("Table Number").setValue("1");

                        //setTable1Track(true);
                    }
                    else if(tracker.value[0] == true){
                        TABLE1.setImageResource(R.drawable.test);
                        //Table1Track = false;
                        tracker.setTracker(0, false);
                    }

                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        ////
//
        final ImageView TABLE2=(ImageView)findViewById(R.id.table2);
        TABLE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,1) == false) {
                    if( tracker.value[1] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE2.setImageResource(R.drawable.test22);
                        tracker.setTracker(1, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "2");


                        mDatabase.child("Reservation").child("Table Number").setValue("2");


                    }
                    else if(tracker.value[1] == true){
                        TABLE2.setImageResource(R.drawable.test2);
                        //Table1Track = false;
                        tracker.setTracker(1, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        ////
        final ImageView TABLE3=(ImageView)findViewById(R.id.table3);
        TABLE3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,2) == false) {
                    if( tracker.value[2] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE3.setImageResource(R.drawable.test33);
                        tracker.setTracker(2, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "3");


                        mDatabase.child("Reservation").child("Table Number").setValue("3");
                    }
                    else if(tracker.value[2] == true){
                        TABLE3.setImageResource(R.drawable.test3);
                        //Table1Track = false;
                        tracker.setTracker(2, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //
        final ImageView TABLE4=(ImageView)findViewById(R.id.table4);
        TABLE4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,3) == false) {
                    if( tracker.value[3] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE4.setImageResource(R.drawable.test44);
                        tracker.setTracker(3, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "4");


                        mDatabase.child("Reservation").child("Table Number").setValue("4");
                    }
                    else if(tracker.value[3] == true){
                        TABLE4.setImageResource(R.drawable.test4);
                        //Table1Track = false;
                        tracker.setTracker(3, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        final ImageView TABLE5=(ImageView)findViewById(R.id.table5);
        TABLE5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,4) == false) {
                    if( tracker.value[4] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE5.setImageResource(R.drawable.test55);
                        tracker.setTracker(4, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "5");


                        mDatabase.child("Reservation").child("Table Number").setValue("5");
                    }
                    else if(tracker.value[4] == true){
                        TABLE5.setImageResource(R.drawable.test5);
                        //Table1Track = false;
                        tracker.setTracker(4, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView TABLE6=(ImageView)findViewById(R.id.table6);
        TABLE6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,5) == false) {
                    if( tracker.value[5] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE6.setImageResource(R.drawable.test66);
                        tracker.setTracker(5, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "6");


                        mDatabase.child("Reservation").child("Table Number").setValue("6");
                    }
                    else if(tracker.value[5] == true){
                        TABLE6.setImageResource(R.drawable.test6);
                        //Table1Track = false;
                        tracker.setTracker(5, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView TABLE7=(ImageView)findViewById(R.id.table7);
        TABLE7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,6) == false) {
                    if( tracker.value[6] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE7.setImageResource(R.drawable.test77);
                        tracker.setTracker(6, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "7");


                        mDatabase.child("Reservation").child("Table Number").setValue("7");
                    }
                    else if(tracker.value[6] == true){
                        TABLE7.setImageResource(R.drawable.test7);
                        //Table1Track = false;
                        tracker.setTracker(6, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView TABLE8=(ImageView)findViewById(R.id.table8);
        TABLE8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,7) == false) {
                    if( tracker.value[7] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE8.setImageResource(R.drawable.test88);
                        tracker.setTracker(7, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "8");


                        mDatabase.child("Reservation").child("Table Number").setValue("8");
                    }
                    else if(tracker.value[7] == true){
                        TABLE8.setImageResource(R.drawable.test8);
                        //Table1Track = false;
                        tracker.setTracker(7, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView TABLE9=(ImageView)findViewById(R.id.table9);
        TABLE9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,8) == false) {
                    if( tracker.value[8] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE9.setImageResource(R.drawable.test99);
                        tracker.setTracker(8, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "9");


                        mDatabase.child("Reservation").child("Table Number").setValue("9");
                    }
                    else if(tracker.value[8] == true){
                        TABLE9.setImageResource(R.drawable.test9);
                        //Table1Track = false;
                        tracker.setTracker(8, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView TABLE10=(ImageView)findViewById(R.id.table10);
        TABLE10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.isConflict(date1,date2,9) == false) {
                    if( tracker.value[9] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE10.setImageResource(R.drawable.test100);
                        tracker.setTracker(9, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "10");


                        mDatabase.child("Reservation").child("Table Number").setValue("10");
                    }
                    else if(tracker.value[9] == true){
                        TABLE10.setImageResource(R.drawable.test10);
                        //Table1Track = false;
                        tracker.setTracker(9, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView TABLE11=(ImageView)findViewById(R.id.table11);
        TABLE11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num!=2){
                    Toast.makeText(TableActivity.this,
                            "This table is only for 2 people!",
                            Toast.LENGTH_LONG).show();
                }

                else if(Data.isConflict(date1,date2,10) == false) {
                    if( tracker.value[10] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE11.setImageResource(R.drawable.test111);
                        tracker.setTracker(10, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "11");


                        mDatabase.child("Reservation").child("Table Number").setValue("11");
                    }
                    else if(tracker.value[10] == true){
                        TABLE11.setImageResource(R.drawable.test11);
                        //Table1Track = false;
                        tracker.setTracker(10, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView TABLE12=(ImageView)findViewById(R.id.table12);
        TABLE12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num<6){
                    Toast.makeText(TableActivity.this,
                            "This table is only for no less than 6 people!",
                            Toast.LENGTH_LONG).show();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Order obj = new Order();
                    HashMap<String, String> dataMap = obj.firebaseMap();

                    dataMap.put("Table Number", "12");


                    mDatabase.child("Reservation").child("Table Number").setValue("12");
                }
                else if(Data.isConflict(date1,date2,11) == false) {
                    if( tracker.value[11] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE12.setImageResource(R.drawable.test122);
                        tracker.setTracker(11, true);
                    }
                    else if(tracker.value[11] == true){
                        TABLE12.setImageResource(R.drawable.test12);
                        //Table1Track = false;
                        tracker.setTracker(11, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView TABLE13=(ImageView)findViewById(R.id.table13);
        TABLE13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num<6){
                    Toast.makeText(TableActivity.this,
                            "This table is only for no less than 6 people!",
                            Toast.LENGTH_LONG).show();
                }
                else if(Data.isConflict(date1,date2,12) == false) {
                    if( tracker.value[12] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE13.setImageResource(R.drawable.test133);
                        tracker.setTracker(12, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "13");


                        mDatabase.child("Reservation").child("Table Number").setValue("13");
                    }
                    else if(tracker.value[12] == true){
                        TABLE13.setImageResource(R.drawable.test13);
                        //Table1Track = false;
                        tracker.setTracker(12, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        final ImageView TABLE14=(ImageView)findViewById(R.id.table14);
        TABLE14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num<6){
                    Toast.makeText(TableActivity.this,
                            "This table is only for no less than 6 people!",
                            Toast.LENGTH_LONG).show();
                }
                else if(Data.isConflict(date1,date2,13) == false) {
                    if( tracker.value[13] == false) {
                        //Data.addTime(date1, date2, 1);
                        TABLE14.setImageResource(R.drawable.test144);
                        tracker.setTracker(13, true);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Table Number", "14");


                        mDatabase.child("Reservation").child("Table Number").setValue("14");
                    }
                    else if(tracker.value[13] == true){
                        TABLE14.setImageResource(R.drawable.test14);
                        //Table1Track = false;
                        tracker.setTracker(13, false);
                    }
                }
                else{
                    Toast.makeText(TableActivity.this,
                            "This Table is Unavailable!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



    }


}


