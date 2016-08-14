package com.lv.mymobilesafeapp.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.Cache;
import com.lv.mymobilesafeapp.untils.ViewHolder;

import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/28.
 */
public class CacheAdapter extends CommonAdapter<Cache> {
    public CacheAdapter(Context context, List<Cache> data, int resId) {
        super(context, data, resId);
    }

    @Override
    public void setviewcontent(ViewHolder holder, Object object) {
        ImageView icon=holder.getView(R.id.cache_icon);
        TextView name=holder.getView(R.id.cache_name);
        TextView size=holder.getView(R.id.cache_size);
        icon.setImageDrawable(((Cache)object).getIcon());
        name.setText(((Cache)object).getName());
        if(((Cache)object).getSize()>1024){
            size.setText(((Cache)object).getSize()/1024+"KB");
        }else {
            size.setText(((Cache)object).getSize()+"B");
        }


    }
}
