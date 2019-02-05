package com.example.user.timetabledemo;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Tasks {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "task_name")
    private String Name;
    @ColumnInfo(name ="task_datetime")
    private String dateTime;
    @ColumnInfo(name = "task_hours")
    private int effortHours; //can be null
    @ColumnInfo(name = "task_words")
    private int effortWords; //can be null
    @ColumnInfo(name = "task_description")
    private String Desc;     //can be null

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getEffortHours() {
        return effortHours;
    }

    public void setEffortHours(int effortHours) {
        this.effortHours = effortHours;
    }

    public int getEffortWords() {
        return effortWords;
    }

    public void setEffortWords(int effortWords) {
        this.effortWords = effortWords;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
