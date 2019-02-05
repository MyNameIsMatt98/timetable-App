package com.example.user.timetabledemo;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Database;

@Database(entities = {Tasks.class},version = 1)
public abstract class Database extends RoomDatabase {

    public abstract Dao dao();

}
