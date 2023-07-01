package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//course adapter for course registration
public class Trainee_Reg_withraw extends RecyclerView.Adapter<Trainee_Reg_withraw.ViewHolder>
{
    private Course courseList;

    public Trainee_Reg_withraw(Course courseList) {
        this.courseList =courseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainee_for_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courseList;
        holder.bind(courseList);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView CourseNameTextView;
        private TextView courseDescription;
        private Button RegisterButton;

        ArrayList<Course> courseList = new ArrayList<Course>();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             CourseNameTextView = itemView.findViewById(R.id.textViewCourseName);
             courseDescription = itemView.findViewById(R.id.textViewCourseTime);
             RegisterButton = itemView.findViewById(R.id.buttonRegisterCourse);

                RegisterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //courseList.remove(getAdapterPosition());
                        //notifyItemRemoved(getAdapterPosition());
                        //notifyItemRangeChanged(getAdapterPosition(), courseList.size());




                    }
                });


        }

        public void bind(Course course) {
            CourseNameTextView.setText(course.getCourseName());
            //courseDescriptionTextView.setText(course.getDescription());
        }



    }
}
