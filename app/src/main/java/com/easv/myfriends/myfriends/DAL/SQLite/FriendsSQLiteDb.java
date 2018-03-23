package com.easv.myfriends.myfriends.DAL.SQLite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by miesz on 23.03.2018.
 */

public class FriendsSQLiteDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "friends_db";
    private static final String TABLE_NAME = "friends";

    //COLUMN NAMES
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String LOCATION = "location_string";
    private static final String PHONE = "phone_string";
    private static final String EMAIL = "email";
    private static final String WEBSITE = "website";
    private static final String BIRTHDAY = "birthday_string";
    private static final String PICTURE = "picture_path";

    private static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "( "
            + ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " VARCHAR(255), "
            + ADDRESS + " VARCHAR(255), "
            + LOCATION + " VARCHAR(255), "
            + PHONE + " VARCHAR(255), "
            + EMAIL + " VARCHAR(255), "
            + WEBSITE + " VARCHAR(255), "
            + BIRTHDAY + " VARCHAR(255), "
            + PICTURE + " VARCHAR(255));";

    private Context context;

    public FriendsSQLiteDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
