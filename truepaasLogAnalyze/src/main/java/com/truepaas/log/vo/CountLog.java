package com.truepaas.log.vo;

/**
 * Created by mas on 2016/11/9.
 */
public class CountLog {
    private String cf_app_id;
    private String cf_app_name;
    private String cf_org_id;

    public String getCf_app_id() {
        return cf_app_id;
    }

    public void setCf_app_id(String cf_app_id) {
        this.cf_app_id = cf_app_id;
    }

    public String getCf_app_name() {
        return cf_app_name;
    }

    public void setCf_app_name(String cf_app_name) {
        this.cf_app_name = cf_app_name;
    }

    public String getCf_org_id() {
        return cf_org_id;
    }

    public void setCf_org_id(String cf_org_id) {
        this.cf_org_id = cf_org_id;
    }

    public String getCf_space_id() {
        return cf_space_id;
    }

    public void setCf_space_id(String cf_space_id) {
        this.cf_space_id = cf_space_id;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public Object getIndicator_value() {
        return indicator_value;
    }

    public void setIndicator_value(Object indicator_value) {
        this.indicator_value = indicator_value;
    }

    public String getIndicator_desc() {
        return indicator_desc;
    }

    public void setIndicator_desc(String indicator_desc) {
        this.indicator_desc = indicator_desc;
    }

    private String cf_space_id;
    private String indicator;
    private Object indicator_value;
    private String indicator_desc;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;
}
