package com.example.wqkshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 95427 on 2019/12/10.
 */

public class Datebase1 {
    MyHelper1 helper1;
    SQLiteDatabase db;
    ContentValues values;
    boolean success;
    public Datebase1(Context context){
        helper1=new MyHelper1(context);
        success=false;
    }

    //加入收藏
    public boolean getlove(String name){
        db=helper1.getWritableDatabase();
        values=new ContentValues();
        values.put("love","已收藏");
        int i=db.update("goods",values,"name=?",new String[]{name});
        if(i!=0){success=true;}
        db.close();
        return success;
    }
    //取消收藏
    public boolean outlove(String name){
        db=helper1.getWritableDatabase();
        values=new ContentValues();
        values.put("love","未收藏");
        int i=db.update("goods",values,"name=?",new String[]{name});
        if(i!=0){success=true;}
        db.close();
        return success;
    }

    //查看收藏夹
    public ArrayList<Goods> loves(){
        db=helper1.getReadableDatabase();
        ArrayList<Goods> goods=new ArrayList<Goods>();
        Cursor cursor=db.query("goods",null,"love=?",new String[]{"已收藏"},null,null,null);
        if(cursor.getCount()!=0){

            while (cursor.moveToNext()) {
                Goods good = new Goods();
                //getString(0)是id;
                good.setId(cursor.getString(0));
                good.setName(cursor.getString(1));
                good.setPrice(cursor.getString(2));
                good.setType(cursor.getString(3));
                good.setIntro(cursor.getString(4));
                good.setLove(cursor.getString(5));
                goods.add(good);
            }
        }
        cursor.close();
        db.close();
        return goods;
    }
    //插入数据库
    public boolean insert(String name,String price,String type,String intro,String love){
        db=helper1.getWritableDatabase();
        values=new ContentValues();
        values.put("name",name);
        values.put("price",price);
        values.put("type",type);
        values.put("intro",intro);
        values.put("love",love);
        long i=db.insert("goods",null,values);
        //如果i不等于0说明插入有效即插入成功
        if(i!=0){success=true;}
        db.close();
        return success;
    }


    //全部显示（查全表，没有任何条件和排序方式）
    public ArrayList<Goods> show(){
        db=helper1.getReadableDatabase();
        ArrayList<Goods> goods=new ArrayList<Goods>();
        Cursor cursor=db.query("goods",null,null,null,null,null,null);

        //查询有结果集
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()) {
                Goods good = new Goods();
                //getString(0)是id;
                good.setId(cursor.getString(0));
                good.setName(cursor.getString(1));
                good.setPrice(cursor.getString(2));
                good.setType(cursor.getString(3));
                good.setIntro(cursor.getString(4));
                good.setLove(cursor.getString(5));
                goods.add(good);
            }
        }
        cursor.close();
        db.close();
        return goods;
    }

    //模糊查询（查名字）
    public ArrayList<Goods> search(String name){
        db=helper1.getReadableDatabase();
        ArrayList<Goods> goods=new ArrayList<Goods>();
        Cursor cursor=db.query("goods",null,"name like ?",new String[] { "%"+name+"%" },null,null,null);

        //查询有结果集
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()) {
                Goods good = new Goods();
                //getString(0)是id;
                good.setId(cursor.getString(0));
                good.setName(cursor.getString(1));
                good.setPrice(cursor.getString(2));
                good.setType(cursor.getString(3));
                good.setIntro(cursor.getString(4));
                good.setLove(cursor.getString(5));
                goods.add(good);
            }
        }
        cursor.close();
        db.close();
        return goods;
    }

    //清空表
    public boolean clear(){
        db=helper1.getWritableDatabase();
        int i=db.delete("goods",null,null);
        if (i!=0){success=true;}
        db.close();
        return success;
    }


    //条件查询（根据商品类型查商品）
    public ArrayList<Goods> goods(String type){
        db=helper1.getReadableDatabase();
        ArrayList<Goods> goods=new ArrayList<Goods>();
        Cursor cursor=db.query("goods",null,"type=?",new String[]{type+""},null,null,null);
        if(cursor.getCount()!=0){

            while (cursor.moveToNext()) {
                Goods good = new Goods();
                //getString(0)是id;
                good.setId(cursor.getString(0));
                good.setName(cursor.getString(1));
                good.setPrice(cursor.getString(2));
                good.setType(cursor.getString(3));
                good.setIntro(cursor.getString(4));
                good.setLove(cursor.getString(5));
                goods.add(good);
            }
        }
        cursor.close();
        db.close();
        return goods;
    }


    //条件查询（根据商品名查询）
    public Goods good(String name){
        db=helper1.getReadableDatabase();
        Goods good=new Goods();
        Cursor cursor=db.query("goods",null,"name=?",new String[]{name+""},null,null,null);
        while (cursor.moveToNext()){
            good.setId(cursor.getString(0));
            good.setName(cursor.getString(1));
            good.setPrice(cursor.getString(2));
            good.setType(cursor.getString(3));
            good.setIntro(cursor.getString(4));
            good.setLove(cursor.getString(5));
        }
        cursor.close();
        db.close();
        return good;
    }



}
