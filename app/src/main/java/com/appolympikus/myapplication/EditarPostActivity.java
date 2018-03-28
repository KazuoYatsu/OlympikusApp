package com.appolympikus.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import com.facebook.share.Share;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;


import com.facebook.FacebookSdk;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class EditarPostActivity extends AppCompatActivity {

    private ImageView imagem_post_rede_social;
    private Button btn_compartilhar_foto, btn_voltar,btn_compartilhar_link;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;


    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            SharePhoto sharePhoto = new SharePhoto.Builder()
                    .setBitmap(bitmap)
                    .build();
            if(ShareDialog.canShow(SharePhotoContent.class)){
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(sharePhoto)
                        .build();

                shareDialog.show(content);
            }


        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

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

        //Toolbar da pagina
        btn_compartilhar_foto = (Button) findViewById(R.id.btn_compartilhar_foto_id);
        btn_voltar = (Button) findViewById(R.id.btn_toolbar_voltar_id);



        btn_compartilhar_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(EditarPostActivity.this, "Compartilhado com sucesso", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(EditarPostActivity.this, "Compartilhamento cancelado", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(EditarPostActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
                String url_imagem = "https://static.olympikus.com.br/produtos/tenis-olympikus-thin-2-feminino/91/D22-0304-791/D22-0304-791_zoom1.jpg?resize=1200:*";

                Picasso.with(getBaseContext())
                        .load(url_imagem)
                        .into(target);

                //Toast.makeText(EditarPostActivity.this, "cliquei", Toast.LENGTH_LONG).show();
            }
        });

    }






}
