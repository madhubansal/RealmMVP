package com.example.orange.myapplication.database.repository.impl;

import com.example.orange.myapplication.App;
import com.example.orange.myapplication.database.entity.Entity;
import com.example.orange.myapplication.database.model.Person;
import com.example.orange.myapplication.database.repository.IBaseRepository;
import com.example.orange.myapplication.database.repository.ISignupRepository;

import java.util.UUID;

import io.realm.Realm;

/**
 * Created by orange on 07/03/2017.
 */

public class SignupRepository implements ISignupRepository {
    @Override
    public void addPerson(Person person, onAddPersonCallback addPersonCallback) {
        Realm realm = Realm.getInstance(App.getInstance());
        realm.beginTransaction();
        Person result = realm.where(Person.class).equalTo(Entity.EMAIL, person.getEmail()).findFirst();
        realm.commitTransaction();
        if(result==null){
            realm.beginTransaction();
            Person realmPerson = realm.createObject(Person.class);
            realmPerson.setId(UUID.randomUUID().toString());
            realmPerson.setEmail(person.getEmail());
            realmPerson.setName(person.getName());
            realmPerson.setBirthday(person.getBirthday());
            realmPerson.setPassword(person.getPassword());
            realm.commitTransaction();
            if (addPersonCallback != null)
                addPersonCallback.onSuccess(person);
        }else{
            if (addPersonCallback != null)
                addPersonCallback.onError("Email is already registered with us!");
        }

    }

    @Override
    public void getPersonByEmailId(String id, OnGetPersonByIdCallback getPersonByIdCallback) {

    }
}
