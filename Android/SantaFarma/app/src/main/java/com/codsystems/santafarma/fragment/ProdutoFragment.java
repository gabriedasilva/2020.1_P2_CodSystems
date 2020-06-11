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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_produto, container, false);



        return view;
    }
}
