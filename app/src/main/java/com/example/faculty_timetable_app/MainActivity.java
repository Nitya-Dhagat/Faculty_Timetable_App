package com.example.faculty_timetable_app;


import android.os.Bundle;

import android.widget.*;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button submitBtn;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitBtn = (Button)findViewById(R.id.submitBtn);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Details saved",Toast.LENGTH_SHORT).show();
            }
        });
    }
}