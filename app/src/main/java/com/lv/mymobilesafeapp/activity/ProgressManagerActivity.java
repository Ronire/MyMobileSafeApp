package com.lv.mymobilesafeapp.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.fragment.InterceptorFragment;
import com.lv.mymobilesafeapp.fragment.LinkmanFragment;
import com.lv.mymobilesafeapp.fragment.RecoderFragment;

import java.util.ArrayList;
import java.util.List;

public class ProgressManagerActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager pager;
    private List<Fragment> fragmentList=new ArrayList<>();
    private ImageView imagesetting;
    private LinkmanFragment linkmanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_manager);
        pager=(ViewPager) findViewById(R.id.pager);
        initview();
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));




    }

    @Override
    protected void onResume() {
        if(1==getIntent().getIntExtra("rcode",0)){
            Toast.makeText(this,"result",Toast.LENGTH_SHORT).show();

           // linkmanFragment.flag=true;



        }
        super.onResume();
    }

    private void initview(){
        linkmanFragment=new LinkmanFragment();
        imagesetting=(ImageView) findViewById(R.id.setting);
        imagesetting.setOnClickListener(this);
        fragmentList.add(linkmanFragment);
        fragmentList.add(new RecoderFragment());
        fragmentList.add(new InterceptorFragment());

    }

    class MyPagerAdapter extends FragmentPagerAdapter{
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.setting:
                startActivity(new Intent(ProgressManagerActivity.this,SetingActivity.class));
                break;


        }

    }


}
