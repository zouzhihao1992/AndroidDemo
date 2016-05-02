package com.androiddeveloper.androiddemo.activity;

import android.Manifest;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.androiddeveloper.androiddemo.R;
import com.fastaccess.permission.base.activity.BasePermissionActivity;
import com.fastaccess.permission.base.model.PermissionModel;
import com.fastaccess.permission.base.model.PermissionModelBuilder;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelperActivity extends BasePermissionActivity {

    @NonNull
    @Override
    protected List<PermissionModel> permissions() {

        List<PermissionModel> permissions = new ArrayList<>();
        permissions.add(PermissionModelBuilder.withContext(this)
                .withTitle("ACCESS_FINE_LOCATION")
                .withCanSkip(false)
                .withPermissionName(Manifest.permission.ACCESS_FINE_LOCATION)
                .withMessage("PermissionHelper also prevents your app getting crashed if the " +
                        "requested permission never exists in your AndroidManifest" +
                        ". Android DOES!")
                .withExplanationMessage("We need this permission to access to your location to" +
                        " find nearby restaurants and places you like!")
                .withLayoutColorRes(R.color.colorAccent)
                .build());

        permissions.add(PermissionModelBuilder.withContext(this)
                .withCanSkip(true)
                .withTitle("WRITE_EXTERNAL_STORAGE")
                .withPermissionName(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withMessage("PermissionHelper lets you customize all these stuff you are seeing!." +
                        " if you ever thought of anything that improves the library please" +
                        " suggest by filling up an issue in github https://github.com/k0shk0sh/PermissionHelper")
                .withExplanationMessage("We need this permission to save your captured images and videos to your SD-Card")
                .withLayoutColorRes(android.R.color.holo_blue_bright)
                .build());

        return permissions;
    }

    @Override
    protected int theme() {
        return R.style.AppTheme;
    }

//    @Override
//    protected int theme() {
//        return R.style.noActionBar;
//    }

    @Override
    protected void onIntroFinished() {
        Log.i("zzh", "Intro has finished");
        // do whatever you like!
       finish();
    }

    @Nullable
    @Override
    protected ViewPager.PageTransformer pagerTransformer() {
        return null;//use default
    }

    @Override
    protected boolean backPressIsEnabled() {
        return false;
    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName) {

        if (permissionName != null){
            for (String result : permissionName){
                Log.d("zzh",result + "Granted");
            }
        }
        super.onPermissionGranted(permissionName);
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName) {

        super.onPermissionGranted(permissionName);
        if (permissionName != null){
            for (String result : permissionName){
                Log.d("zzh",result + "Declined");
            }
        }
        super.onPermissionDeclined(permissionName);
    }



    @Override
    protected void permissionIsPermanentlyDenied(String permissionName) {
        Log.e("zzh", "Permission ( " + permissionName + " ) is permanentlyDenied and can only be granted via settings screen");
        /** {@link com.fastaccess.permission.base.PermissionHelper#openSettingsScreen(Context)} can help you to open it if you like */
    }

    @Override
    protected void onUserDeclinePermission(String permissionName) {
        Log.w("zzh", "Permission ( " + permissionName + " ) is skipped you can request it again by calling doing such\n " +
                "if (permissionHelper.isExplanationNeeded(permissionName)) {\n" +
                "        permissionHelper.requestAfterExplanation(permissionName);\n" +
                "    }\n" +
                "    if (permissionHelper.isPermissionPermanentlyDenied(permissionName)) {\n" +
                "        /** read {@link #permissionIsPermanentlyDenied(String)} **/\n" +
                "    }");

    }
}

