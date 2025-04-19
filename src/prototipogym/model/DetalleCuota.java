package prototipogym.model;

import java.util.Date;

public class DetalleCuota extends EncabezadoCuota {
    private int secuencia;
    private String concepto;
    private double valorCuota;
    private String idCobroCuota;

    public DetalleCuota(
            String idCuota,
            Date fechaCuota,
            String idCliente,
            double valorTotal,
            boolean status,
            int secuencia,
            String concepto,
            double valorCuota,
            String idCobroCuota
    ) {
        super(idCuota, fechaCuota, idCliente, valorTotal, status);
        this.secuencia = secuencia;
        this.concepto = concepto;
        this.valorCuota = valorCuota;
        this.idCobroCuota = idCobroCuota;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public String getIdCobroCuota() {
        return idCobroCuota;
    }

    public void setIdCobroCuota(String idCobroCuota) {
        this.idCobroCuota = idCobroCuota;
    }
}
