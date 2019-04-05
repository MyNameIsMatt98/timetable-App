package com.example.user.timetabledemo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface LessonDao {
    @Insert
    void insert(Lesson lesson);

    @Update
    void update(Lesson lesson);

    @Delete
    void delete(Lesson lesson);

    @Query("DELETE FROM lesson_table")
    void deleteAllLessons();

    @Query("SELECT * FROM lesson_table WHERE day='Monday' ORDER BY time ASC ")
    LiveData<List<Lesson>> getMondayLessons();

    @Query("SELECT * FROM lesson_table WHERE day='Tuesday' ORDER BY time ASC ")
    LiveData<List<Lesson>> getTuesdayLessons();

    @Query("SELECT * FROM lesson_table WHERE day='Wednesday' ORDER BY time ASC ")
    LiveData<List<Lesson>> getWednesdayLessons();

    @Query("SELECT * FROM lesson_table WHERE day='Thursday' ORDER BY time ASC ")
    LiveData<List<Lesson>> getThursdayLessons();

    @Query("SELECT * FROM lesson_table WHERE day='Friday' ORDER BY time ASC ")
    LiveData<List<Lesson>> getFridayLessons();

    @Query("SELECT * FROM lesson_table WHERE day='Saturday' ORDER BY time ASC ")
    LiveData<List<Lesson>> getSaturdayLessons();

    @Query("SELECT * FROM lesson_table WHERE day='Sunday' ORDER BY time ASC ")
    LiveData<List<Lesson>> getSundayLessons();

}
