package com.example.prueba;

public class Pago {
    int id;
    String numerofactura;
    String valor;
    String tipo_factura;

    public Pago() {
    }

    public Pago(String numero, String valor, String tipo_factura) {
        this.numerofactura = numero;
        this.valor = valor;
        this.tipo_factura = tipo_factura;
    }

    public boolean isNull(){
        if(numerofactura.equals("") || valor.equals("") || tipo_factura.equals("")){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public String toString() {
        return "usuario{" +
                "id=" + id +
                ", numerofactura='" + numerofactura + '\'' +
                ", valor='" + valor + '\'' +
                ", tipo_factura='" + tipo_factura + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo_factura() {
        return tipo_factura;
    }

    public void setTipo_factura(String tipo_factura) {
        this.tipo_factura = tipo_factura;
    }
}
