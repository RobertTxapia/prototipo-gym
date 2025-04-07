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

    public Sala() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getIdLocalizacion() { return idLocalizacion; }
    public void setIdLocalizacion(int idLocalizacion) { this.idLocalizacion = idLocalizacion; }
}
