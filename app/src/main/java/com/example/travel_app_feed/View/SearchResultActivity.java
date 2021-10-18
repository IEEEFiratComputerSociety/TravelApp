package com.example.travel_app_feed.View;

import android.os.Bundle;

import com.example.travel_app_feed.Adapter.SearchAdapter;
import com.example.travel_app_feed.Model.SearchResultModel;
import com.example.travel_app_feed.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity{

    private RecyclerView mSearchResult;
    SearchAdapter adapter ;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        ArrayList<SearchResultModel> searchResult = new ArrayList<>();

        searchResult.add(new SearchResultModel(R.drawable.ic_android_black_24dp,"Line1","Line2"));

        searchResult.add(new SearchResultModel(R.drawable.ic_baseline_account_circle_24,"Line3","Line4"));

        searchResult.add(new SearchResultModel(R.drawable.ic_baseline_add_a_photo_24,"Line4","Line5"));

        mSearchResult = findViewById(R.id.search_result);


        layoutManager = new LinearLayoutManager(this);

        adapter = new SearchAdapter(searchResult);
        mSearchResult.setLayoutManager(layoutManager);

        mSearchResult.setAdapter(adapter);

    }





}
