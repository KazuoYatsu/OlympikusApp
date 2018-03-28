package com.appolympikus.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.net.Uri;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;


import com.facebook.FacebookSdk;


public class EditarPostActivity extends AppCompatActivity {

    private ImageView imagem_post_rede_social;
    private Button btn_compartilhar, btn_compartilhar_video, btn_voltar;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Iniciar o SDK do facebook.
        setContentView(R.layout.activity_editar_post);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //init fb
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        //Remove a barra de titulo padrão
        getSupportActionBar().hide();


        imagem_post_rede_social = (ImageView) findViewById(R.id.img_thumbnail_editar_id);
        //Pega a imagem do XML atraves do get Intent
        Intent intent = getIntent();
        int image = intent.getExtras().getInt("thumbnail_detail");
        //Aplica a imagem na tela para o usuário visualizar.
        imagem_post_rede_social.setImageResource(image);

        btn_compartilhar = (Button) findViewById(R.id.btn_compartilhar_id);
        btn_compartilhar_video = (Button) findViewById(R.id.btn_compartilhar_foto_id);
        btn_voltar = (Button) findViewById(R.id.btn_toolbar_voltar_id);

        btn_compartilhar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                compartilharLink();


            }
        });

        btn_compartilhar_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compartilharFoto();
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finishActivity(0);

            }
        });


    }

    private void compartilharFoto(){

        Bitmap foto_facebook = BitmapFactory.decodeResource(getResources(), R.id.img_thumbnail_editar_id);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(EditarPostActivity.this, "compartilhado com sucesso", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {

                Toast.makeText(EditarPostActivity.this, "compartilhamento cancelado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(EditarPostActivity.this, "Erro ao compartilhar", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void compartilharLink() {

        String url_compartilhar = "www.google.com.br";

        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setQuote("Valor do produto ")
                .setContentUrl(Uri.parse(url_compartilhar))
                        .build();

        if(ShareDialog.canShow(ShareLinkContent.class)){
            shareDialog.show(linkContent);
        }
    }


}
