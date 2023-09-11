package com.example.myapplication.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Databasehandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase";
    private static final String TABLE_NAME = "registration";

    private static final String ID_COL = "id";
    private static final String NAME_COL = "username";
    private static final String KEY_EMAIL = "useremail";
    private static final String PASSWORD_COL = "userpassword";
    private static final String DOB_COL = "userdob";
    private static final String GENDER_COL = "userradiobutton";

    public Databasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY,"
                + NAME_COL + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + DOB_COL + " TEXT,"
                + GENDER_COL + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, user.getUserName());
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(PASSWORD_COL, user.getUserPassword());
        values.put(DOB_COL, user.getUserDob());
        values.put(GENDER_COL, user.getUserRadioButton());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUserName(cursor.getString(1));
                user.setUserEmail(cursor.getString(2));
                user.setUserPassword(cursor.getString(3));
                user.setUserDob(cursor.getString(4));
                user.setUserRadioButton(cursor.getString(5));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, user.getUserName());
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(PASSWORD_COL, user.getUserPassword());
        values.put(DOB_COL, user.getUserDob());
        values.put(GENDER_COL, user.getUserRadioButton());

        db.update(TABLE_NAME, values, ID_COL + "=?", new String[]{String.valueOf(user.getId())});
        db.close();
    }


    public boolean isLoginValid(String userEmail, String userPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_EMAIL + "=? AND " + PASSWORD_COL + "=?", new String[]{userEmail, userPassword});

        boolean isValid = cursor.moveToFirst();
        cursor.close();
        db.close();
        return isValid;
    }

    public void deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_COL + "=?", new String[]{String.valueOf(userId)});
        db.close();
    }

}
