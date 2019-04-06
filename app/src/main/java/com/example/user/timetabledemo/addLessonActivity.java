package com.example.user.timetabledemo;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class addLessonActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private Spinner spinnerDay;
    private EditText editTextTime;
    private EditText editTextLocation;
    private EditText editTextDesc;
    private Button addLessonButton;

    final Calendar TimeCalendar = Calendar.getInstance();

    public static final String EXTRA_LESSON_TITLE = "com.example.user.timetabledemo.EXTRA_LESSON_TITLE";
    public static final String EXTRA_LESSON_DAY = "com.example.user.timetabledemo.EXTRA_LESSON_DAY";
    public static final String EXTRA_LESSON_TIME = "com.example.user.timetabledemo.EXTRA_LESSON_TIME";
    public static final String EXTRA_LESSON_LOCATION = "com.example.user.timetabledemo.EXTRA_LESSON_LOCATION";
    public static final String EXTRA_LESSON_DESC = "com.example.user.timetabledemo.EXTRA_LESSON_DESC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Days, android.R.layout.simple_spinner_item); //load items for spinner

        editTextTitle = findViewById(R.id.LessonNameInput);
        spinnerDay = findViewById(R.id.LessonDaySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapter);
        editTextTime = findViewById(R.id.LessonTimeInput);
        editTextLocation = findViewById(R.id.LessonLocationInput);
        editTextDesc = findViewById(R.id.LessonDescInput);
        addLessonButton = findViewById(R.id.NewLessonConfirm);

        button();
        tapTime();
    }
    public void button(){

        addLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLesson();
            }
        });
    }
    public void saveLesson(){
        String title = editTextTitle.getText().toString();
        String day = spinnerDay.getSelectedItem().toString();
        String time = editTextTime.getText().toString();
        String location ="";
        String description = "";
        if(!(editTextLocation.getText().toString().isEmpty())) {
            location = editTextLocation.getText().toString();
        }
        if(!(editTextDesc.getText().toString().isEmpty())) {
            description = editTextDesc.getText().toString();
        }
        if(title.trim().isEmpty() || time.trim().isEmpty()){
            Toast.makeText(this, "Please enter a name and time", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent LessonData = new Intent();
        LessonData.putExtra(EXTRA_LESSON_TITLE,title);
        LessonData.putExtra(EXTRA_LESSON_DAY,day);
        LessonData.putExtra(EXTRA_LESSON_TIME,time);
        LessonData.putExtra(EXTRA_LESSON_LOCATION,location);
        LessonData.putExtra(EXTRA_LESSON_DESC,description);

        setResult(RESULT_OK,LessonData);
        finish();


    }

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            TimeCalendar.set(TimeCalendar.HOUR_OF_DAY,hourOfDay);
            TimeCalendar.set(TimeCalendar.MINUTE,minute);
            setTime();
        }
    };
    public void tapTime(){
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(addLessonActivity.this,time,TimeCalendar.get(TimeCalendar.HOUR_OF_DAY),
                        TimeCalendar.get(TimeCalendar.MINUTE),true).show();
            }
        });
    }
    private void setTime(){
        String theTimeFormat = "HH:mm";
        SimpleDateFormat sdfTime = new SimpleDateFormat(theTimeFormat, Locale.ENGLISH);
        editTextTime.setText(sdfTime.format(TimeCalendar.getTime()));
    }

}
