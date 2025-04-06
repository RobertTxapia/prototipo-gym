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

    // Getters
    public String getId() { return id; }
    public String getDia() { return dia; }
    public String getHora() { return hora; }
    public String getIdActividad() { return idActividad; }
    
}
