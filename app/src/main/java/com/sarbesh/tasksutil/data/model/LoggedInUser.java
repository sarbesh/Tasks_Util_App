package com.sarbesh.tasksutil.data.model;

import com.auth0.android.jwt.JWT;

import static com.sarbesh.tasksutil.util.Constants.USERNAME;
import static com.sarbesh.tasksutil.util.Constants.USER_ID;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private final String userId;
    private final String displayName;
    private String accessToken;
    private String refreshToken;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public LoggedInUser(LoginResponse loginResponse) {
        this.accessToken = loginResponse.getAccess();
        this.refreshToken = loginResponse.getAccess();
        JWT jwt = new JWT(accessToken);
        this.userId = jwt.getClaim(USER_ID).asString();
        this.displayName = jwt.getClaim(USERNAME).asString();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}