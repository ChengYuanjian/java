package com.truepaas.log.vo;

/**
 * Created by mas on 2016/10/9.
 */
/**
 * Created by mas on 2016/10/9.
 */
public class OperLog {
    private String cf_app_id;
    private String cf_app_name;
    private String cf_org_id;
    private String cf_org_name;
    private String cf_space_id;

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

    public String getCf_org_name() {
        return cf_org_name;
    }

    public void setCf_org_name(String cf_org_name) {
        this.cf_org_name = cf_org_name;
    }

    public String getCf_space_id() {
        return cf_space_id;
    }

    public void setCf_space_id(String cf_space_id) {
        this.cf_space_id = cf_space_id;
    }

    public String getCf_space_name() {
        return cf_space_name;
    }

    public void setCf_space_name(String cf_space_name) {
        this.cf_space_name = cf_space_name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String cf_space_name;
    private String msg;
    private String time;
}
