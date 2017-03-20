package com.example.orange.myapplication.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.orange.myapplication.database.model.Post;
import com.example.orange.myapplication.helper.Helper;
import com.example.orange.myapplication.ui.base.BaseActivity;
import com.example.orange.myapplication.ui.login.Login;

/**
 * Created by orange on 08/03/2017.
 */

public class HomePresenter implements IHomePresenter {

    Activity activity;

    private Home view;

    public HomePresenter(Home view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }


    @Override
    public void signout() {
        Helper.createDialogWithTwoLabels(activity, "Logout", "Do you want to logout?", "Yes","No", new Helper.Method() {
                    @Override
                    public void execute() {
                        ((BaseActivity)activity).clearSharedPreferences();
                        Intent i = new Intent(activity, Login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        activity.startActivity(i);
                        activity.finish();
                    }
                }, new Helper.Method() {
                    @Override
                    public void execute() {
                        // do something useful
                    }
                }
        );
    }

    @Override
    public void subscribeCallbacks() {

    }

    @Override
    public void unSubscribeCallbacks() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        view = null;
    }

    @Override
    public Context context() {
        return null;
    }


    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(String message) {

    }
}
