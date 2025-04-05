package prototipogym.model;

public class HorarioActividad {
    private String id;
    private String dia;
    private String hora;
    private String idActividad;
    private String idSala;

    public HorarioActividad(String id, String dia, String hora, String idActividad, String idSala) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.idActividad = idActividad;
        this.idSala = idSala;
    }

    // Getters
    public String getId() { return id; }
    public String getDia() { return dia; }
    public String getHora() { return hora; }
    public String getIdActividad() { return idActividad; }
    public String getIdSala() { return idSala; }
}
