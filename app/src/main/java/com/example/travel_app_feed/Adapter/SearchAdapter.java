package com.example.travel_app_feed.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travel_app_feed.R;
import com.example.travel_app_feed.Model.SearchResultModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.PostHolder> {

    private ArrayList<SearchResultModel> mSearchlist;

    public static class PostHolder extends RecyclerView.ViewHolder{
        public ImageView profilePicture;
        public TextView name;
        public TextView explanation;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            profilePicture = itemView.findViewById(R.id.profile_picture);
            name = itemView.findViewById(R.id.name);
            explanation = itemView.findViewById(R.id.explanation);
        }


    }

    public SearchAdapter(ArrayList<SearchResultModel> searchList){
        mSearchlist = searchList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_searchresult,parent,false);
        PostHolder svh = new PostHolder(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        SearchResultModel curentItem = mSearchlist.get(position);

        holder.profilePicture.setImageResource(curentItem.getProfilePicture());

        holder.name.setText(curentItem.getName());

        holder.explanation.setText(curentItem.getName());

    }

    @Override
    public int getItemCount() {
        return mSearchlist.size();
    }
}
