package com.example.orange.myapplication.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by orange on 07/03/2017.
 */

public class Helper {

    static   SimpleDateFormat inputFormat  = new SimpleDateFormat("MMM dd, yyyy");
   static SimpleDateFormat outputFormat  = new SimpleDateFormat("MMM dd, yyyy");


    public static void showMessage(Context context,String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    public static String convertDateToString(Date date){
        return outputFormat.format(date);
    }
    public static Date getNewDate(){
        Date resultDate= null;
        try {

                resultDate = outputFormat.parse(outputFormat.format(new Date()));

        }catch (Exception e){
            e.printStackTrace();
        }

        return resultDate;
    }



    public static Date convertStringToDate(String date) {
       Date resultDate= null;
      try {
          if (!TextUtils.isEmpty(date)) {
              resultDate = outputFormat.parse(date);
          }
      }catch (Exception e){
          e.printStackTrace();
      }

        return resultDate;
    }


    public static Dialog createDialogWithTwoLabels(Context context, String title, String msg, String btnPosLabel, String btnNegLabel,
                                            final Method methodPos, final Method methodNeg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title)
                .setMessage(msg).setPositiveButton(btnPosLabel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        methodPos.execute();
                    }
                }).setNegativeButton(btnNegLabel,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        methodNeg.execute();
                    }
                });
        Dialog dialod = builder.show();
        return dialod;
    }
    public static Dialog createDialogWithOneLabel(Context context, String title, String msg, String btnPosLabel,
                                                   final Method methodPos){
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title)
                .setMessage(msg).setPositiveButton(btnPosLabel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        methodPos.execute();
                        dialog.dismiss();
                    }
                });
        Dialog dialod = builder.show();
        return dialod;
    }
    public static interface Method{
        void execute();
    }

}
