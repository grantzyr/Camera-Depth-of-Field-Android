package com.example.a276ass2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Model.Lens;
import com.example.Model.LensManager;

public class AddLens extends AppCompatActivity {

    private LensManager lenses = LensManager.getInstance();

//    private static final String ENTRY = "entry";
//
//    public static Intent makeLaunchIntent (Context context, int entryValue) {
//        Intent intent = new Intent (context, AddLens.class);
//        intent.putExtra(ENTRY, entryValue);
//
//        return intent;
//    }

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
        final EditText make = (EditText) findViewById(R.id.AddLens_make);
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

                if (makeValue.length() == 0) {
                    Toast.makeText(getApplicationContext(), "ERROR: Make length = 0", Toast.LENGTH_SHORT).show();
                } else {

                    if (focalLengthValue == 0) {
                        Toast.makeText(getApplicationContext(), "ERROR: Focal length = 0", Toast.LENGTH_SHORT).show();
                    } else {

                        if (apertureValue < 1.4) {
                            Toast.makeText(getApplicationContext(), "ERROR: Aperture < 1.4", Toast.LENGTH_SHORT).show();
                        } else {

                            lenses.add(new Lens(makeValue, apertureValue, focalLengthValue));

                            Intent result = new Intent();
                            setResult(RESULT_OK, result);
                            finish();
                        }
                    }
                }



            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
