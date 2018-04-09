package com.easv.myfriends.myfriends.DAL.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.easv.myfriends.myfriends.model.Friend;

import java.util.ArrayList;

/**
 * Created by miesz on 20.03.2018.
 */

public class FriendsDbAdapter implements IFriendsDbAdapter {

    DatabaseHelper dbSchema;
    String[] columns = {dbSchema.ID, dbSchema.NAME, dbSchema.ADDRESS, dbSchema.LOCATION, dbSchema.PHONE, dbSchema.EMAIL, dbSchema.WEBSITE, dbSchema.BIRTHDAY, dbSchema.PICTURE};

    public FriendsDbAdapter(Context context)
    {
        dbSchema = new DatabaseHelper(context);
    }

    @Override
    public long insert(Friend friendToAdd) {
        SQLiteDatabase db = dbSchema.getWritableDatabase();
        // insert returns -1 or id of emlementadded
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbSchema.NAME, friendToAdd.getmName());
        contentValues.put(dbSchema.ADDRESS, friendToAdd.getmAddress());
        contentValues.put(dbSchema.LOCATION, friendToAdd.getmAddress());
        contentValues.put(dbSchema.PHONE, friendToAdd.getmPhoneNumber());
        contentValues.put(dbSchema.EMAIL, friendToAdd.getmEmail());
        contentValues.put(dbSchema.WEBSITE, friendToAdd.getmWebsite());
        contentValues.put(dbSchema.BIRTHDAY, friendToAdd.getmBirthdayStringDAYMONTH());
        contentValues.put(dbSchema.PICTURE, friendToAdd.getmPictureString());

        long id = db.insert(dbSchema.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    @Override
    public Iterable<Friend> getAll() {
        SQLiteDatabase db = dbSchema.getReadableDatabase();
        ArrayList<Friend> friendsToReturn = new ArrayList<>();
        Cursor cursor = db.query(dbSchema.TABLE_NAME, columns, null, null, null, null, null);
        int id_index = cursor.getColumnIndex(dbSchema.ID);
        int name_index = cursor.getColumnIndex(dbSchema.NAME);
        int address_index = cursor.getColumnIndex(dbSchema.ADDRESS);
        int location_index = cursor.getColumnIndex(dbSchema.LOCATION);
        int phone_index = cursor.getColumnIndex(dbSchema.PHONE);
        int email_index = cursor.getColumnIndex(dbSchema.EMAIL);
        int website_index = cursor.getColumnIndex(dbSchema.WEBSITE);
        int birthday_index = cursor.getColumnIndex(dbSchema.BIRTHDAY);
        int picture_index = cursor.getColumnIndex(dbSchema.PICTURE);

        if (cursor.moveToFirst()) {
            do {
                Friend tempFriend = new Friend();
                tempFriend.setmId(cursor.getInt(id_index));
                tempFriend.setmName(cursor.getString(name_index));
                tempFriend.setmAddress(cursor.getString(address_index));
                // TODO tempFriend.setmLocation(cursor.getString(location_index));
                tempFriend.setmPhoneNumber(cursor.getString(phone_index));
                tempFriend.setmEmail(cursor.getString(email_index));
                tempFriend.setmWebsite(cursor.getString(website_index));
                // todo think if it should be string or long tempFriend.setmBirthday(cursor.getString(birthday_index));
                tempFriend.setmPicturePath(cursor.getString(picture_index));
                friendsToReturn.add(tempFriend);
            } while (cursor.moveToNext());
        }
        db.close();
        return friendsToReturn;
    }

    @Override
    public Iterable<Friend> getAlllByIds(Iterable<Integer> ids) {
        return null;
    }

    @Override
    public Friend getById(int id) {
        SQLiteDatabase db = dbSchema.getReadableDatabase();
        Cursor cursor = db.query(dbSchema.TABLE_NAME, columns, dbSchema.ID+" = '"+id+"'", null, null, null, null);
        int id_index = cursor.getColumnIndex(dbSchema.ID);
        int name_index = cursor.getColumnIndex(dbSchema.NAME);
        int address_index = cursor.getColumnIndex(dbSchema.ADDRESS);
        int location_index = cursor.getColumnIndex(dbSchema.LOCATION);
        int phone_index = cursor.getColumnIndex(dbSchema.PHONE);
        int email_index = cursor.getColumnIndex(dbSchema.EMAIL);
        int website_index = cursor.getColumnIndex(dbSchema.WEBSITE);
        int birthday_index = cursor.getColumnIndex(dbSchema.BIRTHDAY);
        int picture_index = cursor.getColumnIndex(dbSchema.PICTURE);


        if (cursor != null && cursor.moveToFirst()) {
            Friend friendToReturn = new Friend();
            friendToReturn.setmId(cursor.getInt(id_index));
            friendToReturn.setmName(cursor.getString(name_index));
            friendToReturn.setmAddress(cursor.getString(address_index));
            // TODO tempFriend.setmLocation(cursor.getString(location_index));
            friendToReturn.setmPhoneNumber(cursor.getString(phone_index));
            friendToReturn.setmEmail(cursor.getString(email_index));
            friendToReturn.setmWebsite(cursor.getString(website_index));
            // todo think if it should be string or long tempFriend.setmBirthday(cursor.getString(birthday_index));
            friendToReturn.setmPicturePath(cursor.getString(picture_index));
            db.close();
            return friendToReturn;
        }
        db.close();
        return null;
    }

    @Override
    public Boolean update(Friend friendToUpdate) {
        SQLiteDatabase db = dbSchema.getWritableDatabase();
        int friendToUpdateId = friendToUpdate.getmId();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbSchema.NAME, friendToUpdate.getmName());
        contentValues.put(dbSchema.ADDRESS, friendToUpdate.getmAddress());
        contentValues.put(dbSchema.LOCATION, friendToUpdate.getmAddress());
        contentValues.put(dbSchema.PHONE, friendToUpdate.getmPhoneNumber());
        contentValues.put(dbSchema.EMAIL, friendToUpdate.getmEmail());
        contentValues.put(dbSchema.WEBSITE, friendToUpdate.getmWebsite());
        contentValues.put(dbSchema.BIRTHDAY, friendToUpdate.getmBirthdayStringDAYMONTH());
        contentValues.put(dbSchema.PICTURE, friendToUpdate.getmPictureString());

        return db.update(dbSchema.TABLE_NAME, contentValues, dbSchema.ID+"=?",
                new String[]{Integer.toString(friendToUpdateId)}) > 0 ;

    }

