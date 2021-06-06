package com.sarbesh.tasksutil.data.dto;

public class AsyncResponse<T> {
    private int statusCode;
    private String status;
    private T response;
    private Throwable throwable;

    public AsyncResponse(int statusCode, String status, T response) {
        this.statusCode = statusCode;
        this.status = status;
        this.response = response;
    }

    public AsyncResponse(String status, T response) {
        this.status = status;
        this.response = response;
    }

    public AsyncResponse(String status, Throwable throwable) {
        this.status = status;
        this.throwable = throwable;
    }

    public AsyncResponse(String status) {
        this.status = status;
    }

    public AsyncResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public T getType() {
        return this.response;
    }

    @Override
    public String toString() {
        return "AsyncResponse{" +
                "statusCode=" + statusCode +
                ", status='" + status + '\'' +
                ", response=" + response +
                ", throwable=" + throwable +
                '}';
    }
}

