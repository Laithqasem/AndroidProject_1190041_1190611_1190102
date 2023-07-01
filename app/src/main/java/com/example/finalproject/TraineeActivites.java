package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TraineeActivites extends AppCompatActivity {

    public static String email = "big@email.com";//fix to send from login.
    public static FragmentManager fragmentManagerTrainee;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee_activites);

        fragmentManagerTrainee = getSupportFragmentManager();

        Button profileButton = findViewById(R.id.btnProfile);
        Button searchButton = findViewById(R.id.btnSearch);
        Button homeButton = findViewById(R.id.btnHome);

        Bundle bundle = new Bundle();
        bundle.putString("email", getEmail());
        Trainee_Profile_Fragment fragobj = new Trainee_Profile_Fragment();
        Trainee_Schedule_Fragment fragobj2 = new Trainee_Schedule_Fragment();
        fragobj.setArguments(bundle);
        fragobj2.setArguments(bundle);

        currentFragment = fragmentManagerTrainee.findFragmentById(R.id.FirstFragment);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSchedule();
            }
        });



        // Check if there's a saved instance state
        if (savedInstanceState == null) {
            // Set the initial fragment
            openProfile();
        } else {
            // Restore the current fragment
            currentFragment = fragmentManagerTrainee.findFragmentById(R.id.FirstFragment);
        }
    }

    private void openProfile() {

            Fragment profileFragment = new Trainee_Profile_Fragment();

            FragmentTransaction fragmentTransaction = fragmentManagerTrainee.beginTransaction();

            // Hide all other fragments
            for (Fragment fragment : fragmentManagerTrainee.getFragments()) {
                if (fragment != profileFragment) {
                    fragmentTransaction.hide(fragment);
                }
            }

            // Show the profile fragment if it is not already added
            if (!profileFragment.isAdded()) {
                fragmentTransaction.add(R.id.content_frame, profileFragment);
            }

            // Show the profile fragment
            fragmentTransaction.show(profileFragment);

            // Add the transaction to the back stack
            fragmentTransaction.addToBackStack(null);

            // Commit the transaction
            fragmentTransaction.commit();

            // Update the current fragment
            currentFragment = profileFragment;

    }

    private void openSchedule(){
        Fragment scheduleFragment = new Trainee_Schedule_Fragment();

        FragmentTransaction fragmentTransaction = fragmentManagerTrainee.beginTransaction();

        // Hide all other fragments
        for (Fragment fragment : fragmentManagerTrainee.getFragments()) {
            if (fragment != scheduleFragment) {
                fragmentTransaction.hide(fragment);
            }
        }

        // Show the search fragment if it is not already added
        if (!scheduleFragment.isAdded()) {
            fragmentTransaction.add(R.id.content_frame, scheduleFragment);
        }

        // Show the search fragment
        fragmentTransaction.show(scheduleFragment);

        // Add the transaction to the back stack
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();

        // Update the current fragment
        currentFragment = scheduleFragment;

    }

    private void openSearch() {
        Fragment searchFragment = new Trainee_Search_Fragment();

        FragmentTransaction fragmentTransaction = fragmentManagerTrainee.beginTransaction();

        // Hide all other fragments
        for (Fragment fragment : fragmentManagerTrainee.getFragments()) {
            if (fragment != searchFragment) {
                fragmentTransaction.hide(fragment);
            }
        }

        // Show the search fragment if it is not already added
        if (!searchFragment.isAdded()) {
            fragmentTransaction.add(R.id.content_frame, searchFragment);
        }

        // Show the search fragment
        fragmentTransaction.show(searchFragment);

        // Add the transaction to the back stack
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();

        // Update the current fragment
        currentFragment = searchFragment;
    }

    // Override the onBackPressed() method to handle back button presses
    @Override
    public void onBackPressed() {
        // Check if the current fragment is the profile fragment
        if (currentFragment instanceof Trainee_Profile_Fragment) {
            // If it is, perform the default back button behavior
            super.onBackPressed();
        } else if(currentFragment instanceof Trainee_Search_Fragment) {
            openSearch();
        } else if(currentFragment instanceof Trainee_Schedule_Fragment) {
            openSchedule();
        }else {
            // If it's not, go back to the profile fragment
            openProfile();
        }
    }

    // Get the current fragment
    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public int getCurrentFragmentId() {
        return currentFragment != null ? currentFragment.getId() : 0;
    }

    public static String getEmail() { return email;}

}
