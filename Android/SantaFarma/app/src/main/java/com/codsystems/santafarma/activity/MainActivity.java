package com.codsystems.santafarma.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.codsystems.santafarma.fragment.HomeFragment;
import com.codsystems.santafarma.fragment.OfertaFragment;
import com.codsystems.santafarma.fragment.PedidosFragment;
import com.codsystems.santafarma.fragment.PerfilFragment;
import com.codsystems.santafarma.fragment.ProdutosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
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
                    case(R.id.nav_carrinho):
                     fragmentTransaction.replace(R.id.viewPage, new PedidosFragment()).commit();
                     return true;
                    case(R.id.nav_home):
                        fragmentTransaction.replace(R.id.viewPage, new HomeFragment()).commit();
                        return true;
                    case(R.id.nav_oferta):
                        fragmentTransaction.replace(R.id.viewPage, new OfertaFragment()).commit();
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
        }
        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario() {
        try {
            auth.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
