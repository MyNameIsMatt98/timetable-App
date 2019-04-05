package com.example.user.timetabledemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonHolder> {
    private List<Lesson> lessons = new ArrayList<>();
    @NonNull
    @Override
    public LessonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lesson_item, viewGroup,false);
        return new LessonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonHolder lessonHolder, int i) {
        Lesson currentLesson = lessons.get(i);
        lessonHolder.textviewTitle.setText(currentLesson.getTitle());
        lessonHolder.textviewTime.setText(currentLesson.getTime());
        lessonHolder.textviewDesc.setText(currentLesson.getDescription());
        lessonHolder.textviewLocation.setText(currentLesson.getLocation());

    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public void setLessons(List<Lesson> lessons){
        this.lessons = lessons;
        notifyDataSetChanged();//change later again ---
    }

    class LessonHolder extends RecyclerView.ViewHolder{
        private TextView textviewTitle;
        private TextView textviewTime;
        private TextView textviewLocation;
        private TextView textviewDesc;

        public LessonHolder(@NonNull View itemView) {
            super(itemView);
            textviewTitle = itemView.findViewById(R.id.lesson_name);
            textviewDesc = itemView.findViewById(R.id.lesson_desc);
            textviewLocation = itemView.findViewById(R.id.lesson_location);
            textviewTime = itemView.findViewById(R.id.lesson_time);
        }
    }
}
