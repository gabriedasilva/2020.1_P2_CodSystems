package com.codsystems.santafarma.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codsystems.santafarma.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends Fragment {
private TextView textContato;
    public ProdutosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produtos, container, false);
        return view;
    }
}
