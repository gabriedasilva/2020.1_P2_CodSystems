package com.codsystems.santafarma.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.fragment.ProdutosFragment;
import com.codsystems.santafarma.model.Produto;

public class ProdutoActivity extends AppCompatActivity {
    private static Produto prod;
    SeekBar qtde;
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
        qtde.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                qtdText = findViewById(R.id.qtdText);
                int x = qtde.getRight();
                qtdText.setText(String.valueOf(x));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                qtdText = findViewById(R.id.qtdText);
                int x = qtde.getRight();
                qtdText.setText(String.valueOf(x));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                qtdText = findViewById(R.id.qtdText);
                int x = qtde.getRight();
                qtdText.setText(String.valueOf(x));
            }
        });
    }
    public static void setProduto(Produto p){
        prod = p;

    }
    void setInfo(){
        produto = prod;
        System.out.println(prod.getNome());
        qtde = findViewById(R.id.seekQtd);

        System.out.println(qtde.getRight());
        nomePerfProd = findViewById(R.id.perfilNomeProd);
        precoPerfProd = findViewById(R.id.perfilPrecoPrdo);
      dispPerfProd = findViewById(R.id.perfilQtde);
        descPerfProd = findViewById(R.id.perfilDesc);
        nomePerfProd.setText(produto.getNome());
        precoPerfProd.setText("R$:"+produto.getPreco());

        descPerfProd.setText(produto.getDesc());

    }

}
