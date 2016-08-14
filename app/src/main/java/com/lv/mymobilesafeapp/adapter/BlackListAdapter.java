package com.lv.mymobilesafeapp.adapter;

import java.util.List;


import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.BlackMan;
import com.lv.mymobilesafeapp.untils.ViewHolder;

import android.content.Context;
import android.widget.TextView;

public class BlackListAdapter extends CommonAdapter<BlackMan>{

	public BlackListAdapter(Context context, List<BlackMan> data, int resId) {
		super(context, data, resId);
		
	}

	@Override
	public void setviewcontent(ViewHolder holder, Object object) {
		TextView name=holder.getView(R.id.name);
		TextView tel=holder.getView(R.id.tel);
		name.setText(((BlackMan)object).getName());
		tel.setText(((BlackMan)object).getNumber());
		
		
	}

}
