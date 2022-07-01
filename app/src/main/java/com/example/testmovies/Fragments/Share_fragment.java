package com.example.testmovies.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testmovies.Adapters.InfoAdapter;
import com.example.testmovies.MovieInfo;
import com.example.testmovies.R;

public class Share_fragment extends Fragment {


    TextView back_Btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_share_fragment, container, false);


     return view;

    }
}