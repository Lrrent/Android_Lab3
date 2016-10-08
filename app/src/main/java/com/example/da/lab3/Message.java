package com.example.da.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Message extends AppCompatActivity {
    private ListView operation;
    private TextView more_details;
    private ImageView star;
    private ImageButton back;
    private TextView name_message;
    private TextView phone_number;
    private TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        operation = (ListView) findViewById(R.id.operation);
        more_details = (TextView) findViewById(R.id.more_details);
        star = (ImageView) findViewById(R.id.star);
        back = (ImageButton) findViewById(R.id.back);
        name_message = (TextView) findViewById(R.id.name_message);
        phone_number = (TextView) findViewById(R.id.phone_number);
        location  = (TextView) findViewById(R.id.location);
        //返回图标的处理
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Message.this,AddressList.class); //显式调用
                Message.this.startActivity(intent);
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
        String name = bundle.getString("name");
        String phone = bundle.getString("phone_number");
        name_message.setText(name);
        phone_number.setText(phone);
    }
}
