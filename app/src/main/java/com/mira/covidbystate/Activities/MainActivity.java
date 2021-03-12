package com.mira.covidbystate.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);

       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }); */

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

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Constants.URL,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try{
                    //JSONArray statesArray = response.getJSONArray(0);
                    //Log.d("States = ", String.valueOf(statesArray));

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject stateObj = response.getJSONObject(i);

                        State state = new State();
                        state.setTxtState(stateObj.getString("state"));
                        state.setTxtDate("Date: " + stateObj.getInt("date"));
                        state.setTxtCasesPositive("Confirmed Cases: " + stateObj.getInt("positive"));
                        state.setTxtCasesProbable("Probable Cases: " + stateObj.getInt("probableCases"));
                        state.setTxtCasesNegative("Negative Tests: " + stateObj.getInt("negative"));
                        state.setTxtDeaths("Deaths: " + stateObj.getInt("deathConfirmed"));
                        state.setTxtHospitalizations("Hospitalizations: " + stateObj.getInt("hospitalized"));
                        state.setTxtHospitalizedCurrently("Currently Hospitalized: " + stateObj.getInt("hospitalizedCurrently"));
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

        queue.add(jsonArrayRequest);

        return stateList;

    }
    //search
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem=menu.findItem(R.id.new_search);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setQueryHint("Entrer le nom d'un état");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Prefs prefs = new Prefs(MainActivity.this);
                if(!query.isEmpty())
                {
                    prefs.setSearch(query);
                    stateList.clear();
                    getStates(query);

                    //clavier

                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (getCurrentFocus() != null)
                    {
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    } */
    }