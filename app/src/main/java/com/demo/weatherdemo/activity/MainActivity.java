package com.demo.weatherdemo.activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.demo.weatherdemo.AppConfig;
import com.demo.weatherdemo.R;
import com.bumptech.glide.Glide;
import com.demo.weatherdemo.adapter.AllinfoAdapter;
import com.demo.weatherdemo.bean.AllInfoBean;
import com.demo.weatherdemo.service.GetAllInfoService;

import java.net.MalformedURLException;
import java.net.URL;

import lombok.SneakyThrows;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private ImageView background;
    private Toolbar toolbar;
    private RecyclerView info;
    private AllInfoBean allInfoBean;
    private Intent intent;
    private GetAllInfoReceiver getAllInfoReceiver;
    private boolean setview = true;
    private AppConfig appConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setFullscreen(true,true);
        intent = new Intent(this, GetAllInfoService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }

    @Override
    void initView(){
        background = findViewById(R.id.background);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setY((float)getStatusBarHeight() / 2);
        info = findViewById(R.id.info);
    }

    @Override
    void initData(){
        appConfig = (AppConfig)getApplication();
        setBackground();
        allInfoBean = new AllInfoBean();
        //动态注册广播接收器
        getAllInfoReceiver = new GetAllInfoReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.communication.RECEIVER");
        registerReceiver(getAllInfoReceiver, intentFilter);
    }

    @Override
    int getLayout () {
        return R.layout.activity_main;
    }

    public void setFullscreen(boolean isShowStatusBar, boolean isShowNavigationBar) {
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        if (!isShowStatusBar) {
            uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        if (!isShowNavigationBar) {
            uiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        //专门设置一下状态栏导航栏背景颜色为透明，凸显效果。
        setNavigationStatusColor(Color.TRANSPARENT);
    }

    public void setNavigationStatusColor(int color) {
        //VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setNavigationBarColor(color);
        getWindow().setStatusBarColor(color);
    }

    /**
     * 广播接收器
     * @author len
     */
    public class GetAllInfoReceiver extends BroadcastReceiver {
        @Override
        @SneakyThrows
        public void onReceive(Context context, Intent intent) {
            //拿到数据，更新UI
            allInfoBean = (AllInfoBean) intent.getSerializableExtra("allInfoBean");
            if (setview){
                setView();
                setview = false;
            }
        }
    }

    void setView(){
        GridLayoutManager layoutManager = new GridLayoutManager(this,6);
        AllinfoAdapter adapter = new AllinfoAdapter(allInfoBean,this);
        info.setLayoutManager(layoutManager);
        info.setAdapter(adapter);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize (int position) {
                switch (position){
                    case AllinfoAdapter.REALTIME:
                    case AllinfoAdapter.HOUR:
                    case AllinfoAdapter.DAY:
                    case AllinfoAdapter.INFO:
                    case AllinfoAdapter.SUN:
                    case AllinfoAdapter.TIPS:
                    case AllinfoAdapter.ABOUT:
                        return 6;
                    default:
                        return 3;
                }
            }
        });
    }

    void setBackground(){
        try {
            URL url = new URL("https://mat1.gtimg.com/pingjs/ext2020/weather/2017/images/bg-cb2b2552e8.jpg");
            Glide.with(this)
                    .load(url)
                    .into(background);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    int getStatusBarHeight(){
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    private int getNavigationBarHeight() {
        int resourceId = getResources().getIdentifier("navigation_bar_height","dimen", "android");
        int height = getResources().getDimensionPixelSize(resourceId);
        return height;
    }
}