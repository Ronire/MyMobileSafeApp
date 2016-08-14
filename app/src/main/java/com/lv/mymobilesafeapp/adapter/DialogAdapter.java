package com.lv.mymobilesafeapp.adapter;

import java.util.List;



import android.content.Context;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.untils.ViewHolder;

public class DialogAdapter extends CommonAdapter<String>{

	public DialogAdapter(Context context, List<String> data, int resId) {
		super(context, data, resId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setviewcontent(ViewHolder holder, Object object) {
		TextView tv=holder.getView(R.id.Tv_dialog);
		tv.setText((String)object);
		
		
	}

}
