package com.events.registration;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface EventRegistration {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @POST("qr_codes/{uuid}/checkin")
    Call<ResponseBody> checkIn(@Path("uuid") String uuid);
}

