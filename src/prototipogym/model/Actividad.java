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

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getIdLocalizacion() { return idLocalizacion; }
    public int getIdEntrenador() { return idEntrenador; }
}
