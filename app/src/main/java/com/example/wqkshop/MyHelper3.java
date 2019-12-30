package com.example.wqkshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 95427 on 2019/12/11.
 */

public class MyHelper3 extends SQLiteOpenHelper {
    public MyHelper3(Context context){
        super(context,"wqk5.db",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table bills(_id INTEGER primary key autoincrement, username varchar(255),price varchar(255),time varchar(255),send varchar(255)  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
