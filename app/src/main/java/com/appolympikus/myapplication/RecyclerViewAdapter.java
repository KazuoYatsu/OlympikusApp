package com.appolympikus.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arthur on 23/03/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{


    private Context mContext;
    private List<Produto> mData;

    public RecyclerViewAdapter(Context mContext, List<Produto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_produto,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_titulo_ref.setText(mData.get(position).getTitulo_ref());
        holder.img_produto_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Enviar informacao da pagina de galeria para a pagina de detalhe do produto
                Intent intent = new Intent(mContext, ProdutoActivity.class);
                intent.putExtra("titulo_ref", mData.get(position).getTitulo_ref());
                intent.putExtra("descricao_produto", mData.get(position).getDescricao());
                intent.putExtra("thumbnail", mData.get(position).getThumbnail());
                //Iniciar nova pagina 
                mContext.startActivity(intent);
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_titulo_ref;
        ImageView img_produto_thumbnail;
        CardView cardview;



        public MyViewHolder(View itemView) {
            super(itemView);
            tv_titulo_ref = (TextView) itemView.findViewById(R.id.tv_titulo_ref_id);
            img_produto_thumbnail = (ImageView) itemView.findViewById(R.id.image_view_produto_id);
            cardview = (CardView) itemView.findViewById(R.id.cardview_produto_id);


        }
    }

}
