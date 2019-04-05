package com.example.user.timetabledemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class LessonViewModel extends AndroidViewModel {

    private LessonRepository repository;
    private LiveData<List<Lesson>> mondayLessons;
    private LiveData<List<Lesson>> tuesdayLessons;
    private LiveData<List<Lesson>> wednesdayLessons;
    private LiveData<List<Lesson>> thursdayLessons;
    private LiveData<List<Lesson>> fridayLessons;
    private LiveData<List<Lesson>> saturdayLessons;
    private LiveData<List<Lesson>> sundayLessons;

    public LessonViewModel(@NonNull Application application){
        super(application);
        repository = new LessonRepository(application);
        mondayLessons = repository.GetMondayLessons();
        tuesdayLessons = repository.GetTuesdayLessons();
        wednesdayLessons = repository.GetWednesdayLessons();
        thursdayLessons = repository.GetThursdayLessons();
        fridayLessons = repository.GetFridayLessons();
        saturdayLessons = repository.GetSaturdayLessons();
        sundayLessons = repository.GetSundayLessons();

    }

    public void insert(Lesson lesson){
        repository.insert(lesson);
    }

    public void update(Lesson lesson){
        repository.update(lesson);
    }

    public void delete(Lesson lesson){
        repository.delete(lesson);
    }
    public void deleteAllLessons(){
        repository.deleteAllLessons();
    }

    public LiveData<List<Lesson>> getMondayLessons() {
        return mondayLessons;
    }

    public LiveData<List<Lesson>> getTuesdayLessons() {
        return tuesdayLessons;
    }

    public LiveData<List<Lesson>> getWednesdayLessons() {
        return wednesdayLessons;
    }

    public LiveData<List<Lesson>> getThursdayLessons() {
        return thursdayLessons;
    }

    public LiveData<List<Lesson>> getFridayLessons() {
        return fridayLessons;
    }

    public LiveData<List<Lesson>> getSaturdayLessons() {
        return saturdayLessons;
    }

    public LiveData<List<Lesson>> getSundayLessons() {
        return sundayLessons;
    }
}
