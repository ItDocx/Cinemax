package com.example.testmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.testmovies.Adapters.InfoAdapter;
import com.example.testmovies.Adapters.MovieAdapter;
import com.example.testmovies.Dialogues.ShareDialogue;
import com.example.testmovies.Model.InfoModel;
import com.example.testmovies.Model.ModelRV;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieInfo extends AppCompatActivity {



    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=48c540694574b59258d76c1608bf2d46";


    NestedScrollView scrollView;
    ArrayList<InfoModel> infoMovielist;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    LinearLayout LM;
    ImageView posterinfoimage, sharebtn;
    TextView name,release_Date,description,rating,genres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        scrollView = findViewById(R.id.scrollView);
        relativeLayout = findViewById(R.id.layoutInfoPoster);
        posterinfoimage = findViewById(R.id.posterInfo);
        name = findViewById(R.id.moviefullName);
        release_Date = findViewById(R.id.release_date);
        description = findViewById(R.id.descriptionChange);
        rating = findViewById(R.id.ratingChange);
        genres = findViewById(R.id.genresChange);
        recyclerView = findViewById(R.id.recyclerInfoSuggestions);
        LM = findViewById(R.id.layoutRecycler);
        sharebtn = findViewById(R.id.shareIV);


        ShareDialogue shareDialogue = new ShareDialogue(MovieInfo.this);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareDialogue.StartShareDialogue();

                ImageView fb,wtsap,insta;
                fb = findViewById(R.id.fb_share);
                wtsap = findViewById(R.id.whatsapp_share);
                insta = findViewById(R.id.insta_share);
                if (fb.isClickable()){

                    shareDialogue.DismissShareDialogue();
                    Toast.makeText(MovieInfo.this, "Share to Facebook.....", Toast.LENGTH_SHORT).show();
                }
                else if(wtsap.isClickable()){
                    shareDialogue.DismissShareDialogue();
                    Toast.makeText(MovieInfo.this, "Share to Whatsapp.....", Toast.LENGTH_SHORT).show();
                }
                else if (insta.isClickable()){

                    shareDialogue.DismissShareDialogue();
                    Toast.makeText(MovieInfo.this, "Share to Instagram.....", Toast.LENGTH_SHORT).show();
                }
                else{
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            shareDialogue.DismissShareDialogue();

                        }
                    },5000);

                }


            }
        });




        Intent intent = getIntent();

      // posterinfoimage.setImageURI(Uri.parse("https://image.tmdb.org/t/p/w500/"+getIntent().getStringExtra("movie_image")));

        Glide.with(posterinfoimage).
                load("https://image.tmdb.org/t/p/w500/"+intent.getStringExtra("movie_image")).into(posterinfoimage);

        release_Date.setText(intent.getStringExtra("movie_date"));
        name.setText(intent.getStringExtra("movie_name"));
        description.setText(intent.getStringExtra("movie_description"));
        rating.setText(intent.getStringExtra("movie_rating"));
        genres.setText(intent.getStringExtra("movie_genres"));



        infoMovielist = new ArrayList<>();

          putDataintoRecyclerView(infoMovielist);

     GetData getData = new GetData();
        getData.execute();


    }

   public class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try{
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is =urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data!=-1){

                        current += (char)data;
                        data= isr.read();
                    }

                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (urlConnection != null){

                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i =0; i<jsonArray.length(); i++){

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    InfoModel infMrv = new InfoModel();

                    infMrv.setId(jsonObject1.getString("release_date"));
                    infMrv.setName(jsonObject1.getString("title"));
                   infMrv.setImage(jsonObject1.getString("poster_path"));
                    infMrv.setRating(jsonObject1.getString("vote_average"));
                    infMrv.setDescription(jsonObject1.getString("overview"));
                  //  infMrv.setDirectors(jsonObject1.getString("production_companies"));
                   // infMrv.setCast(jsonObject1.getString("budget"));
                    infoMovielist.add(infMrv);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            putDataintoRecyclerView(infoMovielist);


        }
        }

        private void putDataintoRecyclerView(List<InfoModel> infoMovielist) {



        InfoAdapter adapter = new InfoAdapter(this, infoMovielist);
        LinearLayoutManager listView = new LinearLayoutManager(this);
        listView.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(listView);
        recyclerView.setAdapter(adapter);

    }

}