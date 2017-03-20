package com.example.orange.myapplication.database.repository.impl;

import com.example.orange.myapplication.App;
import com.example.orange.myapplication.database.entity.Entity;
import com.example.orange.myapplication.database.model.Person;
import com.example.orange.myapplication.database.repository.IPersonRepository;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by orange on 07/03/2017.
 */

public class PersonRepository implements IPersonRepository {
    @Override
    public void addPerson(Person person, onAddPersonCallback addPersonCallback) {
        Realm realm = Realm.getInstance(App.getInstance());
        realm.beginTransaction();
        Person realmPerson = realm.createObject(Person.class);
        realmPerson.setId(UUID.randomUUID().toString());
        realmPerson.setEmail(person.getEmail());
        realmPerson.setName(person.getName());
        realmPerson.setBirthday(person.getBirthday());
        realmPerson.setPassword(person.getPassword());
        realm.commitTransaction();
        if (addPersonCallback != null)
            addPersonCallback.onSuccess(realmPerson);
    }

    @Override
    public void deletePersonById(String id, onDeletePersonByIdCallback deletePersonByIdCallback) {
        Realm realm = Realm.getInstance(App.getInstance());
        realm.beginTransaction();
        Person result = realm.where(Person.class).equalTo(Entity.ID, id).findFirst();
        result.removeFromRealm();
        realm.commitTransaction();

        if (deletePersonByIdCallback != null)
            deletePersonByIdCallback.onSuccess();
    }

    @Override
    public void getAllPerson(OnGetAllPersonCallback getAllPersonCallback) {
        Realm realm = Realm.getInstance(App.getInstance());
        realm.beginTransaction();
        RealmResults<Person> result = realm.where(Person.class).findAll();
        if (getAllPersonCallback != null)
            getAllPersonCallback.onSuccess(result);
    }

    @Override
    public void getPersonById(String id, OnGetPersonByIdCallback getPersonByIdCallback) {
        Realm realm = Realm.getInstance(App.getInstance());
        realm.beginTransaction();
        Person result = realm.where(Person.class).equalTo(Entity.ID, id).findFirst();
        if (getPersonByIdCallback != null)
            getPersonByIdCallback.onSuccess(result);
    }
}
