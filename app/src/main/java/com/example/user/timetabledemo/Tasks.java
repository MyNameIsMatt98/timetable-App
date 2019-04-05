package com.example.user.timetabledemo;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.joda.time.Instant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

@Entity(tableName = "tasks_table")
public class Tasks {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int Id;
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

    public Tasks (){

    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
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

    public String getDays(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTimeFormatted = LocalDateTime.parse(this.dateTime,formatter);
        //dateTimeFormatted
      // long s = SECONDS.between(LocalDate.now(),dateTimeFormatted);
      /*  long d = DAYS.between(LocalDate.now(),dateTimeFormatted);
        dateTimeFormatted.minusDays(d);
        //long h = HOURS.between(LocalDate.now(),dateTimeFormatted);
        long h = LocalDate.now().until(dateTimeFormatted,HOURS);
        dateTimeFormatted.minusHours(h);
        long m = MINUTES.between(LocalDate.now(),dateTimeFormatted);
        dateTimeFormatted.minusMinutes(m);
        long s = SECONDS.between(LocalDate.now(),dateTimeFormatted);
        return (Long.toString(d)+" Days " +Long.toString(h)+" Hours " +Long.toString(m)+" Minutes " +Long.toString(s)+" Seconds");
        */
      // return Long.toString(s);
        long current = java.time.Instant.now().toEpochMilli();
        long millis = ((dateTimeFormatted.toEpochSecond(ZoneOffset.UTC)*1000 - (current))-3600000); //3600000 is one hour -- quick fix as result was one hour ahead
        //ADD DAYS TO THE STRING BELOW:
        //also values seem to be wrong??
        if(millis <= 0){
            return "Expired on: " + this.dateTime;
        }
        String dhms = String.format("%02d:%02d:%02d:%02d",TimeUnit.MILLISECONDS.toDays(millis), TimeUnit.MILLISECONDS.toHours(millis)-TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return dhms;
    }


}
