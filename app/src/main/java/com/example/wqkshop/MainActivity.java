package com.example.wqkshop;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    ClassifyFragment classifyFragment;
    CartFragment cartFragment;
    AboutFragment aboutFragment;
    FragmentManager fm;
    ImageButton homebtn,classifybtn,cartbtn,aboutbtn;
    Boolean ischange=false;


    public void init(){
        homebtn=(ImageButton) findViewById(R.id.home);
        classifybtn=(ImageButton)findViewById(R.id.classify);
        cartbtn=(ImageButton) findViewById(R.id.cart);
        aboutbtn=(ImageButton)findViewById(R.id.about);
        aboutbtn.setImageDrawable(getResources().getDrawable(R.drawable.c8));
        homeFragment=new HomeFragment();
        classifyFragment=new ClassifyFragment();
        cartFragment=new CartFragment();
        aboutFragment =new AboutFragment();
        fm=getFragmentManager();
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //加载主页
        FragmentTransaction beginTransaction=fm.beginTransaction();
        beginTransaction.replace(R.id.fragment,aboutFragment);
        beginTransaction.commit();
    }
    public void Click(View v){
        FragmentTransaction beginTransaction=fm.beginTransaction();
        switch (v.getId()){
            case R.id.home:
                beginTransaction.replace(R.id.fragment,homeFragment);
                homebtn.setImageDrawable(getResources().getDrawable(R.drawable.c2));
                classifybtn.setImageDrawable(getResources().getDrawable(R.drawable.c3));
                cartbtn.setImageDrawable(getResources().getDrawable(R.drawable.c5));
                aboutbtn.setImageDrawable(getResources().getDrawable(R.drawable.c7));
                break;
            case R.id.classify:
                beginTransaction.replace(R.id.fragment,classifyFragment);
                homebtn.setImageDrawable(getResources().getDrawable(R.drawable.c1));
                classifybtn.setImageDrawable(getResources().getDrawable(R.drawable.c4));
                cartbtn.setImageDrawable(getResources().getDrawable(R.drawable.c5));
                aboutbtn.setImageDrawable(getResources().getDrawable(R.drawable.c7));
                break;
            case R.id.cart:
                beginTransaction.replace(R.id.fragment,cartFragment);
                homebtn.setImageDrawable(getResources().getDrawable(R.drawable.c1));
                classifybtn.setImageDrawable(getResources().getDrawable(R.drawable.c3));
                cartbtn.setImageDrawable(getResources().getDrawable(R.drawable.c6));
                aboutbtn.setImageDrawable(getResources().getDrawable(R.drawable.c7));
                break;
            case R.id.about:
                beginTransaction.replace(R.id.fragment,aboutFragment);
                homebtn.setImageDrawable(getResources().getDrawable(R.drawable.c1));
                classifybtn.setImageDrawable(getResources().getDrawable(R.drawable.c3));
                cartbtn.setImageDrawable(getResources().getDrawable(R.drawable.c5));
                aboutbtn.setImageDrawable(getResources().getDrawable(R.drawable.c8));
                break;
        }
        beginTransaction.commit();
    }
}
