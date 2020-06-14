package com.codsystems.santafarma.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.codsystems.santafarma.fragment.ProdutosFragment;
import com.codsystems.santafarma.model.Pedido;
import com.codsystems.santafarma.model.Produto;
import com.codsystems.santafarma.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdutoActivity extends AppCompatActivity {
    private static Produto prod;
Button btnPedido;
    Produto produto = new Produto();
    TextView nomePerfProd,precoPerfProd,dispPerfProd,qtdText
            ,categoPerfProd,descPerfProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
setInfo();
        int qt;
        qt =   Integer.valueOf(produto.getQtdeDisp());

        if(qt == 0){
            dispPerfProd.setTextColor(Color.RED);
            dispPerfProd.setText("Indisponível");
        }else{
            dispPerfProd.setText("Disponível");
        }
        btnPedido = findViewById(R.id.btn_addCesta);
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCesta(prod);
            }
        });
    }
    public static void setProduto(Produto p){
        prod = p;

    }
    void setInfo(){
        produto = prod;
        System.out.println(prod.getNome());
        nomePerfProd = findViewById(R.id.perfilNomeProd);
        precoPerfProd = findViewById(R.id.perfilPrecoPrdo);
      dispPerfProd = findViewById(R.id.perfilQtde);
        descPerfProd = findViewById(R.id.perfilDesc);
        nomePerfProd.setText(produto.getNome());
        precoPerfProd.setText("R$:"+produto.getPreco());
        descPerfProd.setText(produto.getDesc());

    }

    public void addCesta(Produto p){
        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
               Pedido pedido = new Pedido();
               Usuario u = new Usuario();
               u.buscaUsuario(auth.getUid());
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(p);
        System.out.println("-----"+produtos);
        pedido.setProdutos(produtos);
        pedido.setEndereco(pedido.getEndereco());
        pedido.setNomeCliente(pedido.getNomeCliente());
    }

}
