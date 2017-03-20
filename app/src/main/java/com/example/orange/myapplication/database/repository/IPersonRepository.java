package com.example.orange.myapplication.database.repository;

import com.example.orange.myapplication.database.model.Person;

import io.realm.RealmResults;

/**
 * Created by orange on 07/03/2017.
 */

public interface IPersonRepository extends IBaseRepository{




    void addPerson(Person person, onAddPersonCallback addPersonCallback);


    void deletePersonById(String personId, onDeletePersonByIdCallback deletePersonByIdCallback);

    void getAllPerson(OnGetAllPersonCallback getAllPersonCallback);


    void getPersonById(String id, OnGetPersonByIdCallback getPersonByIdCallback);


}
