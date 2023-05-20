package com.example.ltdd_finalproject.adapters;

import com.example.ltdd_finalproject.models.RegisterResponse;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterAdapter {
    private final API myAPI;

    public RegisterAdapter() {
        Retrofit retrofit = RetrofitClient.getRetrofitRegister();
        myAPI = retrofit.create(API.class);
    }
    public void registerCustomer(String username, String full_name, String email, String phone_number, String image_link,
                                 String address, boolean gender, String birth_day, String password, final RegisterCallback callback) {
        Call<RegisterResponse> call = myAPI.registerCustomer(username, full_name, email, phone_number, image_link, address, gender, birth_day, password);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && registerResponse.isSuccess()) {
                        callback.onRegisterSuccess(registerResponse.getMessage());
                    } else {
                        callback.onRegisterFailure("Đăng ký không thành công.");
                    }
                } else {
                    callback.onRegisterFailure("Lỗi kết nối khi đăng ký.");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onRegisterFailure("Lỗi kết nối.");
                t.printStackTrace();
            }
        });
    }

    public interface RegisterCallback {
        void onRegisterSuccess(String message);

        void onRegisterFailure(String error);
    }
}
