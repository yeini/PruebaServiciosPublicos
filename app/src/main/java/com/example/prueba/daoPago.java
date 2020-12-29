package com.example.prueba;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoPago {
    Context c;
    Pago p;
    ArrayList<Pago> lista;
    SQLiteDatabase sql;
    String bd = "BDServicios";
    String tabla = "create table  if not exists pago(id integer primary key autoincrement, numerofactura text,valor text, tipo_factura text)";

    public daoPago(Context c) {
        this.c = c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        p = new Pago();
    }

    public boolean insertarPago(Pago p){
            ContentValues cv = new ContentValues();
            cv.put("numero", p.getNumerofactura());
            cv.put("valor", p.getValor());
            cv.put("tipo_factura", p.getTipo_factura());
            return (sql.insert("pago", null, cv) > 0);
    }


    public ArrayList<Pago> selectPagos(){
        ArrayList<Pago> lista = new ArrayList<Pago>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from pago", null);
        if(cr!=null && cr.moveToFirst()){
            do {
                Pago p = new Pago();
                p.setId(cr.getInt(0));
                p.setNumerofactura(cr.getString(1));
                p.setValor(cr.getString(2));
                p.setTipo_factura(cr.getString(3));
                lista.add(p);
            }while(cr.moveToNext());
        }
        return lista;
    }

}

