package prototipogym.model;

public class EstadoReserva {
    private String id;
    private String estado;

    public EstadoReserva(String id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String toString() {
        return id + ";" + estado;
    }
}
