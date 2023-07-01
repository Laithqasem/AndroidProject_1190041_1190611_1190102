package com.example.finalproject;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class CreateNewSection   extends AppCompatActivity {
    private Button start_time, end_time, add;
    private ImageButton back;
    private TextView instructortv, maxtv, starttimetv, endtimetv, daystv, roomtv;
    private TextView email, max, days, room;
    int hour1, minute1;
    int hour2, minute2;

    boolean selectDay[];
    ArrayList<Integer> day_index = new ArrayList<>();

    String[] weekDays = {"Saturday", "Monday", "Tuesday", "Wednesday", "Thursday"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_section);

        String COURSE_ID = "-1";
        String START_DATE = "-1";
        String END_DATE = "-1";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            COURSE_ID = extras.getString("COURSE_ID");
            START_DATE = extras.getString("START_DATE");
            END_DATE = extras.getString("END_DATE");
        }

        start_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);

        instructortv = findViewById(R.id.instructortv);
        maxtv = findViewById(R.id.maxtv);
        starttimetv = findViewById(R.id.starttimetv);
        endtimetv = findViewById(R.id.eendtimetv);
        daystv = findViewById(R.id.daystv);
        roomtv = findViewById(R.id.roomtv);
        email = findViewById(R.id.instructor_email);
        max = findViewById(R.id.max_trainees);
        days = findViewById(R.id.days_list);
        room = findViewById(R.id.room_list);

        back = findViewById(R.id.imageButton);
        add = findViewById(R.id.add_the_section);
        selectDay = new boolean[weekDays.length];

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNewSection.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String finalCOURSE_ID = COURSE_ID;
        String finalSTART_DATE = START_DATE;
        String finalEND_DATE = END_DATE;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                String EMAIL = email.getText().toString();
                if(EMAIL.isEmpty()){
                    error = true;
                    instructortv.setTextColor(Color.RED);
                }
                else{
                    instructortv.setTextColor(Color.WHITE);
                }
                String MAX = max.getText().toString();
                if(MAX.isEmpty() || Integer.parseInt(MAX) <= 0){
                    error = true;
                    maxtv.setTextColor(Color.RED);
                }
                else{
                    maxtv.setTextColor(Color.WHITE);
                }
                String START_TIME = start_time.getText().toString();
                if(START_TIME.isEmpty()){
                    error = true;
                    starttimetv.setTextColor(Color.RED);
                }
                else{
                    starttimetv.setTextColor(Color.WHITE);
                }
                String END_TIME = end_time.getText().toString();
                if(END_TIME.isEmpty()){
                    error = true;
                    endtimetv.setTextColor(Color.RED);
                }
                else{
                    endtimetv.setTextColor(Color.WHITE);
                }
                String DAYS = days.getText().toString();
                if(DAYS.isEmpty()){
                    error = true;
                    daystv.setTextColor(Color.RED);
                }
                else{
                    daystv.setTextColor(Color.WHITE);
                }
                String ROOM = room.getText().toString();
                if(ROOM.isEmpty()){
                    error = true;
                    roomtv.setTextColor(Color.RED);
                }
                else{
                    roomtv.setTextColor(Color.WHITE);
                }

                if(error){
                    return;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(
                        CreateNewSection.this,"TRAINING_CENTER",null,1);

                if(!dataBaseHelper.checkInstructor(EMAIL)){
                    Toast toast =Toast.makeText(CreateNewSection.this, "The Instructor Doesn't Exist", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                Cursor cursor = dataBaseHelper.getSections();

                boolean can = true;

                while(cursor.moveToNext()){
                    Section section = new Section(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getInt(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7), cursor.getString(8), cursor.getString(9)
                            );
                    if(section.getInstructorEmail().equals(EMAIL)){
                        String s1 = section.getStartTime();
                        String s2 = section.getEndTime();

                         if(conflict(s1, s2, section.getDays(), START_TIME, END_TIME, DAYS,
                                 finalSTART_DATE, finalEND_DATE, section.getStartDate(), section.getEndDate())){
                             can = false;
                         }
                    }
                }
                if(!can){
                    Toast toast = Toast.makeText(CreateNewSection.this, "The Instructor Has Another Class In This Time", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if(!checkTime(START_TIME, END_TIME)){
                    starttimetv.setTextColor(Color.RED);
                    endtimetv.setTextColor(Color.RED);
                    return;
                }
                starttimetv.setTextColor(Color.WHITE);
                endtimetv.setTextColor(Color.WHITE);

                Section section = new Section(-1, EMAIL, finalCOURSE_ID, Integer.parseInt(MAX), START_TIME,
                        END_TIME, DAYS, ROOM, finalSTART_DATE, finalEND_DATE);

                dataBaseHelper.insertSection(section);

                email.setText("");
                max.setText("");
                start_time.setText("");
                end_time.setText("");
                days.setText("");
                room.setText("");
            }
        });

        days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        CreateNewSection.this
                );

                builder.setTitle("Select Days");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(weekDays, selectDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            day_index.add(i);
                            Collections.sort(day_index);
                        }
                        else{
                            for(int j = 0; j < day_index.size(); j++){
                                if(day_index.get(j) == i){
                                    day_index.remove(j);
                                    break;
                                }
                            }
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j = 0; j < day_index.size(); j++){
                            stringBuilder.append(weekDays[day_index.get(j)]);

                            if(j != day_index.size() - 1){
                                stringBuilder.append(", ");
                            }
                        }
                        days.setText(stringBuilder.toString());
                        days.setTextColor(Color.BLACK);
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
                        Arrays.fill(selectDay, false);
                        day_index.clear();
                        days.setText("");
                    }
                });

                builder.show();
            }
        });

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(start_time.getText().toString().isEmpty() || end_time.getText().toString().isEmpty() ||
                        days.getText().toString().isEmpty()) {
                    Toast toast =Toast.makeText(CreateNewSection.this, "Select Time and Day First", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                boolean[] can = new boolean[20];
                for(int i = 0; i < 20; i++) can[i] = true;
                DataBaseHelper dataBaseHelper = new DataBaseHelper(
                        CreateNewSection.this,"TRAINING_CENTER",null,1);

                Cursor cursor = dataBaseHelper.getSections();
                while(cursor.moveToNext()){
                    Section section = new Section(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getInt(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7), cursor.getString(8), cursor.getString(9));

                    if(conflict(start_time.getText().toString(), end_time.getText().toString(), days.getText().toString(),
                                    section.getStartTime(), section.getEndTime(), section.getDays(),
                                finalSTART_DATE, finalEND_DATE, section.getStartDate(), section.getEndDate())){

                        can[Integer.parseInt(section.getRoom()) - 1] = false;
                    }
                }

                int sz = 0;
                for(int i = 0; i < 20; i++){
                    if(can[i]) sz++;
                }

                if(sz != 0){
                    String[] items = new String[sz];
                    int j = 0;
                    for(int i = 0; i < 20; i++){
                        if(can[i]){
                            items[j] = String.valueOf(i + 1);
                            j++;
                        }
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateNewSection.this);
                    builder.setTitle("Choose Room");
                    builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            room.setText(items[i]);
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    builder.show();
                }
                else{
                    Toast toast =Toast.makeText(CreateNewSection.this, "No available rooms for your selection", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    public boolean time_intersect(int h1, int m1, int h2, int m2, int h3, int m3, int h4, int m4){
        int startTime1 = h1 * 60 + m1;
        int endTime1 = h2 * 60 + m2;
        int startTime2 = h3 * 60 + m3;
        int endTime2 = h4 * 60 + m4;

        return endTime1 >= startTime2 && endTime2 >= startTime1;
    }

    public boolean date_intersect(String l1, String r1, String l2, String r2) {
        String[] l1_split = l1.split("\\s+");
        String[] l2_split = l2.split("\\s+");
        String[] r1_split = r1.split("\\s+");
        String[] r2_split = r2.split("\\s+");

        int startMonth1 = getInvMonthFormat(l1_split[0]);
        int startDay1 = Integer.parseInt(l1_split[1]);
        int startYear1 = Integer.parseInt(l1_split[2]);

        int endMonth1 = getInvMonthFormat(r1_split[0]);
        int endDay1 = Integer.parseInt(r1_split[1]);
        int endYear1 = Integer.parseInt(r1_split[2]);

        int startMonth2 = getInvMonthFormat(l2_split[0]);
        int startDay2 = Integer.parseInt(l2_split[1]);
        int startYear2 = Integer.parseInt(l2_split[2]);

        int endMonth2 = getInvMonthFormat(r2_split[0]);
        int endDay2 = Integer.parseInt(r2_split[1]);
        int endYear2 = Integer.parseInt(r2_split[2]);

        if (endYear1 < startYear2 || endYear2 < startYear1) {
            return false;
        } else if (endYear1 == startYear2 && endMonth1 < startMonth2) {
            return false;
        } else if (endYear2 == startYear1 && endMonth2 < startMonth1) {
            return false;
        } else if (endYear1 == startYear2 && endMonth1 == startMonth2 && endDay1 < startDay2) {
            return false;
        } else if (endYear2 == startYear1 && endMonth2 == startMonth1 && endDay2 < startDay1) {
            return false;
        }
        return true;
    }

    private int getInvMonthFormat(String month){
        if(month.equals("JAN")) return 1;
        if(month.equals("FEB")) return 2;
        if(month.equals("MAR")) return 3;
        if(month.equals("APR")) return 4;
        if(month.equals("MAY")) return 5;
        if(month.equals("JUN")) return 6;
        if(month.equals("JUL")) return 7;
        if(month.equals("AUG")) return 8;
        if(month.equals("SEP")) return 9;
        if(month.equals("OCT")) return 10;
        if(month.equals("NOV")) return 11;
        if(month.equals("DEC")) return 12;
        return -1;
    }

    public boolean conflict(String s1, String s2, String days1, String s3, String s4, String days2,
                            String start_date1, String end_date1, String start_date2, String end_date2){
        if(!date_intersect(start_date1, end_date1, start_date2, end_date2)){
            return false;
        }
        String[] days1_split = days1.split(", ");
        String[] days2_split = days2.split(", ");

        String[] s1_split = s1.split(":");
        String[] s2_split = s2.split(":");
        String[] s3_split = s3.split(":");
        String[] s4_split = s4.split(":");

        int h1 = Integer.parseInt(s1_split[0]);
        int h2 = Integer.parseInt(s2_split[0]);
        int h3 = Integer.parseInt(s3_split[0]);
        int h4 = Integer.parseInt(s4_split[0]);

        int m1 = Integer.parseInt(s1_split[1]);
        int m2 = Integer.parseInt(s2_split[1]);
        int m3 = Integer.parseInt(s3_split[1]);
        int m4 = Integer.parseInt(s4_split[1]);

        for(String day1 : days1_split){
            for(String day2 : days2_split){
                if (day1.equals(day2)) {
                    if(time_intersect(h1, m1, h2, m2, h3, m3, h4, m4)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkTime(String s1, String s2){
        String[] s1_split = s1.split(":");
        String[] s2_split = s2.split(":");

        int hour_1 = Integer.parseInt(s1_split[0]);
        int hour_2 = Integer.parseInt(s2_split[0]);
        int min_1 = Integer.parseInt(s1_split[1]);
        int min_2 = Integer.parseInt(s2_split[1]);

        if(hour_1 < 8 || hour_1 > 17){
            return false;
        }
        if(hour_1 > hour_2){
            return false;
        }
        if(hour_2 > 17){
            return false;
        }
        if(hour_1 == hour_2){
            return min_1 < min_2;
        }
        return true;
    }

    public void popTimePicker1(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour1 = selectedHour;
                minute1 = selectedMinute;
                start_time.setText(String.valueOf(hour1) + ":" + String.valueOf(minute1));
                start_time.setTextColor(Color.BLACK);
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour1, minute1, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void popTimePicker2(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour2 = selectedHour;
                minute2 = selectedMinute;
                end_time.setText(String.valueOf(hour2) + ":" + String.valueOf(minute2));
                end_time.setTextColor(Color.BLACK);
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour2, minute2, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}
