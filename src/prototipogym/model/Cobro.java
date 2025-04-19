package prototipogym.model;

import java.util.Date;

public class Cobro {
    private int id;
    private Date fecha;
    private int idCliente;
    private double valorCobro;
    private String concepto;

    public Cobro(int id, Date fecha, int idCliente, double valorCobro, String concepto) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.valorCobro = valorCobro;
        this.concepto = concepto;
    }

    public int getId() { return id; }
    public Date getFecha() { return fecha; }
    public int getIdCliente() { return idCliente; }
    public double getValorCobro() { return valorCobro; }
    public String getConcepto() { return concepto; }

    public void setId(int id) { this.id = id; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public void setValorCobro(double valorCobro) { this.valorCobro = valorCobro; }
    public void setConcepto(String concepto) { this.concepto = concepto; }
}
