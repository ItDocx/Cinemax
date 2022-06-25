package com.example.testmovies.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmovies.Model.InfoModel;
import com.example.testmovies.R;

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
        v= inflateer.inflate(R.layout.movie_info_items,parent,false);
        return new InfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.InfoViewHolder holder, int position) {

        holder.id.setText(infoModelList.get(position).getId());
        holder.name.setText(infoModelList.get(position).getId());
        // Image Parsing
        Glide.with(infocontext).load("https://image.tmdb.org/t/p/w500/"+infoModelList.
                get(position).getImage()).into(holder.img);





    }

    @Override
    public int getItemCount() {
        return infoModelList.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView id;
        ImageView img;


        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.movieFullname);
            id = itemView.findViewById(R.id.ratingChange);
            img = itemView.findViewById(R.id.posterInfo);




        }
    }
}
