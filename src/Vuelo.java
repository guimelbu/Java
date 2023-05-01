import java.time.LocalDateTime;
import java.time.LocalTime;

public class Vuelo {
    private int idVuelo;
    private String origen;
    private String destino;
    private LocalTime duracionViaje;
    private LocalDateTime dateSalida;
    private LocalDateTime dateLlegada;
    private int plazasDisponibles;
    private int porcenDevolucion;
    private Piloto p;
    private Avion a;
    private float precio;

    //CONSTRUCTOR

    public Vuelo(int idVuelo, String origen, String destino, LocalTime duracionViaje, LocalDateTime dateSalida, LocalDateTime dateLlegada, int plazasDisponibles, int porcenDevolucion, Piloto p, Avion a, float precio) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.duracionViaje = duracionViaje;
        this.dateSalida = dateSalida;
        this.dateLlegada = dateLlegada;
        this.plazasDisponibles = plazasDisponibles;
        this.porcenDevolucion = porcenDevolucion;
        this.p = p;
        this.a = a;
        this.precio = precio;
    }

    //SETTERS & GETTERS

    public int getIdVuelo() {
        return this.idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getOrigen() {
        return this.origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalTime getDuracionViaje() {
        return this.duracionViaje;
    }

    public void setDuracionViaje(LocalTime duracionViaje) {
        this.duracionViaje = duracionViaje;
    }

    public LocalDateTime getDateSalida() {
        return this.dateSalida;
    }

    public void setDateSalida(LocalDateTime dateSalida) {
        this.dateSalida = dateSalida;
    }

    public LocalDateTime getDateLlegada() {
        return this.dateLlegada;
    }

    public void setDateLlegada(LocalDateTime dateLlegada) {
        this.dateLlegada = dateLlegada;
    }

    public int getPlazasDisponibles() {
        return this.plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    public int getPorcenDevolucion() {
        return this.porcenDevolucion;
    }

    public void setPorcenDevolucion(int porcenDevolucion) {
        this.porcenDevolucion = porcenDevolucion;
    }

    public Piloto getP() {
        return this.p;
    }

    public void setP(Piloto p) {
        this.p = p;
    }

    public Avion getA() {
        return this.a;
    }

    public void setA(Avion a) {
        this.a = a;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    // TO STRING

    @Override
    public String toString() {
        return  "\nIdVuelo........................" + idVuelo +
                "\nOrigen........................." + origen +
                "\nDestino........................" + destino +
                "\nDuración del vuelo............." + duracionViaje +
                "\nFecha de salida................" + dateSalida +
                "\nFecha de llegada..............." + dateLlegada +
                "\nNúmero de plazas disponibles..." + plazasDisponibles +
                "\nPorcentaje de devolución......." + porcenDevolucion + "%" +
                "\nPiloto........................." + p +
                "\nAvión.........................." + a +
                "\nPrecio........................." + precio;
    }

    /**
     * Muestra datos específicos del billete para el cliente.
     *
     * @return String con los datos del billete.
     */
    public String datosBillete(){
        return  "\nIdVuelo........................" + idVuelo +
                "\nOrigen........................." + origen +
                "\nDestino........................" + destino +
                "\nDuración del vuelo............." + duracionViaje +
                "\nFecha de salida................" + dateSalida +
                "\nFecha de llegada..............." + dateLlegada +
                "\nPorcentaje de devolución......." + porcenDevolucion + "%" +
                "\nPiloto........................." + p.getNombre() +
                "\nAvión.........................." + a.getNombre() +
                "\nPrecio por persona............." + precio;
    }

    /**
     * Calcula la hora de llegada el vuelo a partir de la fecha de salida y la duración del vuelo.
     *
     * @param anyoSalida Año de salida del vuelo.
     * @param mesSalida Mes de salida del vuelo.
     * @param diaSalida Dia de salida del vuelo.
     * @param horaSalida Hora de salida del vuelo.
     * @param minSalida Minuto de salida del vuelo.
     * @param horaDuracion Hora/s de duración del vuelo.
     * @param minDuracion Minuto/s de duración del vuelo.
     * @return
     */
    public static LocalDateTime calcularHoraLlegada(int anyoSalida, int mesSalida, int diaSalida, int horaSalida, int minSalida, int horaDuracion, int minDuracion){
        LocalDateTime dateSalida = LocalDateTime.of(anyoSalida, mesSalida, diaSalida, horaSalida, minSalida);
        LocalDateTime dateLlegada = dateSalida.plusHours(horaDuracion).plusMinutes(minDuracion);

        return dateLlegada;
    }
}
