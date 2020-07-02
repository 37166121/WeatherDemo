package com.demo.weatherdemo.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 所有信息
 */
@Data
public class AllInfoBean implements Serializable {
    private AirBean airBean;
    private AlarmBean alarmBean;
    private RealTimeBean realTimeBean;
    private IPBean ipBean;
    private List<HourBean> hourBeans;
    private List<DayBean> dayBeans;
    private List<IndexBean> indexBeans;
    private List<SunBean> sunBeans;
    private List<TipsBean> tipsBeans;
}
