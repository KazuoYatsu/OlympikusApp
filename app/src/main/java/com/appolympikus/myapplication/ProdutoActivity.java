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

        //Encontrar os elementos no xml.
        tv_titulo_ref = (TextView) findViewById(R.id.exibir_REF_id);
        tv_descricao = (TextView) findViewById(R.id.exibir_descricao_id);
        img_thumbnail = (ImageView) findViewById(R.id.produto_thumbnail_id);


        //Receber data do Intent da pagina do Recycler.
        Intent intent = getIntent();
        String Titulo_REF = intent.getExtras().getString("titulo_ref");
        String Descricao = intent.getExtras().getString("descricao_produto");
        int image = intent.getExtras().getInt("thumbnail");

        //Setar os valores nos elemenots do XML.
        tv_titulo_ref.setText(Titulo_REF);
        tv_descricao.setText(Descricao);
        img_thumbnail.setImageResource(image);






    }
}
