package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        Button admin_button = (Button) findViewById(R.id.admin);
        Button trainee_button = (Button) findViewById(R.id.trainee);
        Button instructor_button = (Button) findViewById(R.id.instructor);

        admin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainee_button.setTextColor(getApplication().getResources().getColor(R.color.mainColor));;
                trainee_button.setBackgroundColor(getApplication().getResources().getColor(R.color.whiteColor));


            }
        });

        trainee_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        instructor_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });



    }
}