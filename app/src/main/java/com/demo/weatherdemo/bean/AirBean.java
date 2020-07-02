package com.demo.weatherdemo.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import lombok.Data;

/**
 * 空气质量
 */
@Data
public class AirBean implements Serializable {

    /**
     * aqi : 空气质量值
     * aqi_level : 等级
     * aqi_name : 等级名
     * co : 0.7
     * no2 : 24
     * o3 : 142
     * pm10 : 47
     * pm2.5 : 29
     * so2 : 6
     * update_time : 时间戳
     */

    private int aqi;
    private int aqi_level;
    private String aqi_name;
    private String co;
    private String no2;
    private String o3;
    private String pm25;
    private String pm10;
    private String so2;
    private String update_time;
}
