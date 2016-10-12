package com.example.da.lab3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Message extends AppCompatActivity {
    private ListView operation;
    private TextView more_details;
    private ImageView star;
    private ImageView back;
    private TextView name_message;
    private TextView phone_number;
    private TextView type;
    private TextView location;
    private RelativeLayout header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        operation = (ListView) findViewById(R.id.operation);
        more_details = (TextView) findViewById(R.id.more_details);
        star = (ImageView) findViewById(R.id.star);
        back = (ImageView) findViewById(R.id.back);
        name_message = (TextView) findViewById(R.id.name_message);
        phone_number = (TextView) findViewById(R.id.phone_number);
        type = (TextView) findViewById(R.id.type);
        location  = (TextView) findViewById(R.id.location);
        header = (RelativeLayout) findViewById(R.id.header);
        //返回图标的处理
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转的时候如果新建一个intent,然后再startActivity的话并不能达到完全退出activity的效果;等多次之后会一个叠一个，手机内存占用越来越多，而采用finish会
                //销毁当前activity，然后跳转回上一个activity
                /*
                Intent intent = new Intent(Message.this,AddressList.class); //显式调用
                Message.this.startActivity(intent);
               */
                Message.this.finish();
            }
        });
        //星星图标的切换
        star.setOnClickListener(new View.OnClickListener() {
            int star_flag = 0;
            @Override
            public void onClick(View v) {
                if(star_flag == 0){
                    star_flag = 1;
                    star.setImageResource(R.drawable.full_star);
                }
                else{
                    star_flag = 0;
                    star.setImageResource(R.drawable.empty_star);
                }
            }
        });
        more_details.setText("更多资料");
        /*尾部的ListView适配器*/
        String []operations = {"编辑联系人","分享联系人","加入黑名单","删除联系人"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,operations);
        operation.setAdapter(adapter);
        //接收从AddressList.java中数据并对相应控件的取值进行处理
        Bundle bundle = this.getIntent().getExtras();
        Contacts_name person = (Contacts_name) bundle.getSerializable("person");
        String name = person.getName();
        String phone = person.getPhone_number();
        String type_receive = person.getType();
        String location_receive = person.getLocation();
        /*
        String name = bundle.getString("name");
        String phone = bundle.getString("phone_number");
        String type_receive = bundle.getString("type");
        String location_receive = bundle.getString("location");*/
        name_message.setText(name);
        phone_number.setText(phone);
        type.setText(type_receive);
        location.setText(location_receive);
        /*由于一开始将AddressList.java中name数组的Phoebe写成phoebe,和drawable中的Phoebe不一样，
        导致点击Phoebe时找不到背景颜色，出来下面的错误：
        android.content.res.Resources$NotFoundException: Resource ID #0x0*/
        /*int color_id= getResources().getIdentifier(name, "drawable", "com.example.da.lab3");
        Drawable drawable = getResources().getDrawable(color_id);
        header.setBackground(drawable);*/
        header.setBackgroundColor(Color.parseColor("#"+person.getColor()));
    }
}
