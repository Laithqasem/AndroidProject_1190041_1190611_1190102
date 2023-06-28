package com.example.finalproject;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
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
            }
        }
    }
}