    @Override
    public Boolean delete(int id) {
        SQLiteDatabase db = dbSchema.getWritableDatabase();
    // delete returns numb of rows or 0
        return db.delete(dbSchema.TABLE_NAME, dbSchema.ID+"=?",
                new String[]{Integer.toString(id)}) > 0;
    }

    @Override
    public Boolean deleteAll() {
        SQLiteDatabase db = dbSchema.getWritableDatabase();
        return db.delete(dbSchema.TABLE_NAME, null, null) > 0;
    }

    static class DatabaseHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 10;

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

        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
        private static final String CREATE_FRIENDS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(255), "
                + ADDRESS + " VARCHAR(255), "
                + LOCATION + " VARCHAR(255), "
                + PHONE + " VARCHAR(255), "
                + EMAIL + " VARCHAR(255), "
                + WEBSITE + " VARCHAR(255), "
                + BIRTHDAY + " VARCHAR(255), "
                + PICTURE + " VARCHAR(255))";

        //TODO LOCATION TABLE, MODEL, SERVICE
//        private static final String CREATE_LOCATIONS_TABLE = "CREATE TABLE" + TABLE_NAME + "( "
//                + ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + NAME + " VARCHAR(255), "
//                + ADDRESS + " VARCHAR(255), "
//                + LOCATION + " VARCHAR(255), "
//                + PHONE + " VARCHAR(255), "
//                + EMAIL + " VARCHAR(255), "
//                + WEBSITE + " VARCHAR(255), "
//                + BIRTHDAY + " VARCHAR(255), "
//                + PICTURE + " VARCHAR(255));";

        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_FRIENDS_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
}
