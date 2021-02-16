package com.stwpd.recordmaker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.InnerResultCallbcak;
import com.sunmi.peripheral.printer.SunmiPrinterService;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class NewBookingActivity extends AppCompatActivity {

    SQLiteDatabase mydatabase;
    String pNameString,tNumString="",toString,rupeesString,nopString,dateTimeString,fareString,nobString,royaltyString,totalString;
    EditText pName,to,rupees,nop,dateTime,fare,nob,royalty,total;
    SearchableSpinner tNum;
    Button save;
    ArrayList<Integer> taxies = new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mydatabase = openOrCreateDatabase("recordlist",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS recordlist (id INTEGER PRIMARY KEY AUTOINCREMENT, pname VARCHAR,tnum VARCHAR,dest VARCHAR,rupees INT, nop INT,datetime VARCHAR, fare INT, nob INT, royc INT, total INT);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS taxilist (id INTEGER PRIMARY KEY AUTOINCREMENT, taxinumber VARCHAR);");
        Cursor cursor = mydatabase.rawQuery("Select * from taxilist",null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int totalDb = cursor.getInt(1);
                taxies.add(totalDb);
                cursor.moveToNext();
            }
        }


        setContentView(R.layout.new_booking);

        pName = findViewById(R.id.pName);
        tNum = findViewById(R.id.taxinumberspinner);
        to = findViewById(R.id.to);
        rupees = findViewById(R.id.rupees);
        nop = findViewById(R.id.nop);
        dateTime = findViewById(R.id.date);
        fare = findViewById(R.id.fare);
        nob = findViewById(R.id.nob);
        royalty = findViewById(R.id.royc);
        total = findViewById(R.id.total);
        dateTime.setText((new Date()).toString());
        total.setText("0");
        royalty.setText("0");
        fare.setText("0");
        rupees.setText("0");
        nop.setText("0");
        nob.setText("0");



        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                taxies);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        tNum.setAdapter(ad);

        tNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tNumString = String.valueOf(taxies.get(i));
//                Toast.makeText(NewBookingActivity.this, "Got this" + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
//                    rupees.setText((Integer.parseInt(fare.getText().toString())) * (Integer.parseInt(nob.getText().toString())));
                    int fareint = Integer.parseInt(fare.getText().toString());
                    int nobint = Integer.parseInt(nob.getText().toString());
                    int nopint = Integer.parseInt(nop.getText().toString());
                    int royint = Integer.parseInt(royalty.getText().toString());
                    int t = fareint;
                    int tt = t + royint + nobint;

                    total.setText(tt+"");
                    rupees.setText(t+"");
                }catch (Exception e){

                }
            }
        });

        fare.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
//                    rupees.setText((Integer.parseInt(fare.getText().toString())) * (Integer.parseInt(nob.getText().toString())));
                    int fareint = Integer.parseInt(fare.getText().toString());
                    int nobint = Integer.parseInt(nob.getText().toString());
                    int nopint = Integer.parseInt(nop.getText().toString());
                    int royint = Integer.parseInt(royalty.getText().toString());
                    int t = fareint;
                    int tt = t + royint + nobint;

                    total.setText(tt+"");
                    rupees.setText(t+"");
                }catch (Exception e){

                }
            }
        });
        nob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
//                    rupees.setText((Integer.parseInt(fare.getText().toString())) * (Integer.parseInt(nob.getText().toString())));
                    int fareint = Integer.parseInt(fare.getText().toString());
                    int nobint = Integer.parseInt(nob.getText().toString());
                    int nopint = Integer.parseInt(nop.getText().toString());
                    int royint = Integer.parseInt(royalty.getText().toString());
                    int t = fareint;
                    int tt = t + royint + nobint;

                    total.setText(tt+"");
                    rupees.setText(t+"");
                }catch (Exception e){

                }
            }
        });
        royalty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
//                    rupees.setText((Integer.parseInt(fare.getText().toString())) * (Integer.parseInt(nob.getText().toString())));
                    int fareint = Integer.parseInt(fare.getText().toString());
                    int nobint = Integer.parseInt(nob.getText().toString());
                    int nopint = Integer.parseInt(nop.getText().toString());
                    int royint = Integer.parseInt(royalty.getText().toString());
                    int t = fareint;
                    int tt = t + royint + nobint;

                    total.setText(tt+"");
                    rupees.setText(t+"");
                }catch (Exception e){

                }
            }
        });



        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save.setVisibility(View.INVISIBLE);
                Button close = findViewById(R.id.close);
                close.setVisibility(View.VISIBLE);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

                pNameString = pName.getText().toString();
