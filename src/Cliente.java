import java.util.ArrayList;

public class Cliente {
    private int id;
    private String user, password;
    private ArrayList<Billete> billetesUser;

    //CONSTRUCTOR
    public Cliente(int id, String user, String password, ArrayList<Billete> billetesUser) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.billetesUser = billetesUser;
    }


    //SETTERS & GETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Billete> getBilletesUser() {
        return billetesUser;
    }

    public void setBilletesUser(ArrayList<Billete> billetesUser) {
        this.billetesUser = billetesUser;
    }


    //TO STRING

    @Override
    public String toString() {
        return "Cliente:" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", billetesUser=" + billetesUser;
    }

    /**
     * Número autoincremental de id.
     *
     * @return Número id previamente calculado.
     */
    public static int calcularId(){
        int id = 0;

        id++;

        return id;
    }
}

