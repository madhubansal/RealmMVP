package com.example.orange.myapplication.helper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.example.orange.myapplication.ui.base.BaseActivity;

/**
 * Created by orange on 07/03/2017.
 */

public class PermissionHelper {

    public static final int REQUEST_CODE_READ_STORAGE = 101;
    public static final int REQUEST_CODE_CAMERA = 102;

    private Context context;

    public PermissionHelper(Context context) {
        this.context = context;
    }
    public boolean checkReadStoragePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    public void requestReadStoragePermission(BaseActivity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_READ_STORAGE);
    }

    public boolean checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA);

        int writePermissionCheck = ActivityCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);


        return permissionCheck == PackageManager.PERMISSION_GRANTED && writePermissionCheck==PackageManager.PERMISSION_GRANTED;
    }
    public void requestCameraPermission(BaseActivity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE_CAMERA);
    }
    public void requestCameraPermission(Fragment fragment) {
        fragment.requestPermissions(
                new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE_CAMERA);
    }

}
