package com.lv.mymobilesafeapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.adapter.SettingAdapter;
import com.lv.mymobilesafeapp.bean.SettingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/22.
 */
public class Setting1Fragment extends Fragment {
    private ListView listView;
    private List<SettingItem> list = new ArrayList<>();
    private String[] titles = {"sim卡变更报警", "GPS追踪", "远程数据销毁", "远程锁屏"};
    private int[] res = {R.mipmap.b1, R.mipmap.b2, R.mipmap.b3, R.mipmap.b4,};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.setting_list);
        for (int i = 0; i < titles.length; i++) {
            SettingItem item = new SettingItem(titles[i], res[i]);
            list.add(item);
        }

        listView.setAdapter(new SettingAdapter(getActivity(), list, R.layout.setting_item));
        return view;
    }


}

