package com.example.user.timetabledemo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class LessonRepository {
    private LessonDao lessonDao;
    private LiveData<List<Lesson>> getMondayLessons;
    private LiveData<List<Lesson>> getTuesdayLessons;
    private LiveData<List<Lesson>> getWednesdayLessons;
    private LiveData<List<Lesson>> getThursdayLessons;
    private LiveData<List<Lesson>> getFridayLessons;
    private LiveData<List<Lesson>> getSaturdayLessons;
    private LiveData<List<Lesson>> getSundayLessons;

    public LessonRepository(Application application){
        LessonDatabase database = LessonDatabase.getInstance(application);
        lessonDao = database.lessonDao();
        getMondayLessons = lessonDao.getMondayLessons();
        getTuesdayLessons = lessonDao.getTuesdayLessons();
        getWednesdayLessons = lessonDao.getWednesdayLessons();
        getThursdayLessons = lessonDao.getThursdayLessons();
        getFridayLessons = lessonDao.getFridayLessons();
        getSaturdayLessons = lessonDao.getSaturdayLessons();
        getSundayLessons = lessonDao.getSundayLessons();
    }

    public void insert(Lesson lesson){
        new InsertLessonAsyncTask(lessonDao).execute(lesson);
    }
    public void update(Lesson lesson){
        new UpdateLessonAsyncTask(lessonDao).execute(lesson);
    }
    public void delete(Lesson lesson){
        new DeleteLessonAsyncTask(lessonDao).execute(lesson);
    }
    public void deleteAllLessons(){
        new DeleteAllLessonsAsyncTask(lessonDao).execute();
    }

    public LiveData<List<Lesson>> GetMondayLessons() {
        return getMondayLessons;
    }

    public LiveData<List<Lesson>> GetTuesdayLessons() {
        return getTuesdayLessons;
    }

    public LiveData<List<Lesson>> GetWednesdayLessons() {
        return getWednesdayLessons;
    }

    public LiveData<List<Lesson>> GetThursdayLessons() {
        return getThursdayLessons;
    }

    public LiveData<List<Lesson>> GetFridayLessons() {
        return getFridayLessons;
    }

    public LiveData<List<Lesson>> GetSaturdayLessons() {
        return getSaturdayLessons;
    }

    public LiveData<List<Lesson>> GetSundayLessons() {
        return getSundayLessons;
    }
    private static class InsertLessonAsyncTask extends AsyncTask<Lesson,Void,Void> {
        private LessonDao lessonDao;

        private InsertLessonAsyncTask(LessonDao lessonDao){
            this.lessonDao = lessonDao;
        }
        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.insert(lessons[0]);
            return null;
        }
    }

    private static class UpdateLessonAsyncTask extends AsyncTask<Lesson,Void,Void> {
        private LessonDao lessonDao;

        private UpdateLessonAsyncTask(LessonDao lessonDao){
            this.lessonDao = lessonDao;
        }
        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.update(lessons[0]);
            return null;
        }
    }

    private static class DeleteLessonAsyncTask extends AsyncTask<Lesson,Void,Void> {
        private LessonDao lessonDao;

        private DeleteLessonAsyncTask(LessonDao lessonDao){
            this.lessonDao = lessonDao;
        }
        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.delete(lessons[0]);
            return null;
        }
    }
    private static class DeleteAllLessonsAsyncTask extends AsyncTask<Void,Void,Void> {
        private LessonDao lessonDao;

        private DeleteAllLessonsAsyncTask(LessonDao lessonDao){
            this.lessonDao = lessonDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            lessonDao.deleteAllLessons();
            return null;
        }
    }

}
