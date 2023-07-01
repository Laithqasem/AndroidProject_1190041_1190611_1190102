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

        Button bt = (Button) findViewById(R.id.button3);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("adsdasdas");
                System.out.println("YSEEEEEEdfasfafaEEEEEEEEESESESESESESESESES");
                System.out.println("dasdasadss");
                System.out.println("dasasdadsds");
                System.out.println("sdssdsdsd");
                System.out.println("dssadasdsadsdsds");
                System.out.println("czxcxczxcxzxcz");
                System.out.println("czzcxczcx");
                System.out.println("czcxc");
                System.out.println("YSEEEEEEEEEcxxczcxcxzEEEEEESESESESESESESESES");


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


