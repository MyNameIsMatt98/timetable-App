package com.example.user.timetabledemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;

public class NewTask extends AppCompatActivity {
    public int Date;
    private TextView theDate;
    private TextView theTitle;
    private Button button;
    private Button confButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        theDate = (TextView) findViewById(R.id.dateField);
        theTitle = (TextView) findViewById(R.id.editText);
        butTest();
        ArrayList<Tasks> tasksAr = new ArrayList<Tasks>();
        //Intent incomingIntent = getIntent();
        //String date = incomingIntent.getStringExtra("DateResult");
        //theDate.setText(date);
    }
    private void butTest(){
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTask.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
    public void setDateView(String d){
        theDate.setText(d);
    }
    public void butConfirm(){
        confButton = (Button)findViewById(R.id.confirmButton);
        confButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = (String)theDate.getText();
                String title = (String)theTitle.getText();
                Tasks temp = new Tasks(title,date);
            }
        });


    }

}
