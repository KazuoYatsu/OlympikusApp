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
import android.widget.EditText;
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



import com.facebook.share.widget.ShareDialog;


import com.facebook.FacebookSdk;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class EditarPostActivity extends AppCompatActivity implements DialogoValores.DialogoValoresListener,DialogoLinkLoja.DialogoValoresListener {

    private ImageView imagem_post_rede_social;
    private EditText txt_local, txt_link;
    private Button btn_compartilhar_foto, btn_voltar,btn_add_link, btn_add_local, btn_add_logo, btn_add_valor;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    private Bitmap logoOverlay;
    private Bitmap bmFinal;

    private String texto_valor, texto_parcelas;

    private static final int PICK_IMAGE = 100;





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


        //Pegar imagem da pagina.
        imagem_post_rede_social.buildDrawingCache();
        //Guarda o bitmap para os botoes manusearem
        bmFinal = imagem_post_rede_social.getDrawingCache();


        //Toolbar da pagina
        btn_compartilhar_foto = (Button) findViewById(R.id.btn_compartilhar_foto_id);
        btn_voltar = (Button) findViewById(R.id.btn_toolbar_voltar_id);
        //Botoes da pagina.
        btn_add_local = (Button) findViewById(R.id.btn_add_local_id);
        btn_add_logo = (Button) findViewById(R.id.btn_editar_logo_id);
        btn_add_valor = (Button) findViewById(R.id.btn_editar_valor_id);
        btn_add_link = (Button) findViewById(R.id.btn_add_link_id);

        //Campos de texto da pagina.
        txt_local = (EditText) findViewById(R.id.edt_local_id);
        txt_link = (EditText) findViewById(R.id.edt_link_id);
        //Ao iniciar , oculte os campos
        txt_local.setVisibility(View.INVISIBLE);
        txt_local.setEnabled(true);
        txt_link.setVisibility(View.INVISIBLE);
        txt_local.setEnabled(true);

        //Eventos de click
        btn_compartilhar_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Inicie a nova acvity
                //Envie o bitmap, strings e local via putExtra.
                //Comprimir o bitmap antes de enviar
                Bitmap bmFINAL = ObterImagemAtual();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmFINAL.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();


                Intent intent = new Intent(getApplicationContext(), PostarRedeSocial.class);
                intent.putExtra("image", byteArray);
                startActivity(intent);

                /*


                });
                String url_imagem = "https://static.olympikus.com.br/produtos/tenis-olympikus-thin-2-feminino/91/D22-0304-791/D22-0304-791_zoom1.jpg?resize=1200:*";

                Picasso.with(getBaseContext())
                        .load(url_imagem)
                        .into(target); */


            }
        });
        btn_add_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
        Bitmap bmp = ObterImagemAtual();

        //drawTextToBitmap(this, ObterImagemAtual(), numeroParcelas);

        //overlay(ObterImagemAtual(), drawTextToBitmap(this, ObterImagemAtual(), numeroParcelas));


    }

    @Override
    public void aplicarStrings(String urlLoja) {
        Log.d("TAG_URLLOJA", urlLoja);
        txt_link.setText(urlLoja);
        txt_link.setVisibility(View.VISIBLE);
        txt_link.setEnabled(false);

    }

    private Bitmap ObterImagemAtual() {

        imagem_post_rede_social.buildDrawingCache();
        Bitmap bmp = imagem_post_rede_social.getDrawingCache();

        return bmp;
    }



    private void AbrirGaleria() {

        //Pegar imagem da pagina.
        Intent galeria = new Intent(Intent.ACTION_GET_CONTENT);
        galeria.setType("image/*");
        startActivityForResult(galeria,1);



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

    private Bitmap overlayLogo(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        //canvas.drawBitmap(bmp2, new Matrix(), null);
        canvas.drawBitmap(bmp2, null, new RectF(50 , 50, canvas.getWidth() / 3 , canvas.getHeight() / 2), null);
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
                imagem_post_rede_social.setImageDrawable(new BitmapDrawable(getResources(), overlayLogo(bmap,logoOverlay)));
                //bmFinal = overlayLogo(bmap,logoOverlay);



                //Toast.makeText(EditarPostActivity.this, "Imagem Aplicada", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






}
