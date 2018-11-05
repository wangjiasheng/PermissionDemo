package com.wjs.rxjava.permistionutils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import java.util.Calendar;
import java.util.UUID;

public class PermitionUtils {
    private int BAIDU_READ_PHONE_STATE;
    private PermissionResult result;
    private Activity context;
    private String[] permission;
    public interface PermissionResult{
        void permissionOk();
        void permissionFaile();
    }
    public void getPermisstion(Activity context,String[] permission,PermissionResult result){
        this.BAIDU_READ_PHONE_STATE=result.hashCode();
        this.result=result;
        this.context=context;
        this.permission=permission;
        requestPermission();
    }
    public void requestPermission(){
        if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
            boolean hasPermission=true;
            for(int i=0;i<permission.length;i++){
                if (ContextCompat.checkSelfPermission(context, permission[i]) != PermissionChecker.PERMISSION_GRANTED) {// 没有权限，申请权限。
                    hasPermission=false;
                    break;
                }
            }
            if(!hasPermission){
                ActivityCompat.requestPermissions(context,permission,BAIDU_READ_PHONE_STATE);
            }
            else {
                if(result!=null){
                    result.permissionOk();
                }
            }
        } else {
            if(result!=null){
                result.permissionOk();
            }
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode==BAIDU_READ_PHONE_STATE){
            boolean bool=true;
            for(int i=0;i<permissions.length;i++){
                if(grantResults[i]!=PermissionChecker.PERMISSION_GRANTED){
                    bool=false;
                    break;
                }
            }
            if (bool) { //有权限
                // 获取到权限，作相应处理
                if(result!=null){
                    result.permissionOk();
                }
            } else {
                if(result!=null){
                    result.permissionFaile();
                }
            }
        }
    }
}
