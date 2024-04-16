package com.example.a99;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class infincounter_setup extends AppCompatActivity {

    EditText name;
    int current_clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infincounter_setup);
    }
    public void cancel(View V){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    public void save(View B) {
        name = findViewById(R.id.infincounter_name);

        if (name.length() == 0 || name == null) {
            Toast.makeText(this, "PLEASE ASSIGN A NAME", Toast.LENGTH_LONG).show();
        } else{
            database1 myDB = new database1(infincounter_setup.this);
            myDB.addinfincounter(name.getText().toString(), Integer.parseInt(String.valueOf(current_clicks)));
            gotoinfinitecounter();
        }
    }
    public void gotoinfinitecounter(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}