package com.appolympikus.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProdutoActivity extends AppCompatActivity {

    private TextView tv_titulo_ref, tv_descricao;
    private ImageView img_thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        Intent intent = getIntent();

        //Receber data do Intent da pagina do Recycler.

        String Titulo_REF = intent.getExtras().getString("titulo_ref");
        String Descricao = intent.getExtras().getString("descricao_produto");
        int image = intent.getExtras().getInt("thumbnail");


    }
}
