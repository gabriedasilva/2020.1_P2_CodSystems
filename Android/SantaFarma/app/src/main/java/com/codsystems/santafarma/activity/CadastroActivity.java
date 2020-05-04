package com.codsystems.santafarma.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.codsystems.santafarma.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

private TextInputEditText c_nome,c_senha,c_senhaConf,c_email;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        c_nome = findViewById(R.id.editNome);
        c_email = findViewById(R.id.editEmail);
        c_senha = findViewById(R.id.editSenha);
        c_senhaConf = findViewById(R.id.editSenhaConf);
    }
    public void validarCadastroUsuario(View view){

        String t_nome = c_nome.getText().toString();
        String t_email = c_email.getText().toString();
        String t_senha = c_senha.getText().toString();
        String t_senhaConf = c_senhaConf.getText().toString();

        if(!t_nome.isEmpty()){
            if(!t_email.isEmpty()){
                if(!t_senha.isEmpty()){
                    if(t_senha.equals(t_senhaConf)){
                        Usuario u = new Usuario();
                        u.setNome(t_nome);
                        u.setEmail(t_email);
                        u.setSenha(t_senha);
                    cadastarUsuario(u);
                    }else{
                        Toast.makeText(CadastroActivity.this, "As senhas não Correspondem!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroActivity.this, "Preencha a Senha!",
                            Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(CadastroActivity.this, "Preencha o E--mail!",
                        Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(CadastroActivity.this, "Preencha o Nome!",
            Toast.LENGTH_SHORT).show();
        }
    }
    public void cadastarUsuario(Usuario u){

        auth = ConfigFirebase.getFirebaseAutenticacao();
        auth.createUserWithEmailAndPassword(
                u.getEmail(),u.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            Toast.makeText(CadastroActivity.this, "Usuário Cadastrado Com Sucesso!",
                    Toast.LENGTH_SHORT).show();
            finish();
        }else{
            String ex ="";
            try {
                throw task.getException();
            }catch (FirebaseAuthWeakPasswordException e){
            ex ="Insira uma senha mais Forte!";
            }catch (FirebaseAuthInvalidCredentialsException e){
            ex = "Insira um E-mail Válido!";
            }catch (FirebaseAuthUserCollisionException e){
            ex = "Esta Conta Ja foi Cadastrada!";
            }catch (Exception e){
                ex = "Erro ao Cadastrar Usuário" +e.getMessage();
                e.printStackTrace();
            }
            Toast.makeText(CadastroActivity.this, "Erro:"+ex,
                    Toast.LENGTH_SHORT).show();
        }
            }
        });

    }
}
