package com.lv.mymobilesafeapp.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.LinkMan;
import com.lv.mymobilesafeapp.listener.MyRadioListener;

public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<LinkMan> list = null;
    private Context mContext;
    private MyRadioListener myRadioListener;
    private boolean flag=false;

    public SortAdapter(Context mContext, List<LinkMan> list ) {
        this.mContext = mContext;
        this.list = list;

    }


    public void updateListView(List<LinkMan> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final LinkMan mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            viewHolder.setListener(myRadioListener);
            view = LayoutInflater.from(mContext).inflate(R.layout.sortlist_item, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.name);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.Tv_top);
            viewHolder.btHead=(Button)view.findViewById(R.id.Circle) ;
            viewHolder.radioButton=(RadioButton) view.findViewById(R.id.radio) ;

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        int section = getSectionForPosition(position);


        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }
    if(flag){
        viewHolder.radioButton.setVisibility(View.VISIBLE);
    }
        viewHolder.tvTitle.setText(this.list.get(position).getName());
        viewHolder.btHead.setText(list.get(position).getName().substring(0,1));

        return view;

    }


    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }


    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }


    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();

        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }


    final static class ViewHolder {
        private MyRadioListener listener;
        TextView tvLetter;
        TextView tvTitle;
        Button   btHead;
        RadioButton radioButton;
        public void setListener(MyRadioListener listener){
            this.listener=listener;
        }
    }

    public void shown(){
       flag=true;
    }
    public void hide(){
        flag=false;
    }
}