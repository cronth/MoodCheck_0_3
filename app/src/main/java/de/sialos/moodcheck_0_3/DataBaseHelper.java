package de.sialos.moodcheck_0_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_RESULTSETS = "RESULT_SETS";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_DATETIME = "DATE_TIME";
    public static final String COLUMN_RESULTSETS = "RESULT_SETS";


    //Constructor
    public DataBaseHelper(@Nullable Context context) {
        super(context, "MoodCheck.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + TABLE_RESULTSETS + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATETIME + " INT, " +
                COLUMN_RESULTSETS + " INT )";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addRow(LoggerModel loMod) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATETIME, loMod.getTimeDate());
        cv.put(COLUMN_RESULTSETS, loMod.getResultSet());

        long insert = db.insert(TABLE_RESULTSETS, null, cv);
        db.close();
        return (insert == -1 ? false : true);
    }

    public LoggerModel getLastEntry() {
        //get Data from Database
        String queryString =
                "SELECT * " +
                        "FROM " + TABLE_RESULTSETS +
                        " WHERE _ID = ( SELECT MAX(_ID) FROM " + TABLE_RESULTSETS + " )";

        SQLiteDatabase db = this.getReadableDatabase();
        LoggerModel loggerModel;
        Cursor c = db.rawQuery(queryString, null);
        c.moveToFirst();
        loggerModel = new LoggerModel(c.getInt(0), c.getInt(1), c.getInt(2));


        c.close();
        db.close();
        return loggerModel;

    }
}
