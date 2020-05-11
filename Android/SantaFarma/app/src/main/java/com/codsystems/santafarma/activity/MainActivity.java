package com.codsystems.santafarma.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = ConfigFirebase.getFirebaseAutenticacao();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarprincipal);
        setSupportActionBar(toolbar);
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
