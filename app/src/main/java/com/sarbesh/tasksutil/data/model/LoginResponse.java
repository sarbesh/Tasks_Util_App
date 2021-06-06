package com.sarbesh.tasksutil.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("access")
    @Expose
    private String access;
    @SerializedName("refresh")
    @Expose
    private String refresh;
    @SerializedName("detail")
    @Expose
    private String detail;

    public LoginResponse(String access, String refresh) {
        this.access = access;
        this.refresh = refresh;
    }

    public LoginResponse(String detail) {
        this.detail = detail;
    }

    public LoginResponse() {
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
}
