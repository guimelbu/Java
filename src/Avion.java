import java.time.LocalDate;

public class Avion {
    private String nombre;
    private LocalDate fechaCreacion;
    private float capacidadCombustible;
    private int totalPlazas;

    //CONSTRUCTOR
    public Avion(String nombre, LocalDate fechaCreacion, float capacidadCombustible, int totalPlazas) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.capacidadCombustible = capacidadCombustible;
        this.totalPlazas = totalPlazas;
    }

    //GETTERS & SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public float getCapacidadCombustible() {
        return capacidadCombustible;
    }

    public void setCapacidadCombustible(float capacidadCombustible) {
        this.capacidadCombustible = capacidadCombustible;
    }

    public int getTotalPlazas() {
        return totalPlazas;
    }

    public void setTotalPlazas(int totalPlazas) {
        this.totalPlazas = totalPlazas;
    }


    //TO STRING

    @Override
    public String toString() {
        return "Nombre= " + nombre +
                ", Fecha de creación= " + fechaCreacion +
                ", Capacidad del combustible= " + capacidadCombustible +
                ", Plazas totales= " + totalPlazas;
    }
}
