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
import androidx.annotation.Nullable;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.activity.MainActivity;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Timestamp;
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
        consultaMensagem();
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
                textCloud();
                consultaMensagem();
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
        side = false;
        chatText.setText("");
        side = true;

        return true;
    }

    public void consultaMensagem(){
        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Clientes").document(auth.getCurrentUser().getUid())
                .collection("Chat").orderBy("timeStamp", Query.Direction.DESCENDING) //.document().getParent()
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        chatArrayAdapter.clear();
                        if(e != null){

                        }else{
                            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                String sp = (String) documentSnapshot.getData().get("Remetente");
                                if(sp.equalsIgnoreCase("SP")) {
                                    chatArrayAdapter.clear();
                                    String message = (String) documentSnapshot.getData().get("mensagem");
                                    chatArrayAdapter.add(new ChatMessage(true, message));
                                    chatArrayAdapter.clear();
                                }else if(!sp.equalsIgnoreCase("SP")){
                                    chatArrayAdapter.clear();
                                    String message2 = (String) documentSnapshot.getData().get("mensagem");
                                    chatArrayAdapter.add(new ChatMessage(false,message2));
                                    chatArrayAdapter.clear();
                                }
                            }
                        }

                    }
                });

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

                            setAtendimentoCLoud();
                        }

                    }
                });
        return nomeCLi;
    }

    public void textCloud(){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        long timeST = -1*(time.getTime());
        final String s = chatText.getText().toString();
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
                        System.out.println("ENVIOU"+s);
                    }
                });


    }

}
