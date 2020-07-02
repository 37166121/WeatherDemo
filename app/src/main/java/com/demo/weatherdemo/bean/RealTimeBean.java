package com.demo.weatherdemo.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 即时天气
 */
@Data
public class RealTimeBean implements Serializable {

    /**
     * degree : 31
     * humidity : 64
     * precipitation : 0.0
     * pressure : 971
     * update_time : 202006131425
     * weather : 阴
     * weather_code : 02
     * weather_short : 阴
     * wind_direction : 7
     * wind_power : 2
     */

    private String degree;
    private String humidity;
    private String precipitation;
    private String pressure;
    private String update_time;
    private String weather;
    private String weather_code;
    private String weather_short;
    private String wind_direction;
    private String wind_power;
}
