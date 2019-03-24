package com.example.user.timetabledemo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
   // public int Date;
    private EditText theDate;
    private EditText theTitle;
    private EditText theTime;
    private EditText theDesc;
    //private Button button;
    private Button confButton;
    private EditText theEffort;
    private Spinner spinner;
    int h;
    int w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Effort, android.R.layout.simple_spinner_item); //load items for spinner

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        theDate = (EditText) findViewById(R.id.dateField);
        theTitle = (EditText) findViewById(R.id.editText);
        theTime = (EditText) findViewById(R.id.time);
        theEffort = (EditText) findViewById(R.id.effort);
        theDesc = (EditText) findViewById(R.id.description);
        spinner = (Spinner)findViewById(R.id.effort_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        butConfirm();
        tapDate();
    }
//https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setDateView(String d){
        theDate.setText(d);
    }

    public void tapDate(){
        theDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

    }




    public void butConfirm(){

        confButton = (Button)findViewById(R.id.confirmButton);

        confButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = theDate.getText().toString();
                String title = theTitle.getText().toString();
                String time = theTime.getText().toString();
                String dateTime = date + time;
                String effort = theEffort.getText().toString();//temp fix
                String selection = spinner.getSelectedItem().toString();

                if(selection.equals("Words")){
                     w= Integer.parseInt(effort);
                } else if (selection.equals("Hours")){
                     h = Integer.parseInt(effort);
                }


                String desc = theDesc.getText().toString();
                Tasks temp = new Tasks();
                temp.setName(title);
                temp.setDateTime(dateTime);
                temp.setEffortHours(h);
                temp.setEffortWords(w);
                temp.setDesc(desc);
             //  MainActivity.taskDatabase.myDao().addTask(temp); //FIX THIS SHIT BOYO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
               Log.d("database",title + "added");
              // Activity t = NewTask.this.getParent();
               Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
               clear();
            }
        });


    }
    public void clear(){
        theDate.setText("");
        theTitle.setText("");
        theTime.setText("");
        theEffort.setText("");
        theDesc.setText("");
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        String text = parent.getItemAtPosition(pos).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
