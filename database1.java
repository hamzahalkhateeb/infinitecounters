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

class database1 extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME= "infinitecounter.db";
    private static final int DATABASE_VERSION= 1;

    private static final String TABLE_NAME = "infinitecounter_list";
    private static final String COLUMN_ID = "id";
    private static final String COUNTER_NAME = "title";
    private static final String CURRENT_CLICKS = ("Current_Clicks");

    //create a database,
    public database1(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }



    //create the table, this has a query that will make a table
    //here is also the code that is actually called when you press save on infincounter_setup, so an empty table is made
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createquery =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COUNTER_NAME + " TEXT, " +
                        CURRENT_CLICKS + " INTEGER);";
        db.execSQL(createquery);
    }

    //this will be called whenever the version number of the table changes, everytime it updates
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addinfincounter(String title, int current_clicks){
        //get the database we created earlier so we can write into it, open it if you will
        SQLiteDatabase db = this.getWritableDatabase();
        //use the ContentValues object to make a cv variable
        ContentValues cv =  new ContentValues();

        //use the cv variable and give it two parameters, first is the column you wanna insert to,
        // second is the data you wanna insert into said column
        //now cv has the data you inserted into it, and is ready to be inserted to the database
        cv.put(COUNTER_NAME, title);
        cv.put(CURRENT_CLICKS, current_clicks);

        //remember we opened the database and we put it in the db variable
        //now we use the function insert, and provide it with 3 parameters, table name which you wanna insert into
        //null, and cv, which we stored some data in earlier.
        //as for the long data type: the return data type of the insert function is long, if successful, its positive
        //else it is negative
        long results = db.insert(TABLE_NAME, null, cv);
        if(results == -1){
            Toast.makeText(context, "FAILED!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "SUCCESS!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    //the following is a method that will pull up all the info up
    public List<infinconstructor> geteveryone() {
        List<infinconstructor> returnlist = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do{
                int COLUMN_ID = cursor.getInt(0);
                String COUNTER_NAME = cursor.getString(1);
                int CURRENT_CLICKS = cursor.getInt(2);

                infinconstructor newcounter = new infinconstructor(COLUMN_ID, COUNTER_NAME, CURRENT_CLICKS);
                returnlist.add(newcounter);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();


        return returnlist;
    }


    public void updateCurrent_Clicks(int id, int Current_Clicks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CURRENT_CLICKS, Current_Clicks);
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    public void deleteCounter(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }



}
