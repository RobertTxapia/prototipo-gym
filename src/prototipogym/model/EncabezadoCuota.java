package prototipogym.model;

import java.util.Date;


public class EncabezadoCuota {
    private String idCuota;
    private Date fechaCuota;
    private String idCliente;
    private double valorTotal;
    private boolean status;

    public EncabezadoCuota(String idCuota, Date fechaCuota, String idCliente, double valorTotal, boolean status) {
        this.idCuota = idCuota;
        this.fechaCuota = fechaCuota;
        this.idCliente = idCliente;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public String getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(String idCuota) {
        this.idCuota = idCuota;
    }

    public Date getFechaCuota() {
        return fechaCuota;
    }

    public void setFechaCuota(Date fechaCuota) {
        this.fechaCuota = fechaCuota;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
