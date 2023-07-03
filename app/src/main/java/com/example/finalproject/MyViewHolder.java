package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView courseID,courseName, prerequisites,startDate, endDate,registrationStart, registrationEnd;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        courseID = itemView.findViewById(R.id.Course_ID);
        courseName = itemView.findViewById(R.id.Course_Name);
        prerequisites = itemView.findViewById(R.id.Course_Prerequisites);
        startDate = itemView.findViewById(R.id.Course_startDate);
        endDate = itemView.findViewById(R.id.Course_endDate);
        registrationStart = itemView.findViewById(R.id.Course_RegistrationStart);
        registrationEnd = itemView.findViewById(R.id.course_RegistrationEnd);
    }
}