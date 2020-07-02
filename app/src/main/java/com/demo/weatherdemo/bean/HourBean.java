package com.demo.weatherdemo.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 1小时天气预报
 */
@Data
public class HourBean implements Serializable {

    /**
     * degree : 气温
     * update_time : 时间戳
     * weather : 天气
     * weather_code : 天气图标
     * weather_short : 小雨
     * wind_direction : 西北风
     * wind_power : 5
     */

    private String degree;
    private String update_time;
    private String weather;
    private String weather_code;
    private String weather_short;
    private String wind_direction;
    private String wind_power;
}
