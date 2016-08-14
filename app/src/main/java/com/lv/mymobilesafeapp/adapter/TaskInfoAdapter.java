package com.lv.mymobilesafeapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.TaskInfo;
import com.lv.mymobilesafeapp.untils.ViewHolder;

import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/25.
 */
public class TaskInfoAdapter extends CommonAdapter<TaskInfo>{
    public TaskInfoAdapter(Context context, List<TaskInfo> data, int resId) {
        super(context, data, resId);
    }


    @Override
    public void setviewcontent(ViewHolder holder, Object object) {
        TextView top=holder.getView(R.id.toptext);
        ImageView icon=holder.getView(R.id.taskicon);
        TextView name=holder.getView(R.id.taskname);
        TextView type=holder.getView(R.id.tasktype);
        TextView size=holder.getView(R.id.tasksize);


        if(((TaskInfo)object).getName().equals("m")){
            top.setText("用户进程");
            top.setVisibility(View.VISIBLE);
        }else if(((TaskInfo)object).getName().equals("s")){
            top.setText("系统进程");
            top.setVisibility(View.VISIBLE);
        }else {
            top.setVisibility(View.GONE);
            icon.setImageDrawable(((TaskInfo)object).getIcon());
            name.setText(((TaskInfo)object).getName());
            if(((TaskInfo)object).isUserTask()){
                type.setText("用户进程");
            }
            else {
                type.setText("系统进程");
            }
            size.setText(((TaskInfo)object).getMemsize()+"");
        }



    }
}
