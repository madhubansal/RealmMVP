package com.example.orange.myapplication.database.presenter;

import com.example.orange.myapplication.database.model.Person;

/**
 * Created by roma on 03.11.15.
 */
public interface IPersonPresenter extends IBasePresenter {

    void addPerson(Person student);


    void deletePersonByPosition(int position);

    void deletePersonById(String studentId);

    void getAllPerson();

    void getPersonById(String id);


}
