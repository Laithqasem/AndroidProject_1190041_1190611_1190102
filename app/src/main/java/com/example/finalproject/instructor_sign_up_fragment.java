package com.example.finalproject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class instructor_sign_up_fragment extends Fragment {

    private EditText mobileNo,address,email,first_name,last_name,password,confirm_password;
    boolean validSpecialization,valid_email,valid_last_name,valid_first_name,valid_confirm_password,valid_password,valid_address,valid_Specialization=false,valid_can_teach_list,valid_mobile_no;
    private static final String TOAST_TEXT = "check the wrong fields";
    Uri selectedImageUri;
    RadioGroup specialization_group;
    RadioButton bsc_option,msc_option,phd_option;
    private static final int REQUEST_CODE_IMAGE_PICKER = 100;
    String chosen_specialization="";
    private ImageView imageView;
    CheckBox tech_option, business_option,marketing_option,biology_option,physics_option,math_option, literature_option,pharmacy_option,engineering_option,accountant_option,medicine_option,law_option;
    String canTeach="";
    Spinner Specialization;
    String selected_specialization;

    private Button chooseImageButton;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.instructor_sign_up_fragment, container, false);

        imageView = view.findViewById(R.id.imageView);
        chooseImageButton = view.findViewById(R.id.chooseImageButton);

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        email = view.findViewById(R.id.Course_startDate);
        first_name = view.findViewById(R.id.edit_first_name_text);
        last_name = view.findViewById(R.id.edit_last_name_text);
        password = view.findViewById(R.id.edit_password_text);
        confirm_password =view.findViewById(R.id.confirm_password);
        mobileNo = view.findViewById(R.id.edit_mobile_text);
        address = view.findViewById(R.id.edit_address_text);
        specialization_group =  view.findViewById(R.id.specialization);
        bsc_option =  view.findViewById(R.id.BSc);
        msc_option =  view.findViewById(R.id.MSc);
        phd_option =  view.findViewById(R.id.PhD);
        tech_option = view.findViewById(R.id.techCheckBox);
        business_option = view.findViewById(R.id.BuisnessCheckBox);
        marketing_option= view.findViewById(R.id.MarketingCheckBox);
        literature_option = view.findViewById(R.id.LiteratureCheckBox);
        engineering_option = view.findViewById(R.id.EngineeringCheckBox);
        physics_option = view.findViewById(R.id.PhysicsCheckBox);
        math_option = view.findViewById(R.id.MathCheckBox);
        biology_option = view.findViewById(R.id.BiologyCheckBox);
        pharmacy_option= view.findViewById(R.id.PharmacyCheckBox);
        medicine_option = view.findViewById(R.id.MedicineCheckBox);
        law_option= view.findViewById(R.id.LawCheckBox);
        accountant_option = view.findViewById(R.id.AccountingCheckBox);


        Specialization =(Spinner)
                view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Specialization.setAdapter(adapter);



        email.setShowSoftInputOnFocus(false);
        first_name.setShowSoftInputOnFocus(false);
        last_name.setShowSoftInputOnFocus(false);
        password.setShowSoftInputOnFocus(false);
        confirm_password.setShowSoftInputOnFocus(false);
        address.setShowSoftInputOnFocus(false);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirm_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        email.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        first_name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        last_name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        mobileNo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_PHONE);
        address.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Validate character length
                String email_string = s.toString();
                if (!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()) {
                    email.setError("Please enter a valid email address");
                    valid_email = false;
                } else {
                    valid_email = true;
                    email.setError(null);
                }
            }
        });
        first_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Validate character length
                boolean containsNumbers = containsNumbers(s.toString());

                if (containsNumbers) {
                    first_name.setError("First name can't contain numbers");
                    valid_first_name = false;
                }
                else if (s.length() < 3 || s.length() > 20) {
                    first_name.setError("First Name length must be between 3 and 20");
                    valid_first_name = false;
                } else {
                    first_name.setError(null);
                    valid_first_name = true;
                }
            }
        });
        last_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Validate character length
                boolean containsNumbers = containsNumbers(s.toString());

                if (containsNumbers) {
                    last_name.setError("Last name can't contain numbers");
                    valid_last_name = false;
                }
                else if (s.length() < 3 || s.length() > 20) {
                    last_name.setError("Last Name length must be between 3 and 20");
                    valid_last_name = false;
                } else {
                    last_name.setError(null);
                    valid_last_name = true;
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Validate character length
                boolean containsNumbers = containsNumbers(s.toString());
                boolean contains_lower = containsLowercase(s.toString());
                boolean contains_upper = containsUppercase(s.toString());
                if(!containsNumbers || !contains_lower ||!contains_upper ){
                    password.setError("Password must contain at least one \n" +
                            "number, one lowercase letter, and one uppercase letter");
                    valid_password = false;
                }else if (s.length() < 8 || s.length() > 15) {
                    password.setError("Password length must be between 8 and 15");
                    valid_password = false;
                } else {
                    password.setError(null);
                    valid_password = true;
                }
            }
        });
        confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Validate character length
                if (!(password.getText().toString().equals(confirm_password.getText().toString()))) {
                    confirm_password.setError("the confirm password field is different");
                    valid_confirm_password = false;
                } else {
                    confirm_password.setError(null);
                    valid_confirm_password = true;
                }
            }
        });
        mobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Validate character length
                String mobile = s.toString();
                if (!isValidMobileNO(mobile)) {
                    mobileNo.setError("Please enter a valid mobile Number");
                    valid_mobile_no = false;
                } else {
                    valid_mobile_no = true;
                    mobileNo.setError(null);
                }
            }
        });
        specialization_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                mobileNo.setError("Please enter a valid mobile Number");
