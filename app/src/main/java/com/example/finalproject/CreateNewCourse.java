package com.example.finalproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Objects;

public class CreateNewCourse  extends AppCompatActivity {

    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog2;
    private DatePickerDialog datePickerDialog3;
    private DatePickerDialog datePickerDialog4;
    private Button start_date, end_date, reg_start, reg_end, photo, add;
    private ImageButton back;

    TextView id, name, idtv, nametv, topicstv, startdatetv, enddatetv, regstarttv, regendtv, phototv;

    private final int GALLERY_REQ_CODE = 1000;

    TextView topic_list, pre_list;
    boolean selectTopic[], select_pre[];
    private ImageView imageView;
    ArrayList<Integer> topic_index = new ArrayList<>(), pre_index = new ArrayList<>();

    String[] topics = {"Programming", "Math", "History", "Science"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_course);
        initDatePicker();
        idtv = findViewById(R.id.idtv);
        nametv = findViewById(R.id.nametv);
        topicstv = findViewById(R.id.topicstv);
        startdatetv = findViewById(R.id.startdatetv);
        enddatetv = findViewById(R.id.enddatetv);
        regstarttv = findViewById(R.id.regstarttv);
        regendtv = findViewById(R.id.regendtv);
        phototv = findViewById(R.id.phototv);

        id = findViewById(R.id.course_id);
        name = findViewById(R.id.course_name);
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

