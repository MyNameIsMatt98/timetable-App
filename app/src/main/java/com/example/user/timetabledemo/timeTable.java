package com.example.user.timetabledemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class timeTable extends AppCompatActivity implements View.OnClickListener {
    private LessonViewModel lessonViewModel;
    private TextView theDay;
    private Button monday;
    private Button tuesday;
    private Button wednesday;
    private Button thursday;
    private Button friday;
    private Button saturday;
    private Button sunday;
    LessonAdapter lessonAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        theDay = findViewById(R.id.daySelected);
        monday = findViewById(R.id.Mon);
        tuesday = findViewById(R.id.Tue);
        wednesday = findViewById(R.id.Wed);
        thursday = findViewById(R.id.Thu);
        friday = findViewById(R.id.Fri);
        saturday = findViewById(R.id.Sat);
        sunday = findViewById(R.id.Sun);

        monday.setOnClickListener(this);
        tuesday.setOnClickListener(this);
        wednesday.setOnClickListener(this);
        thursday.setOnClickListener(this);
        friday.setOnClickListener(this);
        saturday.setOnClickListener(this);
        sunday.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.timetableRecycler_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        lessonAdapter = new LessonAdapter();
        recyclerView.setAdapter(lessonAdapter);

        //the following checks what today's day is so that the user is shown the lessons for today by default
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                theDay.setText("Sunday");
                break;
            case Calendar.MONDAY:
                theDay.setText("Monday");
                break;
            case Calendar.TUESDAY:
                theDay.setText("Tuesday");
                break;
            case Calendar.WEDNESDAY:
                theDay.setText("Wednesday");
                break;
            case Calendar.THURSDAY:
                theDay.setText("Tuesday");
                break;
            case Calendar.FRIDAY:
                theDay.setText("Friday");
                break;
            case Calendar.SATURDAY:
                theDay.setText("Saturday");
                break;
        }



        lessonViewModel = ViewModelProviders.of(this).get(LessonViewModel.class);
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
                        lessonAdapter.setLessons(lessons);
                    }
                });
                break;
            case "Tuesday":
                lessonViewModel.getTuesdayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        lessonAdapter.setLessons(lessons);
                    }
                });
                break;
            case "Wednesday":
                lessonViewModel.getWednesdayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        lessonAdapter.setLessons(lessons);
                    }
                });
                break;
            case "Thursday":
                lessonViewModel.getThursdayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        lessonAdapter.setLessons(lessons);
                    }
                });
                break;
            case "Friday":
                lessonViewModel.getFridayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        lessonAdapter.setLessons(lessons);
                    }
                });
                break;
            case "Saturday":
                lessonViewModel.getSaturdayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        lessonAdapter.setLessons(lessons);
                    }
                });
                break;
            case "Sunday":
                lessonViewModel.getSundayLessons().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(@Nullable List<Lesson> lessons) {
                        //update recycler view
                        lessonAdapter.setLessons(lessons);
                    }
                });
                break;
            default: lessonViewModel.getMondayLessons().observe(this, new Observer<List<Lesson>>() {
                @Override
                public void onChanged(@Nullable List<Lesson> lessons) {
                    //update recycler view
                    lessonAdapter.setLessons(lessons);
                }
            });
                break;

        }
    }
    public void onClick(View v) {
        // changes the text in the text view to the corresponding day that's been selected
        //in turn this changes what livedata is being observed by calling checkday which changes observer depending on the text
        switch (v.getId()) {
            case R.id.Mon:
                theDay.setText("Monday");
                checkDay();
                break;
            case R.id.Tue:
                theDay.setText("Tuesday");
                checkDay();
                break;
            case R.id.Wed:
                theDay.setText("Wednesday");
                checkDay();
                break;
            case R.id.Thu:
                theDay.setText("Thursday");
                checkDay();
                break;
            case R.id.Fri:
                theDay.setText("Friday");
                checkDay();
                break;
            case R.id.Sat:
                theDay.setText("Saturday");
                checkDay();
                break;
            case R.id.Sun:
                theDay.setText("Sunday");
                checkDay();
                break;
        }
    }
}
