package com.codsystems.santafarma.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.fragment.app.Fragment;

import com.codsystems.santafarma.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TesteSpinnerFragment extends Fragment {

    public TesteSpinnerFragment() {
        // Required empty public constructor
    }

private Spinner spin1;

    private String[]lista_de_categorias = {
      "Medicamentos",
      "Mamãe e Bebê",
      "Cosméticos"
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teste_spinner, container, false);
spin1 = view.findViewById(R.id.spinner_teste);
        SpinnerAdapter adapter = new ArrayAdapter<>(
          getActivity(),
          android.R.layout.simple_spinner_dropdown_item,
          lista_de_categorias
        );
spin1.setAdapter(adapter);

        return view;
    }
}
