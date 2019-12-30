package com.example.wqkshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 95427 on 2019/12/11.
 */

public class MyHelper2 extends SQLiteOpenHelper {
    public MyHelper2(Context context){
        super(context,"wqk2.db",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table carts(_id INTEGER primary key autoincrement, name varchar(255),price varchar(255),number varchar(255) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
