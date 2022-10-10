package Tarea22.Tarea22;

import java.sql.SQLException;
import java.util.Scanner;

public class View {
	static Scanner key=new Scanner(System.in);
	
	public static int menuPrincipal() {
		System.out.print("1. Resetear tabla\n2. Insertar paquete datos\n3. Borrar tabla\n4. CRUD\n5. Salir\nElegir opción: ");
		return key.nextInt();
	}
	
	public static int menuCRUD() {
		System.out.print("1. Añadir registro\n2. Consultar por ID\n3. Listar registros\n4. Modificar registro\n5. Borrar registro\nElegir opción: ");
		return key.nextInt();
		
	}
	
	public static void mensajeSalir() {
		System.out.print("Saliendo...");
	}
	
	public static void mostrarError(SQLException e) {
		System.out.print(e);
	}
	
	public static void accionCorrecta(boolean correcto) {
		if (correcto) {
			System.out.println("La acción se ha llevado a cabo correctamente.");
		} else {
			System.out.println("¡Ha habido un error!");
		}
	}
	
	public static void mostrarTabla(Object[][] data) {
		for (int f=0;f<data.length;f++) {
			for (int c=0;c<data[f].length;c++) {
				System.out.print("\t"+data[f][c]+" ");
			}
			System.out.println();
		}
	}
	
	public static String pedirNombre() {
		System.out.print("Inserte el nombre: ");
		return key.next();
	}
	
	public static int pedirId() {
		System.out.print("Inserte el id: ");
		return key.nextInt();
	}
}
