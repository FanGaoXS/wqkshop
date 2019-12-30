package com.example.wqkshop;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ViewFlipper;


/**
 * Created by 95427 on 2019/12/10.
 */

public class HomeFragment extends Fragment {
    ViewFlipper viewFlipper;
    ImageButton imgbtn1,imgbtn2,imgbtn3,imgbtn4,imgbtn5,imgbtn6;
    SearchView searchView;
    int[] lunbo={R.drawable.timg,R.drawable.timg,R.drawable.timg,R.drawable.timg};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.homefragment,container,false);
        imgbtn1=view.findViewById(R.id.imageButton1);
        imgbtn2=view.findViewById(R.id.imageButton2);
        imgbtn3=view.findViewById(R.id.imageButton3);
        imgbtn4=view.findViewById(R.id.imageButton4);
        imgbtn5=view.findViewById(R.id.imageButton5);
        imgbtn6=view.findViewById(R.id.imageButton6);

        searchView=view.findViewById(R.id.search);       //搜索框
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override

            //搜索框查询结果监听（模糊查询商品名）
            public boolean onQueryTextSubmit(String s) {
                String search=searchView.getQuery().toString().trim();
                Intent intent=new Intent(getActivity(),SearchShow.class);
                intent.putExtra("header",search);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {return true;}
        });


        viewFlipper=view.findViewById(R.id.viewfliper); //轮播容器
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),GoodsShow.class);
                intent.putExtra("name","小米电饭煲");
                startActivity(intent);
            }
        });
        for (int i=0;i<lunbo.length;i++){
            ImageView iv=getImageView(lunbo[i]);
            viewFlipper.addView(iv);
        }
        viewFlipper.setInAnimation(getActivity(),R.anim.left_in);
        viewFlipper.setOutAnimation(getActivity(),R.anim.left_out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();


        //监听
        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),GoodsList.class);
                intent.putExtra("classify","手机数码");
                startActivity(intent);
            }
        });

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),GoodsList.class);
                intent.putExtra("classify","游戏装备");
                startActivity(intent);
            }
        });

        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),GoodsList.class);
                intent.putExtra("classify","生活百货");
                startActivity(intent);
            }
        });

        imgbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),GoodsList.class);
                intent.putExtra("classify","运动户外");
                startActivity(intent);
            }
        });

        imgbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),GoodsList.class);
                intent.putExtra("classify","家用电器");
                startActivity(intent);
            }
        });

        imgbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),GoodsList.class);
                intent.putExtra("classify","美妆");
                startActivity(intent);
            }
        });



        return view;
    }


    private ImageView getImageView(int id) {
        ImageView imageView=new ImageView(getActivity());
        imageView.setBackgroundResource(id);
        return imageView;
    }




}
