package com.example.orange.myapplication.ui.signup;

import android.content.Context;
import android.net.Uri;

import com.example.orange.myapplication.database.presenter.IBasePresenter;
import com.example.orange.myapplication.ui.base.IBaseView;

/**
 * Created by orange on 07/03/2017.
 */

public interface ISignupPresenter extends IBaseView ,IBasePresenter {
    void goToHome();

    Uri getProfileUri();
    String getEmail();
    String getName();
    String getDOB();
    void setDOB(String dob);
    String getPassword();
    String getPasswordConfirm();

}
