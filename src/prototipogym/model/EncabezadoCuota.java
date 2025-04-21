package prototipogym.model;

import java.util.Date;

public class EncabezadoCuota {
    private String idCuota;
    private Date fechaCuota;
    private String idCliente;
    private double valorTotal;
    private boolean status;

public EncabezadoCuota(
    String idCuota, 
    Date fechaCuota, 
    String idCliente, 
    double ValorTotal, 
    boolean status
) {
        this.idCuota = idCuota;
        this.fechaCuota = fechaCuota;
        this.idCliente = idCliente;
        this.valorTotal = ValorTotal;
        this.status = status;
    }

    public boolean isStatus() { return status; }
    public String getIdCliente() { return idCliente; }
    public Date getFechaCuota() { return fechaCuota; }
    public String getIdCuota() { return idCuota; }
    public double getValorTotal() { return valorTotal; }
    
    public void setIdCuota(String idCuota) { this.idCuota = idCuota; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    
    public void setValorTotal(double valorTotal) {this.valorTotal = valorTotal; }
    
    public void setStatus(boolean status) { this.status = status; }
    public void setFechaCuota(Date fechaCuota) {this.fechaCuota = fechaCuota; }
}
