package com.truepaas.log.vo;
/**
 * Created by mas on 2016/10/9.
 */
public class HttpLog {
    private String cf_app_id;
    private String cf_app_name;
    private String cf_org_id;
    private String cf_org_name;
    private String cf_space_id;
    private String cf_space_name;

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }

    public String getInstance_index() {
        return instance_index;
    }

    public void setInstance_index(String instance_index) {
        this.instance_index = instance_index;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }


    private String uri;
    private String instance_id;
    private String instance_index;
    private String ip;
    private String user_agent;
    private String start_timestamp;

    public String getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(String start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public String getStop_timestamp() {
        return stop_timestamp;
    }

    public void setStop_timestamp(String stop_timestamp) {
        this.stop_timestamp = stop_timestamp;
    }

    private String stop_timestamp;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    private String status_code;

    public String getRemote_addr() {
        return remote_addr;
    }

    public void setRemote_addr(String remote_addr) {
        this.remote_addr = remote_addr;
    }

    private String remote_addr;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    private String area;

}

