package com.example.finalproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ApproveStudentsInSections extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve_students_in_sections);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
            return;
        int sectionID = Integer.parseInt(bundle.getString("sectionID"));
        final int[] canApproveCount = {Integer.parseInt(bundle.getString("canApproveCount"))};

        LinearLayout layout = findViewById(R.id.layout);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                ApproveStudentsInSections.this,"TRAINING_CENTER",null,1);

        Cursor cursor = dataBaseHelper.getTraineesInSection(String.valueOf(sectionID));
        ArrayList<TraineeToSection> arrayList = new ArrayList<>();
        while(cursor.moveToNext()){
            LinearLayout horizontalLayout = new LinearLayout(ApproveStudentsInSections.this);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

            String email = cursor.getString(2);
            String name = dataBaseHelper.getTrainee(email);
            int status = cursor.getInt(3);

            TextView textView = new TextView(ApproveStudentsInSections.this);
            textView.setText(name);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            horizontalLayout.addView(textView);

            // now for the email
            TextView textView1 = new TextView(ApproveStudentsInSections.this);
            textView1.setText(email);
            textView1.setTextSize(20);
            textView1.setGravity(Gravity.CENTER);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) textView1.getLayoutParams();
            params1.setMargins(20, 0, 0, 0);
            horizontalLayout.addView(textView1);

            /*
            Now we will add a check box
             */
            CheckBox checkBox = new CheckBox(ApproveStudentsInSections.this);
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) checkBox.getLayoutParams();
            params2.setMargins(20, 0, 0, 0);
            if(status == 1){
                checkBox.setChecked(true);
                checkBox.setEnabled(false);
            }
            else{
                checkBox.setChecked(false);
                checkBox.setEnabled(true);
            }
            horizontalLayout.addView(checkBox);

            TraineeToSection traineeToSection = new TraineeToSection();
            traineeToSection.setSectionID(sectionID);
            traineeToSection.setTraineeEmail(email);
            traineeToSection.setStatus(status);
            arrayList.add(traineeToSection);
        }
        Button button = new Button(ApproveStudentsInSections.this);
        button.setText("Approve");
        button.setTextSize(20);
        button.setGravity(Gravity.CENTER);
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < arrayList.size() && canApproveCount[0] > 0; i++){
                    if(arrayList.get(i).getStatus() == 0){
                        dataBaseHelper.updateTraineeInSection(arrayList.get(i).getSectionID(),
                                arrayList.get(i).getTraineeEmail());
                        canApproveCount[0]--;
                    }
                }
            }
        });
    }
}