package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText correo, clave;
    Button registrarse, ingresar;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = (EditText)findViewById(R.id.correo);
        clave = (EditText)findViewById(R.id.clave);
        ingresar = findViewById(R.id.ingresar);
        registrarse = findViewById(R.id.registrarse);

        ingresar.setOnClickListener(this);
        registrarse.setOnClickListener(this);
        dao = new daoUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ingresar:
                String corr = correo.getText().toString();
                String cont = clave.getText().toString();
                if(corr.equals("") || cont.equals("")){
                    Toast.makeText(this, "Error: Complete todos los campos", Toast.LENGTH_SHORT).show();
                }else if (dao.login(corr,cont)== 1){
                    Usuario ux = dao.getUsuario(corr,cont);
                    Toast.makeText(this,"Has iniciado sesion", Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("Id",ux.getId());
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"Correo y/o contraseña incorrecto", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.registrarse:
                Intent intent2 = new Intent(LoginActivity.this,RegistrarActivity.class);
                startActivity(intent2);
                break;
        }

    }
}

