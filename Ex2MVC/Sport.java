package Ex2MVC;

public class Sport {
    private int id;
    private String name;

    public Sport(String name) {
        this.name = name;
    }

    public Sport(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getName() { return name; }
    @Override
    public String toString() { return id + " - " + name; }
}
