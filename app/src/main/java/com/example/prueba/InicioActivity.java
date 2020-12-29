package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        new Handler().postDelayed(new Runnable() {//Handler es un temporizador para la Actividad
            @Override
            public void run() {
                Intent intent = new Intent(InicioActivity.this,LoginActivity.class);//Intent es para llamar a otra Actividad,
                //le mando dos parametros donde estoy y a donde quiero ir.
                startActivity(intent);
                finish();//este es para que cuando le de atras no vuelva al splash
            }
        }, 3000);//Tiempo de la Actividad
    }
}