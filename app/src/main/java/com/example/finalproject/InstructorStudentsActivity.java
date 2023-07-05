package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InstructorStudentsActivity extends AppCompatActivity {
    String user_email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_students);
        user_email = getIntent().getStringExtra("EMAIL");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation2);
        bottomNavigationView.setSelectedItemId(R.id.student_navbar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_navbar:
                    Intent intent = new Intent(InstructorStudentsActivity.this, Instructor_Activity.class);
                    intent.putExtra("EMAIL", user_email);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.course_navbar:
                    Intent intent3 = new Intent(InstructorStudentsActivity.this, InstructorCoursesActivity.class);
                    intent3.putExtra("EMAIL", user_email);

                    startActivity(intent3);
                    finish();
                    return true;
                case R.id.student_navbar:

                    return true;
                case R.id.schedule_navbar:
                    Intent intent2 = new Intent(InstructorStudentsActivity.this, InstructorScheduleActivity.class);
                    intent2.putExtra("EMAIL", user_email);

                    startActivity(intent2);
                    finish();
                    return true;
            }
            return false;
        });
    }
}