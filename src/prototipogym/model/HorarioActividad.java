package prototipogym.model;

public class HorarioActividad {
    private String id;
    private String dia;
    private String hora;
    private String idActividad;
    

    public HorarioActividad(String id, String dia, String hora, String idActividad ) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.idActividad = idActividad;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public String getIdActividad() { return idActividad; }
    public void setIdActividad(String idActividad) { this.idActividad = idActividad; }
    
}
