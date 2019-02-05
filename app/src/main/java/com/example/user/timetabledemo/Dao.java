package com.example.user.timetabledemo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
@Dao
public interface Dao {

    @Insert
    public void addTask(Tasks task);

}