//                tNumString = tNum.getText().toString();
                toString = to.getText().toString();
                rupeesString = rupees.getText().toString();
                nopString = nop.getText().toString();
                dateTimeString = dateTime.getText().toString();
                fareString = fare.getText().toString();
                nobString = nob.getText().toString();
                royaltyString = royalty.getText().toString();
                totalString = total.getText().toString();
                if (pNameString.equals("") || tNumString.equals("") || toString.equals("") || rupees.equals("") || nopString.equals("") || dateTimeString.equals("") || fareString.equals("") || royaltyString.equals("")) {
                    Toast.makeText(NewBookingActivity.this, "All Fields Are required", Toast.LENGTH_SHORT).show();
                } else {

                    int tNumint, rupeeint, nopint, fareint, nobint, royaltyint, totalint;
                    tNumint = Integer.parseInt(tNumString);
                    rupeeint = Integer.parseInt(rupeesString);
                    nopint = Integer.parseInt(nopString);
                    fareint = Integer.parseInt(fareString);
                    nobint = Integer.parseInt(nobString);
                    royaltyint = Integer.parseInt(royaltyString);
                    totalint = Integer.parseInt(totalString);
                    LinearLayout ll = findViewById(R.id.ll);
                    ll.setVisibility(View.INVISIBLE);

                    TextView finalprint = findViewById(R.id.finalprint);
                    finalprint.setVisibility(View.VISIBLE);

                    String dateTimeStringd = dateTimeString.replace("GMT+05:30 ", "");

                    finalprint.setText("\nPre-paid Taxi Stand Drivers Union \nSrinagar Jammu & Kashmir\nIndia - 190007\nPhone - 91979715032\nEmail - rukayajan290@gmail.com\nPassenger Name : "+pNameString +
                            "\nTaxi Number " + tNumint + "\nTo : " + toString + "\nRupees : ₹" + rupeeint + "/-\nNumber of Pax : " + nopint + "\nD&T : " + dateTimeStringd + "\nFare : ₹" + fareint + "/-\nNumber of Baggage : " + nobint + "\nRoyalty Charge : ₹" + royaltyint + "\nTotal : ₹" + totalint+"/-");
                    final String s = finalprint.getText().toString();

                    InnerPrinterCallback innerPrinterCallback = new InnerPrinterCallback() {
                        @Override
                        protected void onConnected(SunmiPrinterService service) {
                            try {
                                service.printText(s, new InnerResultCallbcak() {
                                    @Override
                                    public void onRunResult(boolean isSuccess) throws RemoteException {
                                        Toast.makeText(NewBookingActivity.this, "Started Printing", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onReturnString(String result) throws RemoteException {
                                        Toast.makeText(NewBookingActivity.this, "Returning String", Toast.LENGTH_SHORT).show();


                                    }

                                    @Override
                                    public void onRaiseException(int code, String msg) throws RemoteException {
                                        Toast.makeText(NewBookingActivity.this, "Printing Error", Toast.LENGTH_SHORT).show();


                                    }

                                    @Override
                                    public void onPrintResult(int code, String msg) throws RemoteException {
                                        Toast.makeText(NewBookingActivity.this, "Print Successfull", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }catch (Exception e){
                                Toast.makeText(NewBookingActivity.this, "Printing Exception Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        protected void onDisconnected() {
                            Toast.makeText(NewBookingActivity.this, "Printer not Connected", Toast.LENGTH_SHORT).show();


                        }
                    };

                    try {

                        boolean result = InnerPrinterManager.getInstance().bindService(NewBookingActivity.this, innerPrinterCallback);
                        if(result){
                            Toast.makeText(NewBookingActivity.this, "Success Printing", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(NewBookingActivity.this, "Failed Printing", Toast.LENGTH_SHORT).show();

                        }
                    }catch (Exception e){

                        Toast.makeText(NewBookingActivity.this, "Failed Print Result", Toast.LENGTH_SHORT).show();


                    }

//                mydatabase.execSQL("INSERT INTO recordlist VALUES('"+pNameString+"', "+tNumint+",'"+toString+"',"+rupeeint+","+nopint+",'"+dateTimeString+"',"+fareint+","+nobint+","+royaltyint+","+totalint+");");

                    mydatabase.execSQL("INSERT INTO recordlist (pname,tnum,dest,rupees, nop,datetime, fare, nob, royc, total) VALUES('" + pNameString + "', " + tNumint + ",'" + toString + "'," + rupeeint + "," + nopint + ",'" + dateTimeString + "'," + fareint + "," + nobint + "," + royaltyint + "," + totalint + ");");
                    Date now = new Date();
                    android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

                    try {
//                        if(!hasStoragePermission(555)){
//
//                        }else {
//
//
//
//                        }

                    } catch (Throwable e) {
                        // Several error may come out with file handling or DOM
                        e.printStackTrace();
                    }

//                    finish();
                    Toast.makeText(NewBookingActivity.this, "Saved Record", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private boolean hasStoragePermission(int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 555){}
                //

        }
    }
}