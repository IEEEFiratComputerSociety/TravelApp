package com.example.travel_app_feed.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.travel_app_feed.Adapter.GezdimCitiesAdapter;
import com.example.travel_app_feed.R;

import java.util.ArrayList;

public class GezdimFilterCity extends AppCompatActivity {
    ArrayList<String> cityName = new ArrayList<String>();
    RecyclerView recyclerView;
    GezdimCitiesAdapter listAdapter;
    Context context = this;
    String kategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gezdimfiltercity);
        kategori = getIntent().getStringExtra("kategori");
        cityName.add("Kayseri");
        cityName.add("İstanbul");
        cityName.add("Ankara");
        cityName.add("Mersin");
        cityName.add("Kocaeli");
        cityName.add("Adana");
        cityName.add("Konya");
        cityName.add("Antalya");
        cityName.add("Kocaeli");
        cityName.add("İzmir");
        cityName.add("Bursa");
        recyclerView = findViewById(R.id.getRecylerView);
        listAdapter = new GezdimCitiesAdapter(context,cityName,kategori);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GezdimFilterCity.this));
    }
}