//                valid_mobile_no = false;


                switch (checkedId) {
                    case R.id.BSc:
                        chosen_specialization="BSc";
                        valid_Specialization=true;
                        break;
                    case R.id.MSc:
                        chosen_specialization="MSc";
                        valid_Specialization=true;
                        break;
                    case R.id.PhD:
                        chosen_specialization="PhD";
                        valid_Specialization=true;
                        break;
                }
            }
        });
        tech_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Technology";
                }
            }
        });
        business_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Business";
                }
            }
        });
        marketing_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Marketing";
                }
            }
        });
        literature_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Literature";
                }
            }
        });
        engineering_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Engineering";
                }
            }
        });
        math_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Math";
                }
            }
        });
        biology_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Biology";
                }
            }
        });
        medicine_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Medicine";
                }
            }
        });
        pharmacy_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Pharmacy";
                }
            }
        });
        physics_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Physics";
                }
            }
        });
        accountant_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Accountant";
                }
            }
        });
        law_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state
                if (isChecked) {
                    canTeach+=", Law";
                }
            }
        });
        Specialization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the selected item
                selected_specialization = parent.getItemAtPosition(position).toString();
                if (selected_specialization.equals("")) {
                    validSpecialization = false;
                } else {
                    validSpecialization = true;
                    mobileNo.setError(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button sign_up_button = view.findViewById(R.id.sign_up2);

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_string = email.getText().toString();
                String first_name_string = first_name.getText().toString();
                String last_name_string = last_name.getText().toString();
                String password_string = password.getText().toString();
                String confirm_password_string = confirm_password.getText().toString();
                String mobile_string = mobileNo.getText().toString();
                String address_string = address.getText().toString();


                if(chosen_specialization.equals("")||address_string.equals("")||mobile_string.equals("")||email_string.equals("")||last_name_string.equals("")||first_name_string.equals("")||password_string.equals("")||confirm_password_string.equals("")){
                    Toast.makeText(getContext(),"There is an Empty Fields" , Toast.LENGTH_SHORT).show();
                }else if(!tech_option.isChecked() && !business_option.isChecked()  && !marketing_option.isChecked()
                        && !literature_option.isChecked()  && !engineering_option.isChecked() && !physics_option.isChecked()
                        && !math_option.isChecked() && !biology_option.isChecked() && !medicine_option.isChecked()
                        && !pharmacy_option.isChecked() && !law_option.isChecked() && !accountant_option.isChecked()
                ){
                    Toast.makeText(getContext(),"check some of Teachable Fields" , Toast.LENGTH_SHORT).show();
                }else if(validSpecialization &&valid_Specialization &&valid_mobile_no && valid_email && valid_confirm_password && valid_first_name && valid_last_name && valid_password){
                    System.out.println(email_string);
                    System.out.println(first_name_string);
                    System.out.println(last_name_string);
                    System.out.println(password_string);
                    System.out.println(confirm_password_string);
                    System.out.println(selectedImageUri);
                    System.out.println(mobile_string);
                    System.out.println(address_string);
                    System.out.println(chosen_specialization);
                    System.out.println(canTeach);


                    Instructor instructor = new Instructor();
                    instructor.setEmail(email_string);
                    instructor.setPassword(password_string);
                    instructor.setFirstName(first_name_string);
                    instructor.setLastName(last_name_string);

                    byte[] image_in_bytes= new byte[1024];
                    try {
                        uriToByteArray(getContext(),selectedImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    instructor.setImage(image_in_bytes);
                    instructor.setMobileNumber(mobile_string);
                    instructor.setAddress(address_string);
                    instructor.setSpecialization(selected_specialization);
                    instructor.setDegree(chosen_specialization);
                    instructor.setCanTeach(canTeach);

//                    String databaseName = "TRAINING_CENTER";
//                    getContext().deleteDatabase(databaseName);

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(
                            getContext(),"TRAINING_CENTER",null,1);

                    boolean validSignUp = dataBaseHelper.vaildSignUp(email_string);
                    if(validSignUp){
                        dataBaseHelper.insertInstructor(instructor);
                    }else{
                        Toast.makeText(getContext(),"the email is used" , Toast.LENGTH_SHORT).show();
                    }

                    Cursor res3 = dataBaseHelper.getAllInstructor();
                    System.out.println("INSTRUCTORS:");
                    while (res3.moveToNext()){

                        System.out.println(
                                "Email= "+res3.getString(0)
                                        +"\nPassword= "+res3.getString(1)
                                        +"\nFirstName= "+res3.getString(2)
                                        +"\nLastName= "+res3.getString(3)
                                        +"\nPersonalPhoto= "+res3.getBlob(4)
                                        +"\n\n"
                        );
                    }



                }else{
                    Toast.makeText(getContext(),TOAST_TEXT , Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_IMAGE_PICKER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_IMAGE_PICKER && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imageView.setImageURI(selectedImageUri);
        }
    }

    public boolean containsNumbers(String s) {
        for (char curr : s.toCharArray()) {
            if (Character.isDigit(curr)) {
                return true;
            }
        }
        return false;
    }
    public boolean containsUppercase(String s) {
        for (char curr : s.toCharArray()) {
            if (Character.isUpperCase(curr)) {
                return true;
            }
        }
        return false;
    }
    public boolean containsLowercase(String s) {
        for (char curr : s.toCharArray()) {
            if (Character.isLowerCase(curr)) {
                return true;
            }
        }
        return false;
    }
    public boolean isValidMobileNO(String mobile_No) {
        String regex = "^[+]?[0-9]{10,13}$";
        return mobile_No.matches(regex);
    }
    public byte[] uriToByteArray(Context context, Uri uri) throws IOException {
        ContentResolver contentResolver = context.getContentResolver();
        InputStream inputStream = contentResolver.openInputStream(uri);

        // Read the input stream and convert it to a byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        // Close the input stream and the output stream
        inputStream.close();
        byteArrayOutputStream.close();

        return byteArray;
    }

}