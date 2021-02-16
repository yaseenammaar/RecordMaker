package com.stwpd.recordmaker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class DayPicker extends AppCompatActivity {

    SQLiteDatabase mydatabase;
    DatePicker datePicker;
    Button getRecord;
    Button getFilterRecordButton;
    RelativeLayout data;
    TextView totalBookingToday,totalRevenueToday,dateselected;
    String monthString;
    Spinner taxiNameSpinner;
    String taxiN;
    int count =0;
    int sum = 0;



    int total = 0;
    int booking = 0;
    ArrayList<Integer> taxies = new ArrayList<Integer>();
    ArrayList<Integer> taxiesnumbers = new ArrayList<Integer>();
    ArrayList<Integer> revenue = new ArrayList<Integer>();
    ArrayList<Integer> taxiesfiltered = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.daypicker);
        data = findViewById(R.id.data);
        getFilterRecordButton = findViewById(R.id.searchtaxi);
        taxiNameSpinner = findViewById(R.id.taxinamespinner);
        totalBookingToday = findViewById(R.id.totalbookingtoday);
        totalRevenueToday = findViewById(R.id.totalrevenuetoday);
        dateselected = findViewById(R.id.dateselected);
        datePicker = findViewById(R.id.datepicker);
        getRecord = findViewById(R.id.recordget);

        mydatabase = openOrCreateDatabase("recordlist",MODE_PRIVATE,null);
        Cursor cursor = mydatabase.rawQuery("Select * from taxilist",null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int totalDb = cursor.getInt(1);
                taxies.add(totalDb);
                cursor.moveToNext();
            }
        }

        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                taxies);

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        taxiNameSpinner.setAdapter(ad);

        taxiNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                taxiN = String.valueOf(taxies.get(i));
//                Toast.makeText(DayPicker.this, taxiN, Toast.LENGTH_SHORT).show();
//                Toast.makeText(NewBookingActivity.this, "Got this" + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getFilterRecordButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                count = 0;
                sum=0;
                for(Integer t : taxiesnumbers){
                    count++;
                    if(t==Integer.parseInt(taxiN)){
                        sum= sum+revenue.get(count-1);
                    }
                }
                totalRevenueToday.setText("Total Revenue of "+taxiN+" is ₹"+sum+"/-");
            }
        });

        getRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                taxies = new ArrayList<Integer>();
                taxiesnumbers = new ArrayList<Integer>();
                taxiesfiltered = new ArrayList<Integer>();

                data.setVisibility(View.VISIBLE);
                getRecord.setVisibility(View.INVISIBLE);

                datePicker.setVisibility(View.INVISIBLE);
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year =  datePicker.getYear();
                String days = day+"";
                monthString = new DateFormatSymbols().getMonths()[month];
                monthString = monthString.substring(0,3);

                dateselected.setText(year +"/"+days+"/"+month);
                totalBookingToday.setText("Total Booking of "+year +"/"+days+"/"+month+ " is "+booking+"");
                totalRevenueToday.setText("Total Revenue of "+year +"/"+days+"/"+month+ " is ₹"+total+"/-");

                if(day<10){
                    days = "0"+day;
                }
                Cursor cursor = mydatabase.rawQuery("Select * from recordlist WHERE datetime LIKE '%"+year+"' AND datetime LIKE '%"+monthString+" "+days+"%'",null);
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        int tnum = cursor.getInt(2);
                        int totalDb = cursor.getInt(10);
                        taxiesnumbers.add(tnum);
                        revenue.add(totalDb);
                        totalRevenueToday.setText(year+days+month+"/-");
                        dateselected.setText(year +"/"+days+"/"+month);
//                        totalBookingToday.setText(5+"");
                        booking++;
                        total = total + totalDb;
                        totalBookingToday.setText("Total Booking of "+year +"/"+days+"/"+month+ " is "+booking+"");
                        totalRevenueToday.setText("Total Revenue of "+year +"/"+days+"/"+month+ " is ₹"+total+"/-");
                        cursor.moveToNext();
                    }
                }



            }
        });



        mydatabase = openOrCreateDatabase("recordlist",MODE_PRIVATE,null);



    }

}
