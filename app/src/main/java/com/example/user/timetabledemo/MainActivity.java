package com.example.user.timetabledemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.arch.persistence.room.Room;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab
    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIView();
        initToolBar();
        setupListView();

    }
    private void setupUIView(){
    toolbar = (Toolbar)findViewById(R.id.ToolbarMain);
    listView = (ListView)findViewById(R.id.MainList);

    }
    private void initToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Timetable and Study");
    }
    private void setupListView(){
        String[] title = getResources().getStringArray(R.array.Main);
        String[] desc = getResources().getStringArray(R.array.Desc);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,title,desc);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: break;
                    case 1: {
                        Intent intent = new Intent(MainActivity.this, NewTask.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: break;
                    case 3: break;
                    case 4: break;
                }
            }
        });

    }
    public class SimpleAdapter extends BaseAdapter{

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleAr;
        private String[] descAr;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] title, String[] desc){
            mContext = context;
            titleAr = title;
            descAr = desc;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleAr.length;
        }

        @Override
        public Object getItem(int position) {
            return titleAr[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                //checks present view and then puts individual items into the list
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item,null);
            }
            title = (TextView)convertView.findViewById(R.id.textMain); //convert view allows you to access ids from another layoutfile
            description = (TextView)convertView.findViewById(R.id.textMainSub);
            imageView = convertView.findViewById(R.id.imageMain);

            title.setText(titleAr[position]);
            description.setText(descAr[position]);

            if(titleAr[position].equalsIgnoreCase("Timetable")){
                imageView.setImageResource(R.drawable.timetable);
            }else if (titleAr[position].equalsIgnoreCase("New Task")){
                imageView.setImageResource(R.drawable.newtask);
            }else if (titleAr[position].equalsIgnoreCase("Pomodoro Timer")){
                imageView.setImageResource(R.drawable.pomodoro);
            }else if (titleAr[position].equalsIgnoreCase("Tasks")){
                imageView.setImageResource(R.drawable.tasks);
            }else if(titleAr[position].equalsIgnoreCase("Settings")){
                imageView.setImageResource(R.drawable.settings);
            }
            return convertView;

        }
    }
}
