package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link deleteCourses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class deleteCourses extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public deleteCourses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment deleteCourses.
     */
    // TODO: Rename and change types and number of parameters
    public static deleteCourses newInstance(String param1, String param2) {
        deleteCourses fragment = new deleteCourses();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_courses, container, false);
        LinearLayout layout = view.findViewById(R.id.layout);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "TRAINING_CENTER", null,1);

        Cursor cursor = dataBaseHelper.getAllCourses();
        while(cursor.moveToNext()){
            LinearLayout layout1 = new LinearLayout(getContext());
            layout1.setOrientation(LinearLayout.VERTICAL);
            layout1.setGravity(Gravity.CENTER_HORIZONTAL);
            layout1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            byte[] array = cursor.getBlob(7);
            Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(bitmap);
            layout1.addView(imageView);

            Button button = new Button(getContext());
            button.setText(cursor.getString(1));
            button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setTextSize(30);
            layout1.addView(button);

            layout.addView(layout1);
            String id = cursor.getString(0);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataBaseHelper.deleteCourse(id);
                }
            });
        }
        return view;
    }
}