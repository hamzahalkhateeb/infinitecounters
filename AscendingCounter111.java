package com.example.a99;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AscendingCounter111 extends AppCompatActivity {

    database2 db;
    ListView recyclerView;
    FloatingActionButton GoToTaskList;
    FloatingActionButton GoToDescendingCounter;
    FloatingActionButton GoToInfiniteCounter;
    FloatingActionButton AddCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ascendingcounter111);

        recyclerView = findViewById(R.id.recyclerView2);
        AddCounter = findViewById(R.id.addcounter2);
        GoToTaskList = findViewById(R.id.GoToTaskList2);
        GoToDescendingCounter = findViewById(R.id.GoToDescendingCounter2);
        GoToInfiniteCounter = findViewById(R.id.GoToInfiniteCounter);

        db = new database2(AscendingCounter111.this);
        List<ASCconstructor> everyone = db.geteveryone2();

        ASCListAdapter adapter = new ASCListAdapter(this, R.layout.asc_adapter_view, everyone);
        recyclerView.setAdapter((adapter));

        AddCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openaddcounter();
            }
        });

        GoToDescendingCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {opendescending();}
        });

        GoToTaskList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {opentasklist();}
        });

        GoToInfiniteCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {gotoinfinitecounter();}
        });
    }

    public void openaddcounter() {
        Intent i = new Intent(this, ADD_COUNTER.class);
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

    public void gotoinfinitecounter(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }














}
