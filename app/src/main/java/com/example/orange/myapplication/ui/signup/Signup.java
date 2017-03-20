package com.example.orange.myapplication.ui.signup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.orange.myapplication.R;
import com.example.orange.myapplication.ui.base.BaseActivity;
import com.example.orange.myapplication.ui.home.Home;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by orange on 07/03/2017.
 */

public class Signup extends BaseActivity implements ISignupPresenter {

    private EditText inputName, inputDOB, inputEmail, inputPassword, inputConfirmPassword;
    private TextInputLayout inputLayoutName, inputLayoutDOB, inputLayoutEmail, inputLayoutPassword, inputLayoutConfirmPassword;
    private Button btnSignup;
    TextView buttonSelectPhoto;
    CircleImageView profileImage;
    Uri profileUri;

    private SignupPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();

    }

    private void initView() {


        profileImage = (CircleImageView) findViewById(R.id.img_user_photo);

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputName = (EditText) findViewById(R.id.input_name);
        inputDOB = (EditText) findViewById(R.id.input_dob);
        inputConfirmPassword = (EditText) findViewById(R.id.input_confirm_password);

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.input_layout_confirm_password);
        inputLayoutDOB = (TextInputLayout) findViewById(R.id.input_layout_dob);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);

        buttonSelectPhoto = (TextView) findViewById(R.id.button_select_photo);
        buttonSelectPhoto.setOnClickListener(this);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(this);
        inputDOB.setOnClickListener(this);
        presenter = new SignupPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                if (validatePerson())
                    presenter.addPerson();
                break;

            case R.id.input_dob:
                presenter.openDOB_Dialog();
                break;

            case R.id.button_select_photo:
                chooseOption();

                break;
        }
    }

    private boolean validatePerson() {
        if (getName().length() == 0) {
            showError(getString(R.string.valid_name));

            return false;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()
                ) {
            showError(getString(R.string.validate_email));
            return false;
        }

        if (getPassword().length() == 0) {
            showError(getString(R.string.validate_password_length));

            return false;
        }

        if (!getPasswordConfirm().equalsIgnoreCase(getPassword())) {
            showError(getString(R.string.validate_confirm_password));

            return false;
        }
        if (getDOB().length() == 0) {
            showError(getString(R.string.validate_dob));

            return false;
        }

        return true;
    }


    @Override
    public void subscribeCallbacks() {
        presenter.subscribeCallbacks();
    }

    @Override
    public void unSubscribeCallbacks() {
        presenter.unSubscribeCallbacks();
    }

    @Override
    public void goToHome() {
        Intent intent = new Intent(this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }

    @Override
    public Uri getProfileUri() {
        return profileUri;
    }

    @Override
    public String getEmail() {
        return inputEmail.getText().toString();
    }

    @Override
    public String getName() {
        return inputName.getText().toString();
    }

    @Override
    public String getDOB() {
        return inputDOB.getText().toString();
    }

    @Override
    public void setDOB(String dob) {
        inputDOB.setText(dob);
    }


    @Override
    public String getPassword() {
        return inputPassword.getText().toString();
    }

    @Override
    public String getPasswordConfirm() {
        return inputConfirmPassword.getText().toString();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_CODE_SELECT_PICTURE:
                if (data.getData() != null) {
                    profileUri = data.getData();
                    Glide.with(this)
                            .load(data.getData())
                            .centerCrop()
                            .placeholder(R.drawable.boy)
                            .crossFade()
                            .into(profileImage);
                }
                break;
            case REQUEST_CODE_CAPTURE_PICTURES:

                if (imageUri != null) {
                    profileUri = imageUri;
                    Glide.with(this)
                            .load(imageUri)
                            .centerCrop()
                            .placeholder(R.drawable.boy)
                            .crossFade()
                            .into(profileImage);
                }
                break;
        }
    }


}
