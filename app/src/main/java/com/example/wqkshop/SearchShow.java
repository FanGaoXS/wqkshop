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
import android.widget.Toast;

import java.util.ArrayList;

public class SearchShow extends AppCompatActivity {
    TextView textView;
    Datebase1 datebase1;
    ListView listView;
    String[] name,price,intro,type;
    int size;
    public void init(){
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        textView=(TextView) findViewById(R.id.header);
        listView=(ListView)findViewById(R.id.ListView);
        name=new String[20];
        price=new String[20];
        intro=new String[20];
        type=new String[20];
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blanklistview2);
        init();
        Intent intent1=getIntent();
        String header=intent1.getStringExtra("header");  //获取搜索框输入的值
        datebase1=new Datebase1(this);
        ArrayList<Goods> goods=datebase1.search(header);
        size=goods.size();
        for (int i=0;i<goods.size();i++){
            name[i]=goods.get(i).getName();
            price[i]=goods.get(i).getPrice();
            intro[i]=goods.get(i).getIntro();
            type[i]=goods.get(i).getType();
        }
        if (size==0){
            Toast.makeText(this,"好像没有该商品",Toast.LENGTH_SHORT).show();
        }
        textView.setText("您搜索的\""+header+"\"结果如下");
        MyAdapter myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long id) {
                Intent intent = new Intent(SearchShow.this,GoodsShow.class);
                intent.putExtra("name",name[postion]);
                intent.putExtra("price",price[postion]);
                intent.putExtra("type",type[postion]);
                intent.putExtra("intro",intro[postion]);
                startActivity(intent);
            }
        });
    }



    //适配器
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
            View v=View.inflate(SearchShow.this,R.layout.list_item2,null);
            TextView nameTV=v.findViewById(R.id.name);
            TextView priceTV=v.findViewById(R.id.price);
            //TextView typeTV=v.findViewById(R.id.type);
            TextView introTV=v.findViewById(R.id.intro);
            nameTV.setText(name[position]);
            priceTV.setText("¥"+price[position]);
            //typeTV.setText(type[postion]);
            introTV.setText(intro[position]);
            return v;
        }
    }
}
