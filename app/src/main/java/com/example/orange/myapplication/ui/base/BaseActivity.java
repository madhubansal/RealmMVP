package com.example.orange.myapplication.ui.base;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.orange.myapplication.R;
import com.example.orange.myapplication.helper.PermissionHelper;

import static android.R.attr.fragment;

/**
 * Created by orange on 07/03/2017.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener , IBaseView{

    Context context;


    public static final int REQUEST_CODE_SELECT_PICTURE = 10;

    public static final int REQUEST_CODE_CAPTURE_PICTURES = 11;
    public static Uri imageUri;

    public String getPreferenceString(String key) {
        SharedPreferences preference = context.getSharedPreferences("demo", 0);
        String data = preference.getString(key, "");
        return data;
    }

    public void setPreferenceString(String key, String value) {
        SharedPreferences preference = context.getSharedPreferences("demo", 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public void clearSharedPreferences() {

        SharedPreferences preference = context.getSharedPreferences("demo", 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.commit();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;

    }

    @Override
    public void onClick(View v) {

    }

    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Context context() {
        return context;
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(String message) {
        showMessage(message);
    }



    public  void chooseOption() {
        final CharSequence[] items = {
                "Gallery", "Camera", "Cancel"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int position) {
                switch (position) {
                    case 0:
                        selectFromStorage();
                        break;
                    case 1:
                        openCameraToSelectPhoto();
                        break;
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setCanceledOnTouchOutside(true);
        alert.show();
    }




    public  void selectFromStorage() {
        PermissionHelper permissionsHelper = new PermissionHelper(context);
        if (permissionsHelper.checkReadStoragePermission()) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, ""), REQUEST_CODE_SELECT_PICTURE);
        } else {
            permissionsHelper.requestReadStoragePermission(this);
        }
    }

    public  void openCameraToSelectPhoto() {
        // Check runtime permission to READ STORAGE
        PermissionHelper permissionsHelper = new PermissionHelper(context);
        if (!permissionsHelper.checkCameraPermission()) {
            permissionsHelper.requestCameraPermission(this);
            return;
        }



        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "CB_Pic");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From Your Camera");
            imageUri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            capture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(capture, REQUEST_CODE_CAPTURE_PICTURES);

        } catch (Exception e) {
            Toast.makeText(context, "Storage not available.", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionHelper.REQUEST_CODE_READ_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectFromStorage();
//                    showMessage("Permission has been granted! Enjoy the app!");
                } else {
                    showMessage("Permission has been denied! Requested function in unavailable without permission.");
                }
                break;
            case PermissionHelper.REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCameraToSelectPhoto();
                } else {
                    showMessage("Permission has been denied! Requested function in unavailable without permission.");
                }
                break;
        }
    }


}
