package com.example.orange.myapplication.database.repository;

import com.example.orange.myapplication.database.model.Person;

/**
 * Created by orange on 07/03/2017.
 */

public interface ISignupRepository extends IBaseRepository {

    void addPerson(Person person, onAddPersonCallback addPersonCallback);
    void getPersonByEmailId(String id, IBaseRepository.OnGetPersonByIdCallback getPersonByIdCallback);

}
