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
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;

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

            String url_link_loja = "https://www.centauro.com.br/tenis-olympikus-love-feminino-887290.html?cupomId=ab4b58e8-7af6-44f8-b5e6-219d66bb4fc9&cor=02&=Cal%C3%A7ados-Corrida-/-Caminhada-Olympikus-887290-02&origem=google_kenshoo&utm_source=google_gs&utm_medium=SCH_NOB_PLA_Cal%C3%A7ados-Corrida&utm_campaign=all\\cal%C3%A7ados\\corrida%20/%20caminhada\\olympikus\\88729002&gclid=Cj0KCQjw-uzVBRDkARIsALkZAdkCQjdacScDds-sMSjKJymuOAlxOItkQ8ZykBmxGPMIOnAotQQTfw4aAj3YEALw_wcB";
            ShareHashtag.Builder hashtag = new ShareHashtag.Builder();
            hashtag.setHashtag("#Olympikus");
            String url_imagem = "https://static.olympikus.com.br/produtos/tenis-olympikus-thin-2-feminino/91/D22-0304-791/D22-0304-791_zoom1.jpg?resize=1200:*";

            /*



            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://developers.facebook.com"))
                    .setQuote(url_imagem)
                    .setShareHashtag(hashtag.build())
                    .build();


            SharePhotoContent photoContent = new SharePhotoContent.Builder()
                    .setShareHashtag(hashtag.build())
                    .addPhoto(sharePhoto)
                    .build();*/


            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(bitmap)
                    .setUserGenerated(true)
                    .build();

            ShareOpenGraphObject object = new ShareOpenGraphObject.Builder()
                    .putString("og:type", "books.book")
                    .putString("og:title", "Titulo do produto")
                    .putString("og:description", "Descricao do produto")
                    .build();

            ShareOpenGraphAction action = new ShareOpenGraphAction.Builder()
                    .setActionType("books.reads")
                    .putObject("book", object)
                    .putPhoto("og:image", photo)
                    .build();

            ShareOpenGraphContent content = new ShareOpenGraphContent.Builder()
                    .setPreviewPropertyName("book")
                    .setAction(action)
                    .build();


            shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);







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


            }
        });

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }






}
