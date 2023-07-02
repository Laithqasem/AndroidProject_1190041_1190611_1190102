package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Instructor_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        String user_email = getIntent().getStringExtra("EMAIL");
        String user_password = getIntent().getStringExtra("PASSWORD");
        String user_first_name = getIntent().getStringExtra("FIRST_NAME");
        String user_last_name = getIntent().getStringExtra("LAST_NAME");
        byte[] user_personal_photo = getIntent().getByteArrayExtra("PERSONAL_PHOTO");
        String user_mobile_no = getIntent().getStringExtra("MOBILE_NUMBER");
        String user_address = getIntent().getStringExtra("ADDRESS");
        String user_specialization = getIntent().getStringExtra("SPECIALIZATION");
        String user_degree = getIntent().getStringExtra("DEGREE");
        String user_canTeach = getIntent().getStringExtra("canTeach");




        Button bt = (Button) findViewById(R.id.button3);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("EMAIL: " + user_email);
                System.out.println("PASSWORD: " +user_password);
                System.out.println("FIRST_NAME: " +user_first_name);
                System.out.println("LAST_NAME: " +user_last_name);
                System.out.println("PERSONAL_PHOTO: " +user_personal_photo);
                System.out.println("MOBILE_NUMBER: " +user_mobile_no);
                System.out.println("ADDRESS: " +user_address);
                System.out.println("SPECIALIZATION: " +user_specialization);
                System.out.println("DEGREE: " +user_degree);
                System.out.println("CANTEACH: " +user_canTeach);


            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation2);
        bottomNavigationView.setSelectedItemId(R.id.home_navbar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_navbar:
                    System.out.println("1");
                    return true;
                case R.id.course_navbar:
                    Intent intent = new Intent(Instructor_Activity.this, InstructorCoursesActivity.class);
                    startActivity(intent);
                    finish();

                    System.out.println("2");
                    return true;
                case R.id.student_navbar:
                    Intent intent2 = new Intent(Instructor_Activity.this, InstructorStudentsActivity.class);
                    startActivity(intent2);
                    finish();
                    System.out.println("3");
                    return true;
                case R.id.schedule_navbar:
                    Intent intent3 = new Intent(Instructor_Activity.this, InstructorScheduleActivity.class);
                    startActivity(intent3);
                    finish();
                    System.out.println("4");
                    return true;
            }
            return false;
        });

//
//
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation2);
//        bottomNavigationView.setSelectedItemId(R.id.home_navbar);
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.home_navbar:
//                    return true;
//                case R.id.course_navbar:
//                    startActivity(new Intent(getApplicationContext(), InstructorCoursesActivity.class));
//                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
//                    finish();
//                    System.out.println("course");
//                    return true;
//                case R.id.student_navbar:
//                    startActivity(new Intent(getApplicationContext(), InstructorStudentsActivity.class));
//                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
//                    finish();
//                    System.out.println("student");
//                    return true;
//                case R.id.schedule_navbar:
//                    startActivity(new Intent(getApplicationContext(), InstructorScheduleActivity.class));
//                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
//                    finish();
//                    System.out.println("schedule");
//                    return true;
//            }
//            return false;
//        });
    }


}


