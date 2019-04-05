package com.example.user.timetabledemo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class NewTask extends AppCompatActivity {
    final Calendar theCalendar = Calendar.getInstance();
    final Calendar theTimeCalendar = Calendar.getInstance();

    private String Holddate = "";
    private String title = "";
    private String Holdtime = "00:00";
    private String dateTime = Holddate + " " + Holdtime;
    private String effort = "0";
    private String selection = "Words";
    private String desc = "";

    private EditText theDate;
    private EditText theTitle;
    private EditText theTime;
    private EditText theDesc;
    private Button confButton;
    private EditText theEffort;
    private Spinner spinner;
    int h;
    int w;
    public static final String EXTRA_date = "com.example.user.timetabledemo.EXTRA_date";
    public static final String EXTRA_title = "com.example.user.timetabledemo.EXTRA_title";
    public static final String EXTRA_time = "com.example.user.timetabledemo.EXTRA_time";
    public static final String EXTRA_dateTime = "com.example.user.timetabledemo.EXTRA_dateTime";
    public static final String EXTRA_effort = "com.example.user.timetabledemo.EXTRA_effort";//temp fix
    public static final String EXTRA_selection = "com.example.user.timetabledemo.EXTRA_selection";
    public static final String EXTRA_desc = "com.example.user.timetabledemo.EXTRA_desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Effort, android.R.layout.simple_spinner_item); //load items for spinner
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        setTitle("Add Task");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        theDate = (EditText) findViewById(R.id.newTaskDate);
        theTitle = (EditText) findViewById(R.id.TaskNameInput);
        theTime = (EditText) findViewById(R.id.newTaskTime);
        theEffort = (EditText) findViewById(R.id.newTaskEffort);
        theDesc = (EditText) findViewById(R.id.newTaskDescription);
        spinner = (Spinner) findViewById(R.id.effort_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        confButton = (Button) findViewById(R.id.newTaskConfirm);
        spinner.setAdapter(adapter);
        tapTime();
        tapDate(); //not fixed yet
        button();
    }
    private void saveTask(){
        Holddate = theDate.getText().toString();
        title = theTitle.getText().toString();
        if(!(theTime.getText().toString().isEmpty()))
        Holdtime = theTime.getText().toString();
        dateTime = Holddate + " " +Holdtime;
        if(!(theEffort.getText().toString().isEmpty()))
        effort = theEffort.getText().toString();//temp fix
        selection = spinner.getSelectedItem().toString();
        if(!(theDesc.getText().toString().isEmpty()))
        desc = theDesc.getText().toString();
        if (title.trim().isEmpty()||Holddate.trim().isEmpty()){
            Toast.makeText(this,"Please enter a title and date",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_dateTime,dateTime);
        data.putExtra(EXTRA_effort,effort);
        data.putExtra(EXTRA_title,title);
        data.putExtra(EXTRA_desc,desc);
        data.putExtra(EXTRA_selection,selection);
        setResult(RESULT_OK,data);
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_task_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_task:
                saveTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
    public void setDateView(String d) {
        theDate.setText(d);
    }

   public void button(){

        confButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }
    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            theTimeCalendar.set(theTimeCalendar.HOUR_OF_DAY,hourOfDay);
            theTimeCalendar.set(theTimeCalendar.MINUTE,minute);
            setTime();
        }
    };
    public void tapTime(){
        theTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(NewTask.this,time,theTimeCalendar.get(theTimeCalendar.HOUR_OF_DAY),
                        theTimeCalendar.get(theTimeCalendar.MINUTE),true).show();
            }
        });
    }
    private void setTime(){
        String theTimeFormat = "hh:mm";
        SimpleDateFormat sdfTime = new SimpleDateFormat(theTimeFormat,Locale.ENGLISH);
        theTime.setText(sdfTime.format(theTimeCalendar.getTime()));
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            theCalendar.set(Calendar.YEAR,year);
            theCalendar.set(Calendar.MONTH,month);
            theCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            setDate();//add later
        }
    };

   public void tapDate(){
       theDate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new DatePickerDialog(NewTask.this,date,theCalendar.get(Calendar.YEAR)
                       ,theCalendar.get(Calendar.MONTH),
                       theCalendar.get(Calendar.DAY_OF_MONTH)).show();

           }
       });
   }

   private void setDate(){
       String theFormat = "dd/MM/yyyy";
       SimpleDateFormat sdf = new SimpleDateFormat(theFormat, Locale.ENGLISH);
       theDate.setText(sdf.format(theCalendar.getTime()));
   }



}
