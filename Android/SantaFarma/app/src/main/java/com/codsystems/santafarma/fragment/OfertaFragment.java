package com.codsystems.santafarma.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codsystems.santafarma.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfertaFragment extends Fragment {

    public OfertaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_oferta, container, false);
    }
}
