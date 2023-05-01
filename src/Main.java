import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int menuInicio, menuAdmin, menuUser, id = 0, anyoSalida = 0, mesSalida = 0, diaSalida = 0, horaSalida = 0, minSalida = 0, horaDuracion = 0, minDuracion = 0;
        Avion[] aviones;
        Piloto[] pilotos;
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        boolean trueUser = true;
        Cliente c = null;

        aviones = crearAviones();
        pilotos = crearPilotos();

        //vuelos de prueba
        /*vuelos.add(new Vuelo(1, "aaa", "aaaaaa", LocalTime.of(2, 30), LocalDateTime.of(2023, 1, 1, 23, 30), LocalDateTime.of(2023, 1, 1, 2, 0), 15, 70, pilotos[1], aviones[1], 20.50f));
        vuelos.add(new Vuelo(2, "bbb", "bbbbbb", LocalTime.of(2, 0), LocalDateTime.of(2023, 1, 1, 23, 30), LocalDateTime.of(2023, 1, 1, 1, 30), 20, 50, pilotos[2], aviones[2], 20.50f));
        vuelos.add(new Vuelo(3, "ccc", "cccccc", LocalTime.of(1, 30), LocalDateTime.of(2023, 1, 1, 23, 30), LocalDateTime.of(2023, 1, 1, 1, 0), 2, 80, pilotos[3], aviones[3], 20.50f));*/


        //Dar la bienvenida al usuario
        mensajeBienvenida();
        do {
            do {
                //Mostrar un menú con opciones de modo de inicio de sensión
                menuInicio = menuInicio(sc);

                switch (menuInicio) {
                    case 1 -> trueUser = comprobarDatosAdmin(sc);
                    case 2 -> {
                        c = iniciarUsuario(sc, clientes);
                        trueUser = comprobarUsuario(c);
                    }
                    case 3 -> {
                        c = registrarUser(sc, clientes, id);
                        id = Cliente.calcularId();
                    }
                }
            } while (!trueUser);

            switch (menuInicio) {
                case 1 -> {//Mostrar menú con opciones de adiminstrador
                    do {
                        menuAdmin = menuAdmin(sc);

                        switch (menuAdmin) {
                            case 1 -> crearVuelo(sc, vuelos, pilotos, aviones);
                            case 2 -> consultarVuelo(sc, vuelos);
                            case 3 -> modificarVuelo(sc, vuelos, pilotos, aviones, anyoSalida, mesSalida, diaSalida, horaSalida, minSalida, horaDuracion, minDuracion);
                            case 4 -> eliminarVuelo(sc, vuelos);
                            case 5 -> mostrarAviones(aviones);
                            case 6 -> mostrarPilotos(pilotos);
                        }
                    } while (menuAdmin != 7);
                }

                case 2, 3 -> { //Mostrar menú con opciones de cliente
                    do {
                        menuUser = menuUser(sc);
                        switch (menuUser) {
                            case 1 -> verInfoVuelo(c);
                            case 2 -> comprarBillete(sc, vuelos, c);
                            case 3 -> anularBillete(sc, c);
                        }
                    } while (menuUser != 4);
                }
            }
        } while (menuInicio != 4);
    }

    /**
     * Cargar aviones a la aplicación.
     *
     * @return Array con los aviones ya creados.
     */
    public static Avion[] crearAviones() {
        Avion a1 = new Avion("A", LocalDate.parse("2020-10-01"), 200, 120);
        Avion a2 = new Avion("B", LocalDate.parse("2019-02-11"), 400, 250);
        Avion a3 = new Avion("C", LocalDate.parse("2020-11-04"), 150, 80);
        Avion a4 = new Avion("D", LocalDate.parse("2018-10-21"), 200, 100);
        Avion a5 = new Avion("E", LocalDate.parse("2021-12-31"), 400, 250);

        Avion[] aviones = new Avion[]{a1, a2, a3, a4, a5};

        return aviones;
    }

    /**
     * Cargar pilotos a la aplicación.
     *
     * @return Array con los aviones ya creados.
     */
    public static Piloto[] crearPilotos() {
        Piloto p1 = new Piloto(101, "Pablo", "Gonzalez García", "45632158T", 652144100, LocalDate.parse("1993-12-30"));
        Piloto p2 = new Piloto(102, "Ernesto", "García Lopez", "45216588P", 654002153, LocalDate.parse("1980-01-22"));
        Piloto p3 = new Piloto(103, "Mónica", "Serra Lopez", "44006532R", 653210050, LocalDate.parse("1990-10-09"));
        Piloto p4 = new Piloto(104, "Laura", "Graell Garcia", "42226933S", 605432121, LocalDate.parse("1990-06-01"));
        Piloto p5 = new Piloto(105, "Ainhoa", "Maquez Trenado", "49005007G", 675463301, LocalDate.parse("1989-03-09"));

        Piloto[] pilotos = new Piloto[]{p1, p2, p3, p4, p5};

        return pilotos;
    }

    /**
     * Mensaje de bienvenida para los usuarios.
     */
    private static void mensajeBienvenida() {
        System.out.println("\nBienvenido/a al centro de consultas de la aerolínea Poli-Vols!");
    }

    /**
     * Menú con todas las opciones de inicio de sesión.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @return Número de la opción escogida.
     */
    private static int menuInicio(Scanner sc) {
        int menu;
        do {
            System.out.println("\n1.- Iniciar sesión como administrador");
            System.out.println("2.- Iniciar sesión como cliente");
            System.out.println("3.- Registrarse como nuevo cliente");
            System.out.println("4.- Salir");
            menu = sc.nextInt();
        } while (menu < 1 || menu > 4);

        return menu;
    }

    /**
     * Verificar si el usuario es realmente el administrador mediante un nombre de usuario y contraseña específicos.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @return Booleano para saber si es realmente el administardor.
     */
    private static boolean comprobarDatosAdmin(Scanner sc) {
        boolean trueAdmin;
        String insertUser;
        String insertPassword;
        Admin a = new Admin();

        System.out.println("Ingresa el nombre de usuario:");
        insertUser = sc.next();
        System.out.println("Ingresa la contraseña:");
        insertPassword = sc.next();

        if (insertUser.equals(a.getUser()) && insertPassword.equals(a.getPassword())) {
            trueAdmin = true;
            System.out.println("\nHa iniciado como administrador correctamente.");
        } else {
            trueAdmin = false;
        }

        return trueAdmin;
    }

    /**
     * Iniciar sesión como usuario anteriormente creado mediante una verificación de usuario y contraseña coincidentes en el ArrayList de clientes.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @param clientes ArrayList donde se guardan todos los clientes previamente registrados en la aplicación.
     * @return En caso de existir el usuario y verificarse con la contraseña correcta, devuelve a un solo objeto de la clase Cliente.
     */
    private static Cliente iniciarUsuario(Scanner sc, ArrayList<Cliente> clientes) {
        String insertUser;
        String insertPassword;
        Cliente c = null;

        System.out.println("Ingresa el nombre de usuario:");
        insertUser = sc.next();
        System.out.println("Ingresa la contraseña:");
        insertPassword = sc.next();

        for (Cliente x : clientes) {
            if (insertUser.equals(x.getUser()) && insertPassword.equals(x.getPassword())){
                c = x;
            }
        }
        return c;
    }

    /**
     * Comprobar la existencia del usuario donde se interpreta el null en Cliente c como que el usuario no existe.
     *
     * @param c Cliente definido (o no) en iniciarUsuario().
     * @return Booleano para saber si el objeto Cliente está vacío o existe.
     */
    private static boolean comprobarUsuario(Cliente c){
        boolean trueUser;

        trueUser = c != null;

        return trueUser;
    }

    /**
     * Se registra un nuevo cliente, se le asigna un nombre de usuario, contraseña y un arrayList para guardar los billetes que compre en el futuro.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @param clientes ArrayList para guardar los clientes creados aquí.
     * @param id Id autoincremental único para cada cliente.
     * @return Objeto del tipo cliente con el que se va a trabajar a continuación..
     */
    private static Cliente registrarUser(Scanner sc, ArrayList<Cliente> clientes, int id) {
        String insertUser;
        String insertPassword;
        Cliente c;
        ArrayList<Billete> billetesUser = new ArrayList<>();

        System.out.println("Ingresa el nombre de usuario:");
        insertUser = sc.next();
        System.out.println("Ingresa la contraseña:");
        insertPassword = sc.next();

        clientes.add(new Cliente(id, insertUser, insertPassword, billetesUser));
        c = clientes.get(id);
        System.out.println("Usuario creado correctamente");

        return c;
    }

    /**
     * Menú con todas las opciones para el administrador.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @return Número de la opción escogida por el usuario.
     */
    private static int menuAdmin(Scanner sc) {
        int menu;

        do {
            System.out.println("\nAhora elige una de las siguientes opciones:");
            System.out.println("1.- Crear vuelo");
            System.out.println("2.- Ver datos de vuelo");
            System.out.println("3.- Modificar datos de vuelo");
            System.out.println("4.- Eliminar vuelo");
            System.out.println("5.- Mostrar aviones");
            System.out.println("6.- Mostrar pilotos");
            System.out.println("7.- Salir");
            menu = sc.nextInt();
        } while (menu < 1 || menu > 7);

        return menu;
    }

    /**
     * Creación de un nuevo vuelo mediante un constructor en la clase Vuelo que se guarda dentro de un ArrayList.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @param vuelos ArrayList con los vuelos almacenados en la aplicación.
     * @param pilotos Array con los Pilotos para poder adjudicarlos a un vuelo.
     * @param aviones Array con los aviones para poder adjudicarlos a un vuelo.
     */
    public static void crearVuelo(Scanner sc, ArrayList<Vuelo> vuelos, Piloto[] pilotos, Avion[] aviones) {
        int idVuelo, plazasDisponibles, porcenDevolucion, numPiloto, numAvion;
        String origen, destino;
        float precio;
        LocalDateTime dateSalida, dateLlegada;
        LocalTime duracionViaje;
        Piloto p;
        Avion a;

        System.out.println("Identificador de vuelo:");
        idVuelo = sc.nextInt();
        System.out.println("Origen de vuelo:");
        origen = sc.next();
        System.out.println("Destino de vuelo:");
        destino = sc.next();
        System.out.println("Información referente a la salida del vuelo (solo números)");
        System.out.println("Año:");
        int anyoSalida = sc.nextInt();
        System.out.println("Mes:");
        int mesSalida = sc.nextInt();
        System.out.println("Día:");
        int diaSalida = sc.nextInt();
        System.out.println("Hora:");
        int horaSalida = sc.nextInt();
        System.out.println("Minuto:");
        int minSalida = sc.nextInt();
        dateSalida = LocalDateTime.of(anyoSalida, mesSalida, diaSalida, horaSalida, minSalida);
        System.out.println("Información referente a la duración de vuelo:");
        System.out.println("Horas:");
        int horaDuracion = sc.nextInt();
        System.out.println("Minutos:");
        int minDuracion = sc.nextInt();
        duracionViaje = LocalTime.of(horaDuracion, minDuracion);
        dateLlegada = Vuelo.calcularHoraLlegada(anyoSalida, mesSalida, diaSalida, horaSalida, minSalida, horaDuracion, minDuracion);
        System.out.println("Número de plazas disponibles:");
        plazasDisponibles = sc.nextInt();
        System.out.println("Porcentaje de devolución:");
        porcenDevolucion = sc.nextInt();
        for (int i = 0; i < pilotos.length; i++) {
            System.out.println("Piloto " + i + ": " + pilotos[i]);
        }
        System.out.println("Asigna un piloto al vuelo(inserta el número de piloto):");
        numPiloto = sc.nextInt();
        p = pilotos[numPiloto];
        for (int i = 0; i < aviones.length; i++) {
            System.out.println("Avión " + i + ": " + aviones[i]);
        }
        System.out.println("Asigna un avión al vuelo(inserta el número de avión):");
        numAvion = sc.nextInt();
        a = aviones[numAvion];
        System.out.println("Indica el precio (por persona) del vuelo:");
        precio = sc.nextFloat();

        vuelos.add(new Vuelo(idVuelo, origen, destino, duracionViaje, dateSalida, dateLlegada, plazasDisponibles, porcenDevolucion, p, a, precio));
    }

    /**
     * Consultar toda la información relacionada a un vuelo mediante el número identificador de vuelo.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @param vuelos ArrayList con los vuelos almacenados en la aplicación.
     */
    public static void consultarVuelo(Scanner sc, ArrayList<Vuelo> vuelos) {
        int buscarId;

        System.out.println("Inserta el identificador del vuelo a consultar:");
        buscarId = sc.nextInt();
        for (Vuelo vuelo : vuelos) {
            if (buscarId == vuelo.getIdVuelo()) {
                System.out.println("Datos del vuelo: " + vuelo);
            }
        }
    }

    /**
     * Modificar uno de los atributos de un vuelo en concreto meidante el número identificador (no se permite cambiar el número de id).
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @param vuelos ArrayList con los vuelos almacenados en la aplicación.
     * @param pilotos Array con los pilotos almacenados.
     * @param aviones Array con los aviones almacenados.
     * @param anyoSalida Año de salida del vuelo.
     * @param mesSalida Mes de salida del vuelo.
     * @param diaSalida Día de salida del vuelo.
     * @param horaSalida Hora de salida del vuelo.
     * @param minSalida Minuto de salida del vuelo.
     * @param horaDuracion Hora/s que dura el viaje.
     * @param minDuracion Minuto/s que dura el viaje.
     */
    public static void modificarVuelo(Scanner sc, ArrayList<Vuelo> vuelos, Piloto[] pilotos, Avion[] aviones, int anyoSalida, int mesSalida, int diaSalida, int horaSalida, int minSalida, int horaDuracion, int minDuracion) {
        int buscarId, plazasDisponibles, porcenDevolucion, numPiloto, numAvion, alterDato;
        String origen, destino;
        float precio;
        LocalDateTime dateSalida, dateLlegada;
        LocalTime duracionViaje;
        Piloto p;
        Avion a;

        do {
            System.out.println("Inserta el identificador del vuelo a modificar:");
            buscarId = sc.nextInt();
            System.out.println("Indica el dato a modificar:");
            System.out.println("1.-Origen");
            System.out.println("2.-Destino");
            System.out.println("3.-Salida de vuelo");
            System.out.println("4.-Duración de vuelo");
            System.out.println("5.-Plazas disponibles");
            System.out.println("6.-Porcentaje de devolución");
            System.out.println("7.-Piloto");
            System.out.println("8.-Avión");
            System.out.println("9.-Precio");
            alterDato = sc.nextInt();
        } while (alterDato < 1 || alterDato > 9);

        for (Vuelo vuelo : vuelos) {
            if (buscarId == vuelo.getIdVuelo()) {
                switch (alterDato) {
                    case 1 -> {
                        System.out.println("Nuevo origen de vuelo:");
                        origen = sc.next();
                        vuelo.setOrigen(origen);
                    }
                    case 2 -> {
                        System.out.println("Nuevo destino de vuelo:");
                        destino = sc.next();
                        vuelo.setDestino(destino);
                    }
                    case 3 -> {
                        System.out.println("Información referente a la salida del vuelo (solo números)");
                        System.out.println("Nuevo año:");
                        anyoSalida = sc.nextInt();
                        System.out.println("Nuevo mes:");
                        mesSalida = sc.nextInt();
                        System.out.println("Nuevo día:");
                        diaSalida = sc.nextInt();
                        System.out.println("Nueva hora:");
                        horaSalida = sc.nextInt();
                        System.out.println("Nuevo minuto:");
                        minSalida = sc.nextInt();
                        dateSalida = LocalDateTime.of(anyoSalida, mesSalida, diaSalida, horaSalida, minSalida);
                        vuelo.setDateSalida(dateSalida);
                        dateLlegada = Vuelo.calcularHoraLlegada(anyoSalida, mesSalida, diaSalida, horaSalida, minSalida, horaDuracion, minDuracion);
                        vuelo.setDateLlegada(dateLlegada);
                    }
                    case 4 -> {
                        System.out.println("Información referente a la duración de vuelo:");
                        System.out.println("Nueva horas:");
                        horaDuracion = sc.nextInt();
                        System.out.println("Nuevo minutos:");
                        minDuracion = sc.nextInt();
                        duracionViaje = LocalTime.of(horaDuracion, minDuracion);
                        vuelo.setDuracionViaje(duracionViaje);
                        dateLlegada = Vuelo.calcularHoraLlegada(anyoSalida, mesSalida, diaSalida, horaSalida, minSalida, horaDuracion, minDuracion);
                        vuelo.setDateLlegada(dateLlegada);
                    }
                    case 5 -> {
                        System.out.println("Nuevo número de plazas disponibles:");
                        plazasDisponibles = sc.nextInt();
                        vuelo.setPlazasDisponibles(plazasDisponibles);
                    }
                    case 6 -> {
                        System.out.println("Nuevo porcentaje de devolución:");
                        porcenDevolucion = sc.nextInt();
                        vuelo.setPorcenDevolucion(porcenDevolucion);
                    }
                    case 7 -> {
                        for (int i = 0; i < pilotos.length; i++) {
                            System.out.println("Piloto " + i + ": " + pilotos[i]);
                        }
                        System.out.println("Asigna un nuevo piloto al vuelo (inserta el número de piloto):");
                        numPiloto = sc.nextInt();
                        p = pilotos[numPiloto];
                        vuelo.setP(p);
                    }
                    case 8 -> {
                        for (int i = 0; i < aviones.length; i++) {
                            System.out.println("Avión: " + i + ": " + aviones[i]);
                        }
                        System.out.println("Asigna un nuevo avión al vuelo (inserta el número de avión):");
                        numAvion = sc.nextInt();
                        a = aviones[numAvion];
                        vuelo.setA(a);
                    }
                    case 9 -> {
                        System.out.println("Nuevo precio (por persona):");
                        precio = sc.nextFloat();
                        vuelo.setPrecio(precio);
                    }
                }
            }
        }
    }

    /**
     * Eliminación de un vuelo mediante su número identificador.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @param vuelos ArrayList con los vuelos almacenados en la aplicación.
     */
    public static void eliminarVuelo(Scanner sc, ArrayList<Vuelo> vuelos) {
        int elimina;

        System.out.println("Inserta el identificador del vuelo a eliminar:");
        elimina = sc.nextInt();

        for (int i = 0; i < vuelos.size(); i++) {
            if (elimina == vuelos.get(i).getIdVuelo()) {
                vuelos.remove(i);
            }
        }
    }

    /**
     * Muestra los aviones creados al inicio de la aplicación.
     *
     * @param aviones Array con los aviones almacenados.
     */
    public static void mostrarAviones(Avion[] aviones) {
        int i = 1;
        for (Avion avions : aviones) {
            System.out.println(i + ".-" + avions);
            i++;
        }
    }

    /**
     * Muestra los aviones creados al inicio de la aplicación.
     *
     * @param pilotos Array con los pilotos almacenados.
     */
    public static void mostrarPilotos(Piloto[] pilotos) {
        int i = 1;
        for (Piloto pilot : pilotos) {
            System.out.println(i + ".-" + pilot);
            i++;
        }
    }

    /**
     * Menú con todas las opciones para el usuario.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @return Número de la opción escogida por el usuario.
     */
    private static int menuUser(Scanner sc) {
        int menu;

        do {
            System.out.println("\nAhora elige una de las siguientes opciones:");
            System.out.println("1.- Visualizar la información del vuelo");
            System.out.println("2.- Comprar billetes");
            System.out.println("3.- Anular billetes");
            System.out.println("4.- Salir");
            menu = sc.nextInt();
        } while (menu < 1 || menu > 4);

        return menu;
    }

    /**
     * Muestra la información de todos lo billetes comprados por el cliente.
     *
     * @param c Objeto del tipo Cliente con toda su información.
     */
    public static void verInfoVuelo(Cliente c) {

        for (Billete billete : c.getBilletesUser()) {
            System.out.println(billete);
        }
    }

    /**
     * Permite al cliente comprar un billete de vuelo.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @param vuelos ArrayList con los vuelos anteriormente creados por el administrador.
     * @param c Objeto del tipo Cliente con toda su información.
     */
    public static void comprarBillete(Scanner sc, ArrayList<Vuelo> vuelos, Cliente c) {
        int numVuelo, numPersonas, confirmacion, numPlazasNuevas;
        ArrayList<Integer> numIds = new ArrayList<>();
        float precioTotal;
        Vuelo v = null;

        System.out.println("Vuelos disponibles:");
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getPlazasDisponibles() != 0) {
                System.out.println(vuelo);
                numIds.add(vuelo.getIdVuelo());
            }
        }
        do {
            System.out.println("Indica el identificador de vuelo:");
            numVuelo = sc.nextInt();
        }while (!numIds.contains(numVuelo));

        System.out.println("Indica el número de pasajeros:");
        numPersonas = sc.nextInt();

        for (Vuelo vuelo : vuelos) {
            if (vuelo.getIdVuelo() == numVuelo) {
                v = vuelo;
            }
        }

        if (v != null && v.getPlazasDisponibles() < numPersonas) {
            System.out.println("Lo sentimos, el billete que ha escogido ha sido anulado a causa de falta de asientos." +
                    "\nEl número de asientos disponibles en el vuelo es:" + v.getPlazasDisponibles());
        } else {
            precioTotal = Billete.calcularPrecio(numPersonas, v);
            System.out.println("El precio total del billete será de " + precioTotal + " euros.");
            do {
                System.out.println("¿Quiere confirmar la compra del billete?");
                System.out.println("1.- Sí");
                System.out.println("2.- No");
                confirmacion = sc.nextInt();
            } while (confirmacion < 1 || confirmacion > 2);

            if (confirmacion == 1) {
                c.getBilletesUser().add(new Billete(precioTotal, v, numPersonas));
                //Arreglar número de plazas disponibles
                numPlazasNuevas = v.getPlazasDisponibles() - numPersonas;
                v.setPlazasDisponibles(numPlazasNuevas);

                System.out.println("Se han comprado los billetes correctamente");
            } else {
                System.out.println("Se ha anulado la compra");
            }
        }
    }

    /**
     * Elimina los billetes anteriormente comprados mediante el número identificador de vuelo y el número de personas.
     *
     * @param sc Clase Scanner para leer las respuestas del usuario.
     * @param c Objeto del tipo Cliente con toda su información.
     */
    public static void anularBillete(Scanner sc, Cliente c) {
        int idVuelo, numBilletes, confirmacion, numPlazasNuevas;
        Billete b = null;
        ArrayList<Integer> numIds = new ArrayList<>();
        float precioDevolucion;

        for (Billete billete: c.getBilletesUser()) {
            numIds.add(billete.getV().getIdVuelo());
        }

        do {
            System.out.println("Indique el número de identificador del vuelo:");
            idVuelo = sc.nextInt();
        }while (!numIds.contains(idVuelo));
        System.out.println("Indique el número de billetes a anular:");
        numBilletes = sc.nextInt();

        for (Billete billete : c.getBilletesUser()) {
            if (idVuelo == billete.getV().getIdVuelo()) {
                b = billete;
            }
        }

        if (numBilletes > b.getNumPasajeros()) {
            System.out.println("Se ha detectado un error en el número de billetes a anular por favor compruebe la información y vuelva a intentar más tarde.");
        } else {
            precioDevolucion = Billete.calcularDevolucion(b.getV(), numBilletes);

            System.out.println("La cantidad total de devolución será:" + precioDevolucion);
            do {
                System.out.println("¿Desea continuar con la anulación de los billetes?");
                System.out.println("1.- Sí");
                System.out.println("2.- No");
                confirmacion = sc.nextInt();
            } while (confirmacion < 1 || confirmacion > 2);
            if (confirmacion == 1) {
                b.setNumPasajeros(b.getNumPasajeros() - numBilletes);
                b.setPrecioTotal(b.getPrecioTotal() - precioDevolucion);
                //Arreglar número de plazas disponibles
                numPlazasNuevas = b.getV().getPlazasDisponibles() + numBilletes;
                b.getV().setPlazasDisponibles(numPlazasNuevas);

                System.out.println("Se han anulado los billetes correctamente");
            } else {
                System.out.println("NO se han anulado los billetes");
            }
        }
    }
}