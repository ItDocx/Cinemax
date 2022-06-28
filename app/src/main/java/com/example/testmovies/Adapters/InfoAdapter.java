package com.example.testmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmovies.Interface.RecyclerViewInterface;
import com.example.testmovies.Model.InfoModel;
import com.example.testmovies.R;

import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {


    private Context infocontext;
    private List<InfoModel> infoModelList;


    public InfoAdapter(Context infocontext, List<InfoModel> infoModelList) {
        this.infocontext = infocontext;
        this.infoModelList = infoModelList;
    }

    @NonNull
    @Override
    public InfoAdapter.InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflateer = LayoutInflater.from(infocontext);
        v= inflateer.inflate(R.layout.info_recycler_suggestion_items,parent,false);
        return new InfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.InfoViewHolder holder, int position) {




        holder.id.setText(infoModelList.get(position).getId());
        holder.name.setText(infoModelList.get(position).getId());
        // Image Parsing
        Glide.with(infocontext).load("https://image.tmdb.org/t/p/w500/"+infoModelList.
                get(position).getImage()).into(holder.img);
/*
        holder.description.setText(infoModelList.get(position).getDescription());
        holder.cast.setText(infoModelList.get(position).getCast());
        holder.genres.setText(infoModelList.get(position).getGenres());
        holder.directors.setText(infoModelList.get(position).getDirectors());
        holder.rating.setText(infoModelList.get(position).getRating());     */






    }

    @Override
    public int getItemCount() {
        return infoModelList.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView name;
        ImageView img;
      //  TextView description;
      //  TextView rating;
      //  TextView genres;
      //  TextView directors;
      //  TextView cast;


        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_sugg_txt);
            id = itemView.findViewById(R.id.movieid_sugg_txt);
            img = itemView.findViewById(R.id.movie_sugg_img);
        //    description = itemView.findViewById(R.id.descriptionChange);
        //    rating = itemView.findViewById(R.id.ratingChange);
        //    genres = itemView.findViewById(R.id.genresChange);
        //    directors  = itemView.findViewById(R.id.directorsChange);
        //    cast = itemView.findViewById(R.id.castChange);
            
        }
    }
}
