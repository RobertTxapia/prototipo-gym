package prototipogym.model;

public class Usuario {
    private String login;
    private String password;
    private int nivelAcceso;
    private String nombre;
    private String apellidos;
    
    
    // Constructor, getters y setters
    public Usuario(String login, String password, int nivelAcceso, String nombre, String apellidos, String correo) {
        this.login = login;
        this.password = password;
        this.nivelAcceso = nivelAcceso;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }
}
