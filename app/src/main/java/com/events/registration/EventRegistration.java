package com.events.registration;

import com.events.registration.Models.CheckinResponse;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface EventRegistration {
    @Headers({"Accept: application/json" })

    @POST("qr_codes/{uuid}/checkin")
    Call<CheckinResponse> checkIn(@Path("uuid") String uuid);
}

