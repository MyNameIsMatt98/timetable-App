package com.example.user.timetabledemo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "lesson_table")
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String location;

    private String time;

    private String description;

    private String day;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getDay() {
        return day;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lesson(String title, String location, String time, String description, String day) {
        this.title = title;
        this.location = location;
        this.time = time;
        this.description = description;
        this.day = day;
    }
}
