package com.nyxwolves.wannabuy.Activities;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nyxwolves.wannabuy.Adapters.RequirementSearchAdapter;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.RequirementHelper;

public class RequirementsSearchActivity extends AppCompatActivity {

    RecyclerView searchList;
    RequirementSearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements_search);

        getSupportActionBar().setTitle(" ");

        searchList = findViewById(R.id.search_result_list);
        searchAdapter = new RequirementSearchAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        searchList.setAdapter(searchAdapter);
        searchList.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.requirement_search_menu,menu);
        MenuItem searchViewItem = menu.findItem(R.id.requirement_search_bar);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();
        final RequirementHelper searchHelper = new RequirementHelper(RequirementsSearchActivity.this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchHelper.getRequirement(getString(R.string.ANY_QUERY),query.toUpperCase());

                searchAdapter.setData(searchHelper.dataList);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchHelper.getRequirement(getString(R.string.ANY_QUERY),newText.toUpperCase());

                searchAdapter.setData(searchHelper.dataList);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
