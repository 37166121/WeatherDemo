package com.demo.weatherdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.weatherdemo.AppConfig;
import com.demo.weatherdemo.bean.AllInfoBean;
import com.demo.weatherdemo.util.JsonUtil;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetAllInfoService extends Service {
    public static final String TAG="GetAllInfoService";
    private GetAllInfoThread thread;
    private AllInfoBean allInfoBean;
    private AppConfig appConfig;
    private JSONObject json;
    private Intent intent = new Intent("com.example.communication.RECEIVER");
    @Override
    public void onCreate() {
        Log.e(TAG, "--------onCreate--------");
        appConfig = (AppConfig) getApplication();
        thread = new GetAllInfoThread();
        thread.start();
    }

    public class GetAllInfoThread extends Thread{
        private volatile boolean stop = true;
        @SneakyThrows
        @Override
        public void run() {
            super.run();
            //获取天气信息
            allInfoBean = new JsonUtil().getAllInfo(getJson("https://wis.qq.com/weather/common?source=pc&weather_type=observe%7Cforecast_1h%7Cforecast_24h%7Cindex%7Calarm%7Climit%7Ctips%7Crise&province=" + appConfig.getIpBean().getProvince() + "&city=" + appConfig.getIpBean().getCity() + "&county="));
            allInfoBean.setAirBean(new JsonUtil().getAir(getJson("https://wis.qq.com/weather/common?source=pc&weather_type=air%7Crise&province=%E9%87%8D%E5%BA%86&city=%E9%87%8D%E5%BA%86&callback=")));
            allInfoBean.setIpBean(appConfig.getIpBean());
            intent.putExtra("allInfoBean", allInfoBean);
            sendBroadcast(intent);
        }

        public void stopThread(){
            stop = false;
        }
    }

    private class GetJsonThread extends Thread{
        private String url;
        GetJsonThread(String url){
            this.url = url;
        }
        @Override
        public void run () {
            super.run();
            try {
                do {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(5000, TimeUnit.SECONDS) //连接超时
                            .build();    //创建OkHClient实例
                    Request request = new Request.Builder()     //发请求创建一个Request对象
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();  //发请求获取服务器返回的数据
                    String responseData = response.body().string();
                    json = JSON.parseObject(responseData);
                } while (json.getJSONObject("data") == null || json.getJSONObject("data").isEmpty());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    private JSONObject getJson(String url){
        Log.e(TAG,url);
        GetJsonThread getJsonThread = new GetJsonThread(url);
        getJsonThread.start();
        getJsonThread.join();
        Log.e(TAG,json.getJSONObject("data").toString());
        return json.getJSONObject("data");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "--------onStartCommand--------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "--------onDestroy--------");
        thread.stopThread();
    }
}