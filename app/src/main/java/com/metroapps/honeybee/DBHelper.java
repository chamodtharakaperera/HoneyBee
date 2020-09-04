package com.metroapps.honeybee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Mobile_App";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS Cart (FoodName varchar2(20), UnitPrice int, Quantity int, ItemTot int)" );
        db.execSQL( "DELETE FROM Cart" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertCart(String fname, int uprice)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( "FoodName", fname );
        cv.put( "UnitPrice", uprice );
        cv.put( "Quantity", 1 );
        cv.put( "ItemTot", uprice );
        db.insert( "Cart", null, cv );

    }

    public Cursor getCartData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Cart", null);
        return c;
    }

    public Cursor getTotal()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor d = db.rawQuery("SELECT sum(ItemTot) FROM Cart", null);
        return d;
    }
}
