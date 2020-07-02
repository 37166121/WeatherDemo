package com.demo.weatherdemo.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 建议
 */
@Data
public class IndexBean implements Serializable {

    /**
     * detail : 天气热，到中午的时候您将会感到有点热，因此建议在午后较热时开启制冷空调。
     * info : 部分时间开启
     * name : 空调开启
     */
    private String detail;
    private String info;
    private String name;
}
