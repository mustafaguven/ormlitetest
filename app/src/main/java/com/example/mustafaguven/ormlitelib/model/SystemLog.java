package com.example.mustafaguven.ormlitelib.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MustafaGuven on 9.3.2015.
 */
@DatabaseTable
public class SystemLog {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    public String name;

    @DatabaseField
    public String description;

    @DatabaseField
    long date;

    public SystemLog(String name, String description, long date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }
}
