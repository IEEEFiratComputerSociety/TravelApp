package com.example.travel_app_feed.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_app_feed.Adapter.RecylerViewAdapter;
import com.example.travel_app_feed.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    ImageButton DmButonu;
    ImageButton GezdimButonu;
    ImageButton GordumButonu;
    ImageButton ProfilFotosu;
    TextView KullaniciAdi;
    ImageButton BegenmeButonu;
    ImageButton YorumButonu;
    ImageButton GondermeButonu;
    ImageButton FavoriButonu;
    TextView YerAdi;
    ImageView PostFotosu;
    TextView Yorumlar;
    EditText YorumYap;
    FirebaseFirestore firebaseFirestore;

    ArrayList<String> userLocationFromFB;
    ArrayList<String> userEmailFromFB;
    ArrayList<String> userCommentFromFB;
    ArrayList<String> userImageFromFB;
    com.example.travel_app_feed.Adapter.RecylerViewAdapter recylerViewAdapter;
    private Object RecylerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GezdimButonu =  findViewById(R.id.GezdimButonu);
        GordumButonu = findViewById(R.id.GordumButonu);
        YorumYap = findViewById(R.id.Recycler_Row_YorumYap);
        YerAdi = findViewById(R.id.Recycler_Row_YerAdi);
        DmButonu = findViewById(R.id.Recyler_Row_Dmbutton);
        ProfilFotosu = findViewById(R.id.Recycler_Row_ProfilFotosu);
        firebaseFirestore = FirebaseFirestore.getInstance();
        KullaniciAdi = findViewById(R.id.Recycler_Row_UserEmail);
        BegenmeButonu = findViewById(R.id.Recycler_Row_BegenmeButonu);
        YorumButonu = findViewById(R.id.Recycler_Row_YorumButonu);
        GondermeButonu = findViewById(R.id.Recycler_Row_GondermeButonu);
        FavoriButonu = findViewById(R.id.Recycler_Row_FavoriButonu);
        PostFotosu = findViewById(R.id.Recycler_Row_PostFotosu);
        Yorumlar = findViewById(R.id.Recycler_Row_Yorumlar);
        userLocationFromFB = new ArrayList<>();
        userCommentFromFB = new ArrayList<>();
        userEmailFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();


        RecyclerView recyclerView = findViewById(R.id.AnaRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recylerViewAdapter = new RecylerViewAdapter(userEmailFromFB,userCommentFromFB,userLocationFromFB,userImageFromFB);
        recyclerView.setAdapter(recylerViewAdapter);
        getDataFromFirebase();




    }





    public void DmButonu (View view) {

    }
    public void GezdimButonu(View view) {
        // Gezdim ekranına intent yapılacak.
        Intent intent = new Intent(MainActivity.this, UploadActivity.class);
        startActivity(intent );
    }
    public void ProfilFotosu (View view) {
        //  Kulanıcının profile intent yapılacak
    }
    public void BegenmeButonu (View view) {
        // Begenince kalbi kırmızı yap.
    }
    public void YorumButonu (View view) {
        //
    }
    public void GondermeButonu (View view) {

    }
    public void FavoriButonu (View view) {

    }
    public void getDataFromFirebase () {

        CollectionReference collectionReference = firebaseFirestore.collection("Posts");



        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Toast.makeText(MainActivity.this, error.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                }

                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()){

                        Map<String,Object> data = snapshot.getData();

                        String comment = (String) data.get("comment");
                        String userEmail = (String) data.get("useremail");
                        String downloadUrl = (String) data.get("downloadurl");
                        String location = (String) data.get("location");


                        userLocationFromFB.add(location);
                        userCommentFromFB.add(comment);
                        userEmailFromFB.add(userEmail);
                        userImageFromFB.add(downloadUrl);

                        recylerViewAdapter.notifyDataSetChanged();

                    }
                }


            }
        });

    }




}
