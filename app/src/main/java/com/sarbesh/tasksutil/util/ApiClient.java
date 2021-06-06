package com.sarbesh.tasksutil.util;

import com.sarbesh.tasksutil.service.LoginService;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.0.10:8080/";
//    private static final String BASE_URL = "https://sarbesh.pythonanywhere.com/";

    public static LoginService getLoginService() {
        return RetrofitInstance.getRetrofitInstance(BASE_URL).create(LoginService.class);
    }
}
