package com.lv.mymobilesafeapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.lv.mymobilesafeapp.R;

/**
 * Created by 吕亚平 on 2016/7/22.
 */
public class Setting2Fragment extends Fragment {
    private Switch aSwitch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.setting_fragment2,container,false);
        aSwitch=(Switch) view.findViewById(R.id.St_first);
        return view;
    }

    public Switch getaSwitch() {
        return aSwitch;
    }
}
