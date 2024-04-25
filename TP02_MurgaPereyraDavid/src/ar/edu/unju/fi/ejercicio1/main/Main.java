package ar.edu.unju.fi.ejercicio1.main;

import java.util.ArrayList;
import java.util.Scanner;
import ar.edu.unju.fi.ejercicio1.model.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();

        int opcion;
        do {
            System.out.println("Menú de opciones:");
            System.out.println("1 – Crear Producto");
            System.out.println("2 – Mostrar productos");
            System.out.println("3 – Modificar producto");
            System.out.println("4 – Salir");
            System.out.println("Seleccione una opción:");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearProducto(scanner, productos);
                    break;
                case 2:
                    mostrarProductos(productos);
                    break;
                case 3:
                    modificarProducto(scanner, productos);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor seleccione una opción válida.");
            }
        } while (opcion != 4);
        
        scanner.close();
    }

    private static void crearProducto(Scanner scanner, ArrayList<Producto> productos) {
        System.out.println("Ingrese el código del producto:");
        String codigo = scanner.nextLine();
        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();
        System.out.println("Ingrese el precio unitario del producto:");
        double precioUnitario = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("------ Origen de fabricación ------");
        mostrarOpcionesOrigenFabricacion();
        int opcionOrigen = scanner.nextInt();
        OrigenFabricacion origen = OrigenFabricacion.values()[opcionOrigen - 1];
        scanner.nextLine();

        System.out.println("------ Categoría ------");
        mostrarOpcionesCategoria();
        int opcionCategoria = scanner.nextInt();
        Categoria categoria = Categoria.values()[opcionCategoria - 1];
        scanner.nextLine(); 

        Producto producto = new Producto(codigo, descripcion, precioUnitario, origen, categoria);
        productos.add(producto);
        System.out.println("Producto creado exitosamente.");
    }

    private static void mostrarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos para mostrar.");
            return;
        }

        System.out.println("Lista de productos:");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private static void modificarProducto(Scanner scanner, ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos para modificar.");
            return;
        }

        System.out.println("Ingrese el código del producto que desea modificar:");
        String codigo = scanner.nextLine();

        Producto productoAModificar = null;
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                productoAModificar = producto;
                break;
            }
        }

        if (productoAModificar == null) {
            System.out.println("No se encontró ningún producto con ese código.");
            return;
        }

        System.out.println("Seleccione qué desea modificar:");
        System.out.println("1 - Descripción");
        System.out.println("2 - Precio unitario");
        System.out.println("3 - Origen fabricación");
        System.out.println("4 - Categoría");
        int opcionModificar = scanner.nextInt();
        scanner.nextLine();

        switch (opcionModificar) {
            case 1:
                System.out.println("Ingrese la nueva descripción:");
                String nuevaDescripcion = scanner.nextLine();
                productoAModificar.setDescripcion(nuevaDescripcion);
                System.out.println("Descripción modificada exitosamente.");
                break;
            case 2:
                System.out.println("Ingrese el nuevo precio unitario:");
                double nuevoPrecio = scanner.nextDouble();
                productoAModificar.setPrecioUnitario(nuevoPrecio);
                System.out.println("Precio unitario modificado exitosamente.");
                break;
            case 3:
                System.out.println("------ Origen de fabricación ------");
                mostrarOpcionesOrigenFabricacion();
                int opcionOrigen = scanner.nextInt();
                OrigenFabricacion nuevoOrigen = OrigenFabricacion.values()[opcionOrigen - 1];
                productoAModificar.setOrigenFabricacion(nuevoOrigen);
                System.out.println("Origen de fabricación modificado exitosamente.");
                break;
            case 4:
                System.out.println("------ Categoría ------");
                mostrarOpcionesCategoria();
                int opcionCategoria = scanner.nextInt();
                Categoria nuevaCategoria = Categoria.values()[opcionCategoria - 1];
                productoAModificar.setCategoria(nuevaCategoria);
                System.out.println("Categoría modificada exitosamente.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void mostrarOpcionesOrigenFabricacion() {
        for (int i = 0; i < OrigenFabricacion.values().length; i++) {
            System.out.println((i + 1) + " - " + OrigenFabricacion.values()[i]);
        }
        System.out.println("Elija una opción:");
    }

    private static void mostrarOpcionesCategoria() {
        for (int i = 0; i < Categoria.values().length; i++) {
            System.out.println((i + 1) + " - " + Categoria.values()[i]);
        }
        System.out.println("Elija una opción:");
    }
}