package com.example.wqkshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by 95427 on 2019/12/11.
 */

public class Datebase2 {
    MyHelper2 helper2;
    SQLiteDatabase db;
    ContentValues values;
    boolean success;
    public Datebase2(Context context){
        helper2=new MyHelper2(context);
        success=false;
    }

    //插入购物车（传商品名字，商品价格）
    public boolean insert(String name,String price){
        db=helper2.getWritableDatabase();
        values=new ContentValues();
        values.put("name",name);
        values.put("price",price);
        long i=db.insert("carts",null,values);
        //如果i不等于0说明插入有效即插入成功
        if(i!=0){success=true;}
        db.close();
        return success;
    }


    //显示购物车
    public ArrayList<Carts> show(){
        db=helper2.getReadableDatabase();
        ArrayList<Carts> carts=new ArrayList<Carts>();
        Cursor cursor=db.query("carts",null,null,null,null,null,null);
        //查询有结果集
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()) {
                Carts cart = new Carts();
                //getString(0)是id;
                cart.setId(cursor.getString(0));
                cart.setName(cursor.getString(1));
                cart.setPrice(cursor.getString(2));
                cart.setNumber(cursor.getString(3));
                carts.add(cart);
            }
        }
        cursor.close();
        db.close();
        return  carts;
    }


    //清空购物车
    public boolean clear(){
        db=helper2.getWritableDatabase();
        int i=db.delete("carts",null,null);
        if (i!=0){success=true;}
        db.close();
        return success;
    }


    //删除购物车单项（按id）
    public boolean delete(String id){
        db=helper2.getWritableDatabase();
        int i=db.delete("carts","_id=?",new String[]{id+""});
        if (i!=0){success=true;}
        db.close();
        return success;
    }
}
