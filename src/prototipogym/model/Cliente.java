package prototipogym.model;
import java.util.Date;

public class Cliente {
    private String idCliente;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String direccion;
    private String fechaNac;
    private String telefono;
    private String celular;
    private String fechaIngreso;
    private boolean status;
    private int tipoCliente;
    private String correo;
   private double balance;
    private double valorCuota;

    // Constructor completo
    public Cliente(String idCliente, String nombre, String apellidoPat, String apellidoMat,
                   String direccion, String fechaNac, String telefono, String celular,
                   String fechaIngreso, boolean status, int tipoCliente, String correo,
                   double balance, double valorCuota) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.celular = celular;
        this.fechaIngreso = fechaIngreso;
        this.status = status;
        this.tipoCliente = tipoCliente;
        this.correo = correo;
        this.balance = balance;
        this.valorCuota = valorCuota;
    }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPat() { return apellidoPat; }
    public void setApellidoPat(String apellidoPat) { this.apellidoPat = apellidoPat; }

    public String getApellidoMat() { return apellidoMat; }
    public void setApellidoMat(String apellidoMat) { this.apellidoMat = apellidoMat; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFechaNac() { return fechaNac; }
    public void setFechaNac(String fechaNac) { this.fechaNac = fechaNac; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public int getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(int tipoCliente) { this.tipoCliente = tipoCliente; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public double getValorCuota() { return valorCuota; }
    public void setValorCuota(double valorCuota) { this.valorCuota = valorCuota; }
}
