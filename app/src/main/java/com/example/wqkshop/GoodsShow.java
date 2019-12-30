package com.example.wqkshop;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsShow extends AppCompatActivity {
    TextView nameTV,priceTV,introTV,loveTV;
    Intent intent;
    Datebase2 datebase2;
    String name,price,intro,love;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_show);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        nameTV=(TextView) findViewById(R.id.name);
        priceTV=(TextView) findViewById(R.id.price);
        introTV=(TextView) findViewById(R.id.intro);
        loveTV=(TextView)findViewById(R.id.love);
        imageButton=(ImageButton)findViewById(R.id.imageButton);

        intent=getIntent();
        name=intent.getStringExtra("name");
        /*price=intent.getStringExtra("price");
        intro=intent.getStringExtra("intro");*/
        final Datebase1 datebase1=new Datebase1(this);
        Goods good=datebase1.good(name);
        price=good.getPrice();
        intro=good.getIntro();
        love=good.getLove();
        if (love.equals("未收藏")){
            imageButton.setBackground(getResources().getDrawable(R.drawable.sc1));
        }else if (love.equals("已收藏")){
            imageButton.setBackground(getResources().getDrawable(R.drawable.sc2));
        }
        nameTV.setText(name);
        priceTV.setText(price+"元");
        introTV.setText(intro);
        loveTV.setText(love);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String love=loveTV.getText().toString().trim();
                if (love.equals("未收藏")){
                    boolean success=datebase1.getlove(name);
                    if (success){
                        Snackbar.make(view,"成功加入收藏夹",Snackbar.LENGTH_SHORT)
                                .setAction("查看收藏夹", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent=new Intent(GoodsShow.this,loveActivity.class);
                                        startActivity(intent);
                                    }
                                }).show();
                        loveTV.setText("已收藏");
                        imageButton.setBackground(getResources().getDrawable(R.drawable.sc2));
                    }else {
                        Toast.makeText(GoodsShow.this,"加入收藏夹失败，请联系管理员",Toast.LENGTH_SHORT).show();
                    }
                }else if (love.equals("已收藏")){
                    boolean success=datebase1.outlove(name);
                    if (success){
                        Snackbar.make(view,"取消收藏",Snackbar.LENGTH_SHORT).show();
                        loveTV.setText("未收藏");
                        imageButton.setBackground(getResources().getDrawable(R.drawable.sc1));
                    }else {
                        Toast.makeText(GoodsShow.this,"加入收藏夹失败，请联系管理员",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public void Click(View v){
        switch (v.getId()){
            case R.id.getcart:
                datebase2=new Datebase2(this);
                boolean success=datebase2.insert(name,price);
                if (success){
                    Toast.makeText(GoodsShow.this,"成功加入购物车",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(GoodsShow.this,"添加购物车失败，请联系管理员",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
