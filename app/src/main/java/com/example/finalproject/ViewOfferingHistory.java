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
                int[] id = new int[sz];
                int i = 0;
                while(cursor.moveToNext()){
                    items[i] = cursor.getString(2);
                    id[i] = cursor.getInt(0);
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
                        Cursor cursor1 = dataBaseHelper.getAllCourses();
                        Course course = new Course();
                        while(cursor1.moveToNext()){
                            if(cursor1.getInt(0) == id[i]){
                                course.setID(cursor1.getInt(0));
                                course.setCourseID(cursor1.getString(1));
                                course.setCourseName(cursor1.getString(2));
                                course.setPrerequisites(cursor1.getString(3));
                                course.setStartDate(cursor1.getString(4));
                                course.setEndDate(cursor1.getString(5));
                                course.setRegistrationStart(cursor1.getString(6));
                                course.setRegistrationEnd(cursor1.getString(7));
                                course.setImage(cursor1.getBlob(8));
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

                        Cursor cursor2 = dataBaseHelper.getAllOfferings(String.valueOf(id[i]));
                        int j = 0;
                        while (cursor2.moveToNext()){
                            Section section = new Section();

                            section.setSectionID(cursor2.getInt(0));
                            section.setInstructorEmail(cursor2.getString(1));
                            section.setCourseID(cursor2.getInt(2));
                            section.setMaxTrainees(cursor2.getInt(3));
                            section.setStartTime(cursor2.getString(4));
                            section.setEndTime(cursor2.getString(5));
                            section.setDays(cursor2.getString(6));
                            section.setRoom(cursor2.getString(7));
                            section.setStartDate(cursor2.getString(8));
                            section.setEndDate(cursor2.getString(9));

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
