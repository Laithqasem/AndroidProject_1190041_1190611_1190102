package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    private EditText email,password;
    CheckBox rememberMe;
    boolean valid_email,valid_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button back_button = findViewById(R.id.back_button);
        Drawable icon = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        back_button.setCompoundDrawablesRelativeWithIntrinsicBounds(icon, null, null, null);
        back_button.setPaddingRelative(20,0,0,0);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        email = findViewById(R.id.edit_first_name_text);
        password = findViewById(R.id.login_password);
        rememberMe = findViewById(R.id.remember_user);
        Button login = findViewById(R.id.login_page_button);
        email.setShowSoftInputOnFocus(false);
        password.setShowSoftInputOnFocus(false);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Validate character length
                String email_string = s.toString();
                if (!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()) {
                    email.setError("Please enter a valid email address");
                    valid_email = false;
                } else {
                    valid_email = true;
                    email.setError(null);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String entered_email = email.getText().toString();
            String entered_password = password.getText().toString();
            boolean remember_me_check_box = rememberMe.isChecked();

            System.out.println(entered_email);
            System.out.println(entered_password);
            System.out.println(remember_me_check_box);

            DataBaseHelper dataBaseHelper = new DataBaseHelper(
                    Login.this,"TRAINING_CENTER",null,1);




                String isValid = dataBaseHelper.getLoginPassword(entered_email,entered_password);
                if(isValid.equals("ADMIN")){
                    Intent intent = new Intent(Login.this, Admin_Activity.class);
                    startActivity(intent);
                    finish();
                }else if(isValid.equals("INSTRUCTOR")){
                    Intent intent = new Intent(Login.this, Instructor_Activity.class);
                    startActivity(intent);
                    finish();
                }else if(isValid.equals("TRAINEE")){
                    Intent intent = new Intent(Login.this, Trainee_Activity.class);
                    startActivity(intent);
                    finish();
                }else{
                    System.out.println("Wrong email or password");
                    Toast.makeText(Login.this,"Wrong email or password" , Toast.LENGTH_LONG).show();

                }




            }
        });


    }
}