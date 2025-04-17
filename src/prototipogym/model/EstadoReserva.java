package prototipogym.model;

public class EstadoReserva {
    private String id;
    private boolean estado;

    public EstadoReserva(String id, boolean estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public boolean getEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public String toString() {
        return id + ";" + estado;
    }
}
