package Ex2MVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private static final String URL = "jdbc:postgresql://localhost:5432/sports";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qwedeqwe";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize view and DAOs
        MainView view = new MainView();
        SportDAO sportDAO = new SportDAO();
        AthleteDAO athleteDAO = new AthleteDAO();

        int option = -1; 

        do {
            try {
                option = view.showMenu();

                switch (option) {
                    case 1: {
                        Sport newSport = view.sportForm();
                        sportDAO.add(newSport);
                        break;
                    }
                    case 2: {
                        List<Sport> sports = sportDAO.getAll();
                        if (sports.isEmpty()) {
                            System.out.println("No hay deportes disponibles. Por favor, añada un deporte primero.");
                        } else {
                            Athlete newAthlete = view.athleteForm(sports);
                            athleteDAO.add(newAthlete);
                        }
                        break;
                    }
                    case 3: {
                        String name = view.askForAthleteName();
                        List<Athlete> athletes = athleteDAO.findByName(name);
                        view.athleteList(athletes);
                        break;
                    }
                    case 4: {
                        List<Sport> sports = sportDAO.getAll();
                        int sportId = view.askSport(sports);
                        List<Athlete> athletes = athleteDAO.findBySportId(sportId);
                        view.athleteList(athletes);
                        break;
                    }
                    case 0: {
                        System.out.println("Saliendo del programa...");
                        break;
                    }
                    default: {
                        System.err.println("Por favor, introduce un número válido (0-4).");
                    }
                }
            } catch (Exception e) {
                System.err.println("Entrada inválida. Por favor, introduce un número válido.");
                scanner.nextLine();
            }
        } while (option != 0);

        scanner.close();
        closeConnection();
    }
}
