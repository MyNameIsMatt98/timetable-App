package com.example.user.timetabledemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    public static final int ADD_LESSON_REQUEST = 1;



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




        FloatingActionButton buttonAddLesson = findViewById(R.id.button_add_lesson);
        buttonAddLesson.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent LessonIntent = new Intent(timeTable.this,addLessonActivity.class);
                startActivityForResult(LessonIntent,ADD_LESSON_REQUEST);
            }
        });

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
        swipe();


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_LESSON_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(addLessonActivity.EXTRA_LESSON_TITLE);
            String day = data.getStringExtra(addLessonActivity.EXTRA_LESSON_DAY);
            String time = data.getStringExtra(addLessonActivity.EXTRA_LESSON_TIME);
            String location = data.getStringExtra(addLessonActivity.EXTRA_LESSON_LOCATION);
            String desc = data.getStringExtra(addLessonActivity.EXTRA_LESSON_DESC);

            Lesson lesson = new Lesson(title,location,time,desc,day);
            lessonViewModel.insert(lesson);

            Toast.makeText(this, "Lesson Added", Toast.LENGTH_SHORT).show();
            checkDay();
        }else{
            Toast.makeText(this, "Lesson Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void swipe(){
        ImageView imageView = new ImageView(this);
        imageView.setOnTouchListener(new OnSwipeTouchListener(timeTable.this){
            public void onSwipeRight() {
                Toast.makeText(timeTable.this, "right", Toast.LENGTH_SHORT).show();
                switch(theDay.getText().toString()){
                    case "Monday": theDay.setText("Sunday");
                        checkDay();
                        break;
                    case "Tuesday": theDay.setText("Monday");
                        checkDay();
                        break;
                    case "Wednesday": theDay.setText("Tuesday");
                        checkDay();
                        break;
                    case "Thursday": theDay.setText("Wednesday");
                        checkDay();
                        break;
                    case "Friday": theDay.setText("Thursday");
                        checkDay();
                        break;
                    case "Saturday": theDay.setText("Friday");
                        checkDay();
                        break;
                    case "Sunday": theDay.setText("Saturday");
                        checkDay();
                        break;

                }
            }
            public void onSwipeLeft() {
                Toast.makeText(timeTable.this, "left", Toast.LENGTH_SHORT).show();
                switch(theDay.getText().toString()){
                    case "Monday": theDay.setText("Tuesday");
                        checkDay();
                        break;
                    case "Tuesday": theDay.setText("Wednesday");
                        checkDay();
                        break;
                    case "Wednesday": theDay.setText("Thursday");
                        checkDay();
                        break;
                    case "Thursday": theDay.setText("Friday");
                        checkDay();
                        break;
                    case "Friday": theDay.setText("Saturday");
                        checkDay();
                        break;
                    case "Saturday": theDay.setText("Sunday");
                        checkDay();
                        break;
                    case "Sunday": theDay.setText("Monday");
                        checkDay();
                        break;

                }

            }


        });
    }
}