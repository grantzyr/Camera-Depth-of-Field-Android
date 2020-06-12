package com.example.a276ass2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Model.Lens;
import com.example.Model.LensManager;

import java.text.DecimalFormat;

import static java.lang.Double.POSITIVE_INFINITY;

public class Calculate extends AppCompatActivity {

    private double hyperFocal;
    private double nearFocal;
    private double farFocal;
    private double depthOfField;

    private LensManager lenses = LensManager.getInstance();
    private static final String INDEX = "index";
    public static Intent makeLaunchIntent (Context context, int index) {
        Intent intent = new Intent(context, Calculate.class);
        intent.putExtra(INDEX, index);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getLensDetails();

        setUpHintTextAndButton();
    }

    private void getLensDetails() {
        int lensIndex = getIntent().getIntExtra(INDEX, 0);
        Lens currLens = lenses.getLens(lensIndex);

        TextView details = (TextView) findViewById(R.id.Calculate_Detail);
        details.setText(currLens.toString());
    }

    private void setUpHintTextAndButton() {
        final EditText COC = (EditText) findViewById(R.id.Calculate_COC);
        final EditText DTS = (EditText) findViewById(R.id.Calculate_DTS);
        final EditText SA = (EditText) findViewById(R.id.Calculate_SA);

        COC.setText("0.029");
        DTS.setHint("ex: 1.5 for 1.5m");
        SA.setHint("ex: 2.8 for F2.8");

        final Button calculate = (Button) findViewById(R.id.Calculate_CalButton);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int lensIndex = getIntent().getIntExtra(INDEX, 0);
                final Lens tmpLens = lenses.getLens(lensIndex);

                double getCOC = Double.parseDouble(COC.getText().toString());
                double getDTS = Double.parseDouble(DTS.getText().toString());
                double getSA = Double.parseDouble(SA.getText().toString());

                TextView nearFocalDistance = (TextView) findViewById(R.id.Calculate_NFD);
                TextView farFocalDistance = (TextView) findViewById(R.id.Calculate_FFD);
                TextView depthOfField = (TextView) findViewById(R.id.Calculate_DOF);
                TextView hyperFocalDistance = (TextView) findViewById(R.id.Calculate_HFD);

                if (getSA < tmpLens.getMaxAperture()) {
                    nearFocalDistance.setText("Invalid aperture");
                    farFocalDistance.setText("Invalid aperture");
                    depthOfField.setText("Invalid aperture");
                    hyperFocalDistance.setText("Invalid aperture");

                } else {
                    double hyperFocalValue = getHyperFocal(tmpLens, getSA, getCOC);
                    double nearFocalValue = getNearFocal(tmpLens, hyperFocalValue, getDTS);
                    double farFocalValue = getFarFocal(tmpLens, hyperFocalValue, getDTS);
                    double depthOfFieldValue = getDepthOfField(farFocalValue, nearFocalValue);


                    hyperFocalDistance.setText(formatM(hyperFocalValue) + "m");
                    nearFocalDistance.setText(formatM(nearFocalValue) + "m");
                    farFocalDistance.setText(formatM(farFocalValue) + "m");
                    depthOfField.setText(formatM(depthOfFieldValue) + "m");
                }
            }
        });
    }

    private double getHyperFocal(Lens lens, double aperture, double COC) {
        this.hyperFocal = (lens.getFocalLens() * lens.getFocalLens()) / (aperture * COC);
        return hyperFocal/1000;
    }


    private double getNearFocal(Lens lens, double hyperFocal, double distance) {
        this.nearFocal = (hyperFocal * distance) /  (hyperFocal + (distance*1000 - lens.getFocalLens())/1000);
        return nearFocal;
    }


    private double getFarFocal(Lens lens, double hyperFocal, double distance) {
        this.farFocal = (hyperFocal * distance) /  (hyperFocal - (distance*1000 - lens.getFocalLens())/1000);
        if (farFocal < 0) {
            return POSITIVE_INFINITY;
        }
        return farFocal;
    }

    private double getDepthOfField(double farFocal, double nearFocal) {
        this.depthOfField = farFocal - nearFocal;
        return depthOfField;
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}