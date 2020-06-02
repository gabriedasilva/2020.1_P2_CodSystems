package com.codsystems.santafarma.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    Spinner spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        CircleImageView circleImageView = view.findViewById(R.id.imgPerfil);
            circleImageView.setImageResource(R.drawable.padrao);

textView = view.findViewById(R.id.edit_perfil);
        return view;
    }
    public void abrirTelaEditarPerfil(View view){
        Intent intent = new Intent(getContext(), EditPerfilActivity.class);
        startActivity(intent);

    }


}
