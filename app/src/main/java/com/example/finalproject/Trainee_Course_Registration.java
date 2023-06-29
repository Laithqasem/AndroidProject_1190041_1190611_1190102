package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Trainee_Course_Registration#newInstance} factory method to
 * create an instance of this fragment.
 */
//fragment for trainee course registration
public class Trainee_Course_Registration extends Fragment {


    private Course courseList;
    private ArrayList<Course> filteredCourseList;
    Trainee_Reg_withraw courseAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Trainee_Course_Registration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Trainee_Course_Registration.
     */
    // TODO: Rename and change types and number of parameters
    public static Trainee_Course_Registration newInstance(String param1, String param2) {
        Trainee_Course_Registration fragment = new Trainee_Course_Registration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_trainee__course__registration, container, false);

        RecyclerView recyclerViewCourses = rootView.findViewById(R.id.recyclerViewCourses);
        // Initialize the course list and adapter

        Bundle args = getArguments();
        String courseName = "ds";
        if (args != null) {
            // Get the course name from the arguments
            courseName = args.getString("courseName"); //sent from course adapter
        }

        //query about the (from the arg) course section and the trainee and send to trainee_Reg_withraw
        
        courseList = new Course();
        courseList.setCourseName(courseName);
        courseAdapter = new Trainee_Reg_withraw(courseList);

        // Set the layout manager and adapter for the RecyclerView
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCourses.setAdapter(courseAdapter);





    return rootView;
    }
}