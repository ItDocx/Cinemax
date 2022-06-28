package com.example.testmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmovies.Interface.RecyclerViewInterface;
import com.example.testmovies.MainActivity;
import com.example.testmovies.Model.ModelRV;
import com.example.testmovies.MovieInfo;
import com.example.testmovies.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<ModelRV> movielist;
    private RecyclerViewInterface onMovieListener;
    private Context context;

    public MovieAdapter(RecyclerViewInterface onMovieListener, List<ModelRV> myMovieList, Context context) {

        this.movielist = myMovieList;
        this.onMovieListener = onMovieListener;
        this.context= context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_items,parent,false);
        return new ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {

        final ModelRV temp = movielist.get(position);

        holder.name.setText(movielist.get(position).getName());
        holder.id.setText(movielist.get(position).getId());

        /*  --------  holder.rating.setText(movielist.get(position).getRating());
        holder.description.setText(movielist.get(position).getDescription());
        holder.directors.setText(movielist.get(position).getDirectors());
        holder.genres.setText(movielist.get(position).getGenres());    -----------   */
        Glide.with(holder.img).
                load("https://image.tmdb.org/t/p/w500/"+movielist.get(position).getImage()).into(holder.img);


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MovieInfo.class);
                intent.putExtra("movie_name",temp.getName());
                intent.putExtra("movie_description",temp.getDescription());
                intent.putExtra("movie_rating",temp.getRating());
                intent.putExtra("movie_image",temp.getImage());
                intent.putExtra("movie_genres",temp.getGenres());
                intent.putExtra("movie_date",temp.getId());
                intent.putExtra("movie_directors",temp.getDirectors());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return movielist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView name;
        ImageView img;
        TextView description;
        TextView rating;
        TextView genres;
        TextView directors;
        TextView cast;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            id = itemView.findViewById(R.id.movieID);
            name = itemView.findViewById(R.id.name_txt);
            img = itemView.findViewById(R.id.image_view);
            description = itemView.findViewById(R.id.descriptiontxt1);
            rating = itemView.findViewById(R.id.ratingtxt);
            genres = itemView.findViewById(R.id.genrestxt);
            directors = itemView.findViewById(R.id.directorstxt);
            cast = itemView.findViewById(R.id.castxt);


        }
    }

}

