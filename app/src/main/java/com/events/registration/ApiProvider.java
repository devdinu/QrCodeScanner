package com.events.registration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    public static EventRegistration getServiceApi() {
        String BASE_URL = BuildConfig.EventServiceUrl;
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(EventRegistration.class);
    }
}
