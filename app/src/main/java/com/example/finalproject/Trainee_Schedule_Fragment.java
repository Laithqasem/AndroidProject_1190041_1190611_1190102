package com.example.finalproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.TreeMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Trainee_Schedule_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Trainee_Schedule_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private TableLayout tableLayout;
    private TextView txtCourse1;
    private TextView txtCourse2;
    private TextView txtCourse3;
    private TextView txtCourse4;
    private TextView txtCourse5;
    private TextView txtCourse6;
    private TextView txtCourse7;
    private TextView txtCourse8;
    private TextView txtCourse9;

    private TextView txtTime1;
    private TextView txtTime2;
    private TextView txtTime3;
    private TextView txtTime4;
    private TextView txtTime5;
    private TextView txtTime6;
    private TextView txtTime7;
    private TextView txtTime8;
    private TextView txtTime9;

    String Email;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Trainee_Schedule_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Trainee_Schedule_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Trainee_Schedule_Fragment newInstance(String param1, String param2) {
        Trainee_Schedule_Fragment fragment = new Trainee_Schedule_Fragment();
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
        View rootView =  inflater.inflate(R.layout.fragment_trainee__schedule_, container, false);

        tableLayout = rootView.findViewById(R.id.tableLayout);
        txtCourse1 = rootView.findViewById(R.id.txtCourseName1);
        txtCourse2 = rootView.findViewById(R.id.txtCourseName2);
        txtCourse3 = rootView.findViewById(R.id.txtCourseName3);
        txtCourse4 = rootView.findViewById(R.id.txtCourseName4);
        txtCourse5 = rootView.findViewById(R.id.txtCourseName5);
        txtCourse6 = rootView.findViewById(R.id.txtCourseName6);
        txtCourse7 = rootView.findViewById(R.id.txtCourseName7);
        txtCourse8 = rootView.findViewById(R.id.txtCourseName8);
        txtCourse9 = rootView.findViewById(R.id.txtCourseName9);

        txtTime1 = rootView.findViewById(R.id.txtTime1);
        txtTime2 = rootView.findViewById(R.id.txtTime2);
        txtTime3 = rootView.findViewById(R.id.txtTime3);
        txtTime4 = rootView.findViewById(R.id.txtTime4);
        txtTime5 = rootView.findViewById(R.id.txtTime5);
        txtTime6 = rootView.findViewById(R.id.txtTime6);
        txtTime7 = rootView.findViewById(R.id.txtTime7);
        txtTime8 = rootView.findViewById(R.id.txtTime8);
        txtTime9 = rootView.findViewById(R.id.txtTime9);


        Bundle bundle = getArguments();
        Email = bundle.getString("email");


        Spinner spinnerDay = rootView.findViewById(R.id.spinnerDay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapter);
        spinnerDay.setOnItemSelectedListener(this);

    return rootView;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String daySelected = adapterView.getItemAtPosition(i).toString();
        //query on the day for the specific trainee

        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                       getContext(),"TRAINING_CENTER",null,1);

        Cursor cursor = dataBaseHelper.getSecId(Email);// search for email in T2S table
        cursor.moveToFirst();
        TreeMap<Section,Integer> map=new TreeMap<Section,Integer>();// Save each section with the status of the student

        while(cursor.moveToNext()){
            cursor.getString(1);
            int status = cursor.getInt(3);

            //with the section id get the section info
            Cursor cursor2 = dataBaseHelper.getSecInfo(Integer.parseInt(cursor.getString(1)));
            cursor2.moveToFirst();
            if (cursor2.moveToNext()){
                Section sec1 = new Section(cursor2.getInt(0),cursor2.getString(1),cursor2.getInt(2),cursor2.getInt(3),
                        cursor2.getString(4),cursor2.getString(5),cursor2.getString(6),cursor2.getString(7),cursor2.getString(8),cursor2.getString(9));
//                cursor2.getString(4);//start time
//                cursor2.getString(5);//end time
//                cursor2.getString(6);//days
//                cursor2.getString(7);//course room

                map.put(sec1, status);//the section and its status for the student

            }

        }


        System.out.println(map.size());

        //get the sections of the specific day and the status of the student in each section and put them in the table layout of the schedule
        //if the student is not in the section the status is 0 and the section is not in the table layout of the schedule of the student for this day
        //if the student is in the section the status is 1 and the section is in the table layout of the schedule of the student for this day with the status of the student in this section (1 or 2)  1:present 2:absent



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}