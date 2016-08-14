package com.lv.mymobilesafeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.App;
import com.lv.mymobilesafeapp.bean.TaskInfo;
import com.lv.mymobilesafeapp.untils.ViewHolder;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/26.
 */
public class FlowerAdapter extends CommonAdapter<App> {
    public FlowerAdapter(Context context, List<App> data, int resId) {

        super(context, data, resId);
    }

    @Override
    public void setviewcontent(ViewHolder holder, Object object) {
        ImageView img = holder.getView(R.id.flow_heand);
        TextView name = holder.getView(R.id.flow_name);
        TextView number = holder.getView(R.id.flow_number);
        if (((App) object).getAppIcon() != null) {
            img.setImageDrawable(((App) object).getAppIcon());
        }
        name.setText(((App) object).getAppName());
        number.setText(((App) object).getFlow_number() / 1024 + "KB");

    }
}
