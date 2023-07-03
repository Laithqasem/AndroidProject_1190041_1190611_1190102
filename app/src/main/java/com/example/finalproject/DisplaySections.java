package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DisplaySections extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_sections);

        TextView textView = findViewById(R.id.textView2);
        Bundle extras = getIntent().getExtras();
        String COURSE_ID = "-1";
        if (extras != null) {
            textView.setText(extras.getString("COURSE_NAME"));
            textView.setTextSize(30);
            textView.setTextColor(Color.BLACK);
            COURSE_ID = extras.getString("COURSE_ID");
        }
        Button add = findViewById(R.id.button);
        String finalCOURSE_ID = COURSE_ID;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(
                        DisplaySections.this,"TRAINING_CENTER",null,1);

                Cursor cursor = dataBaseHelper.getAllCourses();
                while(cursor.moveToNext()){
                    if(cursor.getString(0).equals(finalCOURSE_ID)){
                        Course course = new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                                cursor.getInt(7));
                        String START_DATE = course.getStartDate();
                        String END_DATE = course.getEndDate();
                        Intent intent = new Intent(DisplaySections.this, CreateNewSection.class);
                        intent.putExtra("COURSE_ID", finalCOURSE_ID);
                        intent.putExtra("START_DATE", START_DATE);
                        intent.putExtra("END_DATE", END_DATE);
                        startActivity(intent);
                        return;
                    }
                }
            }
        });

        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                DisplaySections.this,"TRAINING_CENTER",null,1);

        Cursor cursor = dataBaseHelper.getSections();
        LinearLayout layout = findViewById(R.id.layout);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        int i = 0;

        while(cursor.moveToNext()){
            if(cursor.getString(2).equals(COURSE_ID)){
                Section section = new Section(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8), cursor.getString(9));

                String s = section.toString();
                TextView textView1 = new TextView(this);
                if(i != 0){
                    s = "\n\n" + s;
                }
                else{
                    i++;
                }
                textView1.setText(s);
                textView1.setTextColor(Color.BLACK);
                textView1.setTextSize(20);
                layout.addView(textView1);
                Button button = new Button(this);
                button.setText("Edit Section " + String.valueOf(cursor.getInt(0)));
                button.setTextColor(Color.BLACK);
                button.setTextSize(20);

                Button delete = new Button(this);
                delete.setText("Delete Section " + String.valueOf(cursor.getInt(0)));
                delete.setTextColor(Color.BLACK);
                delete.setTextSize(20);

                LinearLayout vertical = new LinearLayout(this);
                vertical.setOrientation(LinearLayout.VERTICAL);
                vertical.setGravity(Gravity.CENTER_HORIZONTAL);
                vertical.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                vertical.addView(button);
                vertical.addView(delete);
                layout.addView(vertical);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                                DisplaySections.this,"TRAINING_CENTER",null,1);
                        dataBaseHelper.deleteSection(delete.getText().toString().substring(15));

                        Intent intent = new Intent(DisplaySections.this, DisplaySections.class);
                        intent.putExtra("COURSE_ID", finalCOURSE_ID);
                        intent.putExtra("COURSE_NAME", textView.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DisplaySections.this, EditSection.class);
                        intent.putExtra("SECTION_ID", button.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }
    }
}
