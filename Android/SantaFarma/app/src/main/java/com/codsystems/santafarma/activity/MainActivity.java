package com.codsystems.santafarma.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.chat.ChatBubbleActivity;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.codsystems.santafarma.fragment.HomeFragment;
import com.codsystems.santafarma.fragment.PromocaoFragment;
import com.codsystems.santafarma.fragment.PedidosFragment;
import com.codsystems.santafarma.fragment.PerfilFragment;
import com.codsystems.santafarma.fragment.ProdutosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public static String sessao;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = ConfigFirebase.getFirebaseAutenticacao();

//Configuração da Toolbar
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarprincipal);
        setSupportActionBar(toolbar);
        //Configuração da Nav Bottom
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPage, new HomeFragment()).commit();
configurarNavBott();

    }

    private void configurarNavBott(){
      BottomNavigationView  bottomNavigationView = findViewById(R.id.bottomNavigation);
      //Habilitando Navegação
habilitarNavigation(bottomNavigationView);
    }
private void habilitarNavigation(BottomNavigationView botView){
        botView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case(R.id.nav_cesta):
                     fragmentTransaction.replace(R.id.viewPage, new PedidosFragment()).commit();
                     return true;
                    case(R.id.nav_home):
                        fragmentTransaction.replace(R.id.viewPage, new HomeFragment()).commit();
                        return true;
                    case(R.id.nav_oferta):
                        fragmentTransaction.replace(R.id.viewPage, new PromocaoFragment()).commit();
                        return true;
                    case(R.id.nav_produtos):
                        fragmentTransaction.replace(R.id.viewPage, new ProdutosFragment()).commit();
                        return true;
                    case(R.id.nav_perfil):
                    fragmentTransaction.replace(R.id.viewPage, new PerfilFragment()).commit();
                    return true;
                }
                return false;
            }
        });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_sair:
                deslogarUsuario();
                finish();
                break;
            case R.id.menu_sobre:
                Toast.makeText(MainActivity.this, "Projeto Integrador 2020 CodSystems.",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.abrirChat:
                abrirTelaChatl();
        }
        return super.onOptionsItemSelected(item);
    }
    public void abrirTelaChatl(){
        Intent intent = new Intent(this, ChatBubbleActivity.class);
        startActivity(intent);
    }
    public void deslogarUsuario() {
        try {
            auth.signOut();
            sessao = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
