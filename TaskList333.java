package com.example.a99;

import android.app.TimePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class TaskList333 extends AppCompatActivity {

    database3 db;
    ListView recyclerView;

    FloatingActionButton GoToDescending;
    FloatingActionButton GoToAscendingCounter;
    FloatingActionButton GoToInfiniteCounter4;
    FloatingActionButton AddCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasklist333);

        recyclerView = findViewById(R.id.RecyclerView44);
        GoToDescending = findViewById(R.id.GoToDescendingCounter4);
        GoToAscendingCounter = findViewById(R.id.GoToAscendingCounter4);
        GoToInfiniteCounter4 = findViewById(R.id.GoToInfiniteCounter4);
        AddCounter = findViewById(R.id.addcounter4);

        db = new database3(TaskList333.this);
        List<TaskConstructor> everyone = db.getallmainTasks();

        TaskListAdapter adapter = new TaskListAdapter(this, R.layout.task_adapter, everyone);
        recyclerView.setAdapter(adapter);


        GoToInfiniteCounter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {gotoinfinitecounter();}
        });

        GoToAscendingCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openascendingcounter();}
        });

        AddCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openaddcounter();
            }
        });

        GoToDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendescending();
            }
        });

    }
    public void gotoinfinitecounter(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void openascendingcounter() {
        Intent i = new Intent(this, AscendingCounter111.class);
        startActivity(i);
    }


    public void openaddcounter() {
        Intent i = new Intent(this, ADD_COUNTER.class);
        startActivity(i);
    }

    public void opendescending() {
        Intent i = new Intent(this, DescendingCounter222.class);
        startActivity(i);
    }



}


