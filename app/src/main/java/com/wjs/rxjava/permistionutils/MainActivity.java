package com.wjs.rxjava.permistionutils;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    PermitionUtils utils=new PermitionUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permission={WRITE_EXTERNAL_STORAGE,RECORD_AUDIO,READ_SMS,SEND_SMS};
        utils.getPermisstion(this,permission, new PermitionUtils.PermissionResult() {
            @Override
            public void permissionOk() {
                Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void permissionFaile() {
                Toast.makeText(MainActivity.this,"faile",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        utils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
