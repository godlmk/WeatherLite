package com.weather.lite.util;

/**
 * 全局数据池
 */
public class Common {

    // 权限请求码
    public static final int REQ_CODE_PERMISSION = 100;

    // 网络返回同一实体字段名
    public static final String DATA_CODE = "code";
    public static final String DATA_MSG = "msg";
    public static final String DATA_DATA = "data";

    // 网络请求状态码
    public static final int CODE_SUCCESS = 200;// 成功
    public static final int CODE_TOKEN = 401;// Token失效
    public static final int CODE_NET = -1;// 网络异常
    public static final int CODE_DATA = -2;// 将数据解析异常

    // 跳转登录标志 (跳转登录页时置为true, 登录成功后置为false)
    public static boolean isStartLogin = false;

    // 高德地图Web API KEY
    public static final String AMAP_WEB_KEY = "36984afb1f6f7b7ca1808cabf60564b8";

    // 地图选点请求码
    public static final int REQ_CHOOSE_POSITION = 1000;

    // 选择城市请求码
    public static final int REQ_CHOOSE_CITY = 2000;

    // 和风天气 KEY
    public static final String WEATHER_KEY = "5549377f0d7449b2bd1e891efdff21cb";



}