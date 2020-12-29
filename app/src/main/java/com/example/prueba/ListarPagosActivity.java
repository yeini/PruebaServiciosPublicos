package com.example.prueba;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListarPagosActivity extends AppCompatActivity {

    ListView lista;
    daoPago dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pagos);
        lista = findViewById(R.id.lista);

        dao = new daoPago(this);
        ArrayList <Pago> l = dao.selectPagos();

        ArrayList<String> list = new ArrayList<String>();
        for (Pago p: l) {
            list.add(" #."+p.getNumerofactura()+ ". Valor: $"+p.getValor());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        lista.setAdapter(a);

    }
}