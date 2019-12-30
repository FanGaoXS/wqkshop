package com.example.wqkshop;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by 95427 on 2019/12/10.
 */

public class ClassifyFragment extends Fragment {
        ListView lv;
        String[] left={"手机数码","游戏装备","生活百货","运动户外","家用电器","美妆"};
        String[] right={">>",">>",">>",">>",">>",">>"};
        //int[] layout_order = {R.layout.list_item,R.layout.list_item2,R.layout.list_item3};
        Intent intent;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.blanklistview,container,false);


        //获取listview控件
        lv=view.findViewById(R.id.ListView);


        //给listview绑定适配器
        Myadapter myadapter=new Myadapter();
        lv.setAdapter(myadapter);


        //ListView里item按钮单击监听事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long id) {
                intent=new Intent(getActivity(),GoodsList.class);
                intent.putExtra("classify",left[postion]);
                startActivity(intent);
            }
        });


        //item按钮长按监听
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent=new Intent(getActivity(),AddActivity.class);
                startActivity(intent);
                return true;  //返回值是false就响应长按后相应单击监听，true就只返回长按事件
            }
        });



        return view;
    }



    //重写ListView适配器方法
    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return left.length;
        }

        @Override
        public Object getItem(int postion) {
            return left[postion];
        }

        @Override
        public long getItemId(int postion) {
            return postion;
        }

        @Override
        public View getView(int postion, View view, ViewGroup viewGroup) {


            //用getActivity()代替this
            View v=View.inflate(getActivity(),R.layout.list_item,null);
           //View v=View.inflate(getActivity(),layout_order[postion],null);
            TextView leftTV=v.findViewById(R.id.left);
            leftTV.setText(left[postion]);
            TextView rightTV=v.findViewById(R.id.right);
            rightTV.setText(right[postion]);
            return v;
        }
    }
}
