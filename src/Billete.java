public class Billete {
    private float precioTotal;
    private Vuelo v;
    private int numPasajeros;

    //CONSTRUCTOR
    public Billete(float precioTotal, Vuelo v, int numPasajeros) {
        this.precioTotal = precioTotal;
        this.v = v;
        this.numPasajeros = numPasajeros;
    }

    //GETTERS & SETTERS

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Vuelo getV() {
        return v;
    }

    public void setV(Vuelo v) {
        this.v = v;
    }

    public int getNumPasajeros() {
        return numPasajeros;
    }

    public void setNumPasajeros(int numPasajeros) {
        this.numPasajeros = numPasajeros;
    }

    //TO STRING


    @Override
    public String toString() {
        return "Billete:\n" +
                 v.datosBillete() +
                "\nPrecio Total...................." + precioTotal +
                "\nNúmero de Pasajeros............." + numPasajeros;
    }

    /**
     * Calcula el precio total del billete de vuelo a partir de el número de personas y el precio por persona correspondiente al vuelo,
     *
     * @param numPersonas Número de personas en el billete de vuelo.
     * @param v El vuelo escogido para el billete.
     * @return Precio total calculado.
     */
    public static float calcularPrecio(int numPersonas, Vuelo v){
        float precioTotal;

        precioTotal = v.getPrecio() * numPersonas;

        return precioTotal;
    }

    /**
     * Calcula el precio de devolución a partir del número de billetes a anular y el porcentaje de devolución del vuelo correspondiente.
     *
     * @param v El vuelo escogido para el billete.
     * @param numBilletes Número de billetes a anular.
     * @return Precio de la devolución calculado.
     */
    public static float calcularDevolucion(Vuelo v, int numBilletes){
        float precioDevolucion, precioTotal;

        precioTotal = v.getPrecio() * numBilletes;
        precioDevolucion = (precioTotal * v.getPorcenDevolucion()) / 100;

        return precioDevolucion;
    }
}
