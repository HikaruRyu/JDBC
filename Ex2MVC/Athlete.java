package Ex2MVC;

public class Athlete {
    private int id;
    private String name;
    private int sportId;

    // Constructor con dos parámetros
    public Athlete(String name, int sportId) {
        this.name = name;
        this.sportId = sportId;
    }

    // Constructor con tres parámetros (si necesitas un constructor con ID)
    public Athlete(int id, String name, int sportId) {
        this.id = id;
        this.name = name;
        this.sportId = sportId;
    }

    // Getters y setters (si es necesario)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSportId() {
        return sportId;
    }

    @Override
    public String toString() {
        return "Athlete{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", sportId=" + sportId +
               '}';
    }
}
