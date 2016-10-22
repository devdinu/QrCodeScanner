package com.events.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ScanActivity extends AppCompatActivity {
    View scanQrView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        scanQrView = findViewById(R.id.scan_qr_button);
        scanQrView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQrCode();
            }
        });
    }

    public void scanQrCode() {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String uuid = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Log.i("Scan!!", "contents: " + uuid + " format: " + format);
                checkinUser(uuid);
            } else if (resultCode == RESULT_CANCELED) {
                Log.i("Scan!!", "Cancelled");
            }
        }
    }

    private void checkinUser(String uuid) {
        try {
            Response<ResponseBody> execute = ApiProvider.getServiceApi().checkIn(uuid).execute();
            if (execute.code() == 200)
                Log.i("!!Scan!!", "Success");
            else
                Log.i("!!Scan!!", "Invalid User");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("!!Scan!!", "OMG@!!!!");
        }
    }
}
