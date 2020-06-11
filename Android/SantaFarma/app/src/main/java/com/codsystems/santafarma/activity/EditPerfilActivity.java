package com.codsystems.santafarma.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.helper.Permissao;
import com.codsystems.santafarma.model.Usuario;

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
        t1 = findViewById(R.id.cidade_editPerfil);
        t2 = findViewById(R.id.bairro_editPerfil);
        t3 = findViewById(R.id.rua_editPefil);
        t4 = findViewById(R.id.num_end_editPerfil);
        t5 = findViewById(R.id.comp_editPerfil);
        t6 = findViewById(R.id.ptoRef_editPerfil);
        t7 = findViewById(R.id.tel_editPerfil);
        t8 = findViewById(R.id.nome_editPerfil);
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
    EditText t1,t2,t3,t4,t5,t6,t7,t8;

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

       nome = t8.getText().toString();
       telefone = t7.getText().toString();
       cidade = t1.getText().toString();
       bairro = t2.getText().toString();
       rua = t3.getText().toString();
       numero = t4.getText().toString();
       complemento = t5.getText().toString();
       pto_ref = t6.getText().toString();

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
