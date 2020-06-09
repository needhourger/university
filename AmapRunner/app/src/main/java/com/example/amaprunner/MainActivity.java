package com.example.amaprunner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String[] permissons ={
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
    };

    private static MainActivity mainActivity=null;

    // 地图
    private static MapView mapView =null;
    private static AMap amap=null;
    private static MyLocationStyle style=null;
    private static Polyline polyline=null;


    //控件
    private ImageButton btStart=null;
    private ImageButton btPause=null;
    private ImageButton btStop=null;
    private TextView tvSpeed=null;
    private TextView tvMile=null;
    private boolean btPauseFlag=true;
    private boolean isRunning=false;
    private static ActionBar actionBar=null;

    private List<LatLng> latLngList=new ArrayList<LatLng>();

    public MainActivity(){
        mainActivity=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        mapView =(MapView)findViewById(R.id.amap);
        mapView.onCreate(savedInstanceState);
        initMap();

        btStart=(ImageButton)findViewById(R.id.btStart);
        btPause=(ImageButton)findViewById(R.id.btPause);
        btStop=(ImageButton)findViewById(R.id.btStop);
        initButton();

        tvSpeed=(TextView)findViewById(R.id.tvSpeed);
        tvMile=(TextView)findViewById(R.id.tvMile);


        actionBar=getSupportActionBar();
//        initActionbar();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        mapView.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.record:
//                Log.e("debug","record");
                break;
                default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkPermissions(){
        boolean flag=false;
        for (String p:permissons){
            if (ContextCompat.checkSelfPermission(this,p)!=PackageManager.PERMISSION_GRANTED){
                flag=true;
                break;
            }
        }
        if (flag){
            ActivityCompat.requestPermissions(this,permissons,0);
        }
    }

    private void initMap(){
        if (amap==null) {
            amap = mapView.getMap();
        }

        style=new MyLocationStyle();
        style.interval(3000);
//        style.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location)) ;
        style.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        style.showMyLocation(true);
        style.strokeWidth(0);
        style.strokeColor(0x00000000);
        style.radiusFillColor(0x00000000);
        amap.setMyLocationStyle(style);
//        amap.getUiSettings().setMyLocationButtonEnabled(true);
        amap.getUiSettings().setRotateGesturesEnabled(false);
        amap.getUiSettings().setTiltGesturesEnabled(false);
        amap.getUiSettings().setZoomControlsEnabled(false);
        amap.getUiSettings().setScaleControlsEnabled(true);
        amap.getUiSettings().setZoomGesturesEnabled(false);
        amap.setMyLocationEnabled(true);
        amap.moveCamera(CameraUpdateFactory.zoomTo(18f));

        amap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Log.e("GPS",location.toString());
                tvSpeed.setText(location.getSpeed()+"");

                if (latLngList!=null && isRunning){
                    PathSmoothTool pathSmoothTool=new PathSmoothTool();
                    pathSmoothTool.setIntensity(4);
                    List<LatLng> pathoptimizeList=pathSmoothTool.pathOptimize(latLngList);
                    latLngList.add(new LatLng(location.getLatitude(),location.getLongitude()));
                    polyline=amap.addPolyline(new PolylineOptions().addAll(pathoptimizeList).color(Color.BLUE).width(40));
//                    polyline=amap.addPolyline(new PolylineOptions().addAll(latLngList).color(Color.BLUE).width(50));
//                    amap.moveCamera(CameraUpdateFactory.newLatLngBounds(getBounds(latLngList),200));
                    DecimalFormat df=new DecimalFormat("0.00");
                    tvMile.setText(df.format(calculateMile(latLngList)));
                }
            }

        });
    }

    private float calculateMile(List<LatLng> list){
        float mile=0;
        for (int i=1;i<list.size();i++){
            mile+= AMapUtils.calculateLineDistance(list.get(i-1),list.get(i));
        }
        mile=mile/1000.0F;
        return mile;
    }

    private LatLngBounds getBounds(List<LatLng> pointList){
        LatLngBounds.Builder b=LatLngBounds.builder();
        if (pointList==null){
            return b.build();
        }
        for (LatLng i:pointList){
            b.include(i);
        }
        return b.build();
    }

    private void initButton(){

        btStart.setVisibility(View.VISIBLE);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btStart.setVisibility(View.INVISIBLE);
                btStop.setVisibility(View.VISIBLE);
                btPause.setVisibility(View.VISIBLE);
                isRunning=true;
                startRun();
            }
        });

        btPause.setVisibility(View.INVISIBLE);
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btPauseFlag){
                    btPause.setBackgroundResource(R.drawable.start);
                    btPauseFlag=false;
                    isRunning=false;
                    pauseRun();
                }else{
                    btPause.setBackgroundResource(R.drawable.pause);
                    btPauseFlag=true;
                    isRunning=false;
                    stopRun();
                }
            }
        });

        btStop.setVisibility(View.INVISIBLE);
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPause.setBackground(getDrawable(R.drawable.pause));
                btPause.setVisibility(View.INVISIBLE);
                btStop.setVisibility(View.INVISIBLE);
                btStart.setVisibility(View.VISIBLE);
            }
        });
    }

    private void startRun(){
        latLngList.clear();
    }

    private void pauseRun(){

    }

    private void stopRun(){
        latLngList.clear();
    }

    public AMap getAmap(){
        return amap;
    }

    public static MainActivity getMainActivity(){
        return mainActivity;
    }

}
