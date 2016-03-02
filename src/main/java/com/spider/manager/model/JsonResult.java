package com.spider.manager.model;

/**
 * Created by wsy on 2015/9/1.
 */
public class JsonResult {

    public static final JsonResult SUCCESS = new JsonResult(0, "success");

    private String desc;

    private Integer value;

    public String getDesc() {

        return desc;
    }

    public Integer getValue() {

        return value;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public void setValue(Integer value) {

        this.value = value;
    }

    public JsonResult(Integer value, String desc) {

        this.value = value;
        this.desc = desc;
    }

    /**
     * 是否是success的结果
     *
     * @return
     */
    public boolean isSuccess() {

        return value == 0;
    }
}
