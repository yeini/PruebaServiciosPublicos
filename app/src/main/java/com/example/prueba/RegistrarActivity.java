package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener{

    EditText nombre, documento, correo, clave, confirmacionclave;
    Button registrarse, volverlogin;
    daoUsuario dao;
    Spinner tipodocumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nombre = findViewById(R.id.nombre);
        documento = findViewById(R.id.documento);
        tipodocumento = findViewById(R.id.tipodocumento);
        correo =  findViewById(R.id.correo);
        clave =  findViewById(R.id.clave);
        confirmacionclave =  findViewById(R.id.confirmacionclave);
        registrarse = findViewById(R.id.registrarse);
        volverlogin = findViewById(R.id.volverlogin);

        registrarse.setOnClickListener(this);
        volverlogin.setOnClickListener(this);

        dao = new daoUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registrarse:

                String corr=correo.getText().toString();
                String clav=clave.getText().toString();
                String confcla=confirmacionclave.getText().toString();

                Usuario u = new Usuario();
                u.setNombre(nombre.getText().toString());
                u.setDocumento(documento.getText().toString());
                u.setCorreo(correo.getText().toString());
                u.setClave(clave.getText().toString());
                u.setTipo_documento(tipodocumento.getSelectedItem().toString());

                if(!u.isNull()) {//validar campos
                    Toast.makeText(this, "Error: Campos vacios!", Toast.LENGTH_LONG).show();
                }else if(!validarCorreo(corr)) {
                    correo.setError("Correo no valido");
                }else if (!clav.matches(".*\\d.*")) {
                    clave.setError("Utilice numeros y letras");
                }else if (!clav.matches(".*[a-z].*")){
                    clave.setError("Utilice numeros y letras");
                }else if(!isPasswordValid(clav)){
                    clave.setError("Utilice minimo 8 caracteres");
                }else if(!validarClaves(clav,confcla)) {
                    clave.setError("Las contraseñas no coinciden");
                    confirmacionclave.setError("Las contraseñas no coinciden");
                }else if(dao.insertarUsuario(u)){
                    Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegistrarActivity.this,MainActivity.class);
                    intent.putExtra("Id",u.getId());
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Usuario ya registrado!!!!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.volverlogin:
                Intent intent2 = new Intent(RegistrarActivity.this,LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
    private boolean validarCorreo(String correo) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(correo).matches();
    }
    /*private boolean isEmailValid(String email) {
      //Si la cadena contiene el caracter @ es un email valido
      return email.contains("@");
}
*/
    private boolean isPasswordValid(String clave) {
        //Si la cadena supera los 8 caracteres es una contraseña valida
        return clave.length() >= 8;
    }

    private boolean validarClaves(String clave, String confirmacionclave ) {
        if(clave.equals(confirmacionclave)){
            return true;
        }else{
            return false;
        }
    }

}