package com.stwpd.recordmaker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormatSymbols;

public class MonthPicker extends AppCompatActivity {

    int yearNow=2021,monthNow=1,dayNow;
    int yearSelected, monthSelected;
    SQLiteDatabase mydatabase;
    int total = 0;

    String monthString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthpicker);

        DatePickerDialog monthDatePickerDialog = new DatePickerDialog(MonthPicker.this,
                AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                TextView mMonth = findViewById(R.id.month);
                yearSelected = year;
                monthSelected = month;
                mydatabase = openOrCreateDatabase("recordlist",MODE_PRIVATE,null);
                monthString = new DateFormatSymbols().getMonths()[month];
                monthString = monthString.substring(0,3);
                Cursor cursor = mydatabase.rawQuery("Select * from recordlist WHERE datetime LIKE '%"+year+"' AND datetime LIKE '%"+monthString+"%'",null);
                mMonth.setText("Total Revenue of "+monthString+ " " +yearSelected+" is ₹"+0+"/-");
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        int totalDb = cursor.getInt(10);
                        total = total + totalDb;
                        mMonth.setText("Total Revenue of "+monthString+ " " +yearSelected+" is ₹"+total+"/-");
                        cursor.moveToNext();
                    }
                }



            }
        }, yearNow, monthNow, dayNow){
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getDatePicker().findViewById(getResources().getIdentifier("day","id","android")).setVisibility(View.GONE);
            }
        };
        monthDatePickerDialog.setTitle("Select Month");
        monthDatePickerDialog.show();
    }

}
