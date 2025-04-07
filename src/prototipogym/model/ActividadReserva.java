package prototipogym.model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ActividadReserva {
    private String idReservaActividad;
    private String idActividad;
    private String idCliente;
    private Date fechaReserva;
    private Date fechaBaja;
    private String idEstado;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ActividadReserva(String idReservaActividad, String idActividad, String idCliente,
                            Date fechaReserva, Date fechaBaja, String idEstado) {
        this.idReservaActividad = idReservaActividad;
        this.idActividad = idActividad;
        this.idCliente = idCliente;
        this.fechaReserva = fechaReserva;
        this.fechaBaja = fechaBaja;
        this.idEstado = idEstado;
    }

    // Getters y Setters (validar campos)
    public String getIdReservaActividad() { return idReservaActividad; }
    public void setIdReservaActividad(String idReservaActividad) {
        if (idReservaActividad.matches("RA-\\d{3}")) {
            this.idReservaActividad = idReservaActividad;
        }
    }

    public String getIdActividad() { return idActividad; }
    public void setIdActividad(String idActividad) {
        if (idActividad.matches("ACT-\\d{3}")) {
            this.idActividad = idActividad;
        }
    }

    // ... Implementar setters similares para otros campos

    @Override
    public String toString() {
        return idReservaActividad + ";"
                + idActividad + ";"
                + idCliente + ";"
                + sdf.format(fechaReserva) + ";"
                + (fechaBaja != null ? sdf.format(fechaBaja) : "N/A") + ";"
                + idEstado;
    }
}