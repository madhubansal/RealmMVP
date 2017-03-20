package com.example.orange.myapplication.ui.login;

import android.app.Activity;
import android.content.Context;

import com.example.orange.myapplication.database.model.Person;
import com.example.orange.myapplication.database.repository.IBaseRepository;
import com.example.orange.myapplication.database.repository.ILoginRepository;
import com.example.orange.myapplication.database.repository.ISignupRepository;
import com.example.orange.myapplication.database.repository.impl.LoginRepository;
import com.example.orange.myapplication.database.repository.impl.SignupRepository;
import com.example.orange.myapplication.helper.Constant;
import com.example.orange.myapplication.helper.Helper;
import com.example.orange.myapplication.ui.base.BaseActivity;
import com.example.orange.myapplication.ui.base.BasePresenter;
import com.example.orange.myapplication.ui.signup.ISignupPresenter;
import com.example.orange.myapplication.ui.signup.Signup;

/**
 * Created by orange on 07/03/2017.
 */

public class LoginPresenter implements BasePresenter {

    private IBaseRepository.OnGetPersonByIdCallback getPersonByIdCallback;
    private ILoginRepository loginRepository;
    private ILoginPresenter loginView;
    Context context;
    Activity activity;

    public LoginPresenter(Login view) {
        this.context = view;
        loginView = view;
        activity = view;
        loginRepository = new LoginRepository();

    }

    public void onStart() {
        subscribeCallbacks();
    }
    public void subscribeCallbacks() {
        getPersonByIdCallback = new IBaseRepository.OnGetPersonByIdCallback() {
            @Override
            public void onSuccess(Person person) {
                (( BaseActivity)activity).setPreferenceString(Constant.USER_ID,person.getId());
                (( BaseActivity)activity).setPreferenceString(Constant.NAME,person.getName());
                (( BaseActivity)activity).setPreferenceString(Constant.EMAIL,person.getEmail());
                (( BaseActivity)activity).setPreferenceString(Constant.DOB,Helper.convertDateToString(person.getBirthday()));
                loginView.goToHome();
            }

            @Override
            public void onError(String message) {

                Helper.createDialogWithOneLabel(context, "Alert", message, "Ok", new Helper.Method() {
                            @Override
                            public void execute() {
                                // do something useful
                            }
                        }
                );

            }
        };
    }


    public void doLogin(){
        loginRepository.getPersonByEmailId(loginView.getEmail(),getPersonByIdCallback);
    }




    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getPersonByIdCallback = null;
        loginView=null;
    }
}
