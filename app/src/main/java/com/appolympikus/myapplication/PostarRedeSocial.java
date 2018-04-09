package com.appolympikus.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.provider.MediaStore;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.facebook.share.Sharer;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import android.net.Uri;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.FileNotFoundException;


public class PostarRedeSocial extends AppCompatActivity {

    private Button btncompartilharFace, btncompartilharInstagram;

    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    private Bitmap bitmapPostagem;





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
        //receber a hasthtag do pra
        //Recebe o bitmap em array e volta ele ao normal.
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bitmapPostagem = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        btncompartilharFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                compartilharFotoNoFacebook(bitmapPostagem);
                //Retorno do Dialog
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(PostarRedeSocial.this, "Compartilhado com sucesso", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancel() {
                        Toast.makeText(PostarRedeSocial.this, "Compartilhamento cancelado", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(PostarRedeSocial.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }


                });







            }
        });
        btncompartilharInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(PostarRedeSocial.this, "You do not have permission to publish on Instagram", Toast.LENGTH_LONG).show();
                compartilhaFotoNoInstragram(bitmapPostagem);
            }
        });

    }

    private void compartilhaFotoNoInstragram(Bitmap bm) {




        Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (intent != null)
        {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setPackage("com.instagram.android");

            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bm, "I am Happy", "Share happy !")));

        }
        else
        {
            // bring user to the market to download the app.
            // or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id="+"com.instagram.android"));
            startActivity(intent);
        }



    }

    private void compartilharFotoNoFacebook(Bitmap bm) {

        //Hasth tag do produto.
        ShareHashtag.Builder hashtag = new ShareHashtag.Builder();
        hashtag.setHashtag("#OlympikusNovaHash");

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bm)
                .setUserGenerated(true)
                .build();
        SharePhotoContent photoContent = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .setShareHashtag(hashtag.build())
                .build();

        shareDialog.show(photoContent);


    }


}
