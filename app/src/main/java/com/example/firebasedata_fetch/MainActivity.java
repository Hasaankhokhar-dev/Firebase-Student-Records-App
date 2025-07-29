package com.example.firebasedata_fetch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recview;
    myadapter adapter;
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the Toolbar (this enables menu with SearchView)
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Initialize RecyclerView and set layout manager
        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        // Configure FirebaseRecyclerOptions to fetch "students" node data
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference().child("students"), model.class)
                        .build();

        // Pass the options to the custom adapter
        adapter = new myadapter(options);
        recview.setAdapter(adapter);


     fb=findViewById(R.id.fadd);
     fb.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(MainActivity.this, AddData.class);
             startActivity(intent);
         }
     });
    }

    // Start Firebase adapter when activity becomes visible
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Stop Firebase adapter to avoid memory leaks
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    // Inflate the menu which contains SearchView
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);

        // Access the SearchView from the menu item
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        // Set a hint in the search box
        searchView.setQueryHint("Search by Course...");

        // Handle text input events (when user types or submits)
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s); // Search when submitted
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s); // Live search as user types
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    // Perform search based on course name (Realtime Firebase query)
    private void processsearch(String s) {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference().child("students")
                                .orderByChild("course")
                                .startAt(s)
                                .endAt(s + "\uf8ff"), model.class)
                        .build();

        // Create new adapter with filtered results
        adapter = new myadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter); // Apply new adapter to RecyclerView
    }
}
