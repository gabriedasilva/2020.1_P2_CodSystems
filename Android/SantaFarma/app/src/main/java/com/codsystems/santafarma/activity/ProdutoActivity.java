package com.codsystems.santafarma.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.model.Pedido;
import com.codsystems.santafarma.model.Produto;

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
        Pedido p = new Pedido();

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

                addCesta();
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

    public void addCesta() {
        Produto p = prod;
        Pedido pedido = new Pedido();
        pedido.setNomeProduto(p.getNome());
        pedido.setPrecoProduto(p.getPreco());



    System.out.println(pedido.getNomeCliente());
    System.out.println(pedido.getEndereco().toString());
    System.out.println(pedido);


        pedido.salvarPedido(pedido);
        Toast.makeText(getApplicationContext(), "Pedido adicionado a Cesta", Toast.LENGTH_SHORT).show();
        finish();
        }


    public void addCloudCesta(){

    }
}
