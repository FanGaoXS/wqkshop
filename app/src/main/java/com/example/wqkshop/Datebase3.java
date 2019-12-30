package com.example.wqkshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by 95427 on 2019/12/11.
 */

public class Datebase3 {
    MyHelper3 helper3;
    SQLiteDatabase db;
    ContentValues values;
    boolean success;
    public Datebase3(Context context){
        helper3=new MyHelper3(context);
        success=false;
    }


    //插入（生成）订单表
    public boolean insert(String price,String send,String time){
        db=helper3.getWritableDatabase();
        values=new ContentValues();
        //values.put("username",username); 传用户名
        values.put("price",price);
        values.put("send",send);
        values.put("time",time);
        long i=db.insert("bills",null,values);
        if (i!=0){success=true;}
        db.close();
        return success;
    }

    //查询所有订单
    public ArrayList<Bill> bills(){
        db=helper3.getReadableDatabase();
        ArrayList<Bill> bills=new ArrayList<Bill>();
        Cursor cursor=db.query("bills",null,null,null,null,null,null);
        //查询有结果集
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()) {
                Bill bill = new Bill();
                //getString(0)是id;
                bill.setId(cursor.getString(0));
                bill.setUsername(cursor.getString(1));
                bill.setPrice(cursor.getString(2));
                bill.setTime(cursor.getString(3));
                bill.setSend(cursor.getString(4));
                bills.add(bill);
            }
        }
        cursor.close();
        db.close();
        return  bills;
    }
}
