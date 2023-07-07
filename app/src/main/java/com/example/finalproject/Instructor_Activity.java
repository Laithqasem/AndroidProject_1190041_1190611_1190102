package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Statement;

public class Instructor_Activity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    String user_email="";
    DataBaseHelper dataBaseHelper = new DataBaseHelper(
            Instructor_Activity.this,"TRAINING_CENTER",null,1);

    TextView nameTextView,emailTextView,addressTextView,degreeTextView,mobileTextView,specializationTextView,canteachTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

//        String str = getIntent().getStringExtra("FROM");
//        if(str == null){
//            str="";
//        }
//        if(str.equals("LOGIN"))
//        {
        user_email = getIntent().getStringExtra("EMAIL");
        String ee = user_email;
        System.out.println("Eeeeee" + ee);

//        }else{
//            user_email=  getIntent().getStringExtra("EMAIL2");
//            System.out.println("Im from COURSES  and here is the email " + user_email );
//
//        }



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation2);
        bottomNavigationView.setSelectedItemId(R.id.home_navbar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_navbar:
                    System.out.println("1");
                    return true;
                case R.id.course_navbar:
                    Intent intent = new Intent(Instructor_Activity.this, InstructorCoursesActivity.class);
//                    String str2 = getIntent().getStringExtra("FROM");
//                    if(str2 == null){
//                        str2="";
//                    }
//                    if(str2.equals("LOGIN"))
//                    {
                    intent.putExtra("EMAIL", user_email);
                    System.out.println("while moving and here is the email " + user_email );

//
//                        intent.putExtra("FROM", "LOGIN");
//
//                    }else{
//                        intent.putExtra("EMAIL2", user_email);
//                        intent.putExtra("FROM", "COURSES");
//
//                    }
                    startActivity(intent);
                    finish();

                    System.out.println("2");
                    return true;
                case R.id.student_navbar:
                    Intent intent2 = new Intent(Instructor_Activity.this, InstructorStudentsActivity.class);
                    intent2.putExtra("EMAIL", user_email);

                    startActivity(intent2);
                    finish();
                    System.out.println("3");
                    return true;
                case R.id.schedule_navbar:
                    Intent intent3 = new Intent(Instructor_Activity.this, InstructorScheduleActivity.class);
                    intent3.putExtra("EMAIL", user_email);
                    startActivity(intent3);
                    finish();
                    System.out.println("4");
                    return true;
            }
            return false;
        });






        Cursor user_data = dataBaseHelper.getInstructorData(user_email);

        byte[]user_personal_photo= new byte[1024];
        String user_password ="PASSWORD";
        String user_first_name = "FIRST_NAME";
        String user_last_name = "LAST_NAME";
        String user_mobile_no ="MOBILE_NUMBER";
        String user_address = "ADDRESS";
        String user_specialization = "SPECIALIZATION";
        String user_degree = "DEGREE";
        String user_canTeach = "canTeach";

        while (user_data.moveToNext()){

            if(user_data.getString(0).equals(user_email) ){
                user_personal_photo = user_data.getBlob(9);
                user_password =  user_data.getString(1);
                user_first_name = user_data.getString(2);
                user_last_name = user_data.getString(3);
                user_mobile_no = user_data.getString(4);
                user_address = user_data.getString(5);
                user_specialization = user_data.getString(6);
                user_degree = user_data.getString(7);
                user_canTeach = user_data.getString(8);

            }
        }





        ImageView profilePhoto = findViewById(R.id.instructor_profile_image);
        emailTextView  = findViewById(R.id.emailTextView);
         addressTextView = findViewById(R.id.addressTextView);
         specializationTextView = findViewById(R.id.specializationTextView);
         mobileTextView = findViewById(R.id.mobileTextView);
         degreeTextView = findViewById(R.id.degreeTextView);
         nameTextView = findViewById(R.id.nameTextView);
         canteachTextView = findViewById(R.id.canteachTextView);
         profilePhoto = findViewById(R.id.instructor_profile_image);

        Bitmap bitmap = BitmapFactory.decodeByteArray(user_personal_photo, 0, user_personal_photo.length);
        profilePhoto.setImageBitmap(bitmap);

        emailTextView.setText(user_email);
        emailTextView.setPaintFlags(emailTextView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        addressTextView.setText(user_address);
        addressTextView.setPaintFlags(addressTextView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        specializationTextView.setText(user_specialization);
        specializationTextView.setPaintFlags(specializationTextView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        mobileTextView.setText(user_mobile_no);
        mobileTextView.setPaintFlags(mobileTextView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        degreeTextView.setText(user_degree);
        degreeTextView.setPaintFlags(degreeTextView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        String name = user_first_name + " " + user_last_name;
        nameTextView.setText(name);
        nameTextView.setPaintFlags(nameTextView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);



        String canTeachString = user_canTeach.substring(2, user_canTeach.length());
        canteachTextView.setText(canTeachString);
        canteachTextView.setPaintFlags(canteachTextView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);






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


        Button logout_button = findViewById(R.id.logout);
        Drawable icon2 = getResources().getDrawable(R.drawable.ic_baseline_exit_to_app_24);
        logout_button.setCompoundDrawablesRelativeWithIntrinsicBounds(icon2, null, null, null);
        logout_button.setPaddingRelative(28,0,0,0);


        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Instructor_Activity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });



        Button btt = (Button) findViewById(R.id.button4);

        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(Instructor_Activity.this, "TRAINING_CENTER", null, 1);

//                dataBaseHelper.deleteALLSections();

//                    String databaseName = "TRAINING_CENTER";
//                    getContext().deleteDatabase(databaseName);

//                Course course = new Course(18,"ENCS1110","Laith", "ENCS1110,ENEE4700","24/7","28/7","24/5","20/6",new byte[1024]);
//                dataBaseHelper.insertCourses(course);
//                Course course2 = new Course(19,"ENEE4112","Majd", "ENCS1110","24/7","28/7","24/5","20/6",new byte[1024]);
//                dataBaseHelper.insertCourses(course2);
//                Course course3 = new Course(20,"ENCS2222","Qasdsa", "ENCS1110","24/7","28/7","24/5","20/6",new byte[1024]);
//                dataBaseHelper.insertCourses(course3);
//                Course course4 = new Course(21,"ENCS3333","Interface", "ENCS1110","24/7","28/7","24/5","20/6",new byte[1024]);
//                dataBaseHelper.insertCourses(course4);


//                private String email;
//                private String password;
//                private String firstName;
//                private String lastName;
//                private String mobileNumber;
//                private String address;
//                private byte[] image;

//                Trainee trainee = new Trainee("qasem@gmail.com","123qweAA","Qasem","Ahmad","0599898989","Azzoun",new byte[1024]);
//                dataBaseHelper.insertTrainee(trainee);
////                Trainee trainee2 = new Trainee("mazen@gmail.com","123qweAA","Mazen","Batrawi","0599898989","Ramallah",new byte[1024]);
////                dataBaseHelper.insertTrainee(trainee2);
////                Trainee trainee3 = new Trainee("rami@gmail.com","123qweAA","Rami","Mahmoud","0599898989","Birzeit",new byte[1024]);
////                dataBaseHelper.insertTrainee(trainee3);
//                System.out.println("YEEEEEEEEEES");


//                private int sectionID;
//                private String instructorEmail;
//                private int courseID;
//                private int maxTrainees;
//                private String startTime, endTime;
//                private String days;
//                private String room, startDate, endDate;

//                Section section = new Section(49,"instructor@gmail.com",10,45,"8:30","9:30","M,W","Masri 332","JUL 1 2023","JUL 12 2023");
//                dataBaseHelper.insertSection(section);
//                Section section2 = new Section(50,"instructor@gmail.com",11,45,"8:30","9:30","M,W","Masri 332","JUL 5 2023","JUL 16 2023");
//                dataBaseHelper.insertSection(section2);
//                Section section3 = new Section(51,"instructor@gmail.com",12,45,"8:30","9:30","M,W","Masri 332","JUL 16 2023","JUL 25 2023");
//                dataBaseHelper.insertSection(section3);
//                Section section4 = new Section(52,"instructor@gmail.com",20,45,"8:30","9:30","M,W","Masri 332","JUL 17 2023","JUL 29 2023");
//                dataBaseHelper.insertSection(section4);
//                Section section5 = new Section(53,"instructor@gmail.com",12,45,"10:00","9:30","M,W","Masri 332","JUL 18 2023","JUL 19 2023");
//                dataBaseHelper.insertSection(section5);
//                Section section6 = new Section(54,"instructor@gmail.com",12,45,"10:00","9:30","M,W","Masri 332","JUL 18 2023","JUL 22 2023");
//                dataBaseHelper.insertSection(section6);
//                System.out.println("YEEEEEEEEEES");

//
            }
        });















    }

}





