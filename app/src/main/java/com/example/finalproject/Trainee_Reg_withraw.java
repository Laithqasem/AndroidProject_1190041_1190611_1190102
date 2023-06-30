package com.example.finalproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//course adapter for course registration
public class Trainee_Reg_withraw extends RecyclerView.Adapter<Trainee_Reg_withraw.ViewHolder>
{
    private ArrayList<Section> SectionList;
    private String Temail;

    public Trainee_Reg_withraw(ArrayList<Section> SecList, String email) {
        this.SectionList = SecList;
        this.Temail = email;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainee_for_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Section section = SectionList.get(position);
        holder.bind(section);
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

//                public Section(int sectionID, String instructorEmail, int courseID, int maxTrainees,
//              String startTime, String endTime, String days, String room, String startDate, String endDate) {
//                this.sectionID = sectionID;
//                this.instructorEmail = instructorEmail;
//                this.courseID = courseID;
//                this.maxTrainees = maxTrainees;
//                this.startTime = startTime;
//                this.endTime = endTime;
//                this.days = days;
//                this.room = room;
//                this.startDate = startDate;
//                this.endDate = endDate;
//            }

                //max size of class, already registered, already waitlisted
                RegisterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Section sec = SectionList.get(getAdapterPosition());

                        DataBaseHelper dataBaseHelper = new DataBaseHelper(v.getContext(),
                                "TRAINING_CENTER",null,1);

                        Cursor cursor = dataBaseHelper.checkExistence(Temail, String.valueOf(sec.getSectionID()));

                        int count = dataBaseHelper.getTraineeCount(String.valueOf(sec.getSectionID()));

                        if(cursor.getCount() == 0) {
                            if (count < sec.getMaxTrainees()){
                                TraineeToSection t1 = new TraineeToSection(-1, sec.getSectionID() ,Temail, 0);
                                dataBaseHelper.insertTraineeSection(t1);
                                Toast.makeText(v.getContext(), "You have been registered for the course", Toast.LENGTH_SHORT).show();
                            }

                        }

                        else {
                            cursor.moveToFirst();
                            dataBaseHelper.deleteTraineeFromSection(Temail, String.valueOf(sec.getSectionID()));
                            Toast.makeText(v.getContext(), "You have been removed from the course", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        }

        public void bind(Section sections) {
            CourseNameTextView.setText(sections.toString());
            //courseDescription.setText(sections.toString());

        }



    }
}
