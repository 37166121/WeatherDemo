package com.demo.weatherdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.weatherdemo.AppConfig;
import com.demo.weatherdemo.R;
import com.demo.weatherdemo.bean.IPBean;
import com.demo.weatherdemo.util.JsonUtil;
import com.demo.weatherdemo.util.NetworkUtil;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EnterActivity extends BaseActivity {
	private static final String TAG = "EnterActivity";
	private String[] loadingValue = {"",".","..","...",};
	private TextView loading;
	private AppConfig appConfig;
	private String result;
	private IPBean ip = new IPBean();
	//    定时器
	private static int TIME = 0;
	private ScheduledExecutorService mService = Executors.newScheduledThreadPool(60);
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter);
		initView();
		initData();
		//等待时间一秒，停顿时间一秒
		mService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// UI thread
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						loading.setText(loadingValue[TIME++ % 4]);
					}
				});
			}
		}, 1, 1, TimeUnit.SECONDS);
	}

	@Override
	void initView(){
		loading = findViewById(R.id.loading);
	}

	@Override
	void initData(){
		appConfig = (AppConfig) getApplication();
		NetworkThrea networkThrea = new NetworkThrea(this);
		networkThrea.start();
	}

	@Override
	int getLayout () {
		return R.layout.activity_enter;
	}

	@Override
	protected void onDestroy () {
		super.onDestroy();
		mService.shutdown();
	}

	public void getNetIp() {
		/**
		 * 获取公网IP
		 * http://www.3322.org/dyndns/getip
		 * 高德地图根据IP地址获取城市
		 * http://restapi.amap.com/v3/ip?key=ff2f1fee5ae848c4caa63a668769916e
		 */
		appConfig.setIp(getUrlInfo("http://www.3322.org/dyndns/getip"));
		String json = getUrlInfo("http://restapi.amap.com/v3/ip?key=ff2f1fee5ae848c4caa63a668769916e&ip=" + appConfig.getIp());
		appConfig.setIpBean(new JsonUtil().getIpInfo(JSON.parseObject(json)));
	}

	private String getUrlInfo (String urls){
		String responseData = null;
		try {
			URL url = new URL(urls);
			OkHttpClient client = new OkHttpClient();    //创建OkHClient实例
			Request request = new Request.Builder()     //发请求创建一个Request对象
					.url(url)
					.build();
			Response response = client.newCall(request).execute();  //发请求获取服务器返回的数据
			responseData = Objects.requireNonNull(response.body()).string();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseData;
	}

	private class NetworkThrea extends Thread{
		private Context context;
		NetworkThrea(Context context){
			this.context = context;
		}
		@Override
		public void run () {
			super.run();
			if (NetworkUtil.isNetworkAvailable(context)){
				getNetIp();
				Intent intent = new Intent(context,MainActivity.class);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(context,"请连接网络后重试",Toast.LENGTH_SHORT).show();
			}
		}
	}
}