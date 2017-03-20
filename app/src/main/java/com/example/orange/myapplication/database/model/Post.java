package com.example.orange.myapplication.database.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by orange on 07/03/2017.
 */

public class Post  extends RealmObject {

    @PrimaryKey
    private String id;
    @Required
    private String title;
    @Required
    private Date dateNtime;
    @Required
    private String personId;
    @Required
    private String description;
    @Required
    private String imahePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateNtime() {
        return dateNtime;
    }

    public void setDateNtime(Date dateNtime) {
        this.dateNtime = dateNtime;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImahePath() {
        return imahePath;
    }

    public void setImahePath(String imahePath) {
        this.imahePath = imahePath;
    }
}