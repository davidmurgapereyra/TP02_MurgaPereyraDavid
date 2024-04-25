package ar.edu.unju.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.Scanner;
import ar.edu.unju.fi.ejercicio2.model.*;
import ar.edu.unju.fi.ejercicio2.constantes.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Efemeride> efemerides = new ArrayList<>();

        int opcion;
        do {
            System.out.println("Menú de acciones:");
            System.out.println("1 – Crear efeméride");
            System.out.println("2 – Mostrar efemérides");
            System.out.println("3 – Eliminar efeméride");
            System.out.println("4 – Modificar efeméride");
            System.out.println("5 – Salir");
            System.out.println("Seleccione una opción:");

            opcion = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcion) {
                case 1:
                    crearEfemeride(scanner, efemerides);
                    break;
                case 2:
                    mostrarEfemerides(efemerides);
                    break;
                case 3:
                    eliminarEfemeride(scanner, efemerides);
                    break;
                case 4:
                    modificarEfemeride(scanner, efemerides);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor seleccione una opción válida.");
            }
        } while (opcion != 5);
        
        scanner.close();
    }

    private static void crearEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        System.out.println("Ingrese el código de la efeméride:");
        String codigo = scanner.nextLine();
        System.out.println("Ingrese el mes (número del 1 al 12):");
        int numeroMes = scanner.nextInt();
        scanner.nextLine();
        Mes mes = obtenerMes(numeroMes);
        if (mes == null) {
            System.out.println("Número de mes inválido.");
            return;
        }
        System.out.println("Ingrese el día:");
        int dia = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el detalle:");
        String detalle = scanner.nextLine();

        Efemeride efemeride = new Efemeride(codigo, mes, dia, detalle);
        efemerides.add(efemeride);
        System.out.println("Efeméride creada exitosamente.");
    }

    private static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para mostrar.");
            return;
        }

        System.out.println("Lista de efemérides:");
        for (Efemeride efemeride : efemerides) {
            System.out.println(efemeride);
        }
    }

    private static void eliminarEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para eliminar.");
            return;
        }

        System.out.println("Ingrese el código de la efeméride que desea eliminar:");
        String codigo = scanner.nextLine();

        Efemeride efemerideAEliminar = null;
        for (Efemeride efemeride : efemerides) {
            if (efemeride.getCodigo().equals(codigo)) {
                efemerideAEliminar = efemeride;
                break;
            }
        }

        if (efemerideAEliminar == null) {
            System.out.println("No se encontró ninguna efeméride con ese código.");
            return;
        }

        efemerides.remove(efemerideAEliminar);
        System.out.println("Efeméride eliminada exitosamente.");
    }

    private static void modificarEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para modificar.");
            return;
        }

        System.out.println("Ingrese el código de la efeméride que desea modificar:");
        String codigo = scanner.nextLine();

        Efemeride efemerideAModificar = null;
        for (Efemeride efemeride : efemerides) {
            if (efemeride.getCodigo().equals(codigo)) {
                efemerideAModificar = efemeride;
                break;
            }
        }

        if (efemerideAModificar == null) {
            System.out.println("No se encontró ninguna efeméride con ese código.");
            return;
        }

        System.out.println("Ingrese el nuevo mes (número del 1 al 12):");
        int numeroMes = scanner.nextInt();
        scanner.nextLine();
        Mes mes = obtenerMes(numeroMes);
        if (mes == null) {
            System.out.println("Número de mes inválido.");
            return;
        }
        efemerideAModificar.setMes(mes);

        System.out.println("Ingrese el nuevo día:");
        int dia = scanner.nextInt();
        scanner.nextLine(); 
        efemerideAModificar.setDia(dia);

        System.out.println("Ingrese el nuevo detalle:");
        String detalle = scanner.nextLine();
        efemerideAModificar.setDetalle(detalle);

        System.out.println("Efeméride modificada exitosamente.");
    }

    private static Mes obtenerMes(int numeroMes) {
        Mes[] meses = Mes.values();
        if (numeroMes < 1 || numeroMes > meses.length) {
            return null;
        }
        return meses[numeroMes - 1];
    }
}
