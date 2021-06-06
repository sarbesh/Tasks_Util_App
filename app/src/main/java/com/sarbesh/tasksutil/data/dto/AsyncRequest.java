package com.sarbesh.tasksutil.data.dto;


import io.reactivex.Observable;

public class AsyncRequest<T> {
    private Observable<T> serviceCall;
    private T request;
    private Class<T> type;
    private Throwable throwable;

    public AsyncRequest(Observable<T> serviceCall) {
        this.serviceCall = serviceCall;
    }

    public AsyncRequest(Observable<T> serviceCall, T request) {
        this.serviceCall = serviceCall;
        this.request = request;
    }

    public AsyncRequest(Observable<T> serviceCall, T request, Class<T> type) {
        this.serviceCall = serviceCall;
        this.request = request;
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public Observable<T> getServiceCall() {
        return serviceCall;
    }

    public void setServiceCall(Observable<T> serviceCall) {
        this.serviceCall = serviceCall;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String toString() {
        return "AsyncRequest{" +
                "serviceCall=" + serviceCall +
                ", throwable=" + throwable +
                '}';
    }
}
