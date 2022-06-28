package com.example.testmovies.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmovies.Interface.HeaderRecyclerInterFace;
import com.example.testmovies.Interface.RecyclerViewInterface;
import com.example.testmovies.MainActivity;
import com.example.testmovies.Model.headerModel;
import com.example.testmovies.R;

import java.util.List;

public class headerAdapter extends RecyclerView.Adapter<headerAdapter.ViewHolder> {


    private List<headerModel> headerList;
    private Context headercontext;
    private HeaderRecyclerInterFace onMovieListener;

    public headerAdapter(Context headercontext, List<headerModel> headerModelArrayList, HeaderRecyclerInterFace onMovieListener) {

        this.headerList = headerModelArrayList;
        this.headercontext = headercontext;
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public headerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflateer = LayoutInflater.from(headercontext);
        view =  inflateer.inflate(R.layout.header_main,parent,false);
        return new ViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull headerAdapter.ViewHolder holder, int position) {

        holder.nameTxt.setText(headerList.get(position).getName());
        holder.dateTxt.setText(headerList.get(position).getReleaseDate());

        // Passing Image into RecyclerView
        Glide.with(headercontext).load("https://image.tmdb.org/t/p/w500/"+headerList.
                get(position).getImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return headerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTxt;
        TextView dateTxt;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_header_txt);
            dateTxt = itemView.findViewById(R.id.movieid_header_txt);
            imageView = itemView.findViewById(R.id.movie_header_img);




        }
    }
}
