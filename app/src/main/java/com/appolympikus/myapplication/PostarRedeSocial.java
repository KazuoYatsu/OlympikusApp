package com.appolympikus.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PostarRedeSocial extends AppCompatActivity {

    private Button btncompartilharFace, btncompartilharInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postar_rede_social);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btncompartilharFace = (Button) findViewById(R.id.btn_share_to_facebook_id);
        btncompartilharInstagram = (Button) findViewById(R.id.btn_share_to_instagram_id);

        //receber o bitmap do intent
        //receber a hasthtag do produto
        

    }

}
