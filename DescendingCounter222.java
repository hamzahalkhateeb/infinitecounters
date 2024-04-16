package com.example.a99;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DescendingCounter222 extends AppCompatActivity {

    database2d db;
    ListView recyclerView;

    FloatingActionButton GoToTaskList;
    FloatingActionButton GoToAscendingCounter;
    FloatingActionButton GoToInfiniteCounter;
    FloatingActionButton AddCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descendingcounter222);

    recyclerView = findViewById(R.id.recyclerView3);
    GoToTaskList = findViewById(R.id.GoToTaskList3);
    GoToAscendingCounter = findViewById(R.id.GoToAscendingCounter3);
    GoToInfiniteCounter = findViewById(R.id.GoToInfiniteCounter3);
    AddCounter = findViewById(R.id.addcounter3);

    db = new database2d(DescendingCounter222.this);
    List<DESCconstructor> everyone = db.geteveryone3();

    DESCListAdapter adapter = new DESCListAdapter(this, R.layout.desc_adapter_view, everyone);
    recyclerView.setAdapter((adapter));

        AddCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openaddcounter();
            }
        });

        GoToInfiniteCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {gotoinfinitecounter();}
        });

        GoToTaskList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {opentasklist();}
        });

        GoToAscendingCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openascendingcounter();}
        });
    }

    public void openaddcounter() {
        Intent i = new Intent(this, ADD_COUNTER.class);
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

    public void openascendingcounter() {
        Intent i = new Intent(this, AscendingCounter111.class);
        startActivity(i);
    }


}
