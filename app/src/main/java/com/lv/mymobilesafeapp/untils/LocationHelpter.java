package com.lv.mymobilesafeapp.untils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by 吕亚平 on 2016/7/21.
 */
public class LocationHelpter {
    private LocationManager locationManager;
    private Location location;
    private Context context;

    public LocationHelpter(Context context) {
        this.context = context;
    }

    public Location getLocation() {
        System.out.println("111111111111111111111111111111111111111111");
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(location==null){
            System.out.println("222222222222222222222222222222222222222222");
        }
        System.out.println("3333333333333333333333333333333333333");

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("4444444444444444444444444444444444444444");
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            System.out.println(location.getLatitude() + " " + location.getLongitude());
            return location;
        }
        System.out.println("555555555555555555555555555555555555555");
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        System.out.println("666666666666666666666666666666");

        return location;
    }

}
