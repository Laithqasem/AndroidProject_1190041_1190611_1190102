package com.example.finalproject;

import static java.security.AccessController.getContext;

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


       // DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "TRAINING_CENTER",null,1);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(holder.itemView.getContext(), "TRAINING_CENTER",null,1);
        Cursor cursor = dataBaseHelper.checkExistence(Temail, String.valueOf(section.getSectionID()));
        int count = dataBaseHelper.getTraineeCount(String.valueOf(section.getSectionID()));

        if(cursor.getCount() == 0) {
            if (count < section.getMaxTrainees()){
                holder.RegisterButton.setText("Register");
            } else {
                holder.RegisterButton.setText("Waitlist");
            }

        } else {
            cursor.moveToFirst();
            holder.RegisterButton.setText("Withdraw");
        }

    }

    @Override
    public int getItemCount() {
        return SectionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView CourseNameTextView = itemView.findViewById(R.id.textViewCourseName);
        private TextView courseDescription = itemView.findViewById(R.id.textViewCourseTime);
        private Button RegisterButton = itemView.findViewById(R.id.buttonRegisterCourse);

        ArrayList<Course> courseList = new ArrayList<Course>();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

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
                                RegisterButton.setText("Withdraw");
                            }

                        } else {
                            cursor.moveToFirst();
                            dataBaseHelper.deleteTraineeFromSection(Temail, String.valueOf(sec.getSectionID()));
                            Toast.makeText(v.getContext(), "You have been removed from the course", Toast.LENGTH_SHORT).show();
                            RegisterButton.setText("Register");
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
