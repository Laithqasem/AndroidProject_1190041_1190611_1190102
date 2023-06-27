package com.example.finalproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

public class CreateNewCourse  extends AppCompatActivity {

    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog2;
    private DatePickerDialog datePickerDialog3;
    private DatePickerDialog datePickerDialog4;
    private Button start_date, end_date, reg_start, reg_end;
    private ImageButton back;

    TextView topic_list, pre_list;
    boolean selectTopic[], select_pre[];
    ArrayList<Integer> topic_index = new ArrayList<>(), pre_index = new ArrayList<>();

    String[] topics = {"Programming", "Math", "History", "Science"};
    String[] pre = {"ABC", "BCD", "CDE"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_course);
        initDatePicker();
        start_date = findViewById(R.id.start_date);
        end_date = findViewById(R.id.end_date);
        reg_start = findViewById(R.id.registration_start_date);
        reg_end = findViewById(R.id.registration_end_date);

        start_date.setText(getTodaysDate());
        end_date.setText(getTodaysDate());
        reg_start.setText(getTodaysDate());
        reg_end.setText(getTodaysDate());

        topic_list = findViewById(R.id.topic_list);
        pre_list = findViewById(R.id.prerequisites_list);

        selectTopic = new boolean[topics.length];
        select_pre = new boolean[pre.length];

        back = findViewById(R.id.imageButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNewCourse.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        topic_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        CreateNewCourse.this
                );

                builder.setTitle("Select Topics");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(topics, selectTopic, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            topic_index.add(i);
                            Collections.sort(topic_index);
                        }
                        else{
                            topic_index.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j = 0; j < topic_index.size(); j++){
                            stringBuilder.append(topics[topic_index.get(j)]);

                            if(j != topic_index.size() - 1){
                                stringBuilder.append(", ");
                            }
                        }
                        topic_list.setText(stringBuilder.toString());
                        topic_list.setTextColor(Color.BLACK);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Arrays.fill(selectTopic, false);
                        topic_index.clear();
                        topic_list.setText("");
                    }
                });

                builder.show();
            }
        });

        pre_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        CreateNewCourse.this
                );

                builder.setTitle("Select Prerequisites");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(pre, select_pre, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            pre_index.add(i);
                            Collections.sort(pre_index);
                        }
                        else{
                            pre_index.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j = 0; j < pre_index.size(); j++){
                            stringBuilder.append(pre[pre_index.get(j)]);

                            if(j != pre_index.size() - 1){
                                stringBuilder.append(", ");
                            }
                        }
                        pre_list.setText(stringBuilder.toString());
                        pre_list.setTextColor(Color.BLACK);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Arrays.fill(select_pre, false);
                        pre_index.clear();
                        pre_list.setText("");
                    }
                });

                builder.show();
            }
        });


    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                start_date.setText(date);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                end_date.setText(date);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener3 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                reg_start.setText(date);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener4 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                reg_end.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog1 = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog2 = new DatePickerDialog(this, style, dateSetListener2, year, month, day);
        datePickerDialog3 = new DatePickerDialog(this, style, dateSetListener3, year, month, day);
        datePickerDialog4 = new DatePickerDialog(this, style, dateSetListener4, year, month, day);
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog1.show();
    }
    public void openDatePicker2(View view) {
        datePickerDialog2.show();
    }
    public void openDatePicker3(View view) {
        datePickerDialog3.show();
    }
    public void openDatePicker4(View view) {
        datePickerDialog4.show();
    }

}
