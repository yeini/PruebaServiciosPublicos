package com.example.prueba;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListarUsuariosActivity extends AppCompatActivity {

    ListView listau;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
        listau = findViewById(R.id.lista);

        dao = new daoUsuario(this);
        ArrayList <Usuario> l = dao.selectUsuarios();

        ArrayList<String> list = new ArrayList<String>();
        for (Usuario u: l) {
            list.add(u.getNombre()+ " Doc."+u.getDocumento());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        listau.setAdapter(a);

    }
}