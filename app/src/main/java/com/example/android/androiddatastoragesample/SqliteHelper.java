package com.example.android.androiddatastoragesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper
{
    public static final String TAG = "AndroidStorage";
    public static final String TABLE_NAME = "blog";
    public static final String TABLE_NAME2 = "logs";
    private static final String COL1 = "ID";
    private static final String COL2 = "blg_message";
    private static final String COLM1 = "ID";
    private static final String COLM2 = "logs_entry";


    public SqliteHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = " CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL2 +" TEXT)" ;
        db.execSQL(createTable);
        String createTable1 = " CREATE TABLE " + TABLE_NAME2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLM2 +" TEXT)" ;
        db.execSQL(createTable1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean saveBlog(String blg_msg)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, blg_msg);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean saveLogs(String logs)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COL2, logs);

        long result = db.insert(TABLE_NAME2,null,contentValues1);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getMessage()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;
    }
}
