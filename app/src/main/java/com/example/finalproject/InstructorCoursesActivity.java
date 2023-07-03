package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class InstructorCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_courses);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation2);
        bottomNavigationView.setSelectedItemId(R.id.course_navbar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_navbar:
                    Intent intent = new Intent(InstructorCoursesActivity.this, Instructor_Activity.class);
                    startActivity(intent);
                    finish();
                    System.out.println("1");
                    return true;
                case R.id.course_navbar:
                    System.out.println("2");
                    return true;
                case R.id.student_navbar:
                    Intent intent2 = new Intent(InstructorCoursesActivity.this, InstructorStudentsActivity.class);
                    startActivity(intent2);
                    finish();
                    System.out.println("3");
                    return true;
                case R.id.schedule_navbar:
                    Intent intent3 = new Intent(InstructorCoursesActivity.this, InstructorScheduleActivity.class);
                    startActivity(intent3);
                    finish();
                    System.out.println("4");
                    return true;
            }
            return false;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Course> items = new ArrayList<Course>();
        items.add(new Course("ENCS5150","Lab Android","ENCS3330","9:00","10:30","3/4","15/6",R.drawable.a));
        items.add(new Course("ENCS3330","Operating System","ENCS3110, ENCS4410","10:00","11:30","2/2","25/1",R.drawable.b));
        items.add(new Course("ENCS2340","Interface","ENCS4120","11:30","12:30","1/1","1/5",R.drawable.c));
        items.add(new Course("ENCS3340","JAVA","ENCS2130","12:45","1:30","12/6","17/7",R.drawable.d));
        items.add(new Course("ENCS3390","Linux","ENCS2350","1:30","3:30","5/5","19/1",R.drawable.e));
        items.add(new Course("ENCS4380","Real Time","ENCS2380","2:45","3:30","11/7","2/2",R.drawable.f));
        items.add(new Course("ENCS4360","Culs","ENCS3380","3:30","4:30","30/2","8/7",R.drawable.g));



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));



    }
}