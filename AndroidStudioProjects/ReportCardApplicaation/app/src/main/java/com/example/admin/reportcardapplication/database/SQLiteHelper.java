package com.example.admin.reportcardapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 2016/10/27.
 */

public class SQLiteHelper extends SQLiteOpenHelper{

    public static final String CATEGORY = "category";

    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_CONTACT = "contact";
    public static final String USER_ID = "User_ID";
    public static final String USER_EMAIL = "email";
    public static final String STUDENT_NUMBER = "STD_Number";
    public static final String USER_GENDER = "gender";

    public static final String ADMIN_NAME = "name";
    public static final String ADMIN_SURNAME = "surname";
    public static final String ADMIN_ID = "Admin_ID";

    public static final String COURSE_ID = "Course_ID";
    public static final String COURSE_NAME = "Course";

    public static final String SUBJECT_ID = "Subject_ID";
    public static final String SUBJECT_NAME = "Subject";
    public static final String SUBJECT_MARK = "Mark";

    private static final String DATABASE = "database";
    private static final String USER_DATABASE_TABLE = "Student_details";
    private static final String ADMIN_DATABASE_TABLE = "Admin_details";
    private static final String COURSE_DATABASE_TABLE = "Course";
    private static final String SUBJECT_DATABASE_TABLE = "Subject";
    private static final int DATABASE_VERSION = 1;
    private int counter = 0;

    public SQLiteHelper(Context context)
    {
        super(context, DATABASE, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        ***creating tables for admin database
         */
        db.execSQL("CREATE TABLE "+ADMIN_DATABASE_TABLE +" ("+
                ADMIN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ADMIN_NAME+" TEXT,"
                + ADMIN_SURNAME +" TEXT," +CATEGORY+" TEXT)");
        /*
        ***creating tables for user database
         */
        db.execSQL("CREATE TABLE "+USER_DATABASE_TABLE + "("+USER_ID +" INTEGER NOT NULL PRIMARY KEY, "+ STUDENT_NUMBER +" INTEGER NOT NULL, "+
                USER_NAME+" TEXT NOT NULL, "+ USER_SURNAME+" TEXT NOT NULL, " + USER_CONTACT +" TEXT NOT NULL, "+
                USER_EMAIL +" TEXT NOT NULL, " + USER_GENDER +" TEXT NOT NULL, "+ CATEGORY+" TEXT NOT NULL, "+
                ADMIN_ID+" INTEGER, FOREIGN KEY ("+ADMIN_ID+" )REFERENCES Admin_details( "+ADMIN_ID+"))");
        /*
        ***creating tables for login database
        */
        db.execSQL("CREATE TABLE "+COURSE_DATABASE_TABLE + " ("+COURSE_ID +" INTEGER PRIMARY KEY NOT NULL, "+
                COURSE_NAME + " TEXT NOT NULL, "+ADMIN_ID+" INTEGER, FOREIGN KEY ("+ADMIN_ID+" )REFERENCES Admin_details( "+ADMIN_ID+"),"+
                USER_ID+" INTEGER, FOREIGN KEY( "+USER_ID+" )REFERENCES Student_details( "+USER_ID+"))");
        /*
        ***creating tables for login database
        */
        db.execSQL("CREATE TABLE "+SUBJECT_DATABASE_TABLE + " ("+SUBJECT_ID +" INTEGER PRIMARY KEY NOT NULL, "+SUBJECT_NAME+" TEXT NOT NULL, "+
                SUBJECT_MARK+" TEXT NOT NULL, "+ COURSE_ID+" INTEGER, FOREIGN KEY( "+COURSE_ID+" )REFERENCES Course( "+COURSE_ID+"))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ ADMIN_DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ COURSE_DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ SUBJECT_DATABASE_TABLE);
        onCreate(db);
    }
    public void createEntry(String m,String n)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ADMIN_NAME,m);
        cv.put(ADMIN_SURNAME,n);
        cv.put(CATEGORY,"admin");
        counter++;
        System.out.println(String.valueOf(db.insert(ADMIN_DATABASE_TABLE, null,cv)));
        db.close();
    }
}
