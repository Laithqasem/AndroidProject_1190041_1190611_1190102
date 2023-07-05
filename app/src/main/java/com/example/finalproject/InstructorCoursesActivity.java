package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class InstructorCoursesActivity extends AppCompatActivity {
    boolean flag = false;
    int cnt=0;
    String user_email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_courses);
//        if(cnt==0){
//            String str = getIntent().getStringExtra("EMAIL");
//            user_email=str;
//        }
//        cnt++;
        user_email = getIntent().getStringExtra("EMAIL");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation2);
        bottomNavigationView.setSelectedItemId(R.id.course_navbar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_navbar:
                    Intent intent = new Intent(InstructorCoursesActivity.this, Instructor_Activity.class);
                    intent.putExtra("EMAIL", user_email);
                    startActivity(intent);
                    finish();
                    System.out.println("1");
                    return true;
                case R.id.course_navbar:
                    System.out.println("2");

                    return true;
                case R.id.student_navbar:
                    Intent intent2 = new Intent(InstructorCoursesActivity.this, InstructorStudentsActivity.class);
                    intent2.putExtra("EMAIL", user_email);
                    startActivity(intent2);
                    finish();
                    System.out.println("3");
                    return true;
                case R.id.schedule_navbar:
                    Intent intent3 = new Intent(InstructorCoursesActivity.this, InstructorScheduleActivity.class);
                    intent3.putExtra("EMAIL", user_email);
                    startActivity(intent3);
                    finish();
                    System.out.println("4");
                    return true;
            }
            return false;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Section> items = new ArrayList<Section>();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(InstructorCoursesActivity.this, "TRAINING_CENTER", null, 1);

        Cursor cursor = dataBaseHelper.getAllSectionsBasedOnInstructor(user_email);

        if (cursor.moveToFirst()) {
            do {
                int sectionID = cursor.getInt(0);
                String instructorEmail = cursor.getString(1);
                int courseID = cursor.getInt(2);
                int maxTrainees = cursor.getInt(3);
                String startTime = cursor.getString(4);
                String endTime = cursor.getString(5);
                String days = cursor.getString(6);
                String room = cursor.getString(7);
                String startDate = cursor.getString(8);
                String endDate = cursor.getString(9);

                items.add( new Section(sectionID,instructorEmail,courseID,maxTrainees,startTime,endTime,days,room,startDate,endDate));

            } while (cursor.moveToNext());

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));



    }
}