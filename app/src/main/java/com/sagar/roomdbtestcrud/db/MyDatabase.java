package com.sagar.roomdbtestcrud.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sagar.roomdbtestcrud.dao.ContactDao;
import com.sagar.roomdbtestcrud.entity.Tbl_Contact;

@Database(entities = {Tbl_Contact.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
}
