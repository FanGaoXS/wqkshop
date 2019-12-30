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

public class loveActivity extends AppCompatActivity {
    TextView header;
    ListView listView;
    int size;
    String[] name,intro,price;
    TextView nameTV,introTV,priceTV;

    public void init(){
        header=(TextView)findViewById(R.id.header);
        header.setText("我的收藏");
        listView=(ListView)findViewById(R.id.ListView);
        name=new String[100];
        intro=new String[100];
        price=new String[100];
        Datebase1 datebase1=new Datebase1(this);
        ArrayList<Goods> loves=datebase1.loves();
        size=loves.size();
        for (int i=0;i<size;i++){
            name[i]=loves.get(i).getName();
            price[i]=loves.get(i).getPrice();
            intro[i]=loves.get(i).getIntro();
        }
        MyAdapter myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);
        //单击跳转的商品详情页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(loveActivity.this,GoodsShow.class);
                intent.putExtra("name",name[position]);
                startActivity(intent);
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blanklistview2);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        init();
    }
    protected void onResume(){
        super.onResume();
        init();
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public Object getItem(int position) {
            return name[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View v=View.inflate(loveActivity.this,R.layout.list_item2,null);
            nameTV=(TextView)v.findViewById(R.id.name);
            priceTV=(TextView)v.findViewById(R.id.price);
            introTV=(TextView)v.findViewById(R.id.intro);
            nameTV.setText(name[position]);
            priceTV.setText(price[position]);
            introTV.setText(intro[position]);
            return v;
        }
    }
}
