package com.example.user.timetabledemo;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import android.arch.lifecycle.LiveData;

@Dao
public interface mydao {

    @Insert
    void addTask(Tasks task);

    @Delete
    void removeTask(Tasks task);

    @Update
    void updateTask(Tasks task);

    @Query("SELECT * FROM tasks_table ORDER BY task_datetime ASC")
    LiveData<List<Tasks>> getAllTasks();//this ain't working
}
