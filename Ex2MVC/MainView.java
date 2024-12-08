package Ex2MVC;

import java.util.List;
import java.util.Scanner;

public class MainView {

	 private final Scanner scanner = new Scanner(System.in);

	    public int showMenu() {
	        System.out.println("""
	                1. Añadir deporte
	                2. Añadir deportista
	                3. Buscar deportista por nombre
	                4. Listar deportistas por deporte
	                0. Salir
	                """);
	        return scanner.nextInt();
	    }

	    public Sport sportForm() {
	        System.out.print("Nombre del deporte: ");
	        scanner.nextLine(); // Limpiar buffer
	        String name = scanner.nextLine();
	        return new Sport(name);
	    }

	    public Athlete athleteForm(List<Sport> sports) {
	        System.out.print("Nombre del deportista: ");
	        scanner.nextLine(); // Limpiar buffer
	        String name = scanner.nextLine();
	        for (Sport sport : sports) {
	            System.out.println(sport.getId() + ". " + sport.getName());
	        }
	        System.out.print("ID del deporte: ");
	        int sportId = scanner.nextInt();
	        return new Athlete(name, sportId);
	    }

	    public String askForAthleteName() {
	        System.out.print("Nombre del deportista (búsqueda parcial): ");
	        scanner.nextLine(); // Limpiar buffer
	        return scanner.nextLine();
	    }

	    public int askSport(List<Sport> sports) {
	        System.out.println("Seleccione un deporte:");
	        for (Sport sport : sports) {
	            System.out.println(sport.getId() + ". " + sport.getName());
	        }
	        return scanner.nextInt();
	    }

	    public void athleteList(List<Athlete> athletes) {
	        if (athletes.isEmpty()) {
	            System.out.println("No se encontraron deportistas.");
	        } else {
	            // Se invoca el método toString() de cada Athlete
	            athletes.forEach(athlete -> System.out.println(athlete.toString()));
	        }
	    }

	}