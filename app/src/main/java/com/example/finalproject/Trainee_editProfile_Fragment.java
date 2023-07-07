package com.example.finalproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Trainee_editProfile_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Trainee_editProfile_Fragment extends Fragment {

    private EditText textViewFirstName;
    private EditText textViewLastName;
    private EditText textViewEmail;
    private EditText textViewMobileNumber;
    private EditText textViewAddress;
    private Button buttonSave;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Trainee_editProfile_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Trainee_editProfile_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Trainee_editProfile_Fragment newInstance(String param1, String param2) {
        Trainee_editProfile_Fragment fragment = new Trainee_editProfile_Fragment();
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
        View rootView =  inflater.inflate(R.layout.fragment_trainee_edit_profile_, container, false);
        textViewFirstName = rootView.findViewById(R.id.editTextFirstName);
        textViewLastName = rootView.findViewById(R.id.editTextLastName);
        textViewEmail = rootView.findViewById(R.id.editTextEmail);
        textViewMobileNumber = rootView.findViewById(R.id.editTextMobileNumber);
        textViewAddress = rootView.findViewById(R.id.editTextAddress);
        buttonSave = rootView.findViewById(R.id.buttonSave);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(),"TRAINING_CENTER",null,1);
        Cursor cursor = dataBaseHelper.getOneTrainee("ahmad@gmail.com");

        String email = null;
        String firstName = null;
        String lastName = null;
        String mobileNumber = null;
        String address = null;
        byte[] image;
        while (cursor.moveToNext()){
            email = cursor.getString(0);
            firstName = cursor.getString(2);
            lastName = cursor.getString(3);
            mobileNumber = cursor.getString(4);
            address = cursor.getString(5);
            image = cursor.getBlob(6);

        }

        textViewEmail.setText(email);
        textViewFirstName.setText(firstName);
        textViewLastName.setText(lastName);
        textViewMobileNumber.setText(mobileNumber);
        textViewAddress.setText(address);

       buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textViewFirstName.getText().toString().isEmpty()){
                    textViewFirstName.setError("First name is required");
                    return;
                }
                if (textViewLastName.getText().toString().isEmpty()){
                    textViewLastName.setError("Last name is required");
                    return;
                }
                if (textViewEmail.getText().toString().isEmpty()){
                    textViewEmail.setError("Email is required");
                    return;
                }
                if (textViewMobileNumber.getText().toString().isEmpty()){
                    textViewMobileNumber.setError("Mobile number is required");
                    return;
                }
                if (textViewAddress.getText().toString().isEmpty()){
                    textViewAddress.setError("Address is required");
                    return;

                } else {
                    String alteredFirst = textViewFirstName.getText().toString();
                    String alteredLast = textViewLastName.getText().toString();
                    String alteredMobile = textViewMobileNumber.getText().toString();
                    String alteredAddress = textViewAddress.getText().toString();

                    dataBaseHelper.UpdateeTrainee(alteredFirst,alteredLast,alteredMobile,alteredAddress);
                }
            }
        });




    return rootView;
    }
}
