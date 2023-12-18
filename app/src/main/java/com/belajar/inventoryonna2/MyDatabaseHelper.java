package com.belajar.inventoryonna2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.internal.NavigationMenu;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME ="Inventory.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Onna_Inventory";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "item_name";
    private static final String COLUMN_AUTHOR = "admin_author";
    private static final String COLUMN_SET = "item_sets";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_AUTHOR + " TEXT, " +
                        COLUMN_SET + " INTEGER) ;";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addBarang(String title, String author, int set){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_SET, set);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1 ){
            Toast.makeText(context, "Isi dulu ya", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Sudah terisi!", Toast.LENGTH_SHORT).show();
        }
    }
}
