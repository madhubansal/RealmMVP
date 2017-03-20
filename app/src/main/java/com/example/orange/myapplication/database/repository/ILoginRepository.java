package com.example.orange.myapplication.database.repository;

/**
 * Created by orange on 07/03/2017.
 */

public interface ILoginRepository {
    void getPersonByEmailId(String id, IBaseRepository.OnGetPersonByIdCallback getPersonByIdCallback);

}
