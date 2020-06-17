package com.codsystems.santafarma.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.model.Produto;

public class ProdutoFragment extends Fragment {


    public ProdutoFragment() {
        // Required empty public constructor
    }
private ListView opcoes;
Produto p = ProdutosFragment.prodtela;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_produto, container, false);
        System.out.println(""+p.getNome());
        return view;

    }


}
