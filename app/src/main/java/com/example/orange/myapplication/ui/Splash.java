package com.example.orange.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.orange.myapplication.R;
import com.example.orange.myapplication.helper.Constant;
import com.example.orange.myapplication.ui.base.BaseActivity;
import com.example.orange.myapplication.ui.home.Home;
import com.example.orange.myapplication.ui.login.Login;
import com.example.orange.myapplication.ui.signup.Signup;

/**
 * Created by orange on 17/03/2017.
 */

public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Intent i;
        if (getPreferenceString(Constant.USER_ID).equalsIgnoreCase("")) {
            i = new Intent(this, Login.class);
        } else {
            i = new Intent(this, Home.class);
        }
        startActivity(i);

    }
}
