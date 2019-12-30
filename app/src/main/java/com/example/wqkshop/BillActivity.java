package com.example.wqkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {
    String[] idvalue,price,time,send;
    int size;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_layout);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }


        idvalue=new String[200];
        price=new String[200];
        time=new String[200];
        send=new String[200];
        Datebase3 datebase3=new Datebase3(this);
        ArrayList<Bill> bills=datebase3.bills();
        size=bills.size();
        for (int i=0;i<bills.size();i++){
            idvalue[i]=bills.get(i).getId();
            //String username=bills.get(i).getUsername();获取用户名
            price[i]=bills.get(i).getPrice();
            time[i]=bills.get(i).getTime();
            send[i]=bills.get(i).getSend();
        }
        lv=(ListView) findViewById(R.id.listview);
        Myadapter myadapter=new Myadapter();
        lv.setAdapter(myadapter);
    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public Object getItem(int postion) {
            return idvalue[postion];
        }

        @Override
        public long getItemId(int postion) {
            return postion;
        }

        @Override
        public View getView(int postion, View view, ViewGroup viewGroup) {
            //用getActivity()代替this
            View v=View.inflate(BillActivity.this,R.layout.list_item4,null);
            TextView idvalueTV=v.findViewById(R.id.idvalue);
            TextView priceTV=v.findViewById(R.id.price);
            TextView timeTV=v.findViewById(R.id.time);
            TextView sendTV=v.findViewById(R.id.send);
            idvalueTV.setText("20191229"+idvalue[postion]);
            priceTV.setText(price[postion]);
            timeTV.setText(time[postion]);
            sendTV.setText(send[postion]);
            return v;
        }
    }
}
