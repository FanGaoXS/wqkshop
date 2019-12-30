package com.example.wqkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText nameET,passwordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        nameET=(EditText) findViewById(R.id.editText);
        passwordET=(EditText)findViewById(R.id.editText2);
    }
    public void Click(View v){
        String name=nameET.getText().toString().trim();
        String password=passwordET.getText().toString().trim();
        if (name.equals("admin")&&(password.equals("admin"))){
            Datebase1 datebase1=new Datebase1(this);
            ArrayList<Goods> goods=datebase1.show();//获取全商品表数据
            if (goods.size()!=0){
                Log.i("goods",""+goods);
                Log.i("goods",""+goods.size());
                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            }else {
                String goodsname[]={"iPhone X","iPhone XR","iPhone XS MAX","Redmi K20 Pro","灭世者的王者之帽","扫把","迪卡侬护膝","AJ1黒脚趾","小米电饭煲","米家扫地机器人","Dior 999"};
                String price[]={"3899","4199","5899","2199","3200","89","59","2199","299","1399","299"};
                String type[]={"手机数码","手机数码","手机数码","手机数码","游戏装备","生活百货","运动户外","运动户外","家用电器","家用电器","美妆"};
                String intro[]={"iPhone X是美国Apple（苹果公司）于北京时间2017年9月13日凌晨1点，在Apple Park新总部的史蒂夫·乔布斯剧院会上发布的新机型。其中“X”是罗马数字“10”的意思，代表着苹果向iPhone问世十周年致敬。\n" +
                        "iPhone X属于高端版机型，采用全新设计，搭载色彩锐利的OLED屏幕，配备升级后的相机，可使用3D面部识别（Face ID）传感器解锁手机，支持AirPower（空中能量）无线充电。分为64GB、256GB两个版本，中国大陆起售价8316人民币 [1]  ，美国 [2]  起售价999美元，2017年10月27日预售，11月3号正式开卖 [3]  。","iPhone XR是美国Apple（苹果公司）旗下的智能手机，搭载7nm工艺的A12仿生芯片，采用TrueDepth摄像头，支持Face ID功能。\n" +
                        "2018年9月13日凌晨，iPhone XR在2018苹果秋季新品发布会上正式发布，起售价749美元。 [1]  2018年10月19日3点01分正式开售。 [2-3]","iPhone XS Max是美国Apple（苹果公司）旗下的智能手机设备，iPhone XS Max采用6.5英寸OLED屏幕，是iPhone迄今最大的显示屏 [1]  ，支持HDR10观影，配置IP68级别抗水、防尘，像素密度达到458ppi，支持3D Touch以及全新的Face ID。 [2-3] \n" +
                        "2018年9月13日凌晨，在2018苹果秋季新品发布会上，iPhone XS Max正式发布，支持双卡双待功能 [4]  ；2018年9月14日预售；2018年9 月21日正式开卖，可选银色、深空灰色和金色 [5]  。 [6]","Redmi K20 Pro是小米公司Redmi品牌手机的产品，搭载高通骁龙855处理器。\n" +
                        "2019年5月28日，Redmi K20 Pro发布，售价2499元起。 [1]","英雄联盟法师必备装备","家用普通扫把","迪卡侬护膝","乔丹一代，其实是国际著名品牌“耐克”麾下的一双富有传奇色彩的球鞋。在乔丹与耐克公司签约后，耐克公司就开始为乔丹打造形象，并于1985年推出以他名字命名的球鞋，于是称为“乔丹一代”。\n" +
                        "其实乔丹一代在功能方面和当时的其他球鞋没有太大的不同，但是它有一个醒目的外表———以黑红作为主色(当时球鞋大多是白色)。","小米电饭煲是小米生态链企业纯米科技打造的米家压力IH电饭煲。小米电饭煲是小米于2016年3月29日在北京健壹景园发布。 [1]","2016年8月31日，在小米生态链2016秋季沟通会上，小米正式发布旗下品牌米家的最新智能化产品——小米扫地机器人，售价1699元，将于2016年9月6日在小米网和小米之家首发销售。","口红"};
                String love[]={"未收藏","未收藏","未收藏","未收藏","未收藏","未收藏","未收藏","未收藏","未收藏","未收藏","未收藏"};
                for (int i=0;i<goodsname.length;i++){
                    datebase1.insert(goodsname[i],price[i],type[i],intro[i],love[i]);
                }
                Toast.makeText(this,"登录成功，同时初始化商品数据",Toast.LENGTH_SHORT).show();
            }
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
