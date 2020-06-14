package com.example.andproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Cursor mCursor;
    AdapterRCV adapterRCV;

    public static final String BAR_CODE_DATABASE ="BarCodeDatabase.db";

    public static final String REFRIGERATOR_TABLE = "REFRIGERATOR_TABLE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_EXPIRATION_DATE = "EXPIRATION_DATE";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "refrigerator.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + REFRIGERATOR_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_NAME + " TEXT, " + COLUMN_EXPIRATION_DATE + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addItem(DatabaseModel databaseModel) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ITEM_NAME, databaseModel.getItemName());
        contentValues.put(COLUMN_EXPIRATION_DATE, databaseModel.getDate());

        long insert = database.insert(REFRIGERATOR_TABLE, null, contentValues);


        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    public void deleteObject(int position) {
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteItem = "DELETE FROM " + REFRIGERATOR_TABLE + " WHERE " + COLUMN_ID + " = " + position;
        //Cursor cursor = database.rawQuery(deleteItem, null);
        close();
    }
    //databaseModel.getId()

    Cursor getAllItems() {
        String query = "SELECT * FROM " + REFRIGERATOR_TABLE;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query,null);
        }
        return cursor;
    }

}