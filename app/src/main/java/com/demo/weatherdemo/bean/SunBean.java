package com.demo.weatherdemo.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 日升日落时间
 */
@Data
public class SunBean implements Serializable {

    /**
     * sunrise : 05:53
     * sunset : 19:54
     * time : 20200613
     */

    private String sunrise;
    private String sunset;
    private String time;
}
