package com.codsystems.santafarma.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.model.BuscasCloud;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    static String cat;
    ImageButton imb1,imb2,imb3,imb4,imb5,imb6,imb7;
    public HomeFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imb1 = view.findViewById(R.id.medicam_btn);
        imb2 = view.findViewById(R.id.beleza_btn);
        imb3 = view.findViewById(R.id.higiene_btn);
        imb4 = view.findViewById(R.id.mamaeBebe_btn);
        imb5 = view.findViewById(R.id.pSocorros_btn);
        imb6 = view.findViewById(R.id.vitSupleNatu_btn);
        botoesClicaveis();
        return view;
    }


    public void botoesClicaveis(){
        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat = "Medicamentos";

            }
        });
        imb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat = "Beleza";

            }
        });
        imb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat = "Higiene Pessoal";
            }
        });
        imb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat = "Mamãe e Bebê";

            }
        });
        imb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat = "Primeiros Socorros/Dor";
            }
        });
        imb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat = "Vitaminas/Suplementos/Naturais";
            }
        });


    }


}
