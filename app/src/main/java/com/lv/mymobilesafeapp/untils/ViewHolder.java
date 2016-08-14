package com.lv.mymobilesafeapp.untils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewHolder {
	private Context mcontext;
	private int mposition;
	private SparseArray<View> mviews;
	private View mconvertview;

	public ViewHolder(Context context, int position, View convertView, ViewGroup parent, int resId) {
		convertView = LayoutInflater.from(context).inflate(resId, parent, false);
		this.mcontext = context;
		this.mposition = position;
		this.mconvertview = convertView;
		mviews = new SparseArray<View>();
		convertView.setTag(this);
		

	}

	public static ViewHolder getHolder(Context context, int position, View convertView, ViewGroup parent, int resId) {
		if (convertView == null) {
			return new ViewHolder(context, position, convertView, parent, resId);
		} else {

			return (ViewHolder) convertView.getTag();
		}

	}

	public <T extends View> T getView(int resId) {
		

		View view = mviews.get(resId);
		if (view == null) {
			view = mconvertview.findViewById(resId);
			mviews.put(resId, view);
		}
		return (T) view;
	}

	public View getconvertview() {
		return mconvertview;
	}

}
