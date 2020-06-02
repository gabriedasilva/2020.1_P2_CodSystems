package com.codsystems.santafarma.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codsystems.santafarma.R;

public class ProdutoFragment extends Fragment {


    public ProdutoFragment() {
        // Required empty public constructor
    }
private ListView opcoes;
    private String[] listaopcoes = {
    "Chat","Histórico de Pedidos","Editar Perfil"
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_produto, container, false);

        opcoes = view.findViewById(R.id.opcoes_perfil);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listaopcoes);

        return view;
    }
}
