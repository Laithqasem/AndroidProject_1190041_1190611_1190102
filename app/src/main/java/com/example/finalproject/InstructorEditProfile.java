package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InstructorEditProfile extends AppCompatActivity {

    EditText mobileNo,address,email,first_name,last_name,password,confirm_password;
    boolean valid_email,valid_last_name,valid_first_name,valid_confirm_password,valid_password,valid_address,valid_Specialization=false,valid_can_teach_list,valid_mobile_no;
    static final String TOAST_TEXT = "check the wrong fields";
    Uri selectedImageUri;
    RadioGroup specialization_group;
    RadioButton bsc_option,msc_option,phd_option;
    static final int REQUEST_CODE_IMAGE_PICKER = 100;
    String chosen_specialization="";
    private ImageView imageView;
    CheckBox tech_option, business_option,marketing_option,biology_option,physics_option,math_option, literature_option,pharmacy_option,engineering_option,accountant_option,medicine_option,law_option;
    String canTeach="";
    private Button chooseImageButton;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_edit_profile);

        imageView = findViewById(R.id.imageView);
        chooseImageButton = findViewById(R.id.chooseImageButton);

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });


        first_name = findViewById(R.id.edit_first_name_text);
        last_name = findViewById(R.id.edit_last_name_text);
        password = findViewById(R.id.edit_password_text);
        mobileNo = findViewById(R.id.edit_mobile_text);
        address = findViewById(R.id.edit_address_text);
        specialization_group =  findViewById(R.id.specialization);
        bsc_option =  findViewById(R.id.BSc);
        msc_option =  findViewById(R.id.MSc);
        phd_option =  findViewById(R.id.PhD);
        tech_option = findViewById(R.id.techCheckBox);
        business_option = findViewById(R.id.BuisnessCheckBox);
        marketing_option= findViewById(R.id.MarketingCheckBox);
        literature_option = findViewById(R.id.LiteratureCheckBox);
        engineering_option =findViewById(R.id.EngineeringCheckBox);
        physics_option =findViewById(R.id.PhysicsCheckBox);
        math_option = findViewById(R.id.MathCheckBox);
        biology_option = findViewById(R.id.BiologyCheckBox);
        pharmacy_option= findViewById(R.id.PharmacyCheckBox);
        medicine_option = findViewById(R.id.MedicineCheckBox);
        law_option= findViewById(R.id.LawCheckBox);
        accountant_option = findViewById(R.id.AccountingCheckBox);


        first_name.setShowSoftInputOnFocus(false);
        last_name.setShowSoftInputOnFocus(false);
        password.setShowSoftInputOnFocus(false);
        address.setShowSoftInputOnFocus(false);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        first_name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        last_name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        mobileNo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_PHONE);
        address.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

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
        Button first_name_button = findViewById(R.id.edit_first_name_button);
        Button last_name_button = findViewById(R.id.edit_last_name_button);
        Button address_button = findViewById(R.id.edit_address_button);
        Button password_button = findViewById(R.id.edit_password_button);
        Button specialization_button = findViewById(R.id.editSpecialization_button);
        Button mobileNo_button = findViewById(R.id.edit_mobile_number_button);
        Button canTeach_button = findViewById(R.id.changeFields_button);


        first_name_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name_string = first_name.getText().toString();

                if(first_name_string.equals("")){
                    Toast.makeText(InstructorEditProfile.this,"There is an Empty First Name" , Toast.LENGTH_SHORT).show();
                }else if(valid_first_name ){

                    System.out.println(first_name_string);


                }

            }
        });
        last_name_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String last_name_string = last_name.getText().toString();

                if(last_name_string.equals("")){
                    Toast.makeText(InstructorEditProfile.this,"There is an Empty Last Name" , Toast.LENGTH_SHORT).show();
                }else if(valid_last_name ){
                    System.out.println(last_name_string);
                }

            }
        });
        password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password_string = password.getText().toString();

                if(password_string.equals("")){
                    Toast.makeText(InstructorEditProfile.this,"There is an Empty Password" , Toast.LENGTH_SHORT).show();
                }else if(valid_password ){
                    System.out.println(password_string);
                }

            }
        });
        mobileNo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile_string = mobileNo.getText().toString();

                if(mobile_string.equals("")){
                    Toast.makeText(InstructorEditProfile.this,"There is an Empty Mobile No" , Toast.LENGTH_SHORT).show();
                }else if(valid_mobile_no ){
                    System.out.println(mobile_string);
                }

            }
        });
        address_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address_string = address.getText().toString();

                if(address_string.equals("")){
                    Toast.makeText(InstructorEditProfile.this,"There is an Empty Address" , Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println(address_string);
                }

            }
        });
        specialization_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chosen_specialization.equals("")){
                    Toast.makeText(InstructorEditProfile.this,"There is an Empty Specialization" , Toast.LENGTH_SHORT).show();
                }else if(valid_Specialization ){
                    System.out.println(chosen_specialization);
                }

            }
        });

        canTeach_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(canTeach.equals("")){
                    Toast.makeText(InstructorEditProfile.this,"There is an Empty Address" , Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println(canTeach);
                }

            }
        });



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











    }
