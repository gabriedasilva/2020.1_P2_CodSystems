package com.codsystems.santafarma.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.codsystems.santafarma.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends Fragment {
private TextView textContato;
    public ProdutosFragment() {
        // Required empty public constructor
    }

    private ListView list_produtos;
    private String[] produtos= {
        "Estado (UF)","Acre (AC)","Alagoas (AL)","Amapá (AP)",
                "Amazonas (AM)","Bahia (BA)","Ceará (CE)",
                "Distrito Federal (DF)","Espírito Santo (ES)",
                "Goiás (GO)","Maranhão (MA)","Mato Grosso (MT)",
                "Mato Grosso do Sul (MS)","Minas Gerais (MG)",
                "Pará (PA)","Paraíba (PB)","Paraná (PR)",
                "Pernambuco (PE)","Piauí (PI)",
                "Rio de Janeiro (RJ)","Rio Grande do Norte (RN)",
                "Rio Grande do Sul (RS)","Rondônia (RO)",
                "Roraima (RR)","Santa Catarina (SC)",
        "São Paulo (SP)","Sergipe (SE)","Tocantins (TO)"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_produtos, container, false);
       list_produtos = view.findViewById(R.id.lista_produtos);
        ArrayAdapter<String>adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                produtos
        );
        list_produtos.setAdapter(adapter);

        return view;
    }

}
