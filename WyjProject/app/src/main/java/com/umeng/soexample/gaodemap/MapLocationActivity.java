package com.umeng.soexample.gaodemap;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapLocationActivity extends BaseActivity {
    @BindView(R.id.btn_startLocation)
    Button btnStartLocation;
    @BindView(R.id.tv_result)
    TextView tvResult;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_main);
        ButterKnife.bind(this);
        initLocation();
    }

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(getLocationOption());

        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
    }

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {//当定位错误码类型为0时定位成功
                    //可在其中解析amapLocation获取相应内容。
                    //解析定位结果
                    String result = LocationUtils.getLocationStr(aMapLocation);
                    tvResult.setText(result);
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    public AMapLocationClientOption getLocationOption() {

        mLocationOption = new AMapLocationClientOption();//初始化AMapLocationClientOption对象
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        //高精度定位模式：会同时使用网络定位和GPS定位，优先返回最高精度的定位结果，以及对应的地址描述信息。
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        //低功耗定位模式：不会使用GPS和其他传感器，只会使用网络定位（Wi-Fi和基站定位）；
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        //仅用设备定位模式：不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位，自 v2.9.0 版本支持返回地址描述信息。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        mLocationOption.setInterval(2000);//设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。

        mLocationOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false

        mLocationOption.setNeedAddress(true);//设置是否返回地址信息（默认返回地址信息）

        mLocationOption.setHttpTimeOut(20000);//单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。

        mLocationOption.setMockEnable(false);//设置是否允许模拟位置,默认为false，不允许模拟位置
        return mLocationOption;

    }

    @OnClick(R.id.btn_startLocation)
    public void onClick(View view) {
        if (btnStartLocation.getText().equals("开始定位")) {
            btnStartLocation.setText("停止定位");
            //启动定位
            mLocationClient.startLocation();
        } else {
            mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
            tvResult.setText("等待定位...");
            btnStartLocation.setText("开始定位");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}
