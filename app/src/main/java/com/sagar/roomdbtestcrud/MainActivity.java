package com.sagar.roomdbtestcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sagar.roomdbtestcrud.dao.ContactDao;
import com.sagar.roomdbtestcrud.db.MyDatabase;
import com.sagar.roomdbtestcrud.entity.Tbl_Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabase myDatabase;

    private EditText etName,etEmail,etPhone;

    private Button btnADD,btnCountRow,uDActivity;

    public static ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = Room.databaseBuilder(MainActivity.this,
                MyDatabase.class,"my_database")
                .allowMainThreadQueries().build();

        contactDao = myDatabase.contactDao();


        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);

        btnADD = findViewById(R.id.btnADD);
        btnCountRow = findViewById(R.id.btnCountRow);
        uDActivity = findViewById(R.id.uDActivity);

        uDActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserDetailsActivity.class);
                startActivity(intent);
            }
        });

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String email = etEmail.getText().toString();
                final String phone = etPhone.getText().toString();

                Tbl_Contact contact = new Tbl_Contact();
                contact.name = name;
                contact.email = email;
                contact.phone = phone;

                contactDao.insert(contact);
                Toast.makeText(MainActivity.this, "LOL", Toast.LENGTH_SHORT).show();

            }
        });

        btnCountRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Tbl_Contact> allContacts = contactDao.getAllContacts();
                Toast.makeText(MainActivity.this, "Row Count " + allContacts.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
