package com.appolympikus.myapplication;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProdutoActivity extends AppCompatActivity implements  View.OnClickListener {

    private TextView tv_titulo_ref, tv_descricao;
    private ImageView img_thumbnail;
    private Button btnshare;

    private Context mContext;

    public ProdutoActivity() {
    }

    public ProdutoActivity(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        //487261

        //Encontrar os elementos no xml.
        tv_titulo_ref = (TextView) findViewById(R.id.exibir_REF_id);
        tv_descricao = (TextView) findViewById(R.id.exibir_descricao_id);
        img_thumbnail = (ImageView) findViewById(R.id.produto_thumbnail_id);
        btnshare = (Button) findViewById(R.id.btn_editar_logo_id);

        btnshare.setOnClickListener(this);



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

    @Override
    public void onClick(View view) {

        //Intent intent = new Intent(mContext, EditarPostActivity.class);
        //mContext.startActivity(intent);

        Intent intent = new Intent(getApplicationContext(), EditarPostActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);



    }


}
