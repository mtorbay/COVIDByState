package com.mira.covidbystate.Data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mira.covidbystate.Activities.DetailsActivity;
import com.mira.covidbystate.Model.State;
import com.mira.covidbystate.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<State> stateList;
    public RecyclerViewAdapter(Context context, List<State> states) {
        this.context = context;
        stateList = states;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ///return null;
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.state,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        State state = stateList.get(position);

        holder.statename.setText(state.getTxtState());
        holder.cases.setText(state.getTxtCasesPositive());
        holder.deaths.setText(state.getTxtDeaths());
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView statename;
        TextView cases;
        TextView deaths;

        public ViewHolder(@NonNull View itemView, final Context ctx)
        {
            super(itemView);
            context=ctx;

            statename = itemView.findViewById(R.id.stateID);
            cases = itemView.findViewById(R.id.confirmedCasesID);
            deaths = itemView.findViewById(R.id.deathsID);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    State state = stateList.get(getAdapterPosition());

                    Intent intent = new Intent(context, DetailsActivity.class);

                    intent.putExtra("state", state);
                    ctx.startActivity(intent);

                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
