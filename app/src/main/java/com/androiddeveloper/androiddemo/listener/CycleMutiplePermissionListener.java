package com.androiddeveloper.androiddemo.listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.androiddeveloper.androiddemo.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzh on 16/4/28.
 */
public class CycleMutiplePermissionListener implements MultiplePermissionsListener {
    private Context mContext;
    //only for angerous permission
    private List<String> mPermissions;

    private List<String> deniedPermissionsName = new ArrayList<>(); //一般被拒绝的权限
    private List<String> permannetPermissionsName = new ArrayList<>(); //永久被拒绝的权限

    public boolean isQuit = false;
    public static final int REQUEST_APP_SETTINGS = 2;

    public boolean isQuit() {
        return isQuit;
    }

    public CycleMutiplePermissionListener(Context context, List<String> permissions) {
        mContext = context;
        this.mPermissions = permissions;
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {

        Log.d("zzh", "onPermissionsChecked");
        if (!isQuit) {
            List<PermissionDeniedResponse> deniedResponses = new ArrayList<>();
            List<PermissionGrantedResponse> grantedResponses = new ArrayList<>();
            deniedResponses.clear();
            grantedResponses.clear();
            deniedPermissionsName.clear();
            permannetPermissionsName.clear();

            deniedResponses = report.getDeniedPermissionResponses();
            grantedResponses = report.getGrantedPermissionResponses();

            //拒绝的权限
            for (PermissionDeniedResponse response : deniedResponses) {
                if (response.isPermanentlyDenied()) {
                    permannetPermissionsName.add(response.getPermissionName());
                } else {
                    deniedPermissionsName.add(response.getPermissionName());
                }
            }

            //拒绝权限处理
            if (deniedPermissionsName.size() >= 1) {
                Log.d("zzh", "处理普通的拒绝");
                Dexter.checkPermissions(this, deniedPermissionsName);
            } else if ( permannetPermissionsName.size() >= 1) { //一般的拒绝处理完毕
                //处理永久拒绝权限
                //在设置中申请权限
                Log.d("zzh", "处理永久的拒绝");
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
                                        ((Activity) mContext).startActivityForResult(myAppSettings, REQUEST_APP_SETTINGS);
                                    }
                                })
                        .setNegativeButton("exit",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        isQuit = true;
                                        Dexter.checkPermissions(CycleMutiplePermissionListener.this, permannetPermissionsName);
                                    }
                                })
                        .show();
            }

            if (deniedResponses.size() < 1){
                //处理完了,获得所有权限
                Log.d("zzh","获得所有权限,启动对应业务");
                //可以让调用者放一个Listener进来，传入相应的逻辑

            }
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken token) {
        final PermissionToken permissionToken = token;
        //当调用checkPermission()时，如果拒绝了，则会调用该方法。
        //首次调用checkPermission()时，如果曾经拒绝了，则也调用该方法。
        // Log.d("zzh", "onPermissionRationaleShouldBeShown");
        //告知用户为什么需要这个权限
        new AlertDialog
                .Builder(mContext)
                .setTitle("Permisson")
                .setMessage("如果没有权限，将无法提供该功能")
                .setCancelable(false)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //继续申请,当前是没有权限的，所有会调用onPermissionsChecked
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

    //需要在对应的Activity回调
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_APP_SETTINGS) {
            if (hasPermissions(mPermissions)) {
                Toast.makeText(mContext, "All permissions granted!", Toast.LENGTH_SHORT).show();
                //获得所有权限
                Log.d("zzh","获得所有权限,启动对应业务");
                //可以让调用者放一个Listener进来，传入相应的逻辑
            } else {
                if (Dexter.isRequestOngoing()) {
                    Dexter.continuePendingRequestsIfPossible(this);
                } else {
                    Dexter.checkPermissions(this, mPermissions);
                }
            }
        }
    }

    public boolean hasPermissions(List<String> permissions) {
        for (String permission : permissions)
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(mContext, permission)) {
                return false;
            }
        return true;
    }

    public void startRequestPermission() {
        if (Dexter.isRequestOngoing()) {
            Dexter.continuePendingRequestsIfPossible(this);
        } else {
            Dexter.checkPermissions(this, mPermissions);
        }
    }
}
