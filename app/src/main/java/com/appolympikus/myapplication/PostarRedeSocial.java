package com.appolympikus.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

public class PostarRedeSocial extends AppCompatActivity {

    private Button btncompartilharFace, btncompartilharInstagram;

    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postar_rede_social);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Iniciar o SDK do facebook.

        FacebookSdk.sdkInitialize(getApplicationContext());

        //init fb
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        //Remove a barra de titulo padrão
        //getSupportActionBar().hide();

        btncompartilharFace = (Button) findViewById(R.id.btn_share_to_facebook_id);
        btncompartilharInstagram = (Button) findViewById(R.id.btn_share_to_instagram_id);

        //receber o bitmap do intent
        //receber a hasthtag do produto
        Intent intent = getIntent();
        final Bitmap bitmapPostagem = (Bitmap) intent.getParcelableExtra("BitmapPostRedeSocial");

        btncompartilharFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hasth tag do produto.
                ShareHashtag.Builder hashtag = new ShareHashtag.Builder();
                hashtag.setHashtag("#OlympikusNovaHash");

                //Foto a ser compartilhada
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmapPostagem)
                        .setUserGenerated(true)
                        .build();
                SharePhotoContent photoContent = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .setShareHashtag(hashtag.build())
                        .setPlaceId("Mecs Bar")
                        .build();


                shareDialog.show(photoContent);
            }
        });


    }

}
