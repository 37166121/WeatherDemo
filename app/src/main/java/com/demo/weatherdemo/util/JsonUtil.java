package com.demo.weatherdemo.util;

import com.alibaba.fastjson.JSONObject;
import com.demo.weatherdemo.annotation.BeanSetter;
import com.demo.weatherdemo.bean.AirBean;
import com.demo.weatherdemo.bean.AlarmBean;
import com.demo.weatherdemo.bean.AllInfoBean;
import com.demo.weatherdemo.bean.DayBean;
import com.demo.weatherdemo.bean.HourBean;
import com.demo.weatherdemo.bean.IPBean;
import com.demo.weatherdemo.bean.IndexBean;
import com.demo.weatherdemo.bean.RealTimeBean;
import com.demo.weatherdemo.bean.SunBean;
import com.demo.weatherdemo.bean.TipsBean;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public AllInfoBean getAllInfo(JSONObject json){
        AllInfoBean allInfoBean = new AllInfoBean();
        AlarmBean alarmBean = getAlarm(json.getJSONObject("alarm"));
        RealTimeBean realTimeBean = getRealTimeBean(json.getJSONObject("observe"));
        List<HourBean> hourBeans = getHours(json.getJSONObject("forecast_1h"));
        List<DayBean> dayBeans = getDays(json.getJSONObject("forecast_24h"));
        List<IndexBean> indexBeans = getIndexs(json.getJSONObject("index"));
        List<SunBean> sunBeans = getSuns(json.getJSONObject("rise"));
        List<TipsBean> tipsBeans = getTips(json.getJSONObject("tips").getJSONObject("observe"));
        allInfoBean.setAlarmBean(alarmBean);
        allInfoBean.setRealTimeBean(realTimeBean);
        allInfoBean.setHourBeans(hourBeans);
        allInfoBean.setDayBeans(dayBeans);
        allInfoBean.setIndexBeans(indexBeans);
        allInfoBean.setSunBeans(sunBeans);
        allInfoBean.setTipsBeans(tipsBeans);
        return allInfoBean;
    }
    
    @BeanSetter
    public AirBean getAir(JSONObject json){
        AirBean airBean = new AirBean();
        json = json.getJSONObject("air");
        airBean.setAqi(json.getInteger("aqi"));
        airBean.setAqi_level(json.getInteger("aqi_level"));
        airBean.setAqi_name(json.getString("aqi_name"));
        airBean.setCo(json.getString("co"));
        airBean.setNo2(json.getString("no2"));
        airBean.setO3(json.getString("o3"));
        airBean.setPm25(json.getString("pm25"));
        airBean.setPm10(json.getString("pm10"));
        airBean.setSo2(json.getString("so2"));
        airBean.setUpdate_time(json.getString("update_time"));
        return airBean;
    }

    @BeanSetter
    private AlarmBean getAlarm(JSONObject json){
        AlarmBean alarmBean = new AlarmBean();
        alarmBean.setCity(json.getString("city"));
        alarmBean.setCounty(json.getString("county"));
        alarmBean.setDetail(json.getString("detail"));
        alarmBean.setInfo(json.getString("info"));
        alarmBean.setLevel_code(json.getString("level_code"));
        alarmBean.setLevel_name(json.getString("level_name"));
        alarmBean.setProvince(json.getString("province"));
        alarmBean.setType_code(json.getString("type_code"));
        alarmBean.setType_name(json.getString("type_name"));
        alarmBean.setUpdate_time(json.getString("update_time"));
        alarmBean.setUrl(json.getString("url"));
        return alarmBean;
    }

    @BeanSetter
    private RealTimeBean getRealTimeBean(JSONObject json){
        RealTimeBean realTimeBean = new RealTimeBean();
        realTimeBean.setDegree(json.getString("degree"));
        realTimeBean.setHumidity(json.getString("humidity"));
        realTimeBean.setPrecipitation(json.getString("precipitation"));
        realTimeBean.setPressure(json.getString("pressure"));
        realTimeBean.setUpdate_time(json.getString("update_time"));
        realTimeBean.setWeather(json.getString("weather"));
        realTimeBean.setWeather_code(json.getString("weather_code"));
        realTimeBean.setWeather_short(json.getString("weather_short"));
        realTimeBean.setWind_direction(json.getString("wind_direction"));
        realTimeBean.setWind_power(json.getString("wind_power"));
        return realTimeBean;
    }

    @BeanSetter
    private List<HourBean> getHours(JSONObject json){
        List<HourBean> hourBeans = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            HourBean hourBean = new HourBean();
            hourBean.setDegree(json.getJSONObject(String.valueOf(i)).getString("degree"));
            hourBean.setUpdate_time(json.getJSONObject(String.valueOf(i)).getString("update_time"));
            hourBean.setWeather(json.getJSONObject(String.valueOf(i)).getString("weather"));
            hourBean.setWeather_code(json.getJSONObject(String.valueOf(i)).getString("weather_code"));
            hourBean.setWeather_short(json.getJSONObject(String.valueOf(i)).getString("weather_short"));
            hourBean.setWind_direction(json.getJSONObject(String.valueOf(i)).getString("wind_direction"));
            hourBean.setWind_power(json.getJSONObject(String.valueOf(i)).getString("wind_power"));
            hourBeans.add(hourBean);
        }
        return hourBeans;
    }

    @BeanSetter
    private List<DayBean> getDays(JSONObject json){
        List<DayBean> dayBeans = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            DayBean dayBean = new DayBean();
            dayBean.setDay_weather(json.getJSONObject(String.valueOf(i)).getString("day_weather"));
            dayBean.setDay_weather_code(json.getJSONObject(String.valueOf(i)).getString("day_weather_code"));
            dayBean.setDay_weather_short(json.getJSONObject(String.valueOf(i)).getString("day_weather_short"));
            dayBean.setDay_wind_direction(json.getJSONObject(String.valueOf(i)).getString("day_wind_direction"));
            dayBean.setDay_wind_direction_code(json.getJSONObject(String.valueOf(i)).getString("day_wind_direction_code"));
            dayBean.setDay_wind_power(json.getJSONObject(String.valueOf(i)).getString("day_wind_power"));
            dayBean.setDay_wind_power_code(json.getJSONObject(String.valueOf(i)).getString("day_wind_power_code"));
            dayBean.setMax_degree(json.getJSONObject(String.valueOf(i)).getString("max_degree"));
            dayBean.setMin_degree(json.getJSONObject(String.valueOf(i)).getString("min_degree"));
            dayBean.setNight_weather(json.getJSONObject(String.valueOf(i)).getString("night_weather"));
            dayBean.setNight_weather_code(json.getJSONObject(String.valueOf(i)).getString("night_weather_code"));
            dayBean.setNight_wind_direction(json.getJSONObject(String.valueOf(i)).getString("night_wind_direction"));
            dayBean.setNight_wind_direction_code(json.getJSONObject(String.valueOf(i)).getString("night_wind_direction_code"));
            dayBean.setNight_wind_power(json.getJSONObject(String.valueOf(i)).getString("night_wind_power"));
            dayBean.setNight_wind_power_code(json.getJSONObject(String.valueOf(i)).getString("night_wind_power_code"));
            dayBean.setTime(json.getJSONObject(String.valueOf(i)).getString("time"));
            dayBeans.add(dayBean);
        }
        return dayBeans;
    }

    @BeanSetter
    private List<IndexBean> getIndexs(JSONObject json){
        List<IndexBean> indexBeans = new ArrayList<>();
        for (int i = 0; i < json.size() - 1; i++) {
            IndexBean indexBean = new IndexBean();
            String[] jsonTitle = {"airconditioner","allergy","carwash","chill",
                    "clothes","cold","comfort","diffusion","dry",
                    "drying","fish","heatstroke","makeup","mood",
                    "morning","sports","sunglasses","sunscreen","tourism",
                    "traffic","ultraviolet","umbrella"};
//            String[] jsonValue = {"空调","过敏","洗车","寒冷",
//                     "衣服","冷","舒适","扩散","干燥",
//                     "烘干","鱼","中暑","化妆","情绪",
//                     "早上","运动","太阳镜","防晒霜","旅游",
//                     "交通","紫外线","雨伞"};
//            ideaBean.setTitle(jsonValue[i]);
            indexBean.setDetail(json.getJSONObject(jsonTitle[i]).getString("detail"));
            indexBean.setInfo(json.getJSONObject(jsonTitle[i]).getString("info"));
            indexBean.setName(json.getJSONObject(jsonTitle[i]).getString("name"));
            indexBeans.add(indexBean);
        }
        return indexBeans;
    }

    @BeanSetter
    private List<SunBean> getSuns(JSONObject json){
        List<SunBean> sunBeans = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            SunBean sunBean = new SunBean();
            sunBean.setSunrise(json.getJSONObject(String.valueOf(i)).getString("sunrise"));
            sunBean.setSunset(json.getJSONObject(String.valueOf(i)).getString("sunset"));
            sunBean.setTime(json.getJSONObject(String.valueOf(i)).getString("time"));
            sunBeans.add(sunBean);
        }
        return sunBeans;
    }

    @BeanSetter
    private List<TipsBean> getTips(JSONObject json){
        List<TipsBean> tipsBeans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TipsBean tipsBean = new TipsBean();
            tipsBean.setTip(json.getString(String.valueOf(i)));
            tipsBeans.add(tipsBean);
        }
        return tipsBeans;
    }

    @BeanSetter
    public IPBean getIpInfo(JSONObject json){
        IPBean ipBean = new IPBean();
        ipBean.setStatus(json.getString("status"));
        ipBean.setInfo(json.getString("info"));
        ipBean.setInfocode(json.getString("infocode"));
        ipBean.setProvince(json.getString("province"));
        ipBean.setCity(json.getString("city"));
        ipBean.setAdcode(json.getString("adcode"));
        ipBean.setRectangle(json.getString("rectangle"));
        return ipBean;
    }
}
