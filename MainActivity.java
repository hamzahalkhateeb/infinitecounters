package com.example.a99;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    database1 myDB;
    ListView recyclerView;
    FloatingActionButton addcounter;
    ArrayAdapter customarrayadapter;
    Button show;
    FloatingActionButton GoToAscendingCounter;
    FloatingActionButton GoToDescendingCounter;

    FloatingActionButton GoToTaskList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         recyclerView = findViewById(R.id.recyclerView);
         addcounter =  findViewById(R.id.addcounter);
        GoToAscendingCounter = findViewById(R.id.GoToAscendingCounter);
        GoToDescendingCounter = findViewById(R.id.GoToDescendingCounter);
        GoToTaskList = findViewById(R.id.GoToTaskList);

        myDB = new database1(MainActivity.this);
        List<infinconstructor> everyone = myDB.geteveryone();


        newListAdapter adapter = new newListAdapter(this, R.layout.adapter_view, everyone);
        recyclerView.setAdapter((adapter));



        addcounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openaddcounter();
            }
        });


        GoToAscendingCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openascendingcounter();}
        });


        GoToDescendingCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {opendescending();}
        });

        GoToTaskList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {opentasklist();}
        });


    }
    public void openaddcounter() {
        Intent i = new Intent(this, ADD_COUNTER.class);
        startActivity(i);
    }

    public void openascendingcounter() {
        Intent i = new Intent(this, AscendingCounter111.class);
        startActivity(i);
    }

    public void opendescending(){
        Intent i = new Intent(this, DescendingCounter222.class);
        startActivity(i);
        }



    public void opentasklist(){
        Intent i = new Intent(this, TaskList333.class);
        startActivity(i);
    }



}

