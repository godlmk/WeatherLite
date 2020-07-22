package com.weather.lite.model;

import com.google.gson.annotations.SerializedName;

public class Index {

    /**
     * name : 紫外线指数
     * level : 5
     * category : 很强
     * text : 紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。
     */

    @SerializedName("name")
    private String name;
    @SerializedName("level")
    private String level;
    @SerializedName("category")
    private String category;
    @SerializedName("text")
    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
