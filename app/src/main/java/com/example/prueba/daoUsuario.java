package com.example.prueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "BDServicios";
    String tabla = "create table  if not exists usuario(id integer primary key autoincrement, nombre text, tipo_documento text, documento text, correo text, clave text,  monto int)";

    public daoUsuario(Context c) {
        this.c = c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u = new Usuario();
    }

    public boolean insertarUsuario(Usuario u){
        if(buscar(u.getCorreo())== 0) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", u.getNombre());
            cv.put("tipo_documento", u.getTipo_documento());
            cv.put("documento", u.getDocumento());
            cv.put("correo", u.getCorreo());
            cv.put("clave", u.getClave());
            cv.put("monto", u.getMonto());
            return (sql.insert("usuario", null, cv) > 0);
        }else{
            return false;
        }
    }
    public int buscar(String u){
        int x=0;
        lista = selectUsuarios();
        for (Usuario us:lista){
            if(us.getCorreo().equals(u)) {
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr!=null && cr.moveToFirst()){
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setTipo_documento(cr.getString(2));
                u.setDocumento(cr.getString(3));
                u.setCorreo(cr.getString(4));
                u.setClave(cr.getString(5));
                u.setMonto(cr.getInt(6));
                lista.add(u);
            }while(cr.moveToNext());
        }
        return lista;
    }


    public int login(String u, String p){
        int a = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr != null && cr.moveToFirst()){
            do {
                if(cr.getString(4).equals(u) && cr.getString(5).equals(p)){
                    a++;
                }
            }while(cr.moveToNext());
        }return a;
    }

    public Usuario getUsuario(String u, String p){
        lista = selectUsuarios();
        for (Usuario us:lista){
            if(us.getCorreo().equals(u) && us.getClave().equals(p)){
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id){
        lista = selectUsuarios();
        for (Usuario us:lista){
            if(us.getId() == id){
                return us;
            }
        }
        return null;
    }

    public boolean UpdateUsuario (Usuario u, int monto){
        ContentValues cv = new ContentValues();
        cv.put("monto", u.getMonto() - monto);
        return (sql.update("usuario", cv,"id="+u.getId(), null) > 0);
    }


}
