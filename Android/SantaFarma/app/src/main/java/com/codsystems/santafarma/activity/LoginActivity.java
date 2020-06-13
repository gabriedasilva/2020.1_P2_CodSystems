package com.codsystems.santafarma.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

private TextInputEditText c_email,c_senha;
   private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        c_email = findViewById(R.id.editLoginEmail);
        c_senha = findViewById(R.id.editLoginSenha);
        auth = ConfigFirebase.getFirebaseAutenticacao();

    }

    public void loginUsuario(Usuario u){
        auth.signInWithEmailAndPassword(
                u.getEmail() ,u.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    abrirTelaPrincipal();
                    MainActivity.sessao = auth.getUid();
                }else{
                    String ex ="";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        ex ="Usuário não está Cadastrado!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        ex ="E mail ou senha não Correspondem!";
                    }catch (Exception e){
                        ex = "Erro ao Efetuar Login:" +e.getMessage();
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public void validarLoginUsuario(View view){
        String t_email = c_email.getText().toString();
        String t_senha = c_senha.getText().toString();

        if(!t_email.isEmpty()){ //Verifica e mail e senha;
            if(!t_senha.isEmpty()){
                Usuario u = new Usuario();
                u.setEmail(t_email);
                u.setSenha(t_senha);
                loginUsuario(u);
            }else{
                Toast.makeText(LoginActivity.this, "Preencha a Senha!",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(LoginActivity.this, "Preencha o E-mail!",
                    Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != (null)) {
MainActivity.sessao = auth.getUid();
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaCadastro(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
    public void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
