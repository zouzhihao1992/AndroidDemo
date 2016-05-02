package com.androiddeveloper.androiddemo.listener;

/**
 * Created by zzh on 16/4/28.
 */

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.androiddeveloper.androiddemo.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

/**
 *
 */
public class CyclePermissionListener implements PermissionListener {

    public boolean isQuit = false;
    public final static int REQUEST_APP_SETTINGS = 1;

    public boolean isQuit() {
        return isQuit;
    }

    private Context mContext;
    private String mPermission;

    public CyclePermissionListener(Context context, String permission) {
        mContext = context;
        mPermission = permission;
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
        //Log.d("zzh", "onPermissionGranted");
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {
        //Log.d("zzh", "onPermissionDenied");

        if (!isQuit && !response.isPermanentlyDenied()) {

             //Log.d("zzh", "再次申请权限");
           Dexter.checkPermission(this, response.getPermissionName());

        } else if (!isQuit && response.isPermanentlyDenied()) {

            //Log.d("zzh", "调用系统设置");
            new AlertDialog
                    .Builder(mContext)
                    .setTitle("从系统设置中获取权限")
                    .setTitle("你已经永久拒绝我们的权限申请，需到设置界面获取权限，才能使用该软件")
                    .setCancelable(false)
                    .setPositiveButton("sure",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.parse("package:" + mContext.getPackageName()));
                                    myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                                    //myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    ((Activity)mContext).startActivityForResult(myAppSettings, REQUEST_APP_SETTINGS);
                                }
                            })
                    .setNegativeButton("exit",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    isQuit = true;
                                    Dexter.checkPermission(CyclePermissionListener.this, mPermission);
                                }
                            })
                    .show();

        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(final PermissionRequest permissionRequest, PermissionToken token) {

        final PermissionToken permissionToken = token;
        //当调用checkPermission()时，如果拒绝了，则会调用该方法。
        //首次调用checkPermission()时，如果曾经拒绝了，则也调用该方法。
        // Log.d("zzh", "onPermissionRationaleShouldBeShown");
        //告知用户为什么需要这个权限
        new AlertDialog
                .Builder(mContext)
                .setTitle("Permisson")
                .setMessage("Without Pemisson,The app can not work")
                .setCancelable(false)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //继续申请,
                                permissionToken.continuePermissionRequest();
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("退出",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //会回调onPermissionDenied
                                isQuit = true;
                                permissionToken.cancelPermissionRequest();
                                //MainSettingActivity.this.finish();
                            }
                        })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_APP_SETTINGS) {
            if (hasPermissions(mPermission)) {
                Toast.makeText(mContext, "All permissions granted!", Toast.LENGTH_SHORT).show();
            } else {
                if (Dexter.isRequestOngoing()) {
                    Dexter.continuePendingRequestIfPossible(this);
                } else {
                    Dexter.checkPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
                }
            }
        }
    }

    public boolean hasPermissions(@NonNull String... permissions) {
        for (String permission : permissions)
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(mContext, permission))
                return false;
        return true;
    }

    public void startRequestPermission(){
        if (Dexter.isRequestOngoing()) {
            Dexter.continuePendingRequestIfPossible(this);
        } else {
            Dexter.checkPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        }
    }

}