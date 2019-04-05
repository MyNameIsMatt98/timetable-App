package com.example.user.timetabledemo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = Lesson.class,version = 1)
public abstract class LessonDatabase extends RoomDatabase {

    private static LessonDatabase instance;

    public abstract LessonDao lessonDao();

    public static synchronized LessonDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),LessonDatabase.class, "lesson_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(lessonCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback lessonCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance);
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private LessonDao lessonDao;

        private PopulateDbAsyncTask(LessonDatabase db){
            lessonDao = db.lessonDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Lesson test = new Lesson("Programming","W301","11:00","Almas' lecture","Monday");
            lessonDao.insert(test);

            return null;
        }
    }
}
