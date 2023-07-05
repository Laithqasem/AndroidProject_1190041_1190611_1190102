package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<Section> items;

    public MyAdapter(Context context, List<Section> items) {
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

        holder.startDate.setText(items.get(position).getStartDate());
        holder.startTime.setText(items.get(position).getStartTime());
        holder.endTime.setText(items.get(position).getEndTime());
        holder.room.setText(items.get(position).getRoom());
        holder.course_id.setText(String.valueOf(items.get(position).getCourseID()));
        holder.courseName.setText(String.valueOf(items.get(position).getSectionID()));
        holder.maxTrainees.setText(String.valueOf(items.get(position).getMaxTrainees()));
        holder.days.setText(items.get(position).getDays());
        holder.endDate.setText(items.get(position).getEndDate());

//        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
