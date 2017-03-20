package com.example.orange.myapplication.ui.base;


import android.content.Context;

public interface IBaseView  {
    void resume();

    void pause();

    void destroy();
    Context context();

    void showProgressDialog();
    void hideProgressDialog();
    void showError(String message);

}
