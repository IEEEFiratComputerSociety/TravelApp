package com.example.travel_app_feed.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.travel_app_feed.R;
import com.example.travel_app_feed.View.GezdimFilterCity;

public class GezdimFilterCategory extends AppCompatActivity {
    Button button2,button3,button4,button5,button6,button7,button8;
    ImageView imageView1,imageView2;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gezdimfiltercategory);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        //imageView1 = findViewById(R.id.imageButton);
        //imageView2 = findViewById(R.id.imageButton2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GezdimFilterCity.class);
                intent.putExtra("kategori","Tarihi Yer");
                context.startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GezdimFilterCity.class);
                intent.putExtra("kategori","Doğal Güzellik");
                context.startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GezdimFilterCity.class);
                intent.putExtra("kategori","Plaj ve Koy");
                context.startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GezdimFilterCity.class);
                intent.putExtra("kategori","Antik Kent");
                context.startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GezdimFilterCity.class);
                intent.putExtra("kategori","Müze");
                context.startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GezdimFilterCity.class);
                intent.putExtra("kategori","AVM");
                context.startActivity(intent);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GezdimFilterCity.class);
                intent.putExtra("kategori","Cafe");
                context.startActivity(intent);
            }
        });

    }

}