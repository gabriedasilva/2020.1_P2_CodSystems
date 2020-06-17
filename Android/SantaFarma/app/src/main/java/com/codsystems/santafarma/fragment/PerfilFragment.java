package com.codsystems.santafarma.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.activity.EditPerfilActivity;
import com.codsystems.santafarma.chat.ChatBubbleActivity;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
TextView textView,nomePerf;
FloatingActionButton chatBtn;
    public PerfilFragment() {
        //
        // Required empty public constructor
    }
private ListView listView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preencherPerfil();
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        CircleImageView circleImageView = view.findViewById(R.id.imgPerfil);
        nomePerf = view.findViewById(R.id.nomeUserTx);
        textView = view.findViewById(R.id.edit_perfil);
        textView.setOnClickListener(new View.OnClickListener() {//IDENTIFICAR O CLICAVEL
            @Override
            public void onClick(View v) {
            abrirEditarPerfil();
            }
        });
        chatBtn = view.findViewById(R.id.floatChatButton);
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              abrirTelaChat();
            }
        });

        return view;
    }
    public void abrirTelaChat(){
        Intent intent = new Intent(getActivity(), ChatBubbleActivity.class);
        startActivity(intent);
    }
public void abrirEditarPerfil(){
    Intent intent = new Intent(getActivity(), EditPerfilActivity.class);
    getActivity().startActivity(intent);
}
    public void preencherPerfil(){
        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        String uidCliente = auth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Clientes").whereEqualTo("idCliente",uidCliente).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {

                } else {
                    String nome = "", telefone = "", cidade = "", bairro="", rua="", num="", complemento="", ptoRef="";
                    ArrayList<String> endereco = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                        nome = (String) doc.get("nome");
                        telefone = (String) doc.getData().get("telefone");



                    }

nomePerf.setText(nome);
                }


            }


        });
    }



}
