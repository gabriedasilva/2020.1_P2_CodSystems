package com.codsystems.santafarma.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.codsystems.santafarma.R;

public class PerfilFragment extends Fragment {

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        String[] ufSpinner = new String[] {
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
       Spinner s = (Spinner) getView().findViewById(R.id.spinnerUF);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
            R.layout.support_simple_spinner_dropdown_item,R.id.spinnerUF,ufSpinner
            );
    s.setAdapter(adapter);


        super.onCreate(savedInstanceState);
    }
}
