package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static Course course;
    private RecyclerView contactsRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        course = new Course("CMSC350","2022","Spring");
        RecyclerView contactsRecView = findViewById(R.id.assignmentList);

    }

    public void addAssignment(View view){
        //TextView txtHello = findViewById(R.id.textView3);
        course.addAssignment("Blah", 90.0, 100.0);


    }

    @Override
    public void onClick(View view) {

    }
}