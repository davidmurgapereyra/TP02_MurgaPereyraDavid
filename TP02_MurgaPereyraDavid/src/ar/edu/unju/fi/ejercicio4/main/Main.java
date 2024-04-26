package ar.edu.unju.fi.ejercicio4.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import ar.edu.unju.fi.ejercicio4.constantes.*;
import ar.edu.unju.fi.ejercicio4.model.*;

public class Main {
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<>();

        int opcion;
        do {
            System.out.println("Menú de opciones:");
            System.out.println("1 - Alta de jugador");
            System.out.println("2 - Mostrar todos los jugadores");
            System.out.println("3 - Modificar la posición de un jugador");
            System.out.println("4 - Eliminar un jugador");
            System.out.println("5 - Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    altaJugador(jugadores, scanner);
                    break;
                case 2:
                    mostrarJugadores(jugadores);
                    break;
                case 3:
                    modificarPosicion(jugadores, scanner);
                    break;
                case 4:
                    eliminarJugador(jugadores, scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
        
        scanner.close();
    }

    private static void altaJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del jugador:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese la fecha de nacimiento del jugador (YYYY-MM-DD):");
        String fechaNacimientoStr = scanner.nextLine();
        System.out.println("Ingrese la nacionalidad del jugador:");
        String nacionalidad = scanner.nextLine();
        System.out.println("Ingrese la estatura del jugador:");
        double estatura = scanner.nextDouble();
        System.out.println("Ingrese el peso del jugador:");
        double peso = scanner.nextDouble();
        System.out.println("Ingrese la posición del jugador (DELANTERO, MEDIO, DEFENSA, ARQUERO):");
        Posicion posicion = Posicion.valueOf(scanner.next().toUpperCase());
        
        jugadores.add(new Jugador(nombre, apellido, LocalDate.parse(fechaNacimientoStr), nacionalidad, estatura, peso, posicion));
        System.out.println("Jugador agregado correctamente.");
    }

    private static void mostrarJugadores(ArrayList<Jugador> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados.");
            return;
        }
        System.out.println("Listado de jugadores:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + " " + jugador.getApellido() + " - Edad: " + jugador.calcularEdad() + " - Posición: " + jugador.getPosicion());
        }
    }

    private static void modificarPosicion(ArrayList<Jugador> jugadores, Scanner scanner) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados.");
            return;
        }
        System.out.println("Ingrese el nombre del jugador:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del jugador:");
        String apellido = scanner.nextLine();
        
        boolean encontrado = false;
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Ingrese la nueva posición del jugador (DELANTERO, MEDIO, DEFENSA, ARQUERO):");
                Posicion nuevaPosicion = Posicion.valueOf(scanner.next().toUpperCase());
                jugador.setPosicion(nuevaPosicion);
                System.out.println("Posición modificada correctamente.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún jugador con ese nombre y apellido.");
        }
    }

    private static void eliminarJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados.");
            return;
        }
        System.out.println("Ingrese el nombre del jugador:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del jugador:");
        String apellido = scanner.nextLine();
        
        boolean encontrado = false;
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                jugadores.remove(i);
                System.out.println("Jugador eliminado correctamente.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún jugador con ese nombre y apellido.");
        }
    }

}
