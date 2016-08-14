package com.lv.mymobilesafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.mymobilesafeapp.activity.AppManagerActivity;
import com.lv.mymobilesafeapp.activity.ClearCacheActivity;
import com.lv.mymobilesafeapp.activity.FlowMonitorActivity;
import com.lv.mymobilesafeapp.activity.KillVirusActivity;
import com.lv.mymobilesafeapp.activity.PreventionActivity;
import com.lv.mymobilesafeapp.activity.ProgressActivity;
import com.lv.mymobilesafeapp.activity.ProgressManagerActivity;
import com.lv.mymobilesafeapp.activity.ToolsActivity;
import com.lv.mymobilesafeapp.bean.RecycleItem;
import com.lv.mymobilesafeapp.listener.MyItemClickListener;
import com.lv.mymobilesafeapp.listener.MyItemLongClickListener;
import com.lv.mymobilesafeapp.service.PhoneService;
import com.lv.mymobilesafeapp.service.SmsService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyItemLongClickListener, MyItemClickListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private View diaolog, login;
    private List<RecycleItem> mDatas = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText e_pwd, e_again, login_pwd;
    private Button yes, no, login_yes, login_no;
    private AlertDialog builder, loginbuilder;
    private boolean flag = false;

    private int[] image = {R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4,
            R.mipmap.image8, R.mipmap.image6, R.mipmap.image7, R.mipmap.image5};
    private String[] title = {"软件管理", "手机卫士", "缓存清理", "手机杀毒", "流量监控", "手机防盗", "高级工具", "进程管理"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, SmsService.class));
        startService(new Intent(this, PhoneService.class));
        System.out.println("+++++++++++++++wwwwwwwwwwwwwwwww+================");
        initview();
        init();

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        HomeAdapter adapter = new HomeAdapter();
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(adapter);

    }


    private void initview() {
        diaolog = View.inflate(this, R.layout.password_dialoag, null);
        login = View.inflate(this, R.layout.login_dialoag, null);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        sharedPreferences = getSharedPreferences("safe", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        e_pwd = (EditText) diaolog.findViewById(R.id.Et_input);
        e_again = (EditText) diaolog.findViewById(R.id.Et_inputagain);
        yes = (Button) diaolog.findViewById(R.id.dialog_yes);
        no = (Button) diaolog.findViewById(R.id.dialog_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        login_pwd = (EditText) login.findViewById(R.id.login_input);
        login_yes = (Button) login.findViewById(R.id.login_yes);
        login_no = (Button) login.findViewById(R.id.login_no);
        login_yes.setOnClickListener(this);
        login_no.setOnClickListener(this);


    }

    private void init() {


        for (int i = 0; i < 8; i++) {

            RecycleItem item = new RecycleItem(image[i], title[i]);
            mDatas.add(item);


        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        MyItemClickListener mItemClickListener;
        MyItemLongClickListener mItemLongClickListener;

        public void setOnItemClickListener(MyItemClickListener listener) {
            this.mItemClickListener = listener;
        }

        public void setOnItemLongClickListener(MyItemLongClickListener listener) {
            this.mItemLongClickListener = listener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.recycle_item, parent,
                    false), mItemClickListener, mItemLongClickListener);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.recycle_image.setImageResource(mDatas.get(position).getImage());
            holder.recycle_text.setText(mDatas.get(position).getNmae());


        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
            private ImageView recycle_image;
            private TextView recycle_text;
            private MyItemClickListener mItemClickListener;
            private MyItemLongClickListener mItemLongClickListener;


            public MyViewHolder(View view, MyItemClickListener mItemClickListener, MyItemLongClickListener mItemLongClickListener) {
                super(view);
                recycle_text = (TextView) view.findViewById(R.id.recycle_item_text);
                recycle_image = (ImageView) view.findViewById(R.id.recycle_item_image);
                this.mItemClickListener = mItemClickListener;
                this.mItemLongClickListener = mItemLongClickListener;
                view.setOnClickListener(this);
                view.setOnLongClickListener(this);

            }


            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, getPosition());
                }

            }

            @Override
            public boolean onLongClick(View v) {
                if (mItemLongClickListener != null) {
                    mItemLongClickListener.onItemLongClick(v, getPosition());
                }
                return true;
            }
        }
    }


    @Override
    public void onItemClick(View view, int postion) {
        switch (postion) {
            case 0:
                //软件管理
                startActivity(new Intent(this, AppManagerActivity.class));

                break;
            case 1:
                //手机卫士
                startActivity(new Intent(this, ProgressManagerActivity.class));
                break;
            case 2:
                //缓存清理
                startActivity(new Intent(this, ClearCacheActivity.class));
                break;
            case 3:
                //手机杀毒
                startActivity(new Intent(this, KillVirusActivity.class));

                break;
            case 4:
                //流量监控
                startActivity(new Intent(this, FlowMonitorActivity.class));

                break;
            case 5:
                //手机防盗
                if (sharedPreferences.getString("pwd", null) == null) {
                    //第一次进入设置密码
                    if (builder == null) {
                        builder = new AlertDialog.Builder(this).setView(diaolog).show();
                    } else {
                        builder.show();
                    }

                } else {
                    if (loginbuilder == null) {
                        loginbuilder = new AlertDialog.Builder(this).setView(login).show();
                    } else {
                        loginbuilder.show();
                    }
                }

                break;
            case 6:
                //高级工具
                startActivity(new Intent(this, ToolsActivity.class));
                break;
            case 7:
                //进程管理
             startActivity(new Intent(this, ProgressActivity.class));
                break;
        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {
        System.out.println("++++++++++++++++++++++++++++++++++++=");
        Toast.makeText(MainActivity.this, postion + " ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        String pwd = e_pwd.getText().toString();
        String pwdagain = e_again.getText().toString();
        String loginpwd = login_pwd.getText().toString();
        switch (v.getId()) {
            case R.id.dialog_yes:
                if (pwd.equals(pwdagain)) {
                    editor.putString("pwd", pwd);
                    editor.commit();
                    e_again.setText("");
                    e_pwd.setText("");
                    builder.dismiss();
                    startActivity(new Intent(this, PreventionActivity.class));
                }
                break;
            case R.id.dialog_no:
                e_again.setText("");
                e_pwd.setText("");
                builder.dismiss();

                break;
            case R.id.login_yes:

                //显示的是login
                if (loginpwd.equals(sharedPreferences.getString("pwd", null))) {
                login_pwd.setText("");
                loginbuilder.dismiss();
                startActivity(new Intent(this, PreventionActivity.class));
            }
                break;
            case R.id.login_no:
                login_pwd.setText("");
                loginbuilder.dismiss();
                break;
        }

    }
}
