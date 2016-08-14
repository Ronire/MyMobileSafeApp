package com.lv.mymobilesafeapp.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.adapter.BlackListAdapter;
import com.lv.mymobilesafeapp.adapter.DialogAdapter;
import com.lv.mymobilesafeapp.bean.BlackMan;
import com.lv.mymobilesafeapp.dao.BlackManDao;
import com.lv.mymobilesafeapp.untils.MyOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SetingActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener {
    private Button addblack;
    private View blackview;
    private EditText e_name, e_number;
    private Button yes, no;
    private AlertDialog builder, addbuiler;
    private ListView blacklist;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private List<BlackMan> list=new ArrayList<>();
    private BlackListAdapter adapter;




    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x001){
                list.add((BlackMan) msg.obj);
                adapter.notifyDataSetChanged();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);
        helper=new MyOpenHelper(this, "safe.db", null, 1);
        db=helper.getWritableDatabase();
        init();
        load();


    }

    private void init() {
        blackview = View.inflate(this, R.layout.add_blacklist, null);
        e_name = (EditText) blackview.findViewById(R.id.E_name);
        e_number = (EditText) blackview.findViewById(R.id.E_number);
        yes = (Button) blackview.findViewById(R.id.yes);
        no = (Button) blackview.findViewById(R.id.no);
        addblack = (Button) findViewById(R.id.setting_but);
        blacklist=(ListView) findViewById(R.id.blacklist);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        addblack.setOnClickListener(this);


    }


    public void load(){
        Cursor cursor= BlackManDao.findall(db);

        while(cursor.moveToNext()){

            BlackMan item=new BlackMan(cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("telephone")));
            list.add(item);
        }

        adapter=new BlackListAdapter(SetingActivity.this, list, R.layout.black_item);

        blacklist.setAdapter(adapter);
        blacklist.setOnItemLongClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_but:
                View view = View.inflate(this, R.layout.dialog_view, null);
                List<String> list = new ArrayList<String>();
                list.add("从联系人添加");
                list.add("从通话记录添加");
                list.add("从信息记录添加");
                list.add("手动输入号码");
                ListView listview = (ListView) view.findViewById(R.id.listview);
                listview.setAdapter(new DialogAdapter(this, list, R.layout.dialog_item));
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        if (position == 3) {
                            builder.hide();
                            if(addbuiler==null){   addbuiler = new AlertDialog.Builder(SetingActivity.this).setView(blackview).show();
                            }else {
                                addbuiler.show();
                            }
                        }
                    }
                });
                if(builder==null){
                    builder = new AlertDialog.Builder(this).setView(view).show();
                }
                else {
                    builder.show();
                }

                /*Intent intent=new Intent(SetingActivity.this,ProgressManagerActivity.class);
                intent.putExtra("rcode",1);
                startActivityForResult(intent,0x001);*/
                break;

            case R.id.yes:
                String name=e_name.getText().toString().trim();
                String number=e_number.getText().toString().trim();
                BlackManDao.save(db, name, number);
                e_name.setText("");
                e_number.setText("");
                addbuiler.hide();
                BlackMan item=new BlackMan(name, number);
                Message msg= Message.obtain();
                msg.what=0x001;
                msg.obj=item;
                handler.sendMessage(msg);

                break;
            case R.id.no:
                e_name.setText("");
                e_number.setText("");
                addbuiler.hide();
                break;
        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        TextView number=(TextView) view.findViewById(R.id.tel);
        String telephone=number.getText().toString();
        BlackManDao.delete(db,telephone);
        list.remove(position);
        adapter.notifyDataSetChanged();
        return true;
    }
}
