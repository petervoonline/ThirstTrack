package com.example.thirsttrack;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    private TextView intakeInfo;
    private TextView intakeInfo2;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_home, container, false);
        Button updateBtn = (Button) v.findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(v -> replaceFragment(new UpdateFragment()) );
        Home homeAct = (Home) getActivity();
        intakeInfo = (TextView) v.findViewById(R.id.intakeLvl);
        intakeInfo.setText("     " + homeAct.getPercentage(homeAct.getIntakeLvl()) + "%");
        intakeInfo2 = (TextView) v.findViewById(R.id.intakeLvl2);
        intakeInfo2.setText("        " + homeAct.getIntakeLvlStr() + "/" + homeAct.getGoalStr() + " (oz)");

        return v;
    }

    private void replaceFragment(UpdateFragment updateFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.flFrag, updateFragment);
        fragmentTransaction.commit();
    }

}