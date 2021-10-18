package com.example.travel_app_feed.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.travel_app_feed.View.GezdimActivity;
import com.example.travel_app_feed.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GezdimCitiesAdapter extends RecyclerView.Adapter<GezdimCitiesAdapter.MyViewHolder> {
    ArrayList<String> dataCity;
    String dataKategori;
    Context context;

    public GezdimCitiesAdapter(Context ct, ArrayList<String> city, String kategori){
        context = ct;
        dataCity = city;
        dataKategori = kategori;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_gezdim_filtercity,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GezdimCitiesAdapter.MyViewHolder holder, int position) {
        holder.nameText.setText(dataCity.get(position));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GezdimActivity.class);
                intent.putExtra("cityName",dataCity.get(position));
                intent.putExtra("kategori",dataKategori);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataCity.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View view) {
            super(view);
            nameText = view.findViewById(R.id.nameText);
            mainLayout = view.findViewById(R.id.mainLayout);
        }
    }
}
