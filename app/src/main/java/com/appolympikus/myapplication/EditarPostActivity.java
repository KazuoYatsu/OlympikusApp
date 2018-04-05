package com.appolympikus.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.net.Uri;
import android.widget.Toast;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.Rect;
import android.content.Context;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;


import com.facebook.share.widget.ShareDialog;


import com.facebook.FacebookSdk;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;


public class EditarPostActivity extends AppCompatActivity implements DialogoValores.DialogoValoresListener,DialogoLinkLoja.DialogoValoresListener {

    private ImageView imagem_post_rede_social;
    private Button btn_compartilhar_foto, btn_voltar,btn_add_link, btn_add_local, btn_add_logo, btn_add_valor;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    private Bitmap logoOverlay;
    private Bitmap bmFinal;

    private String texto_valor, texto_parcelas;

    private static final int PICK_IMAGE = 100;


    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            String url_link_loja = "www.centauro.com.br";
            ShareHashtag.Builder hashtag = new ShareHashtag.Builder();
            hashtag.setHashtag("#OlympikusNovaHash");
            String url_imagem = "https://static.olympikus.com.br/produtos/tenis-olympikus-thin-2-feminino/91/D22-0304-791/D22-0304-791_zoom1.jpg?resize=1200:*";

            //Pegar imagem da pagina.
            imagem_post_rede_social.buildDrawingCache();
            Bitmap bmap = imagem_post_rede_social.getDrawingCache();

            //Bitmap do picasso setBitmap(bitmap)

            //Foto a ser compartilhada
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(bmFinal)
                    .setUserGenerated(true)
                    .build();
            SharePhotoContent photoContent = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .setShareHashtag(hashtag.build())
                    .setPlaceId("Mecs Bar")
                    .build();


            shareDialog.show(photoContent);



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
        setContentView(R.layout.activity_editar_logo);
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

        btn_add_local = (Button) findViewById(R.id.btn_add_local_id);

        btn_add_logo = (Button) findViewById(R.id.btn_editar_logo_id);

        btn_add_valor = (Button) findViewById(R.id.btn_editar_valor_id);

        btn_add_link = (Button) findViewById(R.id.btn_add_link_id);

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
        btn_add_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(EditarPostActivity.this, "Selecione uma foto do device", Toast.LENGTH_LONG).show();

                AbrirGaleria();

            }
        });
        btn_add_valor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                abrirDialogoEditarValores();

            }
        });

        btn_add_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(EditarPostActivity.this, "Selecione um link", Toast.LENGTH_LONG).show();
                abrirDialogoLink();

            }
        });
    }

    private void abrirDialogoEditarValores(){

        DialogoValores dialogoValores = new DialogoValores();
        dialogoValores.show(getSupportFragmentManager(),"Dialogo Valores");


    }

    private void abrirDialogoLink(){
        DialogoLinkLoja dialogoLinkLoja = new DialogoLinkLoja();
        dialogoLinkLoja.show(getSupportFragmentManager(), "Dialogo link");
    }

    @Override
    public void aplicarStrings(String numeroParcelas, String valorParcela) {

        Log.d("TAG_NUMPARCELAS", numeroParcelas);
        Log.d("TAG_VALORPARCELAS", valorParcela);

        drawTextToBitmap(this,bmFinal, numeroParcelas);



    }

    @Override
    public void aplicarStrings(String urlLoja) {
        Log.d("TAG_URLLOJA", urlLoja);

    }

    private void AbrirGaleria() {

        //Pegar imagem da pagina.
        Intent galeria = new Intent(Intent.ACTION_GET_CONTENT);
        galeria.setType("image/*");
        startActivityForResult(galeria,1);

        //Sobrepor a imagem da galeria
        //imagem_post_rede_social.setImageDrawable(new BitmapDrawable(getResources(), overlay(bmap,logoOverlay)));

    }
    public Bitmap drawTextToBitmap(Context gContext,
                                   Bitmap bitmap,
                                   String gText) {

        float scale = bitmap.getDensity();
        Bitmap bmOverlay = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        Canvas canvas = new Canvas(bmOverlay);
        // new antialised Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // text color - #3D3D3D
        paint.setColor(Color.rgb(61, 61, 61));
        // text size in pixels
        paint.setTextSize((int) (32 * scale));
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

        // draw text to the Canvas center
        Rect bounds = new Rect();
        paint.getTextBounds(gText, 0, gText.length(), bounds);
        int x = (bitmap.getWidth() - bounds.width())/2;
        int y = (bitmap.getHeight() + bounds.height())/2;

        canvas.drawText(gText, x, y, paint);

        return bitmap;
    }

    private Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        //canvas.drawBitmap(bmp2, new Matrix(), null);
        canvas.drawBitmap(bmp2, null, new RectF(20, 20, canvas.getWidth() / 3 , canvas.getHeight() / 2), null);
        return bmOverlay;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1)
        {
            Uri chosenImageUri = data.getData();

            Bitmap mBitmap = null;
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), chosenImageUri);
                logoOverlay = mBitmap;
                imagem_post_rede_social.buildDrawingCache();
                Bitmap bmap = imagem_post_rede_social.getDrawingCache();
                imagem_post_rede_social.setImageDrawable(new BitmapDrawable(getResources(), overlay(bmap,logoOverlay)));
                bmFinal = overlay(bmap,logoOverlay);



                Toast.makeText(EditarPostActivity.this, "Imagem Aplicada", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






}
