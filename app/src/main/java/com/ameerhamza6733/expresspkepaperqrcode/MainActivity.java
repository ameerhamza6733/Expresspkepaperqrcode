package com.ameerhamza6733.expresspkepaperqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton mImagebutoon;
    private Switch my_switch_flash_button;
    private boolean flash_light_status;
    private TextView myScanTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mImagebutoon = (ImageButton) findViewById(R.id.imageButton);
        my_switch_flash_button= (Switch) findViewById(R.id.my_flash_light_switch_button);
        myScanTextView= (TextView) findViewById(R.id.my_scan_text_view);
        setSupportActionBar(toolbar);


        my_switch_flash_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    flash_light_status=true;
                }else {
                    flash_light_status=false;
                }
                // do something, the isChecked will be
                // true if the switch is in the On position
            }
        });

        mImagebutoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,myScannerActivity.class);
                intent.putExtra("MY_FLASH_LIGHT_STATUS",flash_light_status);
                startActivity(intent);
            }
        });
        myScanTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,myScannerActivity.class);
                intent.putExtra("MY_FLASH_LIGHT_STATUS",flash_light_status);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,myScannerActivity.class);
                intent.putExtra("MY_FLASH_LIGHT_STATUS",flash_light_status);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this,"Comming soon",Toast.LENGTH_LONG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
