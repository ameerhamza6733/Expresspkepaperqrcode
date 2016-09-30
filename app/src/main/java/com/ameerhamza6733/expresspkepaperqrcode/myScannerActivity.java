package com.ameerhamza6733.expresspkepaperqrcode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class myScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private String TAG="myScannerActivity";
    private boolean FlashLightStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scanner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent= getIntent();
        if(intent!=null)
        {

         FlashLightStatus=   intent.getBooleanExtra("MY_FLASH_LIGHT_STATUS",true);
        }
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        if(FlashLightStatus)
        {
            mScannerView.setFlash(true);
        }
        mScannerView.startCamera();          // Start camera on resume

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

      try
      {
          CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
          CustomTabsIntent customTabsIntent = builder.build();
          customTabsIntent.launchUrl(myScannerActivity.this, Uri.parse(rawResult.getText()));
      }catch (Exception e)
      {
          e.printStackTrace();
      }
    }

}
