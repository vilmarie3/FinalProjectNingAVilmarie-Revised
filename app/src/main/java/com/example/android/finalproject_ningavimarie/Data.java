package com.example.android.finalproject_ningavimarie;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by anning on 2018/4/14.
 */

public class Data {

    public static ArrayList<ArrayList<Date>> timeArrays = new ArrayList<ArrayList<Date>>();
    public static boolean[] TableAvailable = new boolean[14];
    public static Date chuanbuguoqu1 = new Date();
    public static Date chuanbuguoqu2 = new Date();
    public static boolean alreadyExecuted = false;

    public static void Init(){
        //Initialize the 2D Arraylist of time
        if(!alreadyExecuted) {
            for (int i = 0; i < 14; i++) {
                timeArrays.add(new ArrayList<Date>());
            }
            Arrays.fill(TableAvailable, true);
        }
        alreadyExecuted = true;
    }

    public static void addTime(Date d1, Date d2, int TableNumber){
        timeArrays.get(TableNumber).add(d1);
        timeArrays.get(TableNumber).add(d2);
    }

    public static void sendDate1(Date d){
        chuanbuguoqu1 = d;
    }

    public static void sendDate2(Date d){
        chuanbuguoqu2 = d;
    }

    public static Date receiveDate1(){
        return chuanbuguoqu1;
    }

    public static Date receiveDate2(){
        return chuanbuguoqu2;
    }

    public static boolean isConflict(Date newDate1, Date newDate2, int TableNumber){
        int size = timeArrays.get(TableNumber).size();
        //int size = 0;
        if(size%2 != 0){
            System.out.println("Time Array Error");
            return false;
        }
        for(int i = 0; i < size;){
            Date oldDate1 = timeArrays.get(TableNumber).get(i);
            Date oldDate2 = timeArrays.get(TableNumber).get(i+1);
            int flag1 = newDate1.compareTo(oldDate2);
            int flag2 = newDate2.compareTo(oldDate1);
            //Compare the End of inDate to the Start of newDate
            if(flag1 < 0 && flag2 > 0){
                return true;
            }
            i = i + 2;
        }
        return false;
    }
    }