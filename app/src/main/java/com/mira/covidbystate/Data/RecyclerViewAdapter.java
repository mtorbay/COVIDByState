package com.mira.covidbystate.Data;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.mira.covidbystate.Model.State;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<State> stateList;
    public RecyclerViewAdapter(Context context, List<State> states) {
        this.context = context;
        stateList = states;
    }