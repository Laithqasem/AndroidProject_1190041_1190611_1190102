package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Currency;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        Button create_new_course = (Button) findViewById(R.id.create_new_course);
        Button view_courses = (Button) findViewById(R.id.view_courses);
        Button view_offering_history = (Button) findViewById(R.id.view_offering_history);
        Button view_profiles = (Button) findViewById(R.id.view_profiles);

//        DataBaseHelper dataBaseHelper = new DataBaseHelper(
//                MainActivity.this,"TRAINING_CENTER",null,1);
//        // We want to insert dummy data to instructor
//        dataBaseHelper.insertInstructor(new Instructor("mazenbatrawi@gmail.com", "1234",
//                "Mazen", "Batrawi", "0597969540", "Ramallah", "CSE", "Bachelor"));
//        dataBaseHelper.insertInstructor(new Instructor("ramibarakat@gmail.com", "1234",
//                "Rami", "Barakat", "0597969540", "Ramallah", "CSE", "Bachelor"));
//        dataBaseHelper.insertInstructor(new Instructor("bashaqasem@gmail.com", "1234",
//                "Basha", "Qasem", "0597969540", "Ramallah", "CSE", "Bachelor"));
//
//        // We want to insert dummy data to trainees
//        dataBaseHelper.insertTrainee(new Trainee("ahmadmuhsen@gmail.com", "1234",
//                "Ahmad", "Muhsen", "0597969540", "Ramallah", new byte[1]));
//        dataBaseHelper.insertTrainee(new Trainee("hasanrami", "1234",
//                "Hasan", "Rami", "0597969540", "Ramallah", new byte[1]));

        create_new_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                DataBaseHelper dataBaseHelper = new DataBaseHelper(
//                        MainActivity.this,"TRAINING_CENTER",null,1);
////////////
//                Instructor instructor = new Instructor("basha@gmail.com", "1234", "Mazen", "Batrawi", "0597969540",
//                        "Ramallah", "CSE", "Bachelor");
//
//                dataBaseHelper.insertInstructor(instructor);
//////
//                Cursor cursor = dataBaseHelper.getAllInstructor();
//
//                while(cursor.moveToNext()){
//                    Instructor instructor1 = new Instructor(
//                        cursor.getString(0),
//                            cursor.getString(1),
//                            cursor.getString(2),
//                            cursor.getString(3),
//                            cursor.getString(4),
//                            cursor.getString(5),
//                            cursor.getString(6),
//                            cursor.getString(7)
//                            );
//                    System.out.println(instructor1.toString());
//             }
                Intent intent = new Intent(MainActivity.this, CreateNewCourse.class);
                startActivity(intent);
                finish();
            }
        });

        view_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditDeleteCourses.class);
                startActivity(intent);
                finish();
            }
        });

        view_offering_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewOfferingHistory.class);
                startActivity(intent);
                finish();
            }
        });

        view_profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewProfiles.class);
                startActivity(intent);
                finish();
            }
        });

    }
}