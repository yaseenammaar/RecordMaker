package com.stwpd.recordmaker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class NewTaxiActivity extends AppCompatActivity {

    SQLiteDatabase mydatabase;
    int taxinumber;
    EditText taxiNum;
    Button addTaxi;
    ListView taxies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mydatabase = openOrCreateDatabase("recordlist",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS taxilist (id INTEGER PRIMARY KEY AUTOINCREMENT, taxinumber VARCHAR);");

        setContentView(R.layout.addtaxi);
        taxiNum = findViewById(R.id.newtaxiname);
        addTaxi = findViewById(R.id.addtaxi);

        addTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = taxiNum.getText().toString();
                taxinumber = Integer.parseInt(t);
                mydatabase.execSQL("INSERT INTO taxilist (taxinumber) VALUES("+taxinumber+");");
                finish();
                Toast.makeText(NewTaxiActivity.this, "Added Taxi", Toast.LENGTH_SHORT).show();
            }
        });

    }

}