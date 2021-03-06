package com.appolympikus.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProdutoActivity extends AppCompatActivity {

    private TextView tv_titulo_ref, tv_descricao;
    private ImageView img_thumbnail;
    private Button btnshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        //487261

        //Encontrar os elementos no xml.
        tv_titulo_ref = (TextView) findViewById(R.id.exibir_REF_id);
        tv_descricao = (TextView) findViewById(R.id.exibir_descricao_id);
        img_thumbnail = (ImageView) findViewById(R.id.produto_thumbnail_id);
        btnshare = (Button) findViewById(R.id.btnEditarPostar_id);

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CharSequence text = "Hello Face!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();

            }
        });


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
