package com.appolympikus.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class DialogoValores extends AppCompatDialogFragment {

    private EditText editTextValorParcela;
    private EditText editTextNumeroParcelas;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_valores, null);

        builder.setView(view)
                .setTitle("Informe os valores")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        editTextNumeroParcelas = view.findViewById(R.id.edit_valor_nparcelas_id);
        editTextValorParcela = view.findViewById(R.id.edit_valor_parcela_id);

        

        return builder.create();
    }


}
