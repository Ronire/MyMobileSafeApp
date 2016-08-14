package com.lv.mymobilesafeapp.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.Recoder;
import com.lv.mymobilesafeapp.untils.ViewHolder;

import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/20.
 */
public class RecoderAdapter  extends CommonAdapter<Recoder>{
    public RecoderAdapter(Context context, List<Recoder> data, int resId) {
        super(context, data, resId);
    }

    @Override
    public void setviewcontent(ViewHolder holder, Object object) {
        Button head=holder.getView(R.id.recoder_button);
        TextView name=holder.getView(R.id.recoder_name);
        TextView number=holder.getView(R.id.recoder_number);
        TextView time=holder.getView(R.id.recoder_time);
        TextView type=holder.getView(R.id.recoder_type);
        if(((Recoder)object).getName()!=null){
            head.setText(((Recoder)object).getName().substring(0,1));
            name.setText(((Recoder)object).getName());
            number.setText(((Recoder)object).getNumber());
            time.setText(((Recoder)object).getTime().toString());
            type.setText(((Recoder)object).getType());
        }else {
            name.setText(((Recoder)object).getNumber());
            number.setText("");
            head.setText("");
            time.setText(((Recoder)object).getTime().toString());
            type.setText(((Recoder)object).getType());
        }





    }

}
