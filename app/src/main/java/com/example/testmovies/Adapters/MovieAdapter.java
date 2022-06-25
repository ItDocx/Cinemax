package com.example.testmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmovies.MainActivity;
import com.example.testmovies.Model.ModelRV;
import com.example.testmovies.MovieInfo;
import com.example.testmovies.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MYViewHolder> {

    Context mcontext;
   private List<ModelRV> mDAta;
   String mname[];
   String mid[];
   String mrating[];
   String mdescription[];
   String mgenres[];
   String mcast[];
   String mdirectors[];
   String mimage[];


    public MovieAdapter(Context mcontext, List<ModelRV> mDAta) {
        this.mcontext = mcontext;
        this.mDAta = mDAta;
    }

    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        v= inflater.inflate(R.layout.movie_items,parent,false);

        return new MYViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder holder, int position) {

        holder.id.setText(mDAta.get(position).getId());
        holder.name.setText(mDAta.get(position).getName());

        // Image Link full: https://image.tmdb.org/t/p/w500/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg


        Glide.with(mcontext).load("https://image.tmdb.org/t/p/w500/"+mDAta.get(position).getImage()).into(holder.img);

        holder.description.setText(mDAta.get(position).getId());
        holder.rating.setText(mDAta.get(position).getId());
        holder.genres.setText(mDAta.get(position).getId());
        holder.cast.setText(mDAta.get(position).getId());
        holder.directors.setText(mDAta.get(position).getId());
    }

    @Override
    public int getItemCount() {

        return mDAta.size();
    }


    public class MYViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView name;
        ImageView img;
        TextView description;
        TextView rating;
        TextView genres;
        TextView directors;
        TextView cast;

        public MYViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.movieID);
            name = itemView.findViewById(R.id.name_txt);
            img= itemView.findViewById(R.id.image_view);
            description = itemView.findViewById(R.id.descriptiontxt1);
            rating = itemView.findViewById(R.id.ratingtxt);
            genres = itemView.findViewById(R.id.genrestxt);
            directors = itemView.findViewById(R.id.directorstxt);
            cast = itemView.findViewById(R.id.castxt);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mcontext, MovieInfo.class);
                    intent.putExtra("movie_name",mname[getAdapterPosition()]);
                    intent.putExtra("movie_id",mid[getAdapterPosition()]);
                    intent.putExtra("movie_rating",mrating[getAdapterPosition()]);
                    intent.putExtra("movie_description",mdescription[getAdapterPosition()]);
                    intent.putExtra("movie_genres",mgenres[getAdapterPosition()]);
                    intent.putExtra("movie_cast",mcast[getAdapterPosition()]);
                    intent.putExtra("movie_director",mdirectors[getAdapterPosition()]);
                    intent.putExtra("movie_images",mimage[getAdapterPosition()]);
                    mcontext.startActivity(intent);


                }
            });


        }


    }
}
