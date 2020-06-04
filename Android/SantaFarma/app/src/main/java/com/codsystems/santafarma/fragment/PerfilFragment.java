package com.codsystems.santafarma.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.activity.EditPerfilActivity;
import com.codsystems.santafarma.activity.LoginActivity;
import com.codsystems.santafarma.activity.MainActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
TextView textView;
    public PerfilFragment() {
        // Required empty public constructor
    }
private ListView listView;
    private String[] opcoes = {
      "Úlimas Pedidos",
            "MENU-1",
            "MENU-2",
            "MENU-3"
    };
    Spinner spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        CircleImageView circleImageView = view.findViewById(R.id.imgPerfil);
        textView = view.findViewById(R.id.edit_perfil);

        textView.setOnClickListener(new View.OnClickListener() {//IDENTIFICAR O CLICAVEL
            @Override
            public void onClick(View v) {
            abrirEditarPerfil();
            }
        });
        listView = view.findViewById(R.id.opcoes_perfil);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                opcoes);
        listView.setAdapter(adapter);
        return view;
    }
public void abrirEditarPerfil(){
    Intent intent = new Intent(getActivity(), EditPerfilActivity.class);
    getActivity().startActivity(intent);
}



}
