package com.example.a99;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class goalcounter_setup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goalcounter_setup);
    }

    public void cancel(View V){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void disable(View B) {
        CheckBox ascend = findViewById(R.id.ascendingcheck);
        boolean ascend1 = ascend.isChecked();
        CheckBox descend = findViewById(R.id.descendingcheck);
        boolean descend1 = descend.isChecked();

        if (ascend1 == true) {
            descend.setEnabled(false);
        } else if (ascend1 == false) {
            descend.setEnabled(true);
        }
    }

    public void disable2(View M){
        CheckBox ascend = findViewById(R.id.ascendingcheck);
        boolean ascend1 = ascend.isChecked();
        CheckBox descend = findViewById(R.id.descendingcheck);
        boolean descend1 = descend.isChecked();

        if(descend1 == true){
            ascend.setEnabled(false);
        }
        else if(descend1 == false){
            ascend.setEnabled(true);
        }
    }

    public void save2a(View D) {
        //get the goal counter name inputted by user
        String name = ((EditText) findViewById(R.id.goalcountername)).getText().toString();

        //get the click goal number inputted by user as a string
        String amount = ((EditText) findViewById(R.id.clickgoal)).getText().toString();


        //extract a boolean value from the check boxes
        CheckBox ascend = findViewById(R.id.ascendingcheck);
        boolean ascend1 = ascend.isChecked();
        CheckBox descend = findViewById(R.id.descendingcheck);
        boolean descend1 = descend.isChecked();


        //check for name field
        if (name.length() == 0 || name == null) {
            Toast.makeText(this, "PLEASE ASSIGN A NAME", Toast.LENGTH_SHORT).show();
        }

        //check if switches weren't turned on
        else if (ascend1 == false && descend1 == false) {
            Toast.makeText(this, "PLEASE TICK A BOX!", Toast.LENGTH_SHORT).show();

        }

        //check for amount field
        else if (amount.length() == 0 || amount == null) {
            Toast.makeText(this, "PLEASE ASSIGN A CLICK GOAL", Toast.LENGTH_SHORT).show();
        }
        else if (ascend1 == true){
            database2 myDB = new database2(goalcounter_setup.this);
            myDB.addgoalcounter2a(name, 0, Integer.parseInt(amount));
            openascendingcounter();
        }
        else if(descend1 == true){
            database2d myDB = new database2d(goalcounter_setup.this);
            myDB.addgoalcounter2d(name, Integer.parseInt(amount), 0);
            opendescending();

        }

    }

    public void openascendingcounter() {
        Intent i = new Intent(this, AscendingCounter111.class);
        startActivity(i);
    }

    public void opendescending(){
        Intent i = new Intent(this, DescendingCounter222.class);
        startActivity(i);
    }


}