package com.demo.weatherdemo.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 24小时天气预报
 */
@Data
public class DayBean implements Serializable {

    /**
     * day_weather : 天气
     * day_weather_code : 天气图标
     * day_weather_short : 中雨
     * day_wind_direction : 风向
     * day_wind_direction_code : 风向图标
     * day_wind_power : 风力
     * day_wind_power_code : 风力图标
     * max_degree : 最高气温
     * min_degree : 最低气温
     * night_weather : 晚上天气
     * night_weather_code : 天气图标
     * night_weather_short : 小雨
     * night_wind_direction : 风向
     * night_wind_direction_code : 风向图标
     * night_wind_power : 风力
     * night_wind_power_code : 风力图标
     * time : 日期
     */

    private String day_weather;
    private String day_weather_code;
    private String day_weather_short;
    private String day_wind_direction;
    private String day_wind_direction_code;
    private String day_wind_power;
    private String day_wind_power_code;
    private String max_degree;
    private String min_degree;
    private String night_weather;
    private String night_weather_code;
    private String night_weather_short;
    private String night_wind_direction;
    private String night_wind_direction_code;
    private String night_wind_power;
    private String night_wind_power_code;
    private String time;
}
