package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PagosActivity extends AppCompatActivity implements View.OnClickListener {

    EditText numerofactura, valor, confvalor;
    Spinner tipofactura;
    Button aceptar;

    daoPago daop;
    daoUsuario daou;
    int id=0;
    Usuario u;
    Pago p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        numerofactura = findViewById(R.id.numerofactura);
        valor = findViewById(R.id.valor);
        confvalor = findViewById(R.id.confvalor);
        tipofactura = findViewById(R.id.tipofactura);
        aceptar = findViewById(R.id.aceptar);

        aceptar.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        daou = new daoUsuario(this);
        daop = new daoPago(this);
        u = daou.getUsuarioById(id);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aceptar:
            String nf=numerofactura.getText().toString();
            String val=valor.getText().toString();
            String confval=confvalor.getText().toString();

            Pago p = new Pago();
            p.setNumerofactura(numerofactura.getText().toString());
            p.setValor(valor.getText().toString());
            p.setTipo_factura(tipofactura.getSelectedItem().toString());

            if(!p.isNull()) {//validar campos
                Toast.makeText(this, "Error: Campos vacios!", Toast.LENGTH_LONG).show();

            }else if(!(nf.length() == 10)){
                numerofactura.setError("el num. de factura tiene 10 caracteres");
            }else if(!validarvalor(val,confval)) {
                valor.setError("Los valores  no coinciden");
                confvalor.setError("los valores no coinciden");
            }else if(!(Integer.parseInt(val) <= 1000)) {
                valor.setError("Saldo insuficiente");
            }else{
                daop.insertarPago(p);
                daou.UpdateUsuario(u,Integer.parseInt(val));
                Toast.makeText(this, "Pago exitoso!", Toast.LENGTH_LONG).show();
                Intent intent= new Intent(PagosActivity.this,MainActivity.class);
                intent.putExtra("Id",u.getId());
                startActivity(intent);

            }
                    break;
        }

    }

    private boolean validarvalor(String val, String confval ) {
        if(val.equals(confval)){
            return true;
        }else{
            return false;
        }
    }
}