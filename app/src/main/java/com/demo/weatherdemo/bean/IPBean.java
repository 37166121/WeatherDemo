package com.demo.weatherdemo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class IPBean implements Serializable {

	/**
	 * status : 1
	 * info : OK
	 * infocode : 10000
	 * province : 重庆市
	 * city : 重庆市
	 * adcode : 500000
	 * rectangle : 106.2832832,29.36962828;106.8138242,29.7401968
	 */

	private String status;
	private String info;
	private String infocode;
	private String province;
	private String city;
	private String adcode;
	private String rectangle;
}