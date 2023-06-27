package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Button admin_button = (Button) findViewById(R.id.login);
        Button trainee_button = (Button) findViewById(R.id.trainee);
        Button instructor_button = (Button) findViewById(R.id.instructor);
        Button sign_up_button = (Button) findViewById(R.id.sign_up_button);
        trainee_button.setTextColor(getApplication().getResources().getColor(R.color.mainColor));;
        trainee_button.setBackgroundColor(getApplication().getResources().getColor(R.color.whiteColor));
        instructor_button.setTextColor(getApplication().getResources().getColor(R.color.mainColor));;
        instructor_button.setBackgroundColor(getApplication().getResources().getColor(R.color.whiteColor));
        admin_button.setTextColor(getApplication().getResources().getColor(R.color.whiteColor));;
        admin_button.setBackgroundColor(getApplication().getResources().getColor(R.color.mainColor));
        admin_button.setBackground(Drawable.createFromPath("@drawable/round_buttons_dashboard2"));


//        // to disable appearing the keyboard layout
//        Password.setShowSoftInputOnFocus(false);
//        // set the type of the edit text to password
//        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        // set the max password length to 8
//        Password.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});

        admin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainee_button.setTextColor(getApplication().getResources().getColor(R.color.mainColor));;
                trainee_button.setBackgroundColor(getApplication().getResources().getColor(R.color.whiteColor));
                instructor_button.setTextColor(getApplication().getResources().getColor(R.color.mainColor));;
                instructor_button.setBackgroundColor(getApplication().getResources().getColor(R.color.whiteColor));
                admin_button.setTextColor(getApplication().getResources().getColor(R.color.whiteColor));;
                admin_button.setBackgroundColor(getApplication().getResources().getColor(R.color.mainColor));
                admin_button.setBackground(Drawable.createFromPath("@drawable/round_buttons_dashboard2"));
                replaceFragment(new admin_sign_up_fragment());

                EditText email = (EditText) findViewById(R.id.email);
                EditText first_name = (EditText) findViewById(R.id.login_email);
                EditText last_name = (EditText) findViewById(R.id.last_name);
                EditText password = (EditText) findViewById(R.id.password);
                EditText confirm_password = (EditText) findViewById(R.id.confirm_password);
                email.setShowSoftInputOnFocus(false);
                first_name.setShowSoftInputOnFocus(false);
                last_name.setShowSoftInputOnFocus(false);
                password.setShowSoftInputOnFocus(false);
                confirm_password.setShowSoftInputOnFocus(false);

                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                confirm_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                email.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                first_name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                last_name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

            }
        });



        trainee_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admin_button.setTextColor(getApplication().getResources().getColor(R.color.mainColor));;
                admin_button.setBackgroundColor(getApplication().getResources().getColor(R.color.whiteColor));
                instructor_button.setTextColor(getApplication().getResources().getColor(R.color.mainColor));;
                instructor_button.setBackgroundColor(getApplication().getResources().getColor(R.color.whiteColor));
                trainee_button.setTextColor(getApplication().getResources().getColor(R.color.whiteColor));;
                trainee_button.setBackgroundColor(getApplication().getResources().getColor(R.color.mainColor));
                trainee_button.setBackground(Drawable.createFromPath("@drawable/round_buttons_dashboard2"));
                replaceFragment(new trainee_sign_up_fragment());
            }
        });
        instructor_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admin_button.setTextColor(getApplication().getResources().getColor(R.color.mainColor));;
                admin_button.setBackgroundColor(getApplication().getResources().getColor(R.color.whiteColor));
                trainee_button.setTextColor(getApplication().getResources().getColor(R.color.mainColor));;
                trainee_button.setBackgroundColor(getApplication().getResources().getColor(R.color.whiteColor));
                instructor_button.setTextColor(getApplication().getResources().getColor(R.color.whiteColor));;
                instructor_button.setBackgroundColor(getApplication().getResources().getColor(R.color.mainColor));

                replaceFragment(new instructor_sign_up_fragment());


                instructor_button.setBackground(Drawable.createFromPath("@drawable/round_buttons_dashboard2"));
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView3,fragment);
        fragmentTransaction.commit();
    }
}