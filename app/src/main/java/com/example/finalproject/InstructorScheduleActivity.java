package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InstructorScheduleActivity extends AppCompatActivity {
    String user_email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_schedule);
        user_email = getIntent().getStringExtra("EMAIL");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation2);
        bottomNavigationView.setSelectedItemId(R.id.schedule_navbar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_navbar:
                    Intent intent = new Intent(InstructorScheduleActivity.this, Instructor_Activity.class);
                    intent.putExtra("EMAIL", user_email);
                    startActivity(intent);
                    finish();
                    System.out.println("1");
                    return true;
                case R.id.course_navbar:
                    Intent intent3 = new Intent(InstructorScheduleActivity.this, InstructorCoursesActivity.class);
                    intent3.putExtra("EMAIL", user_email);
                    startActivity(intent3);
                    finish();
                    System.out.println("2");
                    return true;
                case R.id.student_navbar:
                    Intent intent2 = new Intent(InstructorScheduleActivity.this, InstructorStudentsActivity.class);
                    intent2.putExtra("EMAIL", user_email);

                    startActivity(intent2);
                    finish();
                    System.out.println("3");
                    return true;
                case R.id.schedule_navbar:

                    System.out.println("4");
                    return true;
            }
            return false;
        });
    }
}