package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>
{
    private List<Course> courseList;

    public CourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_in_trainee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseNameTextView;
        private TextView courseDescriptionTextView;
        private Button buttonView;
        private Button buttonRegister;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.textViewCourseName);
            courseDescriptionTextView = itemView.findViewById(R.id.textViewCourseDescription);
            buttonView = itemView.findViewById(R.id.buttonView);
            buttonRegister = itemView.findViewById(R.id.buttonRegister);
            buttonRegister = itemView.findViewById(R.id.buttonRegister);

            buttonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Course course = courseList.get(position);
                        // Perform the action for viewing the course
                        // Open a new fragment or perform any desired action
                    }
                }
            });

            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Course course = courseList.get(position);
                        // Perform the action for registering in the course
                        // Perform the registration process or any desired action
                    }
                }
            });
        }

        public void bind(Course course) {
            courseNameTextView.setText(course.getCourseName());
            //courseDescriptionTextView.setText(course.getDescription());
        }
    }
}
