package com.mira.covidbystate.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mira.covidbystate.Model.State;
import com.mira.covidbystate.R;
import com.mira.covidbystate.Util.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {
    private State state;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        queue = Volley.newRequestQueue(this);

        state = (State) getIntent().getSerializableExtra("state"); // récupérer tous les éléments
        stateId = state.getTxtDate();

        setUpUI();
        getMovieDetails(stateId);

    }

    private void setUpUI() {

        txtState = findViewById(R.id.txtState);
        txtCasesPositive =findViewById(R.id.txtCasesPositive);
        txtCasesNegative = findViewById(R.id.txtCasesNegative);
        txtCasesProbable = findViewById(R.id.txtCasesProbable);
        txtDate = findViewById(R.id.txtDate);
        txtDeaths = findViewById(R.id.txtDeaths);
        txtHospitalizations = findViewById(R.id.txtHospitalizations);
        txtHospitalizedCurrently = findViewById(R.id.txtHospitalizedCurrently);
        txtOnVentCurrently = findViewById(R.id.txtOnVentCurrently);
    }

    private void getMovieDetails(String id) {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{

                        txtState.setText(response.getString("state"));
                        txtDate.setText("Date: " + response.getString("date"));
                        txtCasesPositive.setText("Positive Tests: " + response.getString("positive"));
                        txtCasesNegative.setText("Negative Tests: " + response.getString("negative"));
                        txtCasesProbable.setText("Probable Cases: " + response.getString("probableCases"));
                        txtDeaths.setText("Deaths: " + response.getString("deaths"));
                        txtHospitalizations.setText("Hospitalizations: " + response.getString("hospitalized"));
                        txtHospitalizedCurrently.setText("Currently Hospitalized: " + response.getString("hospitalizedCurrently"));
                        txtOnVentCurrently.setText("Currently On Ventilator: " + response.getString("onVentilatorCurrently"));

                    }

                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error:", error.getMessage());

            }
        });
        queue.add(jsonObjectRequest);

    }


}