package com.example.testmovies.Dialogues;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.testmovies.R;

public class ShareDialogue {

    private Activity activity;
    private AlertDialog dialog;


        public ShareDialogue(Activity activity) {

            this.activity = activity;
        }


        public void StartShareDialogue() {

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            LayoutInflater inflater = activity.getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.share_spinner_item, null));
            builder.setCancelable(false);

            dialog = builder.create();
            dialog.show();
        }


     public void DismissShareDialogue() {

            dialog.dismiss();
        }
    }

