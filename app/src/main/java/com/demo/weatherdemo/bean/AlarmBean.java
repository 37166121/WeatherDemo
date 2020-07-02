package com.demo.weatherdemo.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 警报
 */
@Data
public class AlarmBean implements Serializable {

    /**
     * city :
     * county :
     * detail : 重庆市气象台2020年06月13日17时发布“暴雨黄色预警信号”，预计13日17时到14日08时我市黔江、涪陵、南川、綦江、武隆、丰都、忠县、垫江、石柱、秀山、酉阳、彭水、万盛等地的部分地区雨量将达50mm以上，局地伴有雷电、阵性大风等强对流天气。【重庆市气象局】（预警信息来源：国家预警信息发布中心）
     * info : 202006131723575162暴雨黄色
     * level_code : 02
     * level_name : 黄色
     * province : 重庆市
     * type_code : 02
     * type_name : 暴雨
     * update_time : 2020-06-13 17:23
     * url : 10104-20200613172300-0202.html
     */

    private String city;
    private String county;
    private String detail;
    private String info;
    private String level_code;
    private String level_name;
    private String province;
    private String type_code;
    private String type_name;
    private String update_time;
    private String url;
}
