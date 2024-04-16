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

class database2d extends SQLiteOpenHelper {
    private Context context2d;
    private static final String DATABASE_NAME= "goalcounter2d.db";
    private static final int DATABASE_VERSION= 1;

    private static final String TABLE_NAME = "goalcounter2d_list";
    private static final String COLUMN_ID = "id";
    private static final String COUNTER_NAME = "title";
    private static final String CURRENT_CLICKS = ("current_clicks");
    private static final String GOAL_CLICKS = ("goal_clicks");

    public database2d(@Nullable Context context2d) {
        super(context2d, DATABASE_NAME, null, DATABASE_VERSION);
        this.context2d = context2d;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COUNTER_NAME + " TEXT, " +
                        CURRENT_CLICKS + " INTEGER, " +
                        GOAL_CLICKS + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addgoalcounter2d(String title, int current_clicks, int goal_clicks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(COUNTER_NAME, title);
        cv.put(CURRENT_CLICKS, current_clicks);
        cv.put(GOAL_CLICKS, current_clicks);
        long results = db.insert(TABLE_NAME, null, cv);
        if(results == -1){
            Toast.makeText(context2d, "FAILED!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context2d, "SUCCESS!", Toast.LENGTH_SHORT).show();
        }

    }

    public List<DESCconstructor> geteveryone3(){
        List<DESCconstructor> returnlist = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int COLUMN_ID = cursor.getInt(0);
                String COUNTER_NAME = cursor.getString(1);
                int CURRENT_CLICKS = cursor.getInt(2);
                int GOAL_CLICKS = cursor.getInt(3);

                DESCconstructor newcounter = new DESCconstructor(COLUMN_ID, COUNTER_NAME, CURRENT_CLICKS, GOAL_CLICKS);
                returnlist.add(newcounter);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return returnlist;
    }

    public void updateCurrent_clicks333(int id, int Current_Clicks){
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

