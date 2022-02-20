package com.sarbesh.tasksutil.data;

import android.util.Log;

import com.sarbesh.tasksutil.data.model.LoggedInUser;
import com.sarbesh.tasksutil.data.model.LoginRequest;
import com.sarbesh.tasksutil.data.model.LoginResponse;
import com.sarbesh.tasksutil.service.LoginService;
import com.sarbesh.tasksutil.util.ApiClient;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private LoggedInUser loggedInUser;

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            /** Create handle for the RetrofitInstance interface*/
            LoginService service = ApiClient.getLoginService();

            /** Call the method with parameter in the interface to get the data*/
            Call<LoginResponse> loginResponseCall = service.getAuthToken(new LoginRequest(username, password));
            CountDownLatch countDownLatch = new CountDownLatch(1);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Log.d("#Login", "Result: " + response);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            loggedInUser = new LoggedInUser(response.body());
                        }
                    } else {
                        Log.e("#Login", "error: " + (response.body() != null ? response.body() : null));
                    }
                    countDownLatch.countDown();
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.e("#Login", "Exception at subscription");
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                Log.e("#Login", "Exception in countDownLatch.await() " + e.getMessage());
            }
//            AsyncService asyncService = new AsyncServiceImpl();
//
//            HashMap<String, AsyncRequest> requestMap = new HashMap<>();
//            requestMap.put("login",new AsyncRequest<>(loginResponseCall, new LoginResponse(), LoginResponse.class));
//
//            Map<String, AsyncResponse> responseMap = asyncService.processAsyncRequest(requestMap);
//            AsyncResponse loginResponse = responseMap.get("login");
//            try{
//                Response<LoginResponse> result = loginResponseCall.execute();
//                if (result.isSuccessful()){
//                    Log.i("#Login","Login Success"+result);
//                    loggedInUser = new LoggedInUser(result.body());
//                } else {
//                    Log.e("#Login","Error logging in "+result.body().getDetail());
//                }
//            } catch (Exception ex){
//                Log.e("#Login","Exception ex: "+ex.getMessage());
//            }

            if (this.loggedInUser != null) {
                Log.d("#LoginDataSource: ", "success loggedInUser is not null: " + loggedInUser.getDisplayName());
                return new Result.Success<>(this.loggedInUser);
            } else {
                Log.e("#LoginDataSource: ", "Error loggedInUser is null");
                return new Result.Error(new RuntimeException("Error while login"));
            }
        } catch (Exception e) {
            Log.e("#LoginDataSource: ", "Error logging in" + e);
            return new Result.Error(new IOException(" Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}