package com.example.thirsttrack;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class UpdateFragment extends Fragment {
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_update, container, false);
        Home homeAct = (Home) getActivity();
        Button backBtn = (Button) v.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> replaceFragment(new HomeFragment()) );
        Button updateIntakebtn = (Button) v.findViewById(R.id.submitBtn);
        EditText updatedIntake = (EditText) v.findViewById(R.id.updatedIntake);
        updateIntakebtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view){
                        double newIntake = Double.valueOf(updatedIntake.getText().toString());
                        homeAct.setIntakeLvl(newIntake);
                        replaceFragment(new HomeFragment());
                    }
                });
        return v;
    }

    private void replaceFragment(HomeFragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.flFrag, fragment);
        fragmentTransaction.commit();
    }
}