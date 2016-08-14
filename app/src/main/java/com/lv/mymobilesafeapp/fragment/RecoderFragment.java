package com.lv.mymobilesafeapp.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.adapter.RecoderAdapter;
import com.lv.mymobilesafeapp.bean.Recoder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.provider.CallLog.*;

/**
 * Created by 吕亚平 on 2016/7/20.
 */
public class RecoderFragment extends Fragment {
    private ListView listView;
    private  List<Recoder> recoderList = new ArrayList<>();


    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x001) {
                listView.setAdapter(new RecoderAdapter(getActivity(), recoderList, R.layout.recoder_item));
            }
        }

        ;
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recoder_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.recoder_list);
        if(recoderList.size()==0){
            initdata();
        }







        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    public void initdata() {
        ContentResolver mResolver = getActivity().getContentResolver();
          /**
           * 检查权限
           * */
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Cursor cursor = mResolver.query(android.provider.CallLog.Calls.CONTENT_URI, null, null, null, null);
        System.out.println("=========================0000000000++++++++++++++++++++++++++++==");



        while(cursor.moveToNext()){

            Recoder calllong=new Recoder();
            //电话号码
            String number=cursor.getString(cursor.getColumnIndexOrThrow(Calls.NUMBER));
            //类型
            String type;

            switch (Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(Calls.TYPE)))) {

                case Calls.INCOMING_TYPE:
                    type="呼入";
                    break;
                case Calls.OUTGOING_TYPE:
                    type="呼出";
                    break;
                case Calls.MISSED_TYPE:
                    type="未接";
                    break;
                default:
                    type="挂断";
                    break;
            }
            //时间
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            //Date date=new Date();
            String time=sdf.format(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(Calls.DATE))));
            String duration=cursor.getString(cursor.getColumnIndexOrThrow(Calls.DURATION));
            String name=cursor.getString(cursor.getColumnIndex(Calls.CACHED_NAME));

            calllong.setTime(time);

            calllong.setType(type);
            calllong.setNumber(number);
            calllong.setName(name);
            System.out.println(calllong.toString());
            recoderList.add(calllong);

        }
        handler.sendEmptyMessage(0x001);
    }
}
