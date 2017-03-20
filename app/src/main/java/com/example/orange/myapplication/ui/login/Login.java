package com.example.orange.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.orange.myapplication.R;
import com.example.orange.myapplication.ui.base.BaseActivity;
import com.example.orange.myapplication.ui.home.Home;
import com.example.orange.myapplication.ui.signup.Signup;

public class Login extends BaseActivity implements ILoginPresenter {
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnLogin, btnCreateAccount;
    LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnCreateAccount = (Button) findViewById(R.id.btn_create_account);
        btnCreateAccount.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        presenter = new LoginPresenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                if (validPerson()) {
                    presenter.doLogin();
                }

                break;
            case R.id.btn_create_account:

                goToSignup();

                break;
        }

    }

    private boolean validPerson() {


        if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()
                ) {
            showError(getString(R.string.validate_email));
            return false;
        }

        if (getPassword().length() == 0) {
            showError(getString(R.string.validate_password_length));

            return false;
        }


        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void goToHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    @Override
    public void goToSignup() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
        finish();
    }

    @Override
    public String getEmail() {
        return inputEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return inputPassword.getText().toString();
    }

    @Override
    public void subscribeCallbacks() {

    }

    @Override
    public void unSubscribeCallbacks() {

    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
