package com.lv.mymobilesafeapp.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.SettingItem;
import com.lv.mymobilesafeapp.untils.ViewHolder;

import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/22.
 */
public class SettingAdapter extends CommonAdapter<SettingItem> {
    public SettingAdapter(Context context, List<SettingItem> data, int resId) {
        super(context, data, resId);
    }

    @Override
    public void setviewcontent(ViewHolder holder, Object object) {
        TextView tv=holder.getView(R.id.setting_tv);
        ImageView img=holder.getView(R.id.setting_img);
        tv.setText(((SettingItem)object).getTitle());
        img.setImageResource(((SettingItem)object).getSrcId());


    }
}
