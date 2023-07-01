package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ViewProfiles extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profiles);

        Button view_trainees_button = findViewById(R.id.view_trainees_button);
        Button view_instructors_button = findViewById(R.id.view_instructors_button);

        Fragment view_instructors_fragment = new ViewInstructors();
        Fragment view_trainees_fragment = new ViewTrainees();

        view_trainees_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, view_trainees_fragment).commit();
            }
        });

        view_instructors_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, view_instructors_fragment).commit();
            }
        });
    }
}
