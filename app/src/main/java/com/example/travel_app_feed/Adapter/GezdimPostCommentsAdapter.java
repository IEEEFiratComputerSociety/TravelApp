package com.example.travel_app_feed.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel_app_feed.Model.CommentModel;
import com.example.travel_app_feed.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


public class GezdimPostCommentsAdapter extends RecyclerView.Adapter<GezdimPostCommentsAdapter.PostHolder> {

    ArrayList<CommentModel> comments;
    Context context;
    String postId;
    CollectionReference makalelerRef;
    Button updateComment;
    Button sendComment;
    EditText commentInput;


    public GezdimPostCommentsAdapter(ArrayList<CommentModel> comments, Context context, String postId, CollectionReference makalelerRef, Button updateComment, Button sendComment, EditText commentInput) {
        this.comments = comments;
        this.context = context;
        this.postId = postId;
        this.makalelerRef = makalelerRef;
        this.updateComment = updateComment;
        this.sendComment = sendComment;
        this.commentInput = commentInput;
    }



    @NonNull
    @Override
    public GezdimPostCommentsAdapter.PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_gezdim_comments,parent,false);

        return new PostHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GezdimPostCommentsAdapter.PostHolder holder, int position) {

        final CommentModel comment = comments.get(position);
        String yorum = comment.getYorum().toString();
        String tarih = comment.getYorum_tarihi();
        String kullanici = comment.getKullanici_adi().toString();
        holder.yorum.setText(yorum);
        holder.tarih.setText(tarih);
        holder.k_adi.setText(kullanici);




        holder.commentOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.commentOptions);

                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_comment, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if(menuItem.getItemId() == R.id.comment_popup_duzenle){
                            //Toast.makeText(context, "Düzenle", Toast.LENGTH_SHORT).show();

                            sendComment.setVisibility(View.INVISIBLE);
                            updateComment.setVisibility(View.VISIBLE);

                            commentInput.setText(holder.yorum.getText().toString());


                            updateComment.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String newComment = commentInput.getText().toString().trim();
                                    makalelerRef.document(postId)
                                            .collection("Makale_Yorumlari").document(comment.getYorum_id())
                                            .update("yorum_metni" , newComment)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                    updateComment.setVisibility(View.INVISIBLE);
                                                    sendComment.setVisibility(View.VISIBLE);

                                                    comment.setYorum(newComment);

                                                    notifyItemChanged(position);
                                                    //getComments();

                                                    Toast.makeText(context, "Yorum Başarıyla Düzenlendi", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(context, "Yorum Düzenlenirken Hata", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });



                        }
                        else if(menuItem.getItemId() == R.id.comment_popup_sil){
                                    makalelerRef.document(postId)
                                    .collection("Makale_Yorumlari").document(comment.getYorum_id())
                                    .delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(context, "Yorum Başarıyla Silindi", Toast.LENGTH_SHORT).show();

                                            comments.remove(position);
                                            getComments();
                                            notifyDataSetChanged();


                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(context, "Yorum Silinirken Hata Oluştu", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                        else if(menuItem.getItemId() == R.id.comment_popup_sikayet){
                            Toast.makeText(context, "Sikayet", Toast.LENGTH_SHORT).show();
                        }

                         return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

    }

    public void getComments(){

        comments.clear();
        makalelerRef.document(postId).collection("Makale_Yorumlari").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(context,error.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
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

                        CommentModel commentModel = new CommentModel(yorum_id,yorum,tarih,kullanici_id,kisi_adi,kisi_foto);
                        comments.add(commentModel);
                        notifyDataSetChanged();



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

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        //ImageView img;
        TextView yorum;
        TextView tarih;
        TextView k_adi;
        ImageButton commentOptions;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

           // img = itemView.findViewById(R.id.img_second_yorum);
            yorum = itemView.findViewById(R.id.txt_second_yorum_metni);
            tarih = itemView.findViewById(R.id.txt_second_yorum_tarihi);
            k_adi = itemView.findViewById(R.id.txt_second_kullanici_adi);
            commentOptions = itemView.findViewById(R.id.comment_options);
            commentOptions.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Ne yapmak istiyorsunuz?");
            menu.add(this.getAdapterPosition(),121,getAdapterPosition(),"Yorumu Düzenle.");
            menu.add(this.getAdapterPosition(),122,getAdapterPosition(),"Yorumu Sil.");
        }
    }
}
