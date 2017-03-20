package com.example.orange.myapplication.database.module;

import com.example.orange.myapplication.database.model.Person;
import com.example.orange.myapplication.database.model.Post;

import io.realm.annotations.RealmModule;

/**
 * Created by roma on 15.10.15.
 */
@RealmModule(classes = {Person.class, Post.class})
public class SimpleRealmModule {

}
