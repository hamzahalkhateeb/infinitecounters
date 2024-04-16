package com.example.a99;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ADD_COUNTER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);
    }

    public void infincounter(View V){
        Intent i = new Intent(this, infincounter_setup.class);
        startActivity(i);
    }
    public void goalcounter(View V){
        Intent i = new Intent(this, goalcounter_setup.class);
        startActivity(i);
    }

    public void task(View V){
        Intent i = new Intent(this, Task_setup.class);
        startActivity(i);
    }
}