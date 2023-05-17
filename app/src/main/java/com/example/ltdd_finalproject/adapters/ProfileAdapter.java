package com.example.ltdd_finalproject.adapters;

import com.example.ltdd_finalproject.models.LoginResponse;
import com.example.ltdd_finalproject.models.ProfileResponse;
import com.example.ltdd_finalproject.retro.API;
import com.example.ltdd_finalproject.retro.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileAdapter {
    private final API myAPI;

    public ProfileAdapter() {
        Retrofit retrofit = RetrofitClient.getRetrofitLogin();
        myAPI = retrofit.create(API.class);
    }

    public void profile(String username, final ProfileAdapter.ProfileCallback callback) {
        Call<ProfileResponse> call = myAPI.getProfile(username);
        call.enqueue(new Callback<ProfileResponse> ()
        {
            @Override
            public void onResponse( Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {
                    ProfileResponse profileResponse = response.body();
                    if (profileResponse != null && profileResponse.isSuccess()) {
                        callback.onProfileSuccess(profileResponse.getMessage(), profileResponse.getCustomer_id(),profileResponse.getFull_name(),
                                profileResponse.getEmail(),profileResponse.getPhone_number(),profileResponse.getImage_link(),profileResponse.getAddress()
                                , profileResponse.isGender(),profileResponse.getBirthday());
                    } else {
                        callback.onProfileFailure("Lấy thông tin không thành công.");
                    }
                } else {
                    callback.onProfileFailure("Lỗi kết nối lấy thông tin.");
                }
            }
            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                callback.onProfileFailure("Lỗi kết nối.");
                t.printStackTrace();
            }
        });
    }
    public interface ProfileCallback {
        void onProfileSuccess(String message, String customerId,String fullName, String email, String phoneNumber
                ,String imageLink, String address, boolean gender, String birthDay);

        void onProfileFailure(String error);
    }
}
