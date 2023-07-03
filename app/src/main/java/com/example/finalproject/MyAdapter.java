package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<Course> items;

    public MyAdapter(Context context, List<Course> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.course_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {


        holder.courseID.setText(items.get(position).getCourseID());
        holder.courseName.setText(items.get(position).getCourseName());
        holder.prerequisites.setText(items.get(position).getPrerequisites());
        holder.startDate.setText(items.get(position).getStartDate());
        holder.endDate.setText(items.get(position).getEndDate());
        holder.registrationStart.setText(items.get(position).getRegistrationStart());
        holder.registrationEnd.setText(items.get(position).getRegistrationEnd());
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
