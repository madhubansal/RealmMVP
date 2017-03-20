package com.example.orange.myapplication.database.repository.impl;

import com.example.orange.myapplication.App;
import com.example.orange.myapplication.database.entity.Entity;
import com.example.orange.myapplication.database.model.Person;
import com.example.orange.myapplication.database.repository.IBaseRepository;
import com.example.orange.myapplication.database.repository.ILoginRepository;

import io.realm.Realm;

/**
 * Created by orange on 07/03/2017.
 */

public class LoginRepository implements ILoginRepository {
    @Override
    public void getPersonByEmailId(String id, IBaseRepository.OnGetPersonByIdCallback getPersonByIdCallback) {
        Realm realm = Realm.getInstance(App.getInstance());
        realm.beginTransaction();
        Person result = realm.where(Person.class).equalTo(Entity.EMAIL, id).findFirst();
        if (getPersonByIdCallback != null) {
            if (result != null)
                getPersonByIdCallback.onSuccess(result);
            else {
                getPersonByIdCallback.onError("Email is not registered with us!");
            }
        }
    }
}
