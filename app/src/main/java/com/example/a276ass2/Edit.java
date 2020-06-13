package com.example.a276ass2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.Model.Lens;
import com.example.Model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends AppCompatActivity {

    private LensManager lenses = LensManager.getInstance();
    private static final String INDEX = "index";
    public static Intent makeLaunchIntent (Context context, int index) {
        Intent intent = new Intent(context, Edit.class);
        intent.putExtra(INDEX, index);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpEditText();

    }


    private void setUpEditText() {
        // set value
        final EditText make = (EditText) findViewById(R.id.Edit_MakeValue);
        final EditText focalLength = (EditText) findViewById(R.id.Edit_FLValue);
        final EditText aperture = (EditText) findViewById(R.id.Edit_ApertureValue);

        int lensIndex = getIntent().getIntExtra(INDEX, 0);
        final Lens tmpLens = lenses.getLens(lensIndex);

        make.setText(tmpLens.getTheMake());
        focalLength.setText(Integer.toString(tmpLens.getFocalLens()));
        aperture.setText(Double.toString(tmpLens.getMaxAperture()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        int lensIndex = getIntent().getIntExtra(INDEX, 0);
        if (id == R.id.action_edit_save) {
            final EditText make = (EditText) findViewById(R.id.Edit_MakeValue);
            final EditText focalLength = (EditText) findViewById(R.id.Edit_FLValue);
            final EditText aperture = (EditText) findViewById(R.id.Edit_ApertureValue);

            String makeValue = make.getText().toString();
            int focalLengthValue = Integer.parseInt(focalLength.getText().toString());
            double apertureValue = Double.parseDouble(aperture.getText().toString());

            if (makeValue.length() == 0) {
                Toast.makeText(getApplicationContext(), "ERROR: Make length = 0", Toast.LENGTH_SHORT).show();
            } else {

                if (focalLengthValue == 0) {
                    Toast.makeText(getApplicationContext(), "ERROR: Focal length = 0", Toast.LENGTH_SHORT).show();
                } else {

                    if (apertureValue < 1.4) {
                        Toast.makeText(getApplicationContext(), "ERROR: Aperture < 1.4", Toast.LENGTH_SHORT).show();
                    } else {
                        Lens tmp = new Lens(makeValue, apertureValue, focalLengthValue);
                        lenses.replace(tmp, lensIndex);

                        Intent result = new Intent();
                        setResult(RESULT_OK, result);
                        finish();
                    }
                }
            }
            return true;

        } else if (id == R.id.action_edit_cancel){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}