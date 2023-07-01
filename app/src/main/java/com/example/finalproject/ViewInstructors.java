package com.example.finalproject;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewInstructors#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewInstructors extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewInstructors() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewInstructors.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewInstructors newInstance(String param1, String param2) {
        ViewInstructors fragment = new ViewInstructors();
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
        View view = inflater.inflate(R.layout.fragment_view_trainees, container, false);
        LinearLayout layout = view.findViewById(R.id.layout);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "TRAINING_CENTER", null,1);
        Cursor cursor = dataBaseHelper.getAllInstructor();
        int i = 0;

        while (cursor.moveToNext()) {
            Instructor instructor = new Instructor(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                    cursor.getString(7), "Programming and some other stuff", new byte[1]);

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            TextView textView = new TextView(getContext());
            String s = instructor.toString();
            if(i == 0){
                s = "\n" + s;
            }
            else{
                s = "\n\n" + s;
            }
            i++;
            textView.setText(s);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setTextColor(Color.BLACK);

//            byte[] array = instructor.getImage();
//            Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
//            ImageView imageView = new ImageView(getContext());
//            imageView.setImageBitmap(bitmap);
//            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            layout.addView(linearLayout);
        }
        return view;
    }
}