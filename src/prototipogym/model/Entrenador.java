package prototipogym.model;

public class Entrenador {
    private String id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    public Entrenador(String id, String nombre, String apellidos, String telefono, String correo){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellidos;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Entrenador() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
}