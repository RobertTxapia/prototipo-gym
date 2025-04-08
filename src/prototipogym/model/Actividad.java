package prototipogym.model;

public class Actividad {
    private int id;
    private String nombre;
    private String descripcion;
    private int idLocalizacion;
    private int idEntrenador;

    public Actividad(int id, String nombre, String descripcion, int idLocalizacion, int idEntrenador) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idLocalizacion = idLocalizacion;
        this.idEntrenador = idEntrenador;
    }

    public Actividad() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getIdLocalizacion() { return idLocalizacion; }
    public void setIdLocalizacion(int IdLocalizacion) { this.idLocalizacion = IdLocalizacion; }

    public int getIdEntrenador() { return idEntrenador; }
    public void setIdEntrenador(int IdEntrenador) {this.idEntrenador = IdEntrenador; }
}
