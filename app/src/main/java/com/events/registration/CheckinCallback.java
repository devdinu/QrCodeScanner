package com.events.registration;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.events.registration.Models.CheckinResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CheckinCallback implements Callback<CheckinResponse> {

    private final String LOG_TAG;
    private Context context;

    public CheckinCallback(Context context) {
        this.context = context;
        LOG_TAG = this.getClass().getName();
    }

    @Override
    public void onResponse(Call<CheckinResponse> call, Response<CheckinResponse> response) {
        String message;
        CheckinResponse serverResponse = response.body();
        if (response.code() == 200) {
            message = String.format("Hi %s, Welcome To %s",
                    serverResponse.getParticipant(), serverResponse.getEvent());
        } else {
            message = "Invalid User";
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        Log.i(LOG_TAG, message);
    }

    @Override
    public void onFailure(Call<CheckinResponse> call, Throwable t) {
        Log.e(LOG_TAG, "Service Hitting Failed", t);
    }

}
