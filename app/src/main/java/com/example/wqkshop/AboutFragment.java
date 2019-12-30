package com.example.wqkshop;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 95427 on 2019/12/10.
 */

public class AboutFragment extends Fragment {
    ImageButton billbtn,likebtn,settingsbtn,morebtn;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.aboutfragment,container,false);
        billbtn=view.findViewById(R.id.bill);
        likebtn=view.findViewById(R.id.like);
        settingsbtn=view.findViewById(R.id.settings);
        morebtn=view.findViewById(R.id.more);

        //全部订单按钮监听
        billbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),BillActivity.class);
                startActivity(intent);
            }
        });

        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),loveActivity.class);
                startActivity(intent);
            }
        });

        settingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"还没写这个",Toast.LENGTH_SHORT).show();
            }
        });

        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"还没写这个",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),AddActivity.class);
                startActivity(intent);
            }
        });
        TextView textView=view.findViewById(R.id.username);
        textView.setText("TaoBaoV1.0");
        return view;
    }
}
