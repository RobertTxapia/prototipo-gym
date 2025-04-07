package prototipogym.model;

import java.util.Date;
import java.text.SimpleDateFormat;


public class Reserva {
    private String idReserva;
    private String idSala; // Campo reintroducido
    private String idCliente;
    private String fechaReserva;
    private String idHorario;
    private String idEstado;

    // Constructor actualizado
    public Reserva(String idReserva, String idSala, String idCliente,
                   String fechaReserva, String idHorario, String idEstado) {
        this.idReserva = idReserva;
        this.idSala = idSala;
        this.idCliente = idCliente;
        this.fechaReserva = fechaReserva;
        this.idHorario = idHorario;
        this.idEstado = idEstado;
    }

    // Getters y Setters (sin setIdSala/getIdSala)
    public String getIdReserva() { return idReserva; }
    public void setIdReserva(String idReserva) { this.idReserva = idReserva; }


    public String getIdSala() { return idSala; }
    public void setIdSala(String idSala) { this.idSala = idSala; }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(String fechaReserva) { this.fechaReserva = fechaReserva; }

    public String getIdHorario() { return idHorario; }
    public void setIdHorario(String idHorario) { this.idHorario = idHorario; }

    public String getIdEstado() { return idEstado; }
    public void setIdEstado(String idEstado) { this.idEstado = idEstado; }
}
