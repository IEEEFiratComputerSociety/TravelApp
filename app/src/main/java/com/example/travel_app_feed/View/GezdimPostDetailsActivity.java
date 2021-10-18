package com.example.travel_app_feed.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel_app_feed.Adapter.GezdimPostCommentsAdapter;
import com.example.travel_app_feed.Model.CommentModel;
import com.example.travel_app_feed.Model.PostModel;
import com.example.travel_app_feed.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class GezdimPostDetailsActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    String kullaniciId;
    RecyclerView recyclerView;
    GezdimPostCommentsAdapter recyclerViewAdapter;
    ArrayList<CommentModel> comments;
    CollectionReference makalelerRef;
    CollectionReference kullanicilarRef;
    CommentModel commentModel;
    Button sendComment;
    Button updateComment;
    EditText commentInput;
    PostModel postModel;
    TextView postBaslik;
    ImageView postResim;
    TextView postIcerik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gezdimpostdetails);

        Intent intent = getIntent();
        postModel = (PostModel) intent.getSerializableExtra("PostClass");

        TextView textViewPostDate = (TextView) findViewById(R.id.txtview_gezdim_postdetails_tarih);
        sendComment = findViewById(R.id.btn_gezdim_postdetails_addcomment);
        commentInput = findViewById(R.id.edittext_gezdim_postdetails_comment);
        updateComment = findViewById(R.id.btn_gezdim_postdetails_updatecomment);
        updateComment.setVisibility(View.INVISIBLE);
        postBaslik = findViewById(R.id.txtview_gezdim_postdetails_baslik);
        postResim = findViewById(R.id.imgview_gezdim_postdetails_foto);
        postIcerik = findViewById(R.id.txtview_gezdim_postdetails_icerik);

        postBaslik.setText(postModel.getBaslik());
        textViewPostDate.setText(postModel.getTarih());
        Picasso.get().load(postModel.getFoto()).into(postResim);
        postIcerik.setText(postModel.getIcerik());


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        makalelerRef = firebaseFirestore.collection("Makaleler");
        kullanicilarRef = firebaseFirestore.collection("Kullanıcılar");

        comments = new ArrayList<>();


        kullaniciId = firebaseAuth.getUid();

        getComments();


        recyclerView = findViewById(R.id.recyclerview_gezdim_postdetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(GezdimPostDetailsActivity.this));
        recyclerViewAdapter = new GezdimPostCommentsAdapter(comments,this,postModel.getId(),makalelerRef,updateComment,sendComment,commentInput);
        recyclerView.setAdapter(recyclerViewAdapter);


    }


    public void getComments(){

        comments.clear();

        makalelerRef.document(postModel.getId()).collection("Makale_Yorumlari").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(GezdimPostDetailsActivity.this,error.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if(value != null){
                    for (DocumentSnapshot snapshot : value.getDocuments()){

                        Map<String,Object> data = snapshot.getData();
                        String kisi_adi = (String) data.get("kullanici_adi");
                        String kisi_foto = (String) data.get("kullanici_resim");
                        String yorum = (String) data.get("yorum_metni");
                        Timestamp y_tarih = (Timestamp) data.get("yorum_tarihi");
                        String tarih = changeDateFormat(y_tarih);
                        String kullanici_id = (String) data.get("kullanici_id");
                        String yorum_id = snapshot.getId();

                        commentModel = new CommentModel(yorum_id,yorum,tarih,kullanici_id,kisi_adi,kisi_foto);
                        comments.add(commentModel);
                        recyclerViewAdapter.notifyDataSetChanged();



                    }
                }
            }
        });

    }




    public void addComment(View v)
    {


        comments.clear();


        Map<String, Object> yorum = new HashMap<>();
        yorum.put("kullanici_adi", "Mami232");
        yorum.put("kullanici_resim", "httpimg");
        yorum.put("yorum_metni", commentInput.getText().toString().trim());
        yorum.put("yorum_tarihi", FieldValue.serverTimestamp());
        yorum.put("kullanici_id", kullaniciId);



                makalelerRef.document(postModel.getId())
                .collection("Makale_Yorumlari").add(yorum)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(GezdimPostDetailsActivity.this,"Yorum başarıyla eklendi",Toast.LENGTH_LONG);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GezdimPostDetailsActivity.this,"Yorum eklenirken hata oluştu.",Toast.LENGTH_LONG);
                    }
                });


                commentInput.setText("");


    }

    public void addToFavorites(View v){


        Map<String, Object> favori = new HashMap<>();
        //favori.put("post_id", postId);
        favori.put("post_baslik", postModel.getBaslik());
        favori.put("post_icerik", postModel.getIcerik());
        favori.put("post_foto", postModel.getFoto());



        kullanicilarRef.document(kullaniciId)
                .collection("Kullanici_Favorileri").document(postModel.getId()).set(favori)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(GezdimPostDetailsActivity.this,"Gönderi başarıyla favorilere eklendi.",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(GezdimPostDetailsActivity.this,"Gönderi favorilere eklenirken hata.",Toast.LENGTH_LONG).show();

            }
        });


    }

    public void openOnMaps(View v){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:"+ postModel.getKoordinat()+"?q=" + Uri.encode(postModel.getBaslik())));
        Intent chooser = Intent.createChooser(intent,"Launch Maps");
        startActivity(chooser);

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