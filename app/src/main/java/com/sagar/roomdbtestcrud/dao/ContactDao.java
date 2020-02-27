package com.sagar.roomdbtestcrud.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sagar.roomdbtestcrud.entity.Tbl_Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Tbl_Contact")
    List<Tbl_Contact> getAllContacts();

    @Insert
    void insert(Tbl_Contact contact);

    @Delete
    void delete(Tbl_Contact tblContact);

}
