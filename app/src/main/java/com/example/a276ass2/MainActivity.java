package com.example.a276ass2;

import android.content.Intent;
import android.os.Bundle;
import com.example.Model.Lens;
import com.example.Model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LensManager lenses = LensManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set list view page
        setUpListView();

        // set floating button
        setUpFloatingActionButton();

        // set item click
        setUpItemClickListener();
    }

    private void setUpListView() {
        ListView listView = (ListView) findViewById(R.id.LensList_ListView);
        ArrayList<String> data = new ArrayList<>();
        for (Lens lens: lenses) {
            data.add(lens.toString());
        }

        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, R.layout.list_item, data);
        listView.setAdapter(adapter);
    }

    private void setUpFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddLens.class);
                startActivity(intent);
            }
        });
    }

    private void setUpItemClickListener() {
        ListView list = (ListView) findViewById(R.id.LensList_ListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}