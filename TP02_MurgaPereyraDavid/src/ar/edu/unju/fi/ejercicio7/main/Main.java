package ar.edu.unju.fi.ejercicio7.main;

import ar.edu.unju.fi.ejercicio5.model.Producto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ArrayList<Producto> productos = precargarProductos();

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1 - Mostrar productos");
            System.out.println("2 - Mostrar los productos faltantes");
            System.out.println("3 - Incrementar los precios de los productos en un 20%");
            System.out.println("4 - Mostrar los productos de categoría Electrohogar disponibles");
            System.out.println("5 - Ordenar los productos por precio de forma descendente");
            System.out.println("6 - Mostrar los productos con los nombres en mayúsculas");
            System.out.println("7 - Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarProductos(productos, p -> System.out.println(p));
                    break;
                case 2:
                    mostrarProductosFaltantes(productos);
                    break;
                case 3:
                    ArrayList<Producto> productosIncrementados = incrementarPrecios(productos);
                    mostrarProductos(productosIncrementados, p -> System.out.println(p));
                    break;
                case 4:
                    mostrarProductosDeElectrohogarDisponibles(productos);
                    break;
                case 5:
                    ordenarProductosPorPrecioDescendente(productos);
                    mostrarProductos(productos, p -> System.out.println(p));
                    break;
                case 6:
                    mostrarProductosEnMayusculas(productos);
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        scanner.close();
    }

        private static ArrayList<Producto> precargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Producto producto = new Producto("Código" + i, "Descripción" + i, 10 * i, Producto.OrigenFabricacion.ARGENTINA,
                    Producto.Categoria.ELECTROHOGAR, i % 2 == 0);
            productos.add(producto);
        }
        return productos;
    }

    private static void mostrarProductos(List<Producto> productos, Consumer<Producto> consumer) {
        productos.forEach(consumer);
    }

    private static void mostrarProductosFaltantes(List<Producto> productos) {
        Predicate<Producto> noDisponibles = p -> !p.isStock_disponible();
        List<Producto> productosFaltantes = productos.stream().filter(noDisponibles).collect(Collectors.toList());
        mostrarProductos(productosFaltantes, p -> System.out.println(p));
    }

    private static ArrayList<Producto> incrementarPrecios(List<Producto> productos) {
        Function<Producto, Producto> incrementarPrecio = p -> {
            p.setPrecioUnitario(p.getPrecioUnitario() * 1.20);
            return p;
        };
        return productos.stream().map(incrementarPrecio).collect(Collectors.toCollection(ArrayList::new));
    }

    private static void mostrarProductosDeElectrohogarDisponibles(List<Producto> productos) {
        Predicate<Producto> electrohogarDisponible = p ->
                p.getCategoria() == Producto.Categoria.ELECTROHOGAR && p.isStock_disponible();
        List<Producto> productosElectrohogarDisponibles = productos.stream().filter(electrohogarDisponible).collect(Collectors.toList());
        mostrarProductos(productosElectrohogarDisponibles, p -> System.out.println(p));
    }

    private static void ordenarProductosPorPrecioDescendente(List<Producto> productos) {
        Comparator<Producto> comparadorPorPrecioDescendente = Comparator.comparing(Producto::getPrecioUnitario).reversed();
        productos.sort(comparadorPorPrecioDescendente);
    }

    private static void mostrarProductosEnMayusculas(List<Producto> productos) {
        Function<Producto, Producto> convertirNombreAMayusculas = p -> {
            p.setDescripcion(p.getDescripcion().toUpperCase());
            return p;
        };
        List<Producto> productosEnMayusculas = productos.stream().map(convertirNombreAMayusculas).collect(Collectors.toList());
        mostrarProductos(productosEnMayusculas, p -> System.out.println(p));
    }
}
