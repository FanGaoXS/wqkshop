package com.example.wqkshop;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 95427 on 2019/12/10.
 */

public class CartFragment extends Fragment {

    String[] name,price,idvalue;
    ListView lv;
    int size;
    Myadapter myadapter;
    TextView textView;
    double sum;   //定义sum来装总价
    Datebase2 datebase2;
    View view;
    Button button;

    public void init(){

        //数组赋长度，不然会报数组越界错误
        idvalue=new String[20];
        name=new String[20];
        price=new String[20];

        //获取前台控件
        textView=view.findViewById(R.id.showprice);
        lv=view.findViewById(R.id.ListView);
        button=view.findViewById(R.id.settle);

        showcarts();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.buttonlistview,container,false);
        init();

        //listview里按钮长按监听删除
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int postion, long id) {
                AlertDialog dialog=new AlertDialog.Builder(getActivity()).setTitle("删除")
                        .setMessage("确认删除？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean success=datebase2.delete(idvalue[postion]);
                                if (success){
                                    Toast.makeText(getActivity(),"已删除",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(),"删除失败",Toast.LENGTH_SHORT).show();
                                }
                                showcarts();
                            }
                        })
                        .setNegativeButton("取消",null)
                        .create();
                dialog.show();
                return true;
            }
        });

        //settle结算按钮监听
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size==0&&sum==0){
                    Snackbar.make(view,"您好像还没加入商品",Snackbar.LENGTH_SHORT).show();
                }else {
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"); //处理时间
                    Date date=new Date(System.currentTimeMillis());
                    String time=simpleDateFormat.format(date);
                    Datebase3 datebase3=new Datebase3(getActivity());
                    boolean success=datebase3.insert(Double.toString(sum),"未发货",time);//新增到订单表里
                    if(success){
                        datebase2.clear(); //清空购物车表
                        Snackbar.make(view,"结算成功，可以前往我的订单查看",Snackbar.LENGTH_SHORT)
                                .setAction("查看我的订单", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent=new Intent(getActivity(),BillActivity.class);
                                        startActivity(intent);
                                    }
                                }).show();
                        showcarts();
                    }
                }
            }
        });

        //item单击监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long id) {
                Intent intent=new Intent(getActivity(),GoodsShow.class);
                TextView nameTV=view.findViewById(R.id.name);
                String name=nameTV.getText().toString().trim();
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        return view;
    }



    //显示购物车表里数据的方法
    public void showcarts(){
        sum=0;
        datebase2=new Datebase2(getActivity());
        ArrayList<Carts> carts=datebase2.show();
        size=carts.size();
        for(int i=0;i<carts.size();i++){
            idvalue[i]=carts.get(i).getId();
            name[i]=carts.get(i).getName();
            price[i]=carts.get(i).getPrice();
            sum=sum+Double.parseDouble(carts.get(i).getPrice());
        }
        //新建适配器实例
        myadapter=new Myadapter();
        //将listview与适配器绑定
        lv.setAdapter(myadapter);
        //给价格赋值
        textView.setText("合计：¥"+sum);
        button.setText("去结算"+"("+size+")");
    }






    //重写listview适配器
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
            //用getActivity()代替this
            View v=View.inflate(getActivity(),R.layout.list_item3,null);
            TextView nameTV=v.findViewById(R.id.name);
            TextView priceTV=v.findViewById(R.id.price);
            nameTV.setText(name[postion]);
            priceTV.setText("¥"+price[postion]);
            return v;
        }
    }
}
