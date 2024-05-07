package ar.edu.unju.fi.ejercicio5.main;
import ar.edu.unju.fi.ejercicio5.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio5.model.PagoTarjeta;
import ar.edu.unju.fi.ejercicio5.model.Producto;
import ar.edu.unju.fi.ejercicio5.model.Producto.*;

import java.time.LocalDate;
import java.util.*;
public class Main {
	public static void main(String [] args) {
		int opcion=0;
		ArrayList<Producto> productos = precargarProductos();
		
		Scanner scanner = new Scanner(System.in);
		do {
			try {
				mostrarMenu();
			    opcion = scanner.nextInt();
		    	scanner.nextLine();

			    switch (opcion) {
			    	case 1:
						System.out.println("*********** PRODUCTOS DISPONIBLES ***********");
			            mostrarProductos(productos);
			            break;
			        case 2: 
			        	realizarCompra(productos);
			            break;  
			        case 3:
			            break;
			        default:
			            System.out.println("INGRESE UNA OPCIÓN VÁLIDA [1-3]"); 
			    }
			    
			}catch (InputMismatchException e) {
				System.out.println("INGRESE UNA ENTRADA VALIDA.");
                scanner.next();
               
             }
		    
		}while (opcion!=3);
		
	}
	
	public static void mostrarMenu() {
	      System.out.println("\n*********** MENU *********");
	      System.out.println("1) Mostrar productos disponibles");
	      System.out.println("2) Realizar compra");
	      System.out.println("3) Salir");
	      System.out.print("Ingrese su opción: ");
	}
	private static void mostrarProductos(ArrayList<Producto> productos) {
	        for (Producto producto : productos) {
	        	System.out.println(producto+"\n");
	        }
	}
	
	private static ArrayList<Producto> productosDisponibles(ArrayList<Producto> productos){
		ArrayList<Producto> productosStock =new ArrayList<>();
		for (Producto producto : productos) {
			if (producto.isStock_disponible()) {
				productosStock.add(producto);
			}
        }
		return productosStock;
	}
	
	private static void realizarCompra(ArrayList<Producto> productos) {
		Scanner scanner = new Scanner(System.in);
		int opcion=0;
		boolean band= true;
		do {
			
		    try {
		    	System.out.println("1) - Agregar producto al carrito ");
		    	System.out.println("2) - Cancelar compra");
				opcion = scanner.nextInt();
			    scanner.nextLine();
		    	switch (opcion) {
				case 1:
					System.out.println("---------PRODUCTOS EN STOCK---------");
					ArrayList<Producto> productosStock=productosDisponibles(productos);
					mostrarProductos(productosStock);
					ArrayList<Producto> productoSeleccionado = new ArrayList<>();
					seleccionarProductos(productoSeleccionado,productosStock);
					double montoTotal=calcularMontoTotal(productoSeleccionado);
					mostrarOpcionesPago(montoTotal);
					band=false;
					break;
				case 2:
					band=false;
					break;
				default:
					System.out.println("INGRESE UNA OPCION DE PAGO VALIDA [1-2]");
				}
			} catch (Exception e) {
				System.out.println("INGRESE UNA ENTRADA VALIDA.");
                scanner.next();
                
			}		
		}while(band);
	}
	
