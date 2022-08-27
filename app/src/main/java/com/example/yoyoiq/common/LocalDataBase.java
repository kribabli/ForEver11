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

 private static final int DATABASE_VERSION = 1;
 private static final String DATABASE_NAME = "MyLocalDataBase";
 private static final String MY_TEAM = "MyTeam";
 private static final String Userid = "user_id";
 private static final String TeamName = "team_Name";
 private static final String Contest_id = "contest_id";
 private static final String TeamId = "Team_id";
 private static final String MATCH_ID = "Match_id";



 public LocalDataBase(@Nullable Context context) {
  super(context, DATABASE_NAME, null, DATABASE_VERSION);
 }

 @Override
 public void onCreate(SQLiteDatabase db) {

  String CREATE_MYTEAM_TABLE = " CREATE TABLE " + MY_TEAM + "(" + TeamName + " INTEGER PRIMARY KEY AUTOINCREMENT, "
          + Userid + " TEXT, " + TeamId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Contest_id + " TEXT, "+MATCH_ID + ")";

  db.execSQL(CREATE_MYTEAM_TABLE);

 }

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  db.execSQL(" DROP TABLE IF EXISTS "+ MY_TEAM);

  onCreate(db);
 }

 public void AddTeam(String self_user,   String contest_id){
  SQLiteDatabase db = this.getReadableDatabase();
  ContentValues contentValues = new ContentValues();
  contentValues.put(Userid,self_user);
  contentValues.put(Contest_id,contest_id);
  db.insert(MY_TEAM,null,contentValues);
  db.close();
 }

 public void getTeamInformation(int id){
  SQLiteDatabase db = this.getReadableDatabase();
  Cursor cursor = db.rawQuery("select * from " + MY_TEAM + " where id="+id+"", null);
 }

 @SuppressLint("Range")
 public int getLastStudentsId(){
  int count = 0;
  SQLiteDatabase db = this.getReadableDatabase();
  String query = "SELECT * FROM " +MY_TEAM ;
  Cursor cursor = db.rawQuery(query,null);
  if(cursor != null && !cursor.isClosed()){
   cursor.moveToLast();

   if(cursor.getCount() == 0) {
    count = 1;
   }else{
    count = cursor.getInt(cursor.getColumnIndex(TeamName));
   }
  }

  return count;

 }


}
