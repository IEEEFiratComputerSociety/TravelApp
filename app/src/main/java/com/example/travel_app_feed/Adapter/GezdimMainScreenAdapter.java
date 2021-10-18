package com.example.travel_app_feed.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travel_app_feed.View.GezdimPostDetailsActivity;
import com.example.travel_app_feed.Model.PostModel;
import com.example.travel_app_feed.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class GezdimMainScreenAdapter extends RecyclerView.Adapter<GezdimMainScreenAdapter.PostHolder> {
    public interface OnItemClickListener {
        public void onItemClicked(int position);
    }


    ArrayList<PostModel> posts;
    Context context;

    public GezdimMainScreenAdapter(ArrayList<PostModel> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_gezdim_main,parent,false);


        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        final PostModel post = posts.get(position);
        holder.baslik.setText(post.getBaslik());
        Picasso.get().load(post.getFoto()).into(holder.img);



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, GezdimPostDetailsActivity.class);
                intent.putExtra("PostClass",post);
                context.startActivity(intent);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView baslik;
        CardView cardView;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgview_gezdim_main_foto);
            baslik = itemView.findViewById(R.id.txtview_gezdim_main_baslik);
            cardView = itemView.findViewById(R.id.cdView);
        }

    }


}
