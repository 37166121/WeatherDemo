package com.demo.weatherdemo.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 建议
 */
@Data
public class TipsBean implements Serializable {

    /**
     * 0 : 天暗下来，你就是阳光~
     * 1 : 天太热了，吃个西瓜~
     */

    private String tip;
}
