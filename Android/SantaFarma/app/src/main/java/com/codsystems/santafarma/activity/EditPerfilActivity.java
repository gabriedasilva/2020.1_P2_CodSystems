package com.codsystems.santafarma.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.helper.Permissao;

public class EditPerfilActivity extends AppCompatActivity {

    private String[] permissoesNescess={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);
        //VALIDAR PERMISSAO DO PERFIL
        Permissao.validarPermissoes(permissoesNescess,this,1);

    }

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
}
