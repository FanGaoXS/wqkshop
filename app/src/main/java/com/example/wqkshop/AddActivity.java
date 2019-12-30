package com.example.wqkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    EditText nameET,priceET,introET;
    Datebase1 datebase1;     //调用数据库goods数据表操作方法类对象
    TextView textView;
    Spinner typeSP;
    public void init(){
        nameET=(EditText) findViewById(R.id.name);
        priceET=(EditText) findViewById(R.id.price);
        //typeET=(EditText) findViewById(R.id.type);
        typeSP=(Spinner)findViewById(R.id.type);
        introET=(EditText) findViewById(R.id.intro);
        textView=(TextView)findViewById(R.id.textView3);
        datebase1= new Datebase1(this);  //声明goods数据表操作方法类实例

        //去标题栏
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }



    public void Click(View v){

        switch (v.getId()) {


            case R.id.insert:
                Log.i("test","buttoninsert");
                String name = nameET.getText().toString().trim();
                String price = priceET.getText().toString().trim();
                String type = typeSP.getSelectedItem().toString().trim();
                Log.i("test",type);
                String intro = introET.getText().toString().trim();
                boolean success = datebase1.insert(name, price, type, intro,"未收藏");
                if (success) {
                    Toast.makeText(AddActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "插入失败", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.show:
                Log.i("test","buttonshow");
                ArrayList<Goods> goods=datebase1.show();
                for(int i=0;i<goods.size();i++){
                    /*goods.get(i).getId();
                    goods.get(i).getName();
                    goods.get(i).getPrice();
                    goods.get(i).getType();
                    goods.get(i).getIntro();*/
                    textView.append(   "共有"+goods.size()+"商品"
                                        +"名字："+goods.get(i).getName()
                                        +"价格："+goods.get(i).getPrice()
                                        +"类型："+goods.get(i).getType()
                                        +"简介："+goods.get(i).getIntro()
                                        +"收藏："+goods.get(i).getLove()
                                        +"\n");
                }
                break;

            case R.id.clear:
                datebase1.clear();
                break;



        }//switch

    }//Click()
}
