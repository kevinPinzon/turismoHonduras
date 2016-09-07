package com.example.alumnos.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by alexp on 6/9/2016.
 */
public class Place_Helper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "place";
    private static final int DATABASE_VERSION = 2;
    private static Place_Helper instance;

    //Table Names
    private final String PLACE_TABLE = "PLACE";

    //Column Names
    private final String KEY_ID = "id";
    private final String KEY_TITLE = "title";
    private final String KEY_DESCRIPTION = "description";
//    private final String KEY_PICTUREURL = "pictureUrl";

    public Place_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static Place_Helper newInstance(Context context) {
        if (instance == null) {
            instance = new Place_Helper(context);
        }

        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE BPLACE (");
        sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql.append("title TEXT,");
        sql.append("description TEXT)");
//        sql.append("pictureUrl TEXT,");

        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PLACE");
        this.onCreate(db);
    }

    public void addPlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, place.getTitle());
        values.put(KEY_DESCRIPTION, place.getDescription());
//        values.put(KEY_PICTUREURL, place.getPictureUrl());

        Log.d("newPlace:",place.getTitle());

        db.insert(PLACE_TABLE, null, values);
        db.close();
    }

    public ArrayList<Place> getAllPlace() {
        ArrayList<Place> places = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(PLACE_TABLE);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql.toString(), null);

        Place place = null;
        if (cursor.moveToFirst()) {
            place = new Place();
            place.setId(cursor.getInt(0));
            place.setTitle(cursor.getString(1));
            place.setDescription(cursor.getString(2));
//            place.setPictureUrl(cursor.getString(3));

            places.add(place);
        }

        while (cursor.moveToNext()) {
            place = new Place();
            place.setId(cursor.getInt(0));
            place.setTitle(cursor.getString(1));
            place.setDescription(cursor.getString(2));
//            place.setPictureUrl(cursor.getString(3));

            places.add(place);
        }

        db.close();
        return places;
    }
}
