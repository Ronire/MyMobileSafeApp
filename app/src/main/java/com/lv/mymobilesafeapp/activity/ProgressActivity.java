package com.lv.mymobilesafeapp.activity;

import android.app.ActivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.adapter.MyTaskInfoAdapter;
import com.lv.mymobilesafeapp.adapter.TaskInfoAdapter;
import com.lv.mymobilesafeapp.bean.TaskInfo;
import com.lv.mymobilesafeapp.untils.SystemInfoHelper;
import com.lv.mymobilesafeapp.untils.TaskInfoProvider;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProgressActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {
    private TextView progress_count, progress_available, progress_total;
    private ListView pListView;
    private List<TaskInfo> taskInfoList;
    private List<TaskInfo> mtaskInfoList = new ArrayList<>();
    private List<TaskInfo> staskInfoList = new ArrayList<>();
    private ActivityManager am;
    private MyTaskInfoAdapter adapter;
    private Button seleteall, opposite, delete;
    private LinearLayout buttongroup;
    private List<TaskInfo> killlist=new ArrayList<>();


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x001) {
                //获取到两个不同List
                for (TaskInfo taskInfo : taskInfoList) {
                    if (taskInfo.isUserTask()) {
                        //用户进程
                        mtaskInfoList.add(taskInfo);

                    } else {
                        staskInfoList.add(taskInfo);
                    }
                }
                adapter = new MyTaskInfoAdapter(mtaskInfoList, staskInfoList, ProgressActivity.this);
                pListView.setAdapter(adapter);
                pListView.setOnItemClickListener(ProgressActivity.this);
                pListView.setOnItemLongClickListener(ProgressActivity.this);
                System.out.println(taskInfoList.size());
            }
            if (msg.what == 0x002) {
                Log.i("msg", "0x002" + "+++++++++++++++++++++=");
                adapter.setFlag(true);
                buttongroup.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();

            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        initview();
        initdata();
        settext();

    }

    private void settext() {
        progress_count.setText(SystemInfoHelper.getRunningProgressSize(this) + "个  ");
        progress_available.setText(SystemInfoHelper.getAvailableRam(this) / 1024 / 1024 + "MB    ");
        progress_total.setText(Math.round(1 + SystemInfoHelper.getTotalRam(this) / 1024 / 1024 / 1024) + "GB");
    }

    public void initview() {
        progress_count = (TextView) findViewById(R.id.progress_count);
        progress_available = (TextView) findViewById(R.id.progress_available);
        progress_total = (TextView) findViewById(R.id.progress_total);
        pListView = (ListView) findViewById(R.id.plistview);
        seleteall = (Button) findViewById(R.id.selectall);
        opposite = (Button) findViewById(R.id.opposite);
        delete = (Button) findViewById(R.id.delete);
        buttongroup = (LinearLayout) findViewById(R.id.buttongroup);
        seleteall.setOnClickListener(this);
        opposite.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    private void initdata() {
        new Thread() {
            @Override
            public void run() {
                taskInfoList = TaskInfoProvider.getTaskInfo(ProgressActivity.this);
                handler.sendEmptyMessage(0x001);
                super.run();
            }

        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // Log.i("msg",  staskInfoList.get(position-mtaskInfoList.size()).getPackageName().toString());

        if (adapter.isFlag()) {
            if (position < mtaskInfoList.size()) {
                if (mtaskInfoList.get(position).isCheck()) {
                    mtaskInfoList.get(position).setCheck(false);
                } else {
                    mtaskInfoList.get(position).setCheck(true);
                }
            } else {
                if (staskInfoList.get(position - mtaskInfoList.size()).isCheck()) {
                    staskInfoList.get(position - mtaskInfoList.size()).setCheck(false);
                } else {
                    staskInfoList.get(position - mtaskInfoList.size()).setCheck(true);

                }

            }
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        handler.sendEmptyMessage(0x002);

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectall:
                //点击全部选中
                if (adapter.isFlag()) {
                    for (TaskInfo info : mtaskInfoList) {
                        info.setCheck(true);
                    }
                    for (TaskInfo info : staskInfoList) {
                        info.setCheck(true);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.opposite:
                if (adapter.isFlag()) {
                    for (TaskInfo info : mtaskInfoList) {
                        if (info.isCheck()) {
                            info.setCheck(false);
                        } else {
                            info.setCheck(true);
                        }
                    }
                    for (TaskInfo info : staskInfoList) {
                        if (info.isCheck()) {
                            info.setCheck(false);
                        } else {
                            info.setCheck(true);
                        }
                    }
                }
                adapter.notifyDataSetChanged();

                break;
            case R.id.delete:
                for(int i=0;i<mtaskInfoList.size();i++){
                    if(mtaskInfoList.get(i).isCheck()){
                             killlist.add(mtaskInfoList.get(i));
                      /*  am.killBackgroundProcesses(mtaskInfoList.get(i).getPackageName());
                        mtaskInfoList.remove(mtaskInfoList.get(i));*/

                    }
                }
                for(int i=0;i< staskInfoList.size();i++){
                    if(staskInfoList.get(i).isCheck()){
                        killlist.add(staskInfoList.get(i));
                        /*am.killBackgroundProcesses(staskInfoList.get(i).getPackageName());
                        staskInfoList.remove(staskInfoList.get(i));*/

                    }
                }
                for(TaskInfo info:killlist){
                    am.killBackgroundProcesses(info.getPackageName());


                    if (info.isUserTask()){
                        mtaskInfoList.remove(info);
                    }else {
                        staskInfoList.remove(info);
                    }
                }
                adapter.setFlag(false);
                adapter.notifyDataSetChanged();
                settext();
                buttongroup.setVisibility(View.INVISIBLE);


                break;
        }

    }
}
