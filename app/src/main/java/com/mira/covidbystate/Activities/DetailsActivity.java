package com.mira.covidbystate.Activities;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class DetailsActivity extends AppCompatActivity {
    private TextView txtState;
    private TextView txtCasesPositive;
    private TextView txtCasesNegative;
    private TextView txtCasesProbable;
    private TextView txtHospitalizations;
    private TextView txtDeaths;
    private TextView txtHospitalizedCurrently;
    private TextView txtOnVentCurrently;
    private TextView txtDate;
    private RequestQueue queue;
    private String stateId;

}