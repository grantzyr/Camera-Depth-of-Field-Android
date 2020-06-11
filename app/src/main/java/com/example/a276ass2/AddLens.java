package com.example.a276ass2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Model.Lens;
import com.example.Model.LensManager;

public class AddLens extends AppCompatActivity {

    private LensManager lenses = LensManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set hint and button
        setUpEditText();


    }

    private void setUpEditText() {
        // set hint
        final EditText make = (EditText) findViewById(R.id.AddLens_Make);
        final EditText focalLength = (EditText) findViewById(R.id.AddLens_FocalLength);
        final EditText aperture = (EditText) findViewById(R.id.AddLens_Aperture);

        make.setHint("ex: Canon");
        focalLength.setHint("ex: 200 for 200mm");
        aperture.setHint("ex: 2.8 for F2.8");

        // set button
        Button save = (Button) findViewById(R.id.AddLens_Save);
        Button cancel = (Button) findViewById(R.id.AddLens_Cancel);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String makeValue = make.getText().toString();
                int focalLengthValue = Integer.parseInt(focalLength.getText().toString());
                double apertureValue = Double.parseDouble(aperture.getText().toString());

                lenses.add(new Lens(makeValue, apertureValue, focalLengthValue));

                Intent result = new Intent();
                result.putExtra("result", "Add New Lens Success");

                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

}