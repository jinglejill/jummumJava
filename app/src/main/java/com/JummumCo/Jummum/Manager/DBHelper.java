package com.JummumCo.Jummum.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.JummumCo.Jummum.Model.CreditUserData;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, CreditUserData.DATABASE_NAME, null, CreditUserData.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FRIEND_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                CreditUserData.TABLE,
                CreditUserData.Column.id,
                CreditUserData.Column.membeerId,
                CreditUserData.Column.fname,
                CreditUserData.Column.lname,
                CreditUserData.Column.year,
                CreditUserData.Column.month,
                CreditUserData.Column.secut,
                CreditUserData.Column.creditType,
                CreditUserData.Column.numCredit
                );

        db.execSQL(CREATE_FRIEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS creditcard";

        db.execSQL(DROP_FRIEND_TABLE);

        onCreate(db);
    }

    //CREATE
    public void addFriend(CreditUserData friend) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CreditUserData.Column.membeerId, friend.getMembeerId());
        values.put(CreditUserData.Column.fname, friend.getFname());
        values.put(CreditUserData.Column.lname, friend.getLname());
        values.put(CreditUserData.Column.year, friend.getYear());
        values.put(CreditUserData.Column.month, friend.getMonth());
        values.put(CreditUserData.Column.secut, friend.getSecut());
        values.put(CreditUserData.Column.creditType, friend.getCreditType());
        values.put(CreditUserData.Column.numCredit, friend.getNumCredit());

        sqLiteDatabase.insert(CreditUserData.TABLE, null, values);

        sqLiteDatabase.close();
    }

    //READ
/*
    public List<CreditUserData> getAllFriend() {

        String QUERY_ALL_FRIEND = "SELECT * FROM " + CreditUserData.TABLE;

        List<CreditUserData> creditUserData = new ArrayList<>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_ALL_FRIEND, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            CreditUserData friend = new CreditUserData();

            friend.setId((int) cursor.getLong(0));
            friend.setFname(cursor.getString(1));
            friend.setLname(cursor.getString(2));
            friend.setYear(cursor.getString(3));
            friend.setMonth(cursor.getString(4));
            friend.setSecut(cursor.getString(5));
            friend.setCreditType(cursor.getString(6));
            friend.setNumCredit(cursor.getString(7));
            friend.setMembeerId(cursor.getString(8));

            creditUserData.add(friend);
            cursor.moveToNext();
        }

        sqLiteDatabase.close();


        return friends;
    }
*/

    public List<CreditUserData> getCreditCard(String id) {
        List<CreditUserData> creditUserData = new ArrayList<>();
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query( CreditUserData.TABLE,
                null,
                CreditUserData.Column.membeerId + " = ? ",
                new String[] { id },
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {
            CreditUserData friend = new CreditUserData();

            friend.setId((int) cursor.getLong(0));
            friend.setMembeerId(cursor.getString(1));
            friend.setFname(cursor.getString(2));
            friend.setLname(cursor.getString(3));
            friend.setYear(cursor.getString(4));
            friend.setMonth(cursor.getString(5));
            friend.setSecut(cursor.getString(6));
            friend.setCreditType(cursor.getString(7));
            friend.setNumCredit(cursor.getString(8));


            creditUserData.add(friend);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return creditUserData;
    }

    //DELETE
    public void deleteFriend(String id) {

        sqLiteDatabase = this.getWritableDatabase();

/*        sqLiteDatabase.delete(Friend.TABLE, Friend.Column.ID + " = ? ",
                new String[] { String.valueOf(friend.getId()) });*/
        sqLiteDatabase.delete(CreditUserData.TABLE, CreditUserData.Column.id + " = " + id, null);

        sqLiteDatabase.close();
    }
}