        photo = findViewById(R.id.add_photo);
        imageView = findViewById(R.id.uploaded_image);
        back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNewCourse.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQ_CODE);
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
                            for(int j = 0; j < topic_index.size(); j++){
                                if(topic_index.get(j) == i){
                                    topic_index.remove(j);
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
                DataBaseHelper dataBaseHelper = new DataBaseHelper(
                        CreateNewCourse.this,"TRAINING_CENTER",null,1);
                Cursor cursor = dataBaseHelper.getAllCourses();
                int sz = 0;
                while(cursor.moveToNext()) sz++;

                if(sz > 0){
                    String[] pre = new String[sz];
                    cursor = dataBaseHelper.getAllCourses();
                    int k = 0;
                    while(cursor.moveToNext()){
                        pre[k] = cursor.getString(1);
                        k++;
                    }
                    select_pre = new boolean[sz];
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
                else{
                    Toast toast = Toast.makeText(CreateNewCourse.this, "There are no courses yet", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        add = findViewById(R.id.add_the_course);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                String ID = id.getText().toString();
                if(ID.isEmpty()){
                    error = true;
                    idtv.setTextColor(Color.RED);
                }
                else{
                    idtv.setTextColor(Color.WHITE);
                }
                String NAME = name.getText().toString();
                if(NAME.isEmpty()){
                    error = true;
                    nametv.setTextColor(Color.RED);
                }
                else{
                    nametv.setTextColor(Color.WHITE);
                }
                String TOPICS = topic_list.getText().toString();
                if(TOPICS.isEmpty()){
                    error = true;
                    topicstv.setTextColor(Color.RED);
                }
                else{
                    topicstv.setTextColor(Color.WHITE);
                }
                String PRE = pre_list.getText().toString();
                String STARTDATE = start_date.getText().toString();
                if(STARTDATE.isEmpty()){
                    error = true;
                    startdatetv.setTextColor(Color.RED);
                }
                else{
                    startdatetv.setTextColor(Color.WHITE);
                }
                String ENDDATE = end_date.getText().toString();
                if(ENDDATE.isEmpty()){
                    error = true;
                    enddatetv.setTextColor(Color.RED);
                }
                else{
                    enddatetv.setTextColor(Color.WHITE);
                }
                String REGSTART = reg_start.getText().toString();
                if(REGSTART.isEmpty()){
                    error = true;
                    regstarttv.setTextColor(Color.RED);
                }
                else{
                    regstarttv.setTextColor(Color.WHITE);
                }
                String REGEND = reg_end.getText().toString();
                if(REGEND.isEmpty()){
                    error = true;
                    regendtv.setTextColor(Color.RED);
                }
                else{
                    regendtv.setTextColor(Color.WHITE);
                }
                if(!STARTDATE.isEmpty() && !ENDDATE.isEmpty() && !checkDates(STARTDATE, ENDDATE, true)){
                    error = true;
                    startdatetv.setTextColor(Color.RED);
                    enddatetv.setTextColor(Color.RED);
                }
                else{
                    startdatetv.setTextColor(Color.WHITE);
                    enddatetv.setTextColor(Color.WHITE);
                }
                if(!REGSTART.isEmpty() && !REGEND.isEmpty() && !checkDates(REGSTART, REGEND, true)){
                    error = true;
                    regstarttv.setTextColor(Color.RED);
                    regendtv.setTextColor(Color.RED);
                }
                else{
                    regstarttv.setTextColor(Color.WHITE);
                    regendtv.setTextColor(Color.WHITE);
                }
                if(!STARTDATE.isEmpty() && !ENDDATE.isEmpty() && !REGSTART.isEmpty() && !REGEND.isEmpty() &&
                        !checkDates(REGEND, STARTDATE, false)){
                    error = true;
                    startdatetv.setTextColor(Color.RED);
                    enddatetv.setTextColor(Color.RED);
                    regstarttv.setTextColor(Color.RED);
                    regendtv.setTextColor(Color.RED);
                }
                else{
                    startdatetv.setTextColor(Color.WHITE);
                    enddatetv.setTextColor(Color.WHITE);
                    regstarttv.setTextColor(Color.WHITE);
                    regendtv.setTextColor(Color.WHITE);
                }

                if(imageView.getDrawable() == null){
                    error = true;
                    phototv.setTextColor(Color.RED);
                }
                else{
                    phototv.setTextColor(Color.WHITE);
                }

                if(error){
                    return;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(
                        CreateNewCourse.this,"TRAINING_CENTER",null,1);

                if(!dataBaseHelper.checkCourseId(ID)){
                    Toast toast =Toast.makeText(CreateNewCourse.this, "The Course ID exists", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                String[] topics = TOPICS.split(", ");
                for(String topic : topics){
                    Topic topic1 = new Topic(1, ID, topic);
                    dataBaseHelper.insertTopics(topic1);
                }

                Course course = new Course();
                course.setCourseID(ID);
                course.setCourseName(NAME);
                course.setPrerequisites(PRE);
                course.setStartDate(STARTDATE);
                course.setEndDate(ENDDATE);
                course.setRegistrationStart(REGSTART);
                course.setRegistrationEnd(REGEND);
                course.setImage(new ImageHandler().getByteArray(imageView));
                dataBaseHelper.insertCourses(course);

                Intent intent = new Intent(CreateNewCourse.this, CreateNewSection.class);
                intent.putExtra("COURSE_ID", ID);
                intent.putExtra("START_DATE", STARTDATE);
                intent.putExtra("END_DATE", ENDDATE);
                startActivity(intent);
                finish();
            }
        });


    }

    private boolean checkDates(String s1, String s2, boolean equal){
        String[] s1_split = s1.split("\\s+");
        String[] s2_split = s2.split("\\s+");
        int month1 = getInvMonthFormat(s1_split[0]);
        int day1 = Integer.parseInt(s1_split[1]);
        int year1 = Integer.parseInt(s1_split[2]);

        int month2 = getInvMonthFormat(s2_split[0]);
        int day2 = Integer.parseInt(s2_split[1]);
        int year2 = Integer.parseInt(s2_split[2]);

        if(year1 == year2){
            if(month1 == month2){
                return equal ? day1 <= day2 : day1 < day2;
            }
            return month1 < month2;
        }
        return year1 < year2;
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
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

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
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

    private String getMonthFormat(int month) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REQ_CODE){
                imageView.setImageURI(data.getData());
            }
        }
    }
}
