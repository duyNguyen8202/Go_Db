package com.example.ltdd_finalproject.retro;

import com.example.ltdd_finalproject.models.Account;
import com.example.ltdd_finalproject.models.AddTourResponse;
import com.example.ltdd_finalproject.models.Hotel;
import com.example.ltdd_finalproject.models.LoginResponse;
import com.example.ltdd_finalproject.models.Place;
import com.example.ltdd_finalproject.models.ProfileResponse;
import com.example.ltdd_finalproject.models.RegisterResponse;
import com.example.ltdd_finalproject.models.Room;
import com.example.ltdd_finalproject.models.Staff;
import com.example.ltdd_finalproject.models.Tour;
import com.example.ltdd_finalproject.models.Vehicle;
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
    Call<ProfileResponse> getProfile(@Field("username") String username);

    // getStaffId
    @FormUrlEncoded
    @POST("admin/account?action=getStaffId")
    Call<Staff> getStaff(@Field("username") String username);


    // booking
    @FormUrlEncoded
    @POST("admin/booking?action=search")
    Call<List<Booking>> getBooking(@Field("customer_id") String customer_id);

    @FormUrlEncoded
    @POST("admin/booking?action=addTour")
    Call<AddTourResponse> addTour(@Field("customer_id") String customer_id,
                                  @Field("tour_id") String tour_id,
                                  @Field("booking_date") String booking_date);

    @FormUrlEncoded
    @POST("admin/booking?action=addhotel")
    Call<AddTourResponse> addHotel(@Field("customer_id") String customer_id,
                                   @Field("hotel_id") String hotel_id,
                                   @Field("booking_date") String booking_date);

    @FormUrlEncoded
    @POST("admin/booking?action=addVehicle")
    Call<AddTourResponse> addVehicle(@Field("customer_id") String customer_id,
                                     @Field("vehicle_id") String vehicle_id,
                                     @Field("booking_date") String booking_date);


    // booking staff
    @POST("admin/booking?action=getforStaff")
    Call<List<Booking>> getUnconfirmedBooking();

    @POST("admin/booking?action=getforStaffUsed")
    Call<List<Booking>> getConfirmedBooking();

    @FormUrlEncoded
    @POST("admin/booking?action=updateBookingforStaff")
    Call<AddTourResponse> confirmBooking(@Field("booking_id") String booking_id,
                                         @Field("staff_id") String staff_id);


    // Staff
    ///// Đầu tiên xem tour
    @GET("admin/tours")
    Call<List<Tour>> getTours();

    ///// Xem Hotel
    @GET("admin/hotel")
    Call<List<Hotel>> getHotels();

    ///// Xem Hotel
    @GET("admin/rooms")
    Call<List<Room>> getRooms();

    ///// Xem Hotel
    @GET("admin/vehicles")
    Call<List<Vehicle>> getVehicles();

    ///// Xem Hotel
    @GET("admin/places")
    Call<List<Place>> getPlaces();
}
