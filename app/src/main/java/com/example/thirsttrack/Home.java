package com.example.thirsttrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.EditText;


import java.text.BreakIterator;
import java.text.DecimalFormat;

public class Home extends AppCompatActivity {
    private TextView inputText;
    private TextView intakeFact;
    private TextView intakeGoal;
    private double intakeLvl;
    private Button updateBtn;
    private Button trackBtn;
    public double weight;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Homepage");

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        int age = Integer.parseInt(i.getStringExtra("age"));
        weight = Integer.parseInt(i.getStringExtra("weight"));
        intakeLvl = 0;

        inputText = findViewById(R.id.input_label);
        inputText.setText("Welcome "+  name.substring(0,1).toUpperCase() +
                name.substring(1).toLowerCase() +"!");

        funFactInfo(age);
        intakeGoalInfo(weight);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("my noti", "my noti", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        trackBtn = findViewById(R.id.trackBtn);
        trackBtn.setOnClickListener(v -> replaceFragment(new HomeFragment()) );
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFrag, fragment);
        fragmentTransaction.commit();
        trackBtn.setEnabled(false);
        trackBtn.setVisibility(View.INVISIBLE);
    }

    public void setIntakeLvl(double intake){
        intakeLvl = intake;
    }

    public String getPercentage(double intake) {
        DecimalFormat df = new DecimalFormat("###");
        double percent = (intake / getGoal()) * 100;
        if (df.format(percent).equals("100")) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(Home.this, "Goal Reached Noti");
            builder.setContentTitle("ThirstTracker");
            builder.setContentText("You have reached your hydration goal");
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
            builder.setAutoCancel(true);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Home.this);
            managerCompat.notify(1, builder.build());
        }

        return df.format(percent);
    }
    public String getIntakeLvlStr(){
        DecimalFormat df = new DecimalFormat("###");
        return df.format(intakeLvl);
    }

    public double getIntakeLvl(){
        return intakeLvl;
    }

    public double getGoal(){
        return (weight*2)/3;
    }

    public String getGoalStr(){
        DecimalFormat df = new DecimalFormat("###");
        return df.format((weight*2)/3);
    }

    @SuppressLint("SetTextI18n")
    private void funFactInfo(int age) {
        intakeFact = findViewById(R.id.intakeFact);
        if (age <= 8) {
            intakeFact.setText("Hydration Fact: children 4–8 years old should consume 5 cups of" +
                    " water, or 40 total ounces!");
        } else if (age <= 13) {
            intakeFact.setText("Hydration Fact: children 9–13 years old should consume 7–8 cups " +
                    "of water, or 56–64 total ounces!");
        } else if (age <= 18) {
            intakeFact.setText("Hydration Fact: children 14–18 years old should consume 8–11 cups" +
                    " of water, or 64–88 total ounces!");
        } else {
            intakeFact.setText("Hydration Fact: adults 19 years and older should consume 9-13 cups"+
                    " of water, or 72-104 total ounces!");
        }
    }

    private void intakeGoalInfo(double weight) {
        DecimalFormat df = new DecimalFormat("###.#");
        double waterIntake_oz = (weight*2)/3;
        double waterIntake_lt = waterIntake_oz/33.814;
        double waterIntake_lbs = waterIntake_oz/16;
        intakeGoal = findViewById(R.id.intakeGoal);
        String intakeGoalStr = "Based on your weight you should consume: \n" +
                df.format(waterIntake_oz) + " ounces\n" + df.format(waterIntake_lt) +
                " liters\n" + df.format(waterIntake_lbs) + " pounds";
        intakeGoal.setText(intakeGoalStr);
    }

}