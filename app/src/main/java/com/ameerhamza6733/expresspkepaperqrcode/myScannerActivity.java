package com.ameerhamza6733.expresspkepaperqrcode;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.google.zxing.Result;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class myScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private String TAG="myScannerActivity";
    private boolean FlashLightStatus;
    private static final String SERVICE_ACTION = "android.support.customtabs.action.CustomTabsService";
    private static final String CHROME_PACKAGE = "com.android.chrome";

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
    private static boolean isChromeCustomTabsSupported(@NonNull final Context context) {
        Intent serviceIntent = new Intent(SERVICE_ACTION);
        serviceIntent.setPackage(CHROME_PACKAGE);
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentServices(serviceIntent, 0);
        return !(resolveInfos == null || resolveInfos.isEmpty());
    }
    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

      try

      {
          if(isChromeCustomTabsSupported(myScannerActivity.this))
          {
              CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
              CustomTabsIntent customTabsIntent = builder.build();
              customTabsIntent.launchUrl(myScannerActivity.this, Uri.parse(rawResult.getText()));
          }else {
              final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(rawResult.getText()));
             myScannerActivity.this.startActivity(intent);
          }
      }catch (Exception e)
      {
          DialogFragment newFragment = new FormateNOtSportedDialogFragment();
          newFragment.show(getSupportFragmentManager(), "missiles");

          e.printStackTrace();
      }
    }

}
