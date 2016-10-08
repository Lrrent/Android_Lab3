package com.example.da.lab3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddressList extends AppCompatActivity {

    private ListView address_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        address_list = (ListView) findViewById(R.id.address_list);
        /*//ArrayAdapter
        String[] address = {"p1","p2","p3","p4","p5","p1","p2","p3","p4","p5","p1","p2","p3","p4","p5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,address);*/
        //SimpleaAdapter
        final List<Map<String,Object>> data = new ArrayList<>();
        final String[] name = new  String[] {"Aaron","Elvis","David","Edwin","Frank",
                "Joshua","Ivan","Mark","Joseph","Phoebe"};
        final String[] phone = {"17715523654","18825653224","15052116654","18854211875 "
        ,"13955188541","13621574410","15684122771","17765213579","13315466578","17895466428"};
        final String[] type = {"手机","手机","手机","手机","手机","手机","手机","手机","手机","手机"};
        final String[] location ={"江苏苏州电信","广东揭阳移动","江苏无锡移动","山东青岛移动",
        "安徽合肥移动 ","江苏苏州移动","山东烟台联通","广东珠海电信","河北石家庄电信","山东东营移动"};
        for(int i = 0;i<name.length;i++)
        {
            Map<String,Object> temp = new LinkedHashMap<>();
            temp.put("name_first",name[i].substring(0,1));
            temp.put("name",name[i]);
            temp.put("phone",phone[i]);
            temp.put("type",type[i]);
            temp.put("location",location[i]);
            data.add(temp);
        }
        final SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.address_item,
                new String[]{"name_first","name"},new int[] {R.id.name_first,R.id.name});
        address_list.setAdapter(adapter);
        //短按
        address_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AddressList.this,Message.class);
                Bundle bundle = new Bundle();
                /*最初直接使用name[position],type[position]这种方式将数据放入bundle，导致删除数据时不一致*/
                bundle.putString("name",data.get(position).get("name").toString());
                bundle.putString("phone_number",data.get(position).get("phone").toString());
                bundle.putString("type",data.get(position).get("type").toString());
                bundle.putString("location",data.get(position).get("location").toString());
                intent.putExtras(bundle);
                AddressList.this.startActivity(intent);
            }
        });
        //长按
        address_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

               final AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddressList.this);
                alertDialog.setTitle("删除联系人").setMessage("确定删除联系人"+data.get(position).get("name").toString()+"?").setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //暂时先点击取消就会回复原状
                    }
                }).show();
                return true;
            }
        });
    }
}
