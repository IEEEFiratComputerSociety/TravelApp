package com.example.travel_app_feed.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_app_feed.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.PostHolder> {

    private ArrayList<String> userEmailList;
    private ArrayList<String> userCommentTextList;
    private ArrayList<String> userYerAdiList;
    private ArrayList<String> userImageList;

    public RecylerViewAdapter(ArrayList<String> userEmailList, ArrayList<String> userCommentTextList, ArrayList<String> userYerAdiList, ArrayList<String> userImageList) {
        this.userEmailList = userEmailList;
        this.userCommentTextList = userCommentTextList;
        this.userYerAdiList = userYerAdiList;
        this.userImageList = userImageList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);


        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.comment.setText(userCommentTextList.get(position));
        holder.yeradi.setText(userYerAdiList.get(position));
        holder.useremail.setText(userEmailList.get(position));
        Picasso.get().load(userImageList.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return userEmailList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView useremail;
        TextView yeradi;
        TextView comment;

        public PostHolder(@NonNull View itemView, TextView comment) {
            super(itemView);
            this.comment = comment;
        }

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            useremail = itemView.findViewById(R.id.Recycler_Row_UserEmail);
            imageView = itemView.findViewById(R.id.Recycler_Row_PostFotosu);
            yeradi = itemView.findViewById(R.id.Recycler_Row_YerAdi);
            comment = itemView.findViewById(R.id.Recycler_Row_Yorumlar);





        }
    }

}