	private static void seleccionarProductos(ArrayList<Producto> productoSeleccionado,ArrayList<Producto> productosStock) {
		Scanner scanner = new Scanner(System.in);
		String continuar="s";
		while(continuar.equalsIgnoreCase("s")) {
			validarCodigo(productoSeleccionado,productosStock);
			mostrarProductos(productoSeleccionado);
			System.out.println("----MONTO A PAGAR :" +calcularMontoTotal(productoSeleccionado)+ " ----");
			System.out.println("Desea agregar mas productos al carrito (s/n)");
			continuar= scanner.nextLine();
		}
		
	}
	
	
	private static double calcularMontoTotal(ArrayList<Producto> productoSeleccionado) {
		double monto=0;
		for (Producto producto : productoSeleccionado) {
			monto+=producto.getPrecioUnitario();
		}
		return monto;
	}
	
	
	private static void validarCodigo(ArrayList<Producto> productoSeleccionado,ArrayList<Producto> productosStock) {
		Scanner scanner = new Scanner(System.in);
		boolean valido=false;
		String codigo;
		do {
			System.out.println("Ingrese el codigo del producto deseado :");
			codigo = scanner.nextLine();
			for (Producto producto : productosStock ) {
				if (codigo.equals(producto.getCodigo())) {
					valido=true;
					productoSeleccionado.add(producto);
					System.out.println("El producto fue agregado correctamente");
					break;
				}				
			} 
			if (!valido) {System.out.println("El codigo ingresado no es valido ");}
		}while (!valido);
	}
	
    
	private static void mostrarOpcionesPago(double montoTotal) {
    	int opcion;
		Scanner scanner = new Scanner(System.in);
		boolean band= true;
		while(band) {
		    try {
		    	System.out.println(" \n ----MONTO TOTAL :" + montoTotal+" ----- " );
		    	System.out.println("\nOpciones de pago:");
		        System.out.println("1) - Pago en efectivo(- 10%)");
		        System.out.println("2) - Pago con tarjeta (+ 15%)");
		        System.out.print("Ingrese su opcion de pago: ");
		        opcion = scanner.nextInt();
			    scanner.nextLine();
		        switch (opcion) {
				case 1:
					PagoEfectivo pagoE = new PagoEfectivo (montoTotal,LocalDate.of(2024, 5, 4));
					pagoE.imprimirRecibo();
					band=false;
					break;
				case 2:
					PagoTarjeta pagoT= new PagoTarjeta("12345678", LocalDate.of(2024, 5, 4), montoTotal);
					pagoT.imprimirRecibo();
					band=false;
					break;
				default:
					System.out.println("INGRESE UNA OPCION DE PAGO VALIDA [1-2]");
					break;
				}
			} catch (Exception e) {
				System.out.println("INGRESE UNA ENTRADA VALIDA.");
                scanner.next();    
			}
		}
    }
	
    
  
    private static ArrayList<Producto> precargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("001", "Teléfono celular", 15000.0, OrigenFabricacion.ARGENTINA, Categoria.TELEFONIA, true));
        productos.add(new Producto("002", "Laptop", 50000.0, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        productos.add(new Producto("003", "Televisor LED", 35000.0, OrigenFabricacion.BRASIL, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("004", "Herramienta eléctrica", 2000.0, OrigenFabricacion.URUGUAY, Categoria.HERRAMIENTAS, true));
        productos.add(new Producto("005", "Tablet", 10000.0, OrigenFabricacion.ARGENTINA, Categoria.INFORMATICA, false));
        productos.add(new Producto("006", "Aspiradora", 7000.0, OrigenFabricacion.CHINA, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("007", "Smartwatch", 8000.0, OrigenFabricacion.BRASIL, Categoria.TELEFONIA, true));
        productos.add(new Producto("008", "Impresora", 6000.0, OrigenFabricacion.URUGUAY, Categoria.INFORMATICA, false));
        productos.add(new Producto("009", "Cámara digital", 12000.0, OrigenFabricacion.ARGENTINA, Categoria.INFORMATICA, true));
        productos.add(new Producto("010", "Refrigerador", 25000.0, OrigenFabricacion.CHINA, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("011", "Cafetera", 3000.0, OrigenFabricacion.BRASIL, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("012", "Smartphone", 20000.0, OrigenFabricacion.URUGUAY, Categoria.TELEFONIA, true));
        productos.add(new Producto("013", "Monitor", 8000.0, OrigenFabricacion.ARGENTINA, Categoria.INFORMATICA, true));
        productos.add(new Producto("014", "Silla de oficina", 1500.0, OrigenFabricacion.CHINA, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto("015", "Secadora de cabello", 2500.0, OrigenFabricacion.BRASIL, Categoria.ELECTROHOGAR, true));
        return productos;
    }
}