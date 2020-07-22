package com.weather.lite.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable {

    /**
     * adcode : 510723
     * name : 盐亭县
     * center : 105.391991,31.22318
     */

    @SerializedName("adcode")
    private String adcode;
    @SerializedName("name")
    private String name;
    @SerializedName("center")
    private String center;

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

}