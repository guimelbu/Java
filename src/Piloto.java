import java.time.LocalDate;

public class Piloto {
    private int idPiloto;
    private String nombre;
    private String apellidos;
    private String dni;
    private int telefono;
    private LocalDate fechaNacimiento;

    //CONSTRUCTOR

    public Piloto(int idPiloto, String nombre, String apellidos, String dni, int telefono, LocalDate fechaNacimiento) {
        this.idPiloto = idPiloto;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    //GETTERS & SETTERS

    public int getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(int idPiloto) {
        this.idPiloto = idPiloto;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    //TO STRING

    @Override
    public String toString() {
        return "idPiloto= " + idPiloto +
                ", Nombre= " + nombre +
                ", Apellidos= " + apellidos +
                ", DNI= " + dni +
                ", Teléfono= " + telefono +
                ", Fecha de Nacimiento= " + fechaNacimiento;
    }
}
