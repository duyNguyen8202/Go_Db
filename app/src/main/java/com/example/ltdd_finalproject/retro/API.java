package com.example.ltdd_finalproject.retro;
import com.example.ltdd_finalproject.models.Account;
import com.example.ltdd_finalproject.models.Booking;
import com.example.ltdd_finalproject.models.LoginResponse;
import com.example.ltdd_finalproject.models.ProfileResponse;
import com.example.ltdd_finalproject.models.RegisterResponse;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @FormUrlEncoded
    @POST("admin/account?action=login")
    Call<LoginResponse> login(@Field("username") String username,
                              @Field("password") String password);
//    @GET("items")
//    Call<List<>> getItems();
        @FormUrlEncoded
        @POST("admin/account?action=signupcustomer")
        Call<RegisterResponse> registerCustomer(@Field("username") String username,
                                                @Field("full_name") String full_name,
                                                @Field("email") String email,
                                                @Field("phone_number") String phone_number,
                                                @Field("image_link") String image_link,
                                                @Field("address") String address,
                                                @Field("gender") boolean gender,
                                                @Field("birth_day") String birth_day,
                                                @Field("acc_password") String acc_password);
    @FormUrlEncoded
    @POST("admin/account?action=profile")
    Call<ProfileResponse> getProfile (@Field("username") String username);
    @GET("admin/booking")
    Call<List<Booking>> getBooking();

}
