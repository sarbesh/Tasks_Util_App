package com.sarbesh.tasksutil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sarbesh.tasksutil.ui.login.LoginActivity;

import static com.sarbesh.tasksutil.util.Constants.ACCESS;
import static com.sarbesh.tasksutil.util.Constants.DEFAULT;
import static com.sarbesh.tasksutil.util.Constants.REFRESH;
import static com.sarbesh.tasksutil.util.Constants.SHARED_DATA_REGION;
import static com.sarbesh.tasksutil.util.Constants.USERNAME;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            sharedPreferences = getSharedPreferences(SHARED_DATA_REGION, MODE_PRIVATE);
            if (!sharedPreferences.contains(ACCESS) || !sharedPreferences.contains(REFRESH)) {
                String access = sharedPreferences.getString(ACCESS, DEFAULT);
                String refresh = sharedPreferences.getString(REFRESH, DEFAULT);
                loadLogin();
            } else {
                String welcome = getString(R.string.welcome) + sharedPreferences.getString(USERNAME, DEFAULT);
                Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Exception ex" + e);
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            loadLogin();
        }

    }

    //Call the login activity
    public void loadLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private boolean isMetered(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.isActiveNetworkMetered();
    }
}
