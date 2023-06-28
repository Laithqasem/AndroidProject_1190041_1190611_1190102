package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ccourse_desc_Frag_Trainee#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ccourse_desc_Frag_Trainee extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Ccourse_desc_Frag_Trainee() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ccourse_desc_Frag_Trainee.
     */
    // TODO: Rename and change types and number of parameters
    public static Ccourse_desc_Frag_Trainee newInstance(String param1, String param2) {
        Ccourse_desc_Frag_Trainee fragment = new Ccourse_desc_Frag_Trainee();
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
        View rootView =  inflater.inflate(R.layout.fragment_ccourse_desc__frag__trainee, container, false);

        TextView textViewCourseName = rootView.findViewById(R.id.textViewCourseName);
        TextView textViewCourseDescription = rootView.findViewById(R.id.textViewCourseDescription);
        Button buttonBack = rootView.findViewById(R.id.buttonBack);

        // Get the course data (replace with your actual course data retrieval)

        Bundle args = getArguments();
        if (args != null) {
            // Get the course name from the arguments
            String courseName = args.getString("courseName");
            String courseDescription = args.getString("courseDescription");

            // Set the course name to the TextView
            textViewCourseName.setText(courseName);
            textViewCourseDescription.setText(courseDescription);

        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the previous fragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });



    return rootView;
    }
}