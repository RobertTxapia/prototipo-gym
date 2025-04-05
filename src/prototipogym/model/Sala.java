package prototipogym.model;

public class Sala {
    private int id;
    private String nombre;
    private String descripcion;
    private int idLocalizacion;

    public Sala(int id, String nombre, String descripcion, int idLocalizacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idLocalizacion = idLocalizacion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getIdLocalizacion() { return idLocalizacion; }
}
