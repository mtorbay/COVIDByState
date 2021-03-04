package com.mira.covidbystate.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mira.covidbystate.Data.RecyclerViewAdapter;
import com.mira.covidbystate.Model.State;
import com.mira.covidbystate.R;
import com.mira.covidbystate.Util.Constants;
import com.mira.covidbystate.Util.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter RecyclerViewAdapter;
    private List<State> stateList;
    private RequestQueue queue;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stateList = new ArrayList<>();

        //preferences Prefs

        Prefs prefs = new Prefs(MainActivity.this);
        String search = prefs.getSearch();

        stateList = getStates(search);
        RecyclerViewAdapter = new RecyclerViewAdapter(this, stateList );
        recyclerView.setAdapter(RecyclerViewAdapter);
        RecyclerViewAdapter.notifyDataSetChanged();

    }

    // Recupérer les différents états
    public List<State> getStates(String searchTerm) {
        stateList.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try{
                    JSONArray statesArray = response.getJSONArray("Search");

                    for (int i = 0; i < statesArray.length(); i++) {

                        JSONObject stateObj = statesArray.getJSONObject(i);

                        State state = new State();
                        state.setTxtState(stateObj.getString("state"));
                        state.setTxtDate("Date: " + stateObj.getString("date"));
                        state.setTxtCasesPositive("Confirmed Cases: " + stateObj.getString("positive"));
                        state.setTxtCasesProbable("Probable Cases: " + stateObj.getString("probableCases"));
                        state.setTxtCasesNegative("Negative Tests: " + stateObj.getString("negative"));
                        state.setTxtDeaths("Deaths: " + stateObj.getString("deathConfirmed"));
                        state.setTxtHospitalizations("Hospitalizations: " + stateObj.getString("hospitalized"));
                        state.setTxtHospitalizedCurrently("Currently Hospitalized: " + stateObj.getString("hospitalizedCurrently"));
                        Log.d("States =: ", state.getTxtState());
                        stateList.add(state);

                    }

                    // mettre a jour les resultats de recherche
                    RecyclerViewAdapter.notifyDataSetChanged();


                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);

        return stateList;

    }

    }