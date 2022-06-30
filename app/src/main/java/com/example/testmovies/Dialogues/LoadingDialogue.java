package com.example.testmovies.Dialogues;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testmovies.R;

public class LoadingDialogue {

    private Activity activity;
    private AlertDialog alertDialog;

    public LoadingDialogue(Activity activity){

        this.activity = activity;
    }


    public void StartLoadingDialogue(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.loading_dialogue_layout,null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();
    }


    public void DismissDialogue(){

        alertDialog.dismiss();
    }

}
