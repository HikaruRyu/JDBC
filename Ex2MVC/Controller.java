package Ex2MVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private static final String URL = "jdbc:postgresql://localhost:5432/sports";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qwedeqwe";

    private static Connection connection;

    // Static block for initializing the connection
    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to return the connection
    public static Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {

        // Initialize view and DAOs
        MainView view = new MainView();
        SportDAO sportDAO = new SportDAO();
        AthleteDAO athleteDAO = new AthleteDAO();

        // Variable for storing user option
        int option;

        do {
            // Show the menu and get user input
            option = view.showMenu();
            
            switch (option) {
                case 1: {
                    // Add new sport
                    Sport newSport = view.sportForm();
                    sportDAO.add(newSport);
                    break;
                }
                case 2: {
                    // Add new athlete
                    List<Sport> sports = sportDAO.getAll();
                    if (sports.isEmpty()) {
                        System.out.println("No hay deportes disponibles. Por favor, añada un deporte primero.");
                    } else {
                        Athlete newAthlete = view.athleteForm(sports);
                        athleteDAO.add(newAthlete);
                    }
                }
                case 3: {
                    // Search athlete by name
                    String name = view.askForAthleteName();
                    List<Athlete> athletes = athleteDAO.findByName(name);
                    view.athleteList(athletes);
                    break;
                }
                case 4: {
                    // List athletes by sport
                    List<Sport> sports = sportDAO.getAll();
                    int sportId = view.askSport(sports);
                    List<Athlete> athletes = athleteDAO.findBySportId(sportId);
                    view.athleteList(athletes);
                    break;
                }
            }
        } while (option != 0); // Loop until user chooses to exit
    }
}
