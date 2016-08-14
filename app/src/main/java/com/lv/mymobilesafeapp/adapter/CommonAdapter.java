package com.lv.mymobilesafeapp.adapter;

import java.util.List;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lv.mymobilesafeapp.untils.ViewHolder;

public  abstract class CommonAdapter<DataType> extends BaseAdapter {
	protected Context context;
	protected List<DataType> data;
	protected int resId;
	public CommonAdapter(Context context,List<DataType> data,int resId) {
		this.context=context;
		this.data=data;
		this.resId=resId;
		
	}

	@Override
	public int getCount() {
		
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	public  View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = ViewHolder.getHolder(context, position, convertView, parent, resId);
		  setviewcontent(holder, getItem(position));  

		return holder.getconvertview();
		
	}

	public abstract void setviewcontent(ViewHolder holder,Object object);
	

}
