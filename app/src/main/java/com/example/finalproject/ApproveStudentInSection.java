package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApproveStudentInSection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApproveStudentInSection extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ApproveStudentInSection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApproveStudentInSection.
     */
    // TODO: Rename and change types and number of parameters
    public static ApproveStudentInSection newInstance(String param1, String param2) {
        ApproveStudentInSection fragment = new ApproveStudentInSection();
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
        View view = inflater.inflate(R.layout.fragment_approve_student_in_section, container, false);
        Bundle bundle = getArguments();
        int sectionID = -1;
        int[] canApproveCount = {0};
        if (bundle != null){
            sectionID = Integer.parseInt(bundle.getString("sectionID"));
            canApproveCount = new int[]{Integer.parseInt(bundle.getString("canApproveCount"))};
      }


        LinearLayout layout = view.findViewById(R.id.layout);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                getContext(),"TRAINING_CENTER",null,1);

        Cursor cursor = dataBaseHelper.getTraineesInSection(String.valueOf(sectionID));
        ArrayList<TraineeToSection> arrayList = new ArrayList<>();
        while(cursor.moveToNext()){
            LinearLayout horizontalLayout = new LinearLayout(getContext());
            horizontalLayout.setOrientation(LinearLayout.VERTICAL);

            horizontalLayout.setBackgroundColor(Color.DKGRAY);

            String email = cursor.getString(2);
            String name = dataBaseHelper.getTrainee(email);
            int status = cursor.getInt(3);

            TextView textView = new TextView(getContext());
            textView.setText("Trainee's Name: " + name);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            horizontalLayout.addView(textView);

            // now for the email
            TextView textView1 = new TextView(getContext());
            textView1.setText("Trainee's Email: " + email);
            textView1.setTextSize(20);
            textView1.setGravity(Gravity.CENTER);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            horizontalLayout.addView(textView1);

            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            if(status == 1){
                checkBox.setChecked(true);
                checkBox.setEnabled(false);
            }
            else{
                checkBox.setChecked(false);
                checkBox.setEnabled(true);
            }
            horizontalLayout.addView(checkBox);
            layout.addView(horizontalLayout);

            TraineeToSection traineeToSection = new TraineeToSection();
            traineeToSection.setSectionID(sectionID);
            traineeToSection.setTraineeEmail(email);
            traineeToSection.setStatus(status);
            int i = arrayList.size();
            arrayList.add(traineeToSection);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!checkBox.isEnabled()){
                        return;
                    }
                    if (checkBox.isChecked()) {
                        arrayList.get(i).setStatus(2);
                    } else {
                        arrayList.get(i).setStatus(0);
                    }
                }
            });
        }
        Button button = new Button(getContext());
        button.setText("Approve");
        button.setTextSize(20);
        button.setGravity(Gravity.CENTER);
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        int[] finalCanApproveCount = canApproveCount;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < arrayList.size() && finalCanApproveCount[0] > 0; i++){
                    if(arrayList.get(i).getStatus() == 2){
                        String email = arrayList.get(i).getTraineeEmail();
                        dataBaseHelper.updateTraineeInSection(arrayList.get(i).getSectionID(),
                                arrayList.get(i).getTraineeEmail());
                        finalCanApproveCount[0]--;

                        Notification notification = new Notification();
                        notification.setNotText("You have been approved in section " + arrayList.get(i).getSectionID());
                        notification.setStatus(0);
                        notification.setTraineeEmail(email);
                        dataBaseHelper.insertNotifications(notification);

                    }
                }

                Toast.makeText(getContext(), "Approved Students", Toast.LENGTH_SHORT).show();

                replaceFragment(new ApproveStudent());
            }
        });
        layout.addView(button);
        return view;
    }

    private void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, newFragment);
        fragmentTransaction.commit();
    }
}