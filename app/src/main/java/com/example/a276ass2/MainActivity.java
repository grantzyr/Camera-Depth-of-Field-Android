package com.example.a276ass2;

import android.content.Intent;
import android.os.Bundle;
import com.example.Model.Lens;
import com.example.Model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LensManager lenses = LensManager.getInstance();

    private static final int ADD_LENS = 0;

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
                Intent intent = AddLens.makeLaunchIntent(MainActivity.this);
                startActivityForResult(intent, ADD_LENS);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                recreate();
    }

    private void setUpItemClickListener() {
        ListView list = (ListView) findViewById(R.id.LensList_ListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = Calculate.makeLaunchIntent(MainActivity.this, position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       if (id == R.id.action_add_Lens) {
            Intent intent = new Intent(MainActivity.this, AddLens.class);
            startActivityForResult(intent, ADD_LENS);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}