package com.example.wqkshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 95427 on 2019/12/10.
 */

public class MyHelper1 extends SQLiteOpenHelper {

    public MyHelper1(Context context){
        super(context,"wqk4.db",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table goods(_id INTEGER primary key autoincrement, name varchar(255),price varchar(255),type varchar(255),intro varchar(255),love varchar(255) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
