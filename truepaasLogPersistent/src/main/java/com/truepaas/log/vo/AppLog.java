package com.truepaas.log.vo;

/**
 * Created by mas on 2016/10/8.
 */
/**
 * Created by mas on 2016/10/8.
 */
public class AppLog {
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

    public String getCpu_percentage() {
        return cpu_percentage;
    }

    public void setCpu_percentage(String cpu_percentage) {
        this.cpu_percentage = cpu_percentage;
    }

    public String getDisk_bytes() {
        return disk_bytes;
    }

    public void setDisk_bytes(String disk_bytes) {
        this.disk_bytes = disk_bytes;
    }

    public String getDisk_bytes_quota() {
        return disk_bytes_quota;
    }

    public void setDisk_bytes_quota(String disk_bytes_quota) {
        this.disk_bytes_quota = disk_bytes_quota;
    }

    public String getMemory_bytes() {
        return memory_bytes;
    }

    public void setMemory_bytes(String memory_bytes) {
        this.memory_bytes = memory_bytes;
    }

    public String getMemory_bytes_quota() {
        return memory_bytes_quota;
    }

    public void setMemory_bytes_quota(String memory_bytes_quota) {
        this.memory_bytes_quota = memory_bytes_quota;
    }



    private String cf_space_name;
    private String cpu_percentage;
    private String disk_bytes;
    private String disk_bytes_quota;
    private String memory_bytes;
    private String memory_bytes_quota;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;
}
