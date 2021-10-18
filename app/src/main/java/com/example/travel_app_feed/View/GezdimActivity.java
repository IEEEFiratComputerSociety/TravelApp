package com.example.travel_app_feed.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.travel_app_feed.Adapter.GezdimMainScreenAdapter;
import com.example.travel_app_feed.Model.PostModel;
import com.example.travel_app_feed.R;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class GezdimActivity extends AppCompatActivity {

    ArrayList<PostModel> posts;
    RecyclerView recyclerView;
    GezdimMainScreenAdapter gezdimMainScreenAdapter;
    private FirebaseFirestore firebaseFirestore;
    PostModel postModel;
    String cityName;
    String kategori;
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gezdim);

        cityName = getIntent().getStringExtra("cityName");
        kategori = getIntent().getStringExtra("kategori");

        firebaseFirestore = FirebaseFirestore.getInstance();

        posts = new ArrayList<>();

        getPosts();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(GezdimActivity.this));
        gezdimMainScreenAdapter = new GezdimMainScreenAdapter(posts,this);
        recyclerView.setAdapter(gezdimMainScreenAdapter);


    }


    public void getPosts(){
        CollectionReference collectionReference = firebaseFirestore.collection("Makaleler");
        collectionReference.whereEqualTo("sehir",cityName).whereEqualTo("kategori",kategori).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(GezdimActivity.this,error.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if(value != null){
                    for (DocumentSnapshot snapshot : value.getDocuments()){

                        Map<String,Object> data = snapshot.getData();
                        String baslik = (String) data.get("baslik");
                        String icerik = (String) data.get("icerik");
                        String kategori = (String) data.get("kategori");
                        String sehir = (String) data.get("sehir");
                        Timestamp olusturma_tarihi = (Timestamp) data.get("olusturma_tarihi");
                        String tarih = changeDateFormat(olusturma_tarihi);
                        String koordinat = (String) data.get("koodinat");
                        String foto = (String) data.get("foto");
                        String id = snapshot.getId();

                        postModel = new PostModel(baslik,icerik,kategori,sehir,tarih,koordinat,foto,id);
                        posts.add(postModel);

                        gezdimMainScreenAdapter.notifyDataSetChanged();



                    }
                }
            }
        });
    }


    public static String changeDateFormat(Timestamp timestamp){
        if(timestamp != null){

            Date date = timestamp.toDate();
            DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy HH:mm", new Locale("tr"));
            String tarihString = dateFormat.format(date);
            tarihString = tarihString.replace('-',' ');
            return tarihString;
        }
        return "";
    }

}