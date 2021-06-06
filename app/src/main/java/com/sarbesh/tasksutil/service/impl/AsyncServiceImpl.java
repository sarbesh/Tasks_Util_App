package com.sarbesh.tasksutil.service.impl;

import android.util.Log;

import com.sarbesh.tasksutil.data.dto.AsyncRequest;
import com.sarbesh.tasksutil.data.dto.AsyncResponse;
import com.sarbesh.tasksutil.service.AsyncService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.sarbesh.tasksutil.util.Constants.FAILED;
import static com.sarbesh.tasksutil.util.Constants.SUCCESS;

public class AsyncServiceImpl implements AsyncService {

    @Override
    public Map<String, AsyncResponse> processAsyncRequest(Map<String, AsyncRequest> requestMap) {
        CountDownLatch countDownLatch = new CountDownLatch(requestMap.size());
        Map<String, AsyncResponse> responseMap = new ConcurrentHashMap<>();
        for (Map.Entry<String, AsyncRequest> entry : requestMap.entrySet()) {
            try {
                invokeCall(entry.getValue(), responseMap, entry.getKey(), countDownLatch);
            } catch (Exception e) {
                Log.e("#processAsyncRequest", "request error occurred " + e.getMessage());
                countDownLatch.countDown();
            }
        }

        Log.i("#processAsyncRequest", "Entering countDownLatch wait :" + countDownLatch.getCount());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Log.e("#processAsyncRequest", "Exception in countDownLatch.await() " + e.getMessage());
        }
        Log.i("#processAsyncRequest", "Exiting countDownLatch wait :" + countDownLatch.getCount());

        return responseMap;
    }

    private <T> void invokeCall(AsyncRequest<T> asyncRequest, Map<String, AsyncResponse> responseMap, String key, CountDownLatch countDownLatch) {
        Class<T> type = asyncRequest.getType();
        Log.d("#invokeCall Type:", type.getName());
        responseMap.put(key, new AsyncResponse<T>(FAILED));
        Observable<T> observable = asyncRequest.getServiceCall().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Disposable disposable = observable.subscribe(
                (T result) -> {
                    Log.d("#invokeCall", "Got response for " + key + " Response: " + result.toString() + ", CountDownLatch : " + countDownLatch.getCount());
                    responseMap.put(key, new AsyncResponse<>(SUCCESS, result));
                    countDownLatch.countDown();
                }, (Throwable e) -> {
                    Log.e("#invokeCall", "Error : " + e.getMessage() + ", CountDownLatch :" + countDownLatch.getCount());
                    responseMap.put(key, new AsyncResponse<T>(FAILED, e));
                    countDownLatch.countDown();
                });
    }
}
