package com.demo.weatherdemo;

import android.app.Application;

import com.demo.weatherdemo.bean.IPBean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class AppConfig extends Application {
	@Setter	@Getter
	private String ip;
	@Setter	@Getter
	private IPBean ipBean;
	@Override
	public void onCreate(){
		super.onCreate();
	}
}