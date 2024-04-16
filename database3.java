package com.example.a99;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class database3 extends SQLiteOpenHelper {
    private Context context3;
    //for the database in general
    private static final String DATABASE_NAME= "tasks.db";
    private static final int DATABASE_VERSION= 1;

    //for the main tasks table
    private static final String TABLE_NAME = "tasks_list";
    private static final String COLUMN_ID = "id";
    private static final String TASK_NAME = "title";
    private static final String TASK_DUEDATE = "Duedate";

    private static final boolean TASK_COMPLETION = Boolean.parseBoolean("completed");

    //for the subtasks table
    private static final String SUBTASK_TABLE_NAME = "subtasks_list";
    private static final String SUBTASK_ID = "task_id";
    private static final String SUBTASK_TASK_ID = "main_task_id";
    private static final String SUBTASK_NAME = "subtask_name";

    private static final boolean SUBTASK_COMPLETION = Boolean.parseBoolean("subtask_completion");





    public database3(@Nullable Context context3) {
        super(context3, DATABASE_NAME, null, DATABASE_VERSION);
        this.context3 = context3;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String task_table_query =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TASK_NAME + " TEXT, " +
                        TASK_DUEDATE + " TEXT, " +
                        TASK_COMPLETION + " INTEGER);";
        db.execSQL(task_table_query);

        String subtask_table_query =
                "CREATE TABLE " + SUBTASK_TABLE_NAME +
                        "(" + SUBTASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        SUBTASK_TASK_ID + " INTEGER, " +
                        SUBTASK_NAME + " TEXT, " +
                        SUBTASK_COMPLETION + " INTEGER, " +
                        "FOREIGN KEY (" + SUBTASK_TASK_ID + ") REFERENCES " + TABLE_NAME + "(" + COLUMN_ID + "));";
        db.execSQL(subtask_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SUBTASK_TABLE_NAME);
        onCreate(db);
    }


    public long addtask(String task_name, String task_duedate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TASK_NAME, task_name);
        cv.put(TASK_DUEDATE, task_duedate);

        long taskId = db.insert(TABLE_NAME, null, cv);
        if (taskId == -1) {
            Toast.makeText(context3, "FAILED!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context3, "SUCCESS!", Toast.LENGTH_SHORT).show();
        }

        db.close();
        return taskId; // Return the ID of the inserted row
    }

    void addSubTask(long taskId, String subtaskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SUBTASK_TASK_ID, taskId);
        cv.put(SUBTASK_NAME, subtaskName);

        long subtaskId = db.insert(SUBTASK_TABLE_NAME, null, cv);

        if (subtaskId == -1){
            Toast.makeText(context3, "Failed to add subtask!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context3, "Subtask added!", Toast.LENGTH_SHORT).show();
                }
    }

    public List<TaskConstructor> getallmainTasks() {
        List<TaskConstructor> returnlist = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int mainTaskId = cursor.getInt(0);
                String taskName = cursor.getString(1);
                String taskDueDate = cursor.getString(2);
                int taskCompletion = cursor.getInt(3);

                List<SubtaskConstructor> subtasks = getSubtasksForMainTask(mainTaskId);

                TaskConstructor newTask = new TaskConstructor(mainTaskId, taskName, taskDueDate, taskCompletion, subtasks);
                returnlist.add(newTask);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnlist;
    }


    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ? ";
        String[] whereArgs = {String.valueOf(id)};
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

    public long addSubtask(long mainTaskId, String subtaskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SUBTASK_TASK_ID, mainTaskId);
        cv.put(SUBTASK_NAME, subtaskName);
        cv.put(String.valueOf(SUBTASK_COMPLETION), 0); // Initialize as not completed

        long subtaskId = db.insert(SUBTASK_TABLE_NAME, null, cv);

        if (subtaskId == -1) {
            Toast.makeText(context3, "Failed to add subtask!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context3, "Subtask added!", Toast.LENGTH_SHORT).show();
        }

        db.close();
        return subtaskId;
    }

    public List<SubtaskConstructor> getSubtasksForMainTask(int mainTaskId) {
        List<SubtaskConstructor> subtaskList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String subtaskQuery = "SELECT * FROM " + SUBTASK_TABLE_NAME + " WHERE " + SUBTASK_TASK_ID + " = ?";
        Cursor subtaskCursor = db.rawQuery(subtaskQuery, new String[]{String.valueOf(mainTaskId)});

        if (subtaskCursor.moveToFirst()) {
            do {
                int subtaskId = subtaskCursor.getInt(0);
                mainTaskId = subtaskCursor.getInt(1);
                String subtaskName = subtaskCursor.getString(2);
                int subtaskCompletion = subtaskCursor.getInt(3);

                SubtaskConstructor subtask = new SubtaskConstructor(subtaskId, mainTaskId, subtaskName, subtaskCompletion);
                subtaskList.add(subtask);
            } while (subtaskCursor.moveToNext());
        }

        subtaskCursor.close();
        db.close();
        return subtaskList;
    }

    public void deleteSubtask(int subtaskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = SUBTASK_ID + " = ?";
        String[] whereArgs = {String.valueOf(subtaskId)};
        db.delete(SUBTASK_TABLE_NAME, whereClause, whereArgs);
        db.close();
    }


}






