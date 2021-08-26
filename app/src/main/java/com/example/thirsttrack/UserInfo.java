package com.example.thirsttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserInfo extends AppCompatActivity {
    private Button btn;
    private EditText usrName;
    private EditText usrAge;
    private EditText usrWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        btn = (Button) findViewById(R.id.button2);
        setTitle("User Information");
        usrName = (EditText) findViewById(R.id.name);
        usrAge = (EditText) findViewById(R.id.age);
        usrWeight = (EditText) findViewById(R.id.weight);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        String name = usrName.getText().toString();
                        String age = usrAge.getText().toString();
                        String weight = usrWeight.getText().toString();
                        launchHome(name, age, weight);
                    }
                });
    }

    private void launchHome(String name, String age, String weight) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        intent.putExtra("weight", weight);
        startActivity(intent);
    }
}