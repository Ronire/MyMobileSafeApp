package com.lv.mymobilesafeapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.App;
import com.lv.mymobilesafeapp.untils.ViewHolder;

import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/22.
 */
public class AppAdapter extends CommonAdapter<App> {
    public AppAdapter(Context context, List<App> data, int resId) {
        super(context, data, resId);
    }

    @Override
    public void setviewcontent(ViewHolder holder, Object object) {
        ImageView icon=holder.getView(R.id.appicon);
        TextView name=holder.getView(R.id.appname);
        TextView type=holder.getView(R.id.apptype);
        TextView size=holder.getView(R.id.appsize);
        icon.setImageDrawable(((App)object).getAppIcon());
        name.setText(((App)object).getAppName());
        size.setText(((App)object).getAppSize());
        if(((App)object).getAppType()){
            type.setText("系统应用");
        }else {
            type.setText("用户应用");
        }


    }
}
