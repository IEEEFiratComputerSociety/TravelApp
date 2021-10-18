package com.example.travel_app_feed.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.travel_app_feed.R;

public class SearchActivity extends AppCompatActivity {

    public ImageButton searchButton;
    public EditText searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                startActivity(intent);
            }
        });
    }

    public void goToGezdim(View v){
        Intent intent = new Intent(SearchActivity.this, GezdimFilterCategory.class);
        startActivity(intent);
    }
}