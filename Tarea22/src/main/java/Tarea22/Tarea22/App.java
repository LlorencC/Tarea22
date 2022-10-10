package Tarea22.Tarea22;


import java.sql.Connection;

import Tarea22.Tarea22.PiezaController;

public class App {
	public static void main( String[] args ) {
		Connection conn=null;
		try {
			conn=Tarea22.Tarea22.PiezaController.iniciar();
		} catch (Exception e) {
			e.printStackTrace();
		}
			Tarea22.Tarea22.PiezaController.seleccionOpciones(conn);
    }
}
