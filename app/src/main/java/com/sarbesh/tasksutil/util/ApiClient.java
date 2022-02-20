package com.sarbesh.tasksutil.util;

import com.sarbesh.tasksutil.service.LoginService;

public class ApiClient {

//    private static final String BASE_URL = "https://e879fc2c-da58-4b12-ba30-c72a86c59339.mock.pstmn.io/";
    private static final String BASE_URL = "http://192.168.0.10:8080/";
//    private static final String BASE_URL = "https://sarbesh.pythonanywhere.com/";

    public static LoginService getLoginService() {
        return RetrofitInstance.getRetrofitInstance(BASE_URL).create(LoginService.class);
    }
}
