package com.example.orange.myapplication.database.repository;

import com.example.orange.myapplication.database.model.Person;

import io.realm.RealmResults;

/**
 * Created by orange on 07/03/2017.
 */

public interface IBaseRepository {

    public interface onAddPersonCallback {
        void onSuccess(Person person);

        void onError(String message);
    }

    public interface onDeletePersonByIdCallback {
        void onSuccess();

        void onError(String message);
    }

    interface OnGetPersonByIdCallback {
        void onSuccess(Person person);

        void onError(String message);
    }

    interface OnGetAllPersonCallback {
        void onSuccess(RealmResults<Person> students);

        void onError(String message);
    }
}
