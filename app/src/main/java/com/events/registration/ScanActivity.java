package com.events.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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
                checkinUser(uuid);
            } else if (resultCode == RESULT_CANCELED) {
                Log.i("Scan!!", "Cancelled");
            }
        }
    }

    private void checkinUser(String uuid) {
        ApiProvider
                .getServiceApi()
                .checkIn(uuid)
                .enqueue(new CheckinCallback(getApplicationContext()));
    }
}
