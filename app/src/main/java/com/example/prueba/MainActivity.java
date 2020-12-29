package com.example.prueba;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView fechat,  nombret, saldot;
    Button pagar, listarpagos, listarusuarios;
    int id=0;
    Usuario u;
    daoUsuario daou;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagar = findViewById(R.id.pagar);
        fechat = findViewById(R.id.fechat);
        nombret = findViewById(R.id.nombret);
        listarpagos = findViewById(R.id.listarpagos);

        pagar.setOnClickListener(this);
        listarpagos.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        daou = new daoUsuario(this);
        u = daou.getUsuarioById(id);


        Date d = new Date();
        CharSequence f = DateFormat.format("yyyy-MM-dd", d.getTime());
        CharSequence h = DateFormat.format("hh:mm:ss", d.getTime());


        nombret.setText(u.getNombre()+"\nSaldo Disponible $ "+ u.getMonto());
        fechat.setText("Fecha: "+ f + "      Hora: "+ h);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pagar:
                Intent intent = new Intent(MainActivity.this,PagosActivity.class);//Intent es para llamar a otra Actividad,
                intent.putExtra("Id",id);
                startActivity(intent);

                break;
            case R.id.listarpagos:
                Intent intent2 = new Intent(MainActivity.this,ListarPagosActivity.class);//Intent es para llamar a otra Actividad,

                startActivity(intent2);

                break;

        }
    }


}