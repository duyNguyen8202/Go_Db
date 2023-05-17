package com.example.ltdd_finalproject.adapters;

import com.example.ltdd_finalproject.models.LoginResponse;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginAdapter {
    private API myAPI;
    public LoginAdapter() {
        Retrofit retrofit = RetrofitClient.getRetrofitLogin();
        myAPI = retrofit.create(API.class);
    }

    public void login(String username, String password, final LoginCallback callback) {
        Call<LoginResponse> call = myAPI.login(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null && loginResponse.isSuccess()) {
                        callback.onLoginSuccess(loginResponse.getMessage(), loginResponse.getAccount_type(), loginResponse.getUsername());
                    } else {
                        callback.onLoginFailure("Đăng nhập không thành công.");
                    }
                } else {
                    callback.onLoginFailure("Lỗi kết nối đăng nhập.");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onLoginFailure("Lỗi kết nối.");
                t.printStackTrace();
            }
        });
    }

    public interface LoginCallback {
        void onLoginSuccess(String message, String accountType, String username);

        void onLoginFailure(String error);
    }
}
