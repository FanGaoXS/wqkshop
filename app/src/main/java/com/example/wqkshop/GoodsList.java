package com.example.wqkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GoodsList extends AppCompatActivity {

    /*String[] name={"名字1","名字1","名字1","名字1","名字1"};
    String[] price={"价格1","价格1","价格1","价格1","价格1"};
    String[] type={"类型1","类型1","类型1","类型1","类型1"};
    String[] intro={"简介1","简介1","简介1","简介1","简介1"};*/
    String[] name,price,type,intro;
    Datebase1 datebase1;
    Intent intent;
    int size;
    TextView classifyTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blanklistview2);
        datebase1 = new Datebase1(this);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        name=new String[20];
        price=new String[20];
        type=new String[20];
        intro=new String[20];

        classifyTV=(TextView) findViewById(R.id.header);

        intent=getIntent();
        String classify=intent.getStringExtra("classify");
        classifyTV.setText(classify);
        ArrayList<Goods> goods=datebase1.goods(classify);
        size=goods.size();
        for(int i=0;i<goods.size();i++){
            name[i]=goods.get(i).getName();
            price[i]=goods.get(i).getPrice();
            type[i]=goods.get(i).getType();
            intro[i]=goods.get(i).getIntro();
        }

        //如果是手机数码分类按钮过来
        /*if (classify.equals("手机数码")){
            ArrayList<Goods> goods=datebase1.goods(classify);
            size=goods.size();
            for(int i=0;i<goods.size();i++){
                name[i]=goods.get(i).getName();
                price[i]=goods.get(i).getPrice();
                type[i]=goods.get(i).getType();
                intro[i]=goods.get(i).getIntro();
            }
        }

        //游戏装备
        else if (classify.equals("游戏装备")){
            ArrayList<Goods> goods=datebase1.goods(classify);
            size=goods.size();
            for(int i=0;i<goods.size();i++){
                name[i]=goods.get(i).getName();
                price[i]=goods.get(i).getPrice();
                type[i]=goods.get(i).getType();
                intro[i]=goods.get(i).getIntro();
            }
        }


        //生活百货
        else if (classify.equals("生活百货")){
            ArrayList<Goods> goods=datebase1.goods(classify);
            size=goods.size();
            for(int i=0;i<goods.size();i++){
                name[i]=goods.get(i).getName();
                price[i]=goods.get(i).getPrice();
                type[i]=goods.get(i).getType();
                intro[i]=goods.get(i).getIntro();
            }
        }


        //运动户外
        else if (classify.equals("运动户外")){
            ArrayList<Goods> goods=datebase1.goods(classify);
            size=goods.size();
            for(int i=0;i<goods.size();i++){
                name[i]=goods.get(i).getName();
                price[i]=goods.get(i).getPrice();
                type[i]=goods.get(i).getType();
                intro[i]=goods.get(i).getIntro();
            }
        }

        //家用电器
        else if (classify.equals("家用电器")){
            ArrayList<Goods> goods=datebase1.goods(classify);
            size=goods.size();
            for(int i=0;i<goods.size();i++){
                name[i]=goods.get(i).getName();
                price[i]=goods.get(i).getPrice();
                type[i]=goods.get(i).getType();
                intro[i]=goods.get(i).getIntro();
            }
        }


        //美妆
        else if (classify.equals("美妆")){
            ArrayList<Goods> goods=datebase1.goods(classify);
            size=goods.size();
            for(int i=0;i<goods.size();i++){
                name[i]=goods.get(i).getName();
                price[i]=goods.get(i).getPrice();
                type[i]=goods.get(i).getType();
                intro[i]=goods.get(i).getIntro();
            }
        }*/



        Myadapter myadapter=new Myadapter();
        ListView lv=(ListView) findViewById(R.id.ListView);
        lv.setAdapter(myadapter);

        //商品单击监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long id) {
                intent = new Intent(GoodsList.this,GoodsShow.class);
                intent.putExtra("name",name[postion]);
                intent.putExtra("price",price[postion]);
                intent.putExtra("type",type[postion]);
                intent.putExtra("intro",intro[postion]);
                startActivity(intent);
            }
        });

    }















    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public Object getItem(int postion) {
            return name[postion];
        }

        @Override
        public long getItemId(int postion) {
            return postion;
        }

        @Override
        public View getView(int postion, View view, ViewGroup viewGroup) {
            View v=View.inflate(GoodsList.this,R.layout.list_item2,null);
            TextView nameTV=v.findViewById(R.id.name);
            TextView priceTV=v.findViewById(R.id.price);
            //TextView typeTV=v.findViewById(R.id.type);
            TextView introTV=v.findViewById(R.id.intro);
            nameTV.setText(name[postion]);
            priceTV.setText("¥"+price[postion]);
            //typeTV.setText(type[postion]);
            introTV.setText(intro[postion]);
            return v;
        }
    }
}
