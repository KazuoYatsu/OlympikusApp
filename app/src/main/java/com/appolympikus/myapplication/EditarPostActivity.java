package com.appolympikus.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;import android.content.Context;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;


import com.facebook.FacebookSdk;


public class EditarPostActivity extends AppCompatActivity {

    private ImageView imagem_post_rede_social;
    private Button btn_compartilhar;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_post);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //init fb
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        //getActionBar().hide();
        getSupportActionBar().hide();

        imagem_post_rede_social = (ImageView) findViewById(R.id.img_thumbnail_editar_id);
        Intent intent = getIntent();
        int image = intent.getExtras().getInt("thumbnail_detail");

        imagem_post_rede_social.setImageResource(image);

        btn_compartilhar = (Button) findViewById(R.id.btn_compartilhar_id);

        btn_compartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("This a useful link ")
                        .setContentUrl("https://youtube.com")
                        .build();

                if(ShareDialog.canShow(ShareLinkContent.Cla))






            }
        });


    }

    private void PostFB(){
        //Implementar a função post fb
        Context context = getApplicationContext();
        CharSequence text = "Hello Share Facebook!!!!!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        SharePhoto photo = new SharePhoto.Builder().setBitmap(imagem_post_rede_social.getDrawingCache()).build();
        SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
        ShareDialog dialog = new ShareDialog(this);
        if (dialog.canShow(SharePhotoContent.class)){
            dialog.show(content);

        }
        else{
            Log.d("Activity", "you cannot share photos :(");
        }


    }

}
