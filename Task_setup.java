package com.example.a99;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Task_setup extends AppCompatActivity {

    private Button dateButton, timeButton, savet;
    private Calendar calendar;

    private String selectedDate;
    private String selectedTime;
    private String Duedate;
    private String task_name;
    private String subtask1;
    private String subtask2;
    private String subtask3;
    private CheckBox subtaskcheck;
    private CheckBox subtask2check;
    private CheckBox subtask3check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_setup);

        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);
        task_name = ((EditText) findViewById(R.id.taskname)).getText().toString();
        calendar = Calendar.getInstance();
        savet = findViewById(R.id.savet);
        subtask1 = ((EditText)findViewById(R.id.subtask1)).getText().toString();
        subtask2 = ((EditText)findViewById(R.id.subtask2)).getText().toString();
        subtask3 = ((EditText)findViewById(R.id.subtask3)).getText().toString();

        subtaskcheck = findViewById(R.id.subtaskcheck);
        subtask2check = findViewById(R.id.subtask2check);
        subtask3check = findViewById(R.id.subtask3check);


        savet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Savet();
            }
        });


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });



    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String yearS = String.valueOf(year);
                        String monthS = String.valueOf(month);
                        String dayOfMonthS = String.valueOf(dayOfMonth);

                        if(year < 10){
                            yearS = "0" + String.valueOf(year);
                        }

                        if(month == 0){
                            monthS = "0" + String.valueOf(month);
                        } else if (month < 10 && month > 0) {
                            monthS = "0" + String.valueOf(month);
                        }

                        if(dayOfMonth < 10){
                            dayOfMonthS = "0" + String.valueOf(dayOfMonth);
                        }

                        selectedDate = "" + yearS + (monthS) + dayOfMonthS;
                        updateDueDate();
                        Log.d(TAG, "selected date " + selectedDate);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hourofdayS = String.valueOf(hourOfDay);
                        String minuteS = String.valueOf(minute);

                        if(hourOfDay < 10){
                            hourofdayS = "0" + String.valueOf(hourOfDay);
                        }

                        if(minute < 10){
                            minuteS = "0" + String.valueOf(minute);
                        }


                        // Handle selected time
                        selectedTime = "" + hourofdayS + minuteS;
                        updateDueDate();
                        Log.d(TAG, "selected time " + selectedTime);
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true // 24-hour format
        );
        timePickerDialog.show();

    }


    public void Cancet(View V) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        gotoinfinitecounter();
    }


        public void Savet() {
            task_name = ((EditText) findViewById(R.id.taskname)).getText().toString(); // Get task name from EditText

            if (!task_name.isEmpty() && Duedate != null && !Duedate.isEmpty())  {
            database3 myDB = new database3(Task_setup.this);
            long taskId = myDB.addtask(task_name, Duedate);


            if (subtaskcheck.isChecked() && subtask1 != null) {
                String subtask1Name = ((EditText) findViewById(R.id.subtask1)).getText().toString();
                myDB.addSubTask(taskId, subtask1Name);
            }

            if (subtask2check.isChecked() && subtask2 != null) {
                String subtask2Name = ((EditText) findViewById(R.id.subtask2)).getText().toString();
                myDB.addSubTask(taskId, subtask2Name);
            }

            if (subtask3check.isChecked() && subtask3 != null) {
                String subtask3Name = ((EditText) findViewById(R.id.subtask3)).getText().toString();
                myDB.addSubTask(taskId, subtask3Name);
            }
                GotoTasList();}
            else {
                Toast.makeText(this, "Please assign a name and a duedate!", Toast.LENGTH_SHORT).show();
            }
            }


    private void updateDueDate() {
        if (selectedDate != null && selectedTime != null) {
            Duedate = selectedDate + selectedTime;


        }
    }

    public void GotoTasList(){
        Intent i = new Intent(this, TaskList333.class);
        startActivity(i);
    }

    public void gotoinfinitecounter(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}