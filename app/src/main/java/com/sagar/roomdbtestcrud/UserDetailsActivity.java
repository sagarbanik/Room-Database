package com.sagar.roomdbtestcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.sagar.roomdbtestcrud.adapter.RecViewAdapter;

public class UserDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecViewAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        context = UserDetailsActivity.this;

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new
                LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecViewAdapter(context,MainActivity.contactDao.getAllContacts());
        recyclerView.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }
}
