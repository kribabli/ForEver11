package com.example.yoyoiq.common;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LocalDataBase extends SQLiteOpenHelper {

 public static final String DATABASE_NAME = "myTeamlist.db";
 public static final String MYTEAM_TABLE_NAME = "myteam";
 public static final String MYTEAM_COLUMN_ID = "id";
 public static final String MYTEAM_COLUMN_NAME = "playername";
 public static final String MYTEAM_COLUMN_COUNTRY = "country";
 public static final String MYTEAM_COLUMN_FANTASY_RATING = "fantasy_rating";
 public static final String MYTEAM_COLUMN_ROLE = "role";
 public static final String MYTEAM_COLUMN_CAP = "cap";
 public static final String MYTEAM_COLUMN_VCAP = "vcap";
 public static final String MYTEAM_COLUMN_PHONE = "point";


 public LocalDataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
  super(context, name, factory, version);
 }

 @Override
 public void onCreate(SQLiteDatabase db) {
  db.execSQL(
          "create table contacts " +
                  "(id integer primary key, playername text,country text,fantasy_rating text, role text,cap text,vcap text,point text)"
  );

 }

 @Override
 public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

 }

 public boolean insertContact (String playername, String country, String fantasy_rating, String role,String cap,String vcap,String point) {
  SQLiteDatabase db = this.getWritableDatabase();
  ContentValues contentValues = new ContentValues();
  contentValues.put("name", playername);
  contentValues.put("phone", country);
  contentValues.put("fantasy_rating", fantasy_rating);
  contentValues.put("role", role);
  contentValues.put("cap", cap);
  contentValues.put("vacap", vcap);
  contentValues.put("point", point);
  db.insert("contacts", null, contentValues);
  return true;
 }
 public Cursor getData(int id) {
  SQLiteDatabase db = this.getReadableDatabase();
  Cursor res =  db.rawQuery( "select * from myteam where id="+id+"", null );
  return res;
 }


 @SuppressLint("Range")
 public ArrayList<String> get_All_Team() {
  ArrayList<String> array_list = new ArrayList<String>();

  //hp = new HashMap();
  SQLiteDatabase db = this.getReadableDatabase();
  Cursor res =  db.rawQuery( "select * from myteam", null );
  res.moveToFirst();

  while(res.isAfterLast() == false){
   array_list.add(res.getString(res.getColumnIndex(MYTEAM_COLUMN_NAME)));
   res.moveToNext();
  }
  return array_list;
 }



}
