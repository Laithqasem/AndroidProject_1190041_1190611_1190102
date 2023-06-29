package com.example.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ViewOfferingHistory extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_offering_history);

        Button button = findViewById(R.id.button);
        LinearLayout linearLayout = findViewById(R.id.layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(
                        ViewOfferingHistory.this,"TRAINING_CENTER",null,1);

                Cursor cursor = dataBaseHelper.getAllCourses();
                int sz = cursor.getCount();
                String[] items = new String[sz];
                String[] id = new String[sz];
                int i = 0;
                while(cursor.moveToNext()){
                    items[i] = cursor.getString(1);
                    id[i] = cursor.getString(0);
                    i++;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewOfferingHistory.this);
                builder.setTitle("Select Course");
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        button.setText(items[i]);
                        linearLayout.removeAllViews();

                        String courseName = items[i];
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                                ViewOfferingHistory.this,"TRAINING_CENTER",null,1);
                        Cursor cursor = dataBaseHelper.getAllCourses();
                        Course course = new Course();
                        while(cursor.moveToNext()){
                            if(cursor.getString(1).equals(courseName)){
                                course.setCourseID(cursor.getString(0));
                                course.setCourseName(cursor.getString(1));
                                course.setPrerequisites(cursor.getString(2));
                                course.setStartDate(cursor.getString(3));
                                course.setEndDate(cursor.getString(4));
                                course.setRegistrationStart(cursor.getString(5));
                                course.setRegistrationEnd(cursor.getString(6));
                                course.setImage(cursor.getBlob(7));
                                break;
                            }
                        }

                        String s = course.toString();
                        TextView textView = new TextView(ViewOfferingHistory.this);
                        textView.setText(s);
                        textView.setTextSize(20);
                        textView.setTextColor(Color.BLACK);
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);

                        ImageView imageView = new ImageView(ViewOfferingHistory.this);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(course.getImage(), 0, course.getImage().length);
                        imageView.setImageBitmap(bitmap);

                        linearLayout.addView(imageView);
                        linearLayout.addView(textView);

                        TextView textView1 = new TextView(ViewOfferingHistory.this);
                        textView1.setText("Offering History");
                        textView1.setTextSize(30);
                        textView1.setTypeface(null, Typeface.BOLD);
                        textView1.setTextColor(Color.BLACK);
                        textView1.setGravity(Gravity.CENTER_HORIZONTAL);
                        linearLayout.addView(textView1);

                        System.out.println(id[i]);

                        Cursor cursor1 = dataBaseHelper.getAllOfferings(id[i]);
                        int j = 0;
                        while (cursor1.moveToNext()){
                            Section section = new Section();

                            section.setSectionID(cursor1.getInt(0));
                            section.setInstructorEmail(cursor1.getString(1));
                            section.setCourseID(cursor1.getString(2));
                            section.setMaxTrainees(cursor1.getInt(3));
                            section.setStartTime(cursor1.getString(4));
                            section.setEndTime(cursor1.getString(5));
                            section.setDays(cursor1.getString(6));
                            section.setRoom(cursor1.getString(7));
                            section.setStartDate(cursor1.getString(8));
                            section.setEndDate(cursor1.getString(9));

                            String s1 = section.toString();
                            if(j != 0){
                                s1 = "\n\n" + s1;
                            }
                            j++;
                            TextView textView2 = new TextView(ViewOfferingHistory.this);
                            textView2.setText(s1);
                            textView2.setTextSize(20);
                            textView2.setTextColor(Color.BLACK);
                            textView2.setGravity(Gravity.CENTER_HORIZONTAL);
                            linearLayout.addView(textView2);

                        }
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
        });

    }
}
