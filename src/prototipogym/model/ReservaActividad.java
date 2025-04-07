package prototipogym.model;

public class ReservaActividad {
    private int idReservaActividad;
    private String fechaReserva;
    private String fechaBaja;
    private String idEstadoReserva;
    private String idCliente;
    private String idActividad;
    private String idHorarioActividad;

    // Constructor
    public ReservaActividad(int idReservaActividad, String fechaReserva, String fechaBaja,
                            String idEstadoReserva, String idCliente, String idActividad,
                            String idHorarioActividad) {
        this.idReservaActividad = idReservaActividad;
        this.fechaReserva = fechaReserva;
        this.fechaBaja = fechaBaja;
        this.idEstadoReserva = idEstadoReserva;
        this.idCliente = idCliente;
        this.idActividad = idActividad;
        this.idHorarioActividad = idHorarioActividad;
    }

    // Getters y Setters
    public int getIdReservaActividad() { return idReservaActividad; }
    public void setIdReservaActividad(int idReservaActividad) { this.idReservaActividad = idReservaActividad; }

    public String getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(String fechaReserva) { this.fechaReserva = fechaReserva; }

    public String getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(String fechaBaja) { this.fechaBaja = fechaBaja; }

    public String getIdEstadoReserva() { return idEstadoReserva; }
    public void setIdEstadoReserva(String idEstadoReserva) { this.idEstadoReserva = idEstadoReserva; }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getIdActividad() { return idActividad; }
    public void setIdActividad(String idActividad) { this.idActividad = idActividad; }

    public String getIdHorarioActividad() { return idHorarioActividad; }
    public void setIdHorarioActividad(String idHorarioActividad) { this.idHorarioActividad = idHorarioActividad; }
}
