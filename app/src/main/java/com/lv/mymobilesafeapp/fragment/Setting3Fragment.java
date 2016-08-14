package com.lv.mymobilesafeapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lv.mymobilesafeapp.R;

/**
 * Created by 吕亚平 on 2016/7/22.
 */
public class Setting3Fragment extends Fragment {
    private EditText e_safe;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.setting_fragment3,container,false);
        e_safe=(EditText)view.findViewById(R.id.e_safe);

        return view;
    }

    public EditText getE_safe() {
        return e_safe;
    }
}
