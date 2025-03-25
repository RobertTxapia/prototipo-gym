package prototipogym.model;

public class Usuario {
    private String login;
    private String password;
    private int nivelAcceso;
    private String nombre;
    private String apellidos;
    private String correo;
    
    public Usuario(String login, String password, int nivelAcceso, String nombre, String apellidos, String correo) {
        this.login = login;
        this.password = password;
        this.nivelAcceso = nivelAcceso;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }
}
