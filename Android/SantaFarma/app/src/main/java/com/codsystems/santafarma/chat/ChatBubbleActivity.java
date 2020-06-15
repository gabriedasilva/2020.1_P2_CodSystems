package com.codsystems.santafarma.chat;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.activity.MainActivity;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.codsystems.santafarma.model.Atendimento;
import com.codsystems.santafarma.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatBubbleActivity extends Activity {
    private static final String TAG = "ChatActivity";
    private String idCliente;
    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    Intent intent;
    private boolean side = false;
String nomeCLi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        setContentView(R.layout.activity_chat);


        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        buscarUsuario(auth.getCurrentUser().getUid());
        buttonSend = (Button) findViewById(R.id.buttonSend);

        listView = (ListView) findViewById(R.id.listView1);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.activity_chat_singlemessage);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.chatText);
        chatText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
    }

    private boolean sendChatMessage(){
        chatArrayAdapter.add(new ChatMessage(side, chatText.getText().toString()));
        textCloud();
        chatText.setText("");
        side = side;

        return true;
    }


public boolean receberMessage(){
        chatArrayAdapter.add(new ChatMessage(side,chatText.getText().toString()));
        chatText.setText("");
        side = !side;
        return true;
}
public void consultaMensagem(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Atendimento");
}

    String s = MainActivity.sessao;
    public void setAtendimentoCLoud(){
        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        String uidCliente = auth.getCurrentUser().getUid();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        idCliente = auth.getCurrentUser().getUid();
        System.out.println("USUARIOATUAL:"+idCliente);
        final Map<String,Object> atendimento = new HashMap<>();
        if(!idCliente.isEmpty()) {
            atendimento.put("atendente", "Não Definido");
            atendimento.put("idCliente", uidCliente);
            atendimento.put("situacao", 0);
            atendimento.put("nomeCliente",nomeCLi);
            db.collection("Atendimentos").document(idCliente)

                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                    }
                    DocumentReference docRef = db.collection("Atendimentos").document(idCliente);
                    docRef.set(atendimento);
                }
            });
        }
        }

    public String buscarUsuario(String s){
       FirebaseFirestore db = FirebaseFirestore.getInstance();
       db.collection("Clientes")
               .document(s)
               .get()
               .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                       if(task.isSuccessful()){
                       nomeCLi = task.getResult().getData().get("nome").toString();
                           System.out.println("Nome do CLie"+nomeCLi);
                           setAtendimentoCLoud();
                       }
                   }
               });
       return nomeCLi;
    }

    public void textCloud(){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        long timeST = -1*(time.getTime());
        String s = chatText.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        idCliente = auth.getCurrentUser().getUid();
        Map<String,Object> cliente = new HashMap<>();
        cliente.put("Remetente",idCliente);
        cliente.put("mensagem",s);
        cliente.put("timeStamp",timeST);
        final DocumentReference docRef = db.collection("Clientes").document(idCliente).collection("Chat").document();
        docRef.set(cliente)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        System.out.println(  docRef.get());

                    }
                });

    }

}