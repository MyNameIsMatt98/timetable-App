package com.example.user.timetabledemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class timeTable extends AppCompatActivity {
    private LessonViewModel lessonViewModel;
    private TextView theDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        theDay = findViewById(R.id.daySelected);


        lessonViewModel = ViewModelProviders.of(this).get(LessonViewModel.class);
        //using radio button menu - observed different data depending on selection
        checkDay();

    }

    public void checkDay(){ //change data being observed depending on the day of the week selected
        String setDay = theDay.getText().toString();
        switch (setDay){
            case "Monday":
                lessonViewModel.getMondayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        Toast.makeText(timeTable.this,"Monday",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "Tuesday":
                lessonViewModel.getTuesdayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        Toast.makeText(timeTable.this,"Tuesday",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "Wednesday":
                lessonViewModel.getWednesdayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        Toast.makeText(timeTable.this,"Wednesday",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "Thursday":
                lessonViewModel.getThursdayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        Toast.makeText(timeTable.this,"Thursday",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "Friday":
                lessonViewModel.getFridayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        Toast.makeText(timeTable.this,"Friday",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "Saturday":
                lessonViewModel.getSaturdayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        Toast.makeText(timeTable.this,"Saturday",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "Sunday":
                lessonViewModel.getSundayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        Toast.makeText(timeTable.this,"Sunday",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default: lessonViewModel.getMondayLessons().observe(this, new Observer<List<Lesson>>() {
                @Override
                public void onChanged(@Nullable List<Lesson> lessons) {
                    //update recycler view
                    Toast.makeText(timeTable.this,"Default Loaded",Toast.LENGTH_SHORT).show();
                }
            });
                break;

        }
    }
}
