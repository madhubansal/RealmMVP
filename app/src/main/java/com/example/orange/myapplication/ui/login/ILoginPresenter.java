package com.example.orange.myapplication.ui.login;

import com.example.orange.myapplication.database.presenter.IBasePresenter;
import com.example.orange.myapplication.ui.base.IBaseView;

/**
 * Created by orange on 07/03/2017.
 */

public interface ILoginPresenter extends IBaseView,IBasePresenter {
    void goToHome();
    void goToSignup();
    String getEmail();
    String getPassword();
}
