package com.sarbesh.tasksutil.service;

import com.sarbesh.tasksutil.data.dto.AsyncRequest;
import com.sarbesh.tasksutil.data.dto.AsyncResponse;

import java.util.Map;

public interface AsyncService {
    Map<String, AsyncResponse> processAsyncRequest(Map<String, AsyncRequest> requestMap);
}
