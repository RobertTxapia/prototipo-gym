package prototipogym.model;

public class EstadoReserva {
    private String id;
    private String estado;

    public EstadoReserva(String id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    // Getters
    public String getId() { return id; }
    public String getEstado() { return estado; }

    public String toString() {
        return id + ";" + estado;
    }
}
