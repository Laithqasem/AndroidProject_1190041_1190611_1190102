package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Instructor_Activity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

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
//        System.out.println("EMAIL: " + user_email);
//        System.out.println("PASSWORD: " +user_password);
//        System.out.println("FIRST_NAME: " +user_first_name);
//        System.out.println("LAST_NAME: " +user_last_name);
//        System.out.println("PERSONAL_PHOTO: " +user_personal_photo);
//        System.out.println("MOBILE_NUMBER: " +user_mobile_no);
//        System.out.println("ADDRESS: " +user_address);
//        System.out.println("SPECIALIZATION: " +user_specialization);
//        System.out.println("DEGREE: " +user_degree);
//        System.out.println("CANTEACH: " +user_canTeach);




        Button bt = (Button) findViewById(R.id.button3);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Instructor_Activity.this, InstructorEditProfile.class);
                intent.putExtra("EMAIL", user_email);

                startActivity(intent);
                finish();

            }
        });
        Button btt = (Button) findViewById(R.id.button4);

        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(Instructor_Activity.this, "TRAINING_CENTER", null, 1);

//                Course course = new Course(10,"ENCS1110","IC", "ENCS1110,ENEE4700","24/7","28/7","24/5","20/6",new byte[1024]);
//                dataBaseHelper.insertCourses(course);
//                Course course2 = new Course(11,"ENEE4112","LAB", "ENCS1110","24/7","28/7","24/5","20/6",new byte[1024]);
//                dataBaseHelper.insertCourses(course2);
//                Course course3 = new Course(12,"ENCS2222","Arch", "ENCS1110","24/7","28/7","24/5","20/6",new byte[1024]);
//                dataBaseHelper.insertCourses(course3);
//                Course course4 = new Course(13,"ENCS3333","Interface", "ENCS1110","24/7","28/7","24/5","20/6",new byte[1024]);
//                dataBaseHelper.insertCourses(course4);

//                private int sectionID;
//                private String instructorEmail;
//                private int courseID;
//                private int maxTrainees;
//                private String startTime, endTime;
//                private String days;
//                private String room, startDate, endDate;
//                Section section = new Section(1,"laith@gmail.com",10,45,"8:30","9:30","M,W","Masri 332","22/7","23/7");
//                dataBaseHelper.insertSection(section);
//                Section section2 = new Section(2,"laith@gmail.com",11,45,"8:30","9:30","M,W","Masri 332","22/7","23/7");
//                dataBaseHelper.insertSection(section2);
//                Section section3 = new Section(3,"laith@gmail.com",12,45,"8:30","9:30","M,W","Masri 332","22/7","23/7");
//                dataBaseHelper.insertSection(section3);
//                Section section4 = new Section(4,"instructor@gmail.com",13,45,"8:30","9:30","M,W","Masri 332","22/7","23/7");
//                dataBaseHelper.insertSection(section4);
//                Section section5 = new Section(5,"instructor@gmail.com",14,45,"8:30","9:30","M,W","Masri 332","22/7","23/7");
//                dataBaseHelper.insertSection(section5);
//                System.out.println("YEEEEEEEEEES");
//
//
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
                    intent.putExtra("EMAIL", user_email);
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



    }

}





