package com.example.testmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.testmovies.Adapters.MovieAdapter;
import com.example.testmovies.Interface.RecyclerViewInterface;
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

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private static String JSON_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=48c540694574b59258d76c1608bf2d46";

    ArrayList<ModelRV> myMovieList;
    RecyclerView recyclerView;
    LinearLayout linearLayout,LM;
    ImageView posterimage,images;
    TextView name,id,mdescription,mrating,mcast,mdirectors,mgenres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.poster);
        posterimage = findViewById(R.id.posterIMG);
        name = findViewById(R.id.name_txt);
        id = findViewById(R.id.movieID);
        mdescription = findViewById(R.id.descriptiontxt1);
        mcast = findViewById(R.id.castxt);
        mdirectors = findViewById(R.id.directorstxt);
        mrating = findViewById(R.id.ratingtxt);
        images = findViewById(R.id.image_view);



        myMovieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);


        GetData getData = new GetData();
        getData.execute();

    }

    @Override
    public void OnitemClickedListener(int position) {




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

                    ModelRV MRV = new ModelRV();

                    MRV.setId(jsonObject1.getString("release_date"));
                    MRV.setName(jsonObject1.getString("title"));
                    MRV.setImage(jsonObject1.getString("poster_path"));
                    MRV.setRating(jsonObject1.getString("vote_average"));
                    MRV.setDescription(jsonObject1.getString("overview"));

                    myMovieList.add(MRV);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            putDataintoRecyclerView(myMovieList);


        }
    }

    private void putDataintoRecyclerView(List<ModelRV> myMovieList) {

        MovieAdapter adapter = new MovieAdapter(this, myMovieList,this);
        GridLayoutManager gridView = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridView);
        recyclerView.setAdapter(adapter);


    }


}