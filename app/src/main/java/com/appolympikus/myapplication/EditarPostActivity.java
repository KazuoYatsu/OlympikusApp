package com.appolympikus.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class EditarPostActivity extends AppCompatActivity {

    private ImageView imagem_post_rede_social;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_post);

        //getActionBar().hide();
        getSupportActionBar().hide();

        imagem_post_rede_social = (ImageView) findViewById(R.id.img_thumbnail_editar_id);
        Intent intent = getIntent();
        int image = intent.getExtras().getInt("thumbnail_detail");

        imagem_post_rede_social.setImageResource(image);


    }

}
