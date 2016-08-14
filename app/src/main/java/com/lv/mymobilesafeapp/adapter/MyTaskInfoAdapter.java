package com.lv.mymobilesafeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.TaskInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/25.
 */
public class MyTaskInfoAdapter extends BaseAdapter {
    private List<TaskInfo> mtaskInfoList;
    private List<TaskInfo> staskInfoList;
    private Context context;
    private LayoutInflater inflater;
    private boolean flag=false;

    public MyTaskInfoAdapter(List<TaskInfo> mtaskInfoList, List<TaskInfo> staskInfoList, Context context) {
        this.mtaskInfoList = mtaskInfoList;
        this.staskInfoList = staskInfoList;
        this.context = context;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mtaskInfoList.size()+staskInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        TaskInfo info = null;
        if(position ==0){
            return null;
        }else if(position == (mtaskInfoList.size() +1)){
            return null;
        }else if(position <= mtaskInfoList.size()){
            int newposition = position-1;
            info = mtaskInfoList.get(newposition);
        }else{
            info = staskInfoList.get(position-1-mtaskInfoList.size()-1);
        }
        return info;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=null;
        if(convertView==null){
            v=inflater.inflate(R.layout.progresstask_item,parent,false);
        }
        else {
            v=convertView;
        }
        TextView top=(TextView)v.findViewById(R.id.toptext) ;
        ImageView icon=(ImageView) v.findViewById(R.id.taskicon);
        TextView name=(TextView)v.findViewById(R.id.taskname) ;
        TextView type=(TextView)v.findViewById(R.id.tasktype) ;
        TextView size=(TextView)v.findViewById(R.id.tasksize) ;
        TextView packname=(TextView)v.findViewById(R.id.hide) ;
        CheckBox checkBox=(CheckBox)v.findViewById(R.id.checkbox);
        if(flag){
            checkBox.setVisibility(View.VISIBLE);

        }
        else {
            checkBox.setVisibility(View.INVISIBLE);
        }
        if(position==0){
            top.setText("用户进程:   "+mtaskInfoList.size());
            top.setVisibility(View.VISIBLE);
            icon.setImageDrawable(mtaskInfoList.get(position).getIcon());
            name.setText(mtaskInfoList.get(position).getName());
            type.setText("用户进程");
            size.setText(mtaskInfoList.get(position).getMemsize()+"");
            packname.setText(mtaskInfoList.get(position).getPackageName());
            if(mtaskInfoList.get(position).isCheck()){
                checkBox.setChecked(true);
            }
            else {
                checkBox.setChecked(false);
            }
        }
        if(position==mtaskInfoList.size()){
            top.setText("系统进程:   "+staskInfoList.size());
            top.setVisibility(View.VISIBLE);
            icon.setImageDrawable(staskInfoList.get(position-mtaskInfoList.size()).getIcon());
            name.setText(staskInfoList.get(position-mtaskInfoList.size()).getName());
            type.setText("系统进程");
            size.setText(staskInfoList.get(position-mtaskInfoList.size()).getMemsize()+"");
            packname.setText(staskInfoList.get(position-mtaskInfoList.size()).getPackageName());
            if(staskInfoList.get(position-mtaskInfoList.size()).isCheck()){
                checkBox.setChecked(true);
            }
            else {
                checkBox.setChecked(false);
            }

        }
        if(0<position&&position<mtaskInfoList.size()){
            top.setVisibility(View.GONE);
            icon.setImageDrawable(mtaskInfoList.get(position).getIcon());
            name.setText(mtaskInfoList.get(position).getName());
                type.setText("用户进程");
            size.setText(mtaskInfoList.get(position).getMemsize()+"");
            packname.setText(mtaskInfoList.get(position).getPackageName());
            if(mtaskInfoList.get(position).isCheck()){
                checkBox.setChecked(true);
            }
            else {
                checkBox.setChecked(false);
            }
        }
        if(mtaskInfoList.size()<position&&position<mtaskInfoList.size()+staskInfoList.size()){
            top.setVisibility(View.GONE);
            icon.setImageDrawable(staskInfoList.get(position-mtaskInfoList.size()).getIcon());
            name.setText(staskInfoList.get(position-mtaskInfoList.size()).getName());
            type.setText("系统进程");
            size.setText(staskInfoList.get(position-mtaskInfoList.size()).getMemsize()+"");
            packname.setText(staskInfoList.get(position-mtaskInfoList.size()).getPackageName());
            if(staskInfoList.get(position-mtaskInfoList.size()).isCheck()){
                checkBox.setChecked(true);
            }
            else {
                checkBox.setChecked(false);
            }

        }

        return v;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }
}
