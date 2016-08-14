package com.lv.mymobilesafeapp.adapter;

import android.content.Context;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.untils.ViewHolder;
import com.lv.mymobilesafeapp.view.UpdateTextView;

import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/26.
 */
public class TestAdapter extends CommonAdapter<Integer> {
    public TestAdapter(Context context, List<Integer> data, int resId) {

        super(context, data, resId);
    }

    @Override
    public void setviewcontent(ViewHolder holder, Object object) {
        UpdateTextView up=holder.getView(R.id.up_item);
        up.setTime(0);

    }
}
