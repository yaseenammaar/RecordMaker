package com.stwpd.recordmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newBooking = findViewById(R.id.newbooking);
        Button monthPicker = findViewById(R.id.monthlyreport);
        Button dayPicker = findViewById(R.id.dailyreport);
        Button addTaxi = findViewById(R.id.addtaxt);
        newBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewBookingActivity.class);
                startActivity(i);
            }
        });
        monthPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MonthPicker.class);
                startActivity(i);
            }
        });
        dayPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DayPicker.class);
                startActivity(i);

            }
        });
        addTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewTaxiActivity.class);
                startActivity(i);

            }
        });


    }
}