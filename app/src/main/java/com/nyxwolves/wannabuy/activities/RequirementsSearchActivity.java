package com.nyxwolves.wannabuy.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.nyxwolves.wannabuy.Adapters.RequirementSearchAdapter;
import com.nyxwolves.wannabuy.R;

public class RequirementsSearchActivity extends AppCompatActivity{

    RecyclerView searchList;
    RequirementSearchAdapter searchAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements_search);

        toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchList = findViewById(R.id.search_result_list);
        searchAdapter = new RequirementSearchAdapter(this,getIntent().getStringExtra("LOCATION"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        searchList.setAdapter(searchAdapter);
        searchList.setLayoutManager(linearLayoutManager);

    }

}
