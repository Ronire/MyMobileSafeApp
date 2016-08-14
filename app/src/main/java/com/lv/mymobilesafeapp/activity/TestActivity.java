package com.lv.mymobilesafeapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ListView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.adapter.TestAdapter;
import com.lv.mymobilesafeapp.untils.AppStats;
import com.lv.mymobilesafeapp.untils.LocationHelpter;
import com.lv.mymobilesafeapp.untils.StatsHelper;
import com.lv.mymobilesafeapp.view.CircleProgressBar;
import com.lv.mymobilesafeapp.view.UpdateTextView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private String packname="com.android.backupconfirm";
    private UpdateTextView updateTextView;
    private ListView uplist;
    private List<Integer> list=new ArrayList<>();
    private CircleProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        bar=(CircleProgressBar)findViewById(R.id.bar);
       /* RotateAnimation rotateAnimation=new RotateAnimation(0,360);
        rotateAnimation.setDuration(2000);
        bar.startAnimation(rotateAnimation);*/
        //init();
      //  uplist.setAdapter(new TestAdapter(this,list,R.layout.test_up));

    }

    private void initdata(){
        for(int i=0;i<10;i++){
            Integer integer=new Integer(i);
            list.add(integer);
        }
    }
private void init(){
    //uplist=(ListView)findViewById(R.id.uplist);
    initdata();

}
    public void but3(View view){

    }

    public void but(View view) {

       /* Intent startintent=new Intent();
        startintent.setAction("com.lv.main");
        startintent.addCategory("android.intent.category.DEFAULT");
        this.startActivity(startintent);*/
        //SmsManager.getDefault().sendTextMessage("15727658318", null,"大表哥，手机号码发生改变", null,null);
       /* String safenumber=getSharedPreferences("safe",MODE_PRIVATE).getString("safenumber",null);
        String sim=getSharedPreferences("safe",MODE_PRIVATE).getString("sim",null);
        boolean flag=getSharedPreferences("safe",MODE_PRIVATE).getBoolean("flag",false);
        System.out.println(safenumber+"+++++++++++----"+sim+flag);*/
        //LocationHelpter locationHelpter=new LocationHelpter(this);)

      /*  LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(locationManager==null){
            System.out.println("++++++++null++++++++++");
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           System.out.println("++++++++++++++return+++++++++++++++++++=");
            return ;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        List<String> providerlist=locationManager.getAllProviders();
        for(String s:providerlist){
            System.out.println(s);
        }
        if(location==null){
            System.out.println("++++++++++++++location+++++++++++++++++++=");
        }
        System.out.println(location.getLatitude()+"... "+location.getLongitude());*/

        try{
         AppStats appStats= StatsHelper.queryPacakgeSize(packname,getPackageManager());
           // Thread.sleep(3000);
            while(appStats.getDatasize()==null){

            }
            System.out.println(appStats.getCachesize()+"++++++++++++="+appStats.getCodesize()+"++++++++++++="+appStats.getDatasize());
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
