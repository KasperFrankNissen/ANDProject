package com.example.andproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BarCodeScanner extends AppCompatActivity implements View.OnClickListener {

    public static TextView resultText;
    Button scanButton, toastButton, date, addToDatabase;
    TextView textView, datePicked;
    EditText editText;
    Calendar calendar;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_scanner);

        scanButton = findViewById(R.id.scan_button);
        toastButton = findViewById(R.id.toast_button);
        scanButton.setOnClickListener(this);
        resultText = findViewById(R.id.scan_result);
        textView = findViewById(R.id.txtContent);
        date = findViewById(R.id.date);
        datePicked = findViewById(R.id.datePicked);
        addToDatabase = findViewById(R.id.addToDatabase);
        this.setTitle("Add product to the refrigerator");

        addToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseModel databaseModel;
                try {
                    databaseModel = new DatabaseModel(-1, BarCodeScanner.this.resultText.getText().toString(), datePicked.getText().toString());
                    Toast.makeText(BarCodeScanner.this, addToDatabase.toString(), Toast.LENGTH_SHORT).show();


                }
                catch(Exception e){
                    Toast.makeText(BarCodeScanner.this, "There was a problem with the thing that you added. Please make sure that you have both a product and time before adding", Toast.LENGTH_LONG).show();
                    databaseModel = new DatabaseModel(-1, "error", "error");
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(BarCodeScanner.this);

                if(resultText.getText().toString() != "" | datePicked.getText().toString() != "") {
                    boolean b = databaseHelper.addItem(databaseModel);
                    Toast.makeText(BarCodeScanner.this, "Success" + b, Toast.LENGTH_SHORT).show();
                }
                else{
                    return;
                }
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(BarCodeScanner.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datePicked.setText(dayOfMonth + "/" + (month+1) + "-" + year);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });


        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));
            }
        });

        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BarCodeScanner.this, resultText.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onClick(View v) {

    }


}
