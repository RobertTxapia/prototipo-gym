package prototipogym.model;

public class Usuario {
    private String login;
    private String password;
    private int nivelAcceso;
    private String nombre;
    private String apellidos;
    private String correo;
    
    public Usuario(String user, String password, int nivelAcceso, String nombre, String apellidos, String correo) {
        this.login = user;
        this.password = password;
        this.nivelAcceso = nivelAcceso;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public Usuario() {
        // vacío por la consulta
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }
    
    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String user) {
        this.login = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
