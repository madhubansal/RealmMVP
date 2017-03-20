package com.example.orange.myapplication.ui.signup;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.widget.Toast;

import com.example.orange.myapplication.database.model.Person;
import com.example.orange.myapplication.database.repository.IBaseRepository.onAddPersonCallback;
import com.example.orange.myapplication.database.repository.ISignupRepository;
import com.example.orange.myapplication.database.repository.impl.SignupRepository;
import com.example.orange.myapplication.helper.Constant;
import com.example.orange.myapplication.helper.Helper;
import com.example.orange.myapplication.ui.base.BaseActivity;
import com.example.orange.myapplication.ui.base.BasePresenter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * Created by orange on 07/03/2017.
 */

public class SignupPresenter implements BasePresenter {

    private onAddPersonCallback addPersonCallback;
    private ISignupRepository signupRepository;
    private ISignupPresenter signupView;
    Context context;
    Activity activity;

    public SignupPresenter(Signup view) {
        this.context = view;
        signupView = view;
        activity = view;
        signupRepository = new SignupRepository();

    }

    public void addPerson() {
        Person person = new Person();
        person.setEmail(signupView.getEmail());
        person.setName(signupView.getName());
        person.setBirthday(Helper.convertStringToDate(signupView.getDOB()));
        person.setPassword(signupView.getPassword());
        signupPerson(person);
    }

    public void signupPerson(Person person) {
        signupRepository.addPerson(person, addPersonCallback);
    }

    public void openDOB_Dialog() {
        Calendar now = Calendar.getInstance();
        final DatePickerDialog d = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
                        Calendar checkedCalendar = Calendar.getInstance();
                        checkedCalendar.set(year, monthOfYear, dayOfMonth);
                        signupView.setDOB(Helper.convertDateToString(checkedCalendar.getTime()));
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        d.setMaxDate(now);
        d.show(activity.getFragmentManager(), this.getClass().getName());
    }







    public void subscribeCallbacks() {
        addPersonCallback = new onAddPersonCallback() {
            @Override
            public void onSuccess(Person person) {


                ((BaseActivity)activity).setPreferenceString(Constant.USER_ID,person.getId());
                (( BaseActivity)activity).setPreferenceString(Constant.NAME,person.getName());
                (( BaseActivity)activity).setPreferenceString(Constant.EMAIL,person.getEmail());
                (( BaseActivity)activity).setPreferenceString(Constant.DOB,Helper.convertDateToString(person.getBirthday()));

                Helper.showMessage(context, "Successfully registered with us!");
                signupView.goToHome();
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


//                Helper.showMessage(context, message);
            }
        };
    }

    public void unSubscribeCallbacks() {
        addPersonCallback = null;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        signupView.unSubscribeCallbacks();
        signupView = null;
    }

    public void start() {
        signupView.subscribeCallbacks();
    }


}
