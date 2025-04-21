package prototipogym.model;

import java.util.Date;

public class DetalleCuota {
    private String idCuota;
    private int secuencia;
    private String concepto;
    private double valor;
    private int idCobroCuota;

    public DetalleCuota(String idCuota, int secuencia, String concepto, double valor, int idCobroCuota) {
        this.idCuota = idCuota;
        this.secuencia = secuencia;
        this.concepto = concepto;
        this.valor = valor;
        this.idCobroCuota = idCobroCuota;
    }

    public int getIdCobroCuota() {return idCobroCuota;}
    public int getSecuencia() {return secuencia;}
    public String getConcepto() {return concepto;}
    public double getValor() {return valor;}
    public String getIdCuota() {return idCuota;}
    public void setIdCuota(String idCuota) {this.idCuota = idCuota;}
    public void setSecuencia(int secuencia) {this.secuencia = secuencia;}
    public void setConcepto(String concepto) {this.concepto = concepto;}
    public void setValor(double valor) {this.valor = valor;}
}
