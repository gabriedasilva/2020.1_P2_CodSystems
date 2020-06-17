package com.codsystems.santafarma.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.codsystems.santafarma.helper.Permissao;
import com.codsystems.santafarma.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EditPerfilActivity extends AppCompatActivity {

    private String[] permissoesNescess={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(MainActivity.sessao.equals("")){
            Toast.makeText(this, "Efetue o Login Novamente!", Toast.LENGTH_SHORT).show();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);
        //VALIDAR PERMISSAO DO PERFIL

        Permissao.validarPermissoes(permissoesNescess,this,1);
        preencherCampoPerfil();
        c_cidadeEditPerf = findViewById(R.id.cidade_editPerfil);
        c_bairroEditPerf = findViewById(R.id.bairro_editPerfil);
        c_ruaEditPerf = findViewById(R.id.rua_editPefil);
        c_numEditPerf = findViewById(R.id.num_end_editPerfil);
        c_compEditPerf = findViewById(R.id.comp_editPerfil);
        c_ptoEditPerf = findViewById(R.id.ptoRef_editPerfil);
        c_telefoneEditPerf = findViewById(R.id.tel_editPerfil);
        c_nomeEditPerf = findViewById(R.id.nome_editPerfil);
        Toast.makeText(this, "TA LOGADO:"+MainActivity.sessao, Toast.LENGTH_SHORT).show();
        System.out.println("--------------------->"+MainActivity.sessao);
        Button btn = findViewById(R.id.btnsalvar_editPerfil);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarUsuario();
            }
        });

    }
    EditText c_cidadeEditPerf, c_bairroEditPerf, c_ruaEditPerf, c_numEditPerf, c_compEditPerf, c_ptoEditPerf, c_telefoneEditPerf, c_nomeEditPerf;

    protected String cidade,bairro,rua,numero,complemento,pto_ref, nome,telefone;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int permissaoReslt :grantResults){
            if(permissaoReslt == PackageManager.PERMISSION_DENIED){
                alertaPermissao();
            }
        }
    }

    private void alertaPermissao(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setCancelable(false);

        builder.setMessage("Para utilizar o app é nescessario aceitar as Permissões!");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void converterString(){

        nome = c_nomeEditPerf.getText().toString();
        telefone = c_telefoneEditPerf.getText().toString();
        cidade = c_cidadeEditPerf.getText().toString();
        bairro = c_bairroEditPerf.getText().toString();
        rua = c_ruaEditPerf.getText().toString();
        numero = c_numEditPerf.getText().toString();
        complemento = c_compEditPerf.getText().toString();
        pto_ref = c_ptoEditPerf.getText().toString();

    }
    public void preencherCampoPerfil(){
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
                        endereco = (ArrayList<String>) doc.getData().get("endereco");

                        System.out.println(telefone + " " + nome + " ENDERECO:" + endereco);

                    }
                    if (!endereco.isEmpty()) {
                        cidade = endereco.get(0);
                        bairro = endereco.get(1);
                        rua = endereco.get(2);
                        num = endereco.get(3);
                        complemento = endereco.get(4);
                        ptoRef = endereco.get(5);
                        c_nomeEditPerf.setText(nome);
                        c_telefoneEditPerf.setText(telefone);
                        c_cidadeEditPerf.setText(cidade);
                        c_bairroEditPerf.setText(bairro);
                        c_ruaEditPerf.setText(rua);
                        c_numEditPerf.setText(num);
                        c_compEditPerf.setText(complemento);
                        c_ptoEditPerf.setText(ptoRef);
                    }else{
                        c_nomeEditPerf.setText("");
                        c_telefoneEditPerf.setText("");
                        c_cidadeEditPerf.setText("");
                        c_bairroEditPerf.setText("");
                        c_ruaEditPerf.setText("");
                        c_numEditPerf.setText("");
                        c_compEditPerf.setText("");
                        c_ptoEditPerf.setText("");
                    }
                    if(telefone !=null){
                        c_telefoneEditPerf.setText(telefone);
                    }else{
                        c_telefoneEditPerf.setText("");
                    }
                }


            }


        });
    }






    public void salvarUsuario(){
        converterString();
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setTelefone(telefone);
        u.setCidade(cidade);
        u.setBairro(bairro);
        u.setRua(rua);
        u.setNumero(numero);
        u.setComplemento(complemento);
        u.setPto_ref(pto_ref);
        u.setEndereco(u);
        u.setUid(MainActivity.sessao);
        u.updateUsuario(u);
        Toast.makeText(this, "Usuario Atualizado Com Sucesso!", Toast.LENGTH_SHORT).show();

    }




}
