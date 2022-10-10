package Tarea22.Tarea22;

import Tarea22.Tarea22.Modelo;
import Tarea22.Tarea22.View;

import java.sql.Connection;
import java.sql.SQLException;

import Tarea22.Tarea22.ConexionDB;
import Tarea22.Tarea22.Modelo;

public class PiezaController {
	/*public void setIdPieza(int id){
		model.setId(id);
	}
	public String getIdPieza(){
		return model.getId();
	}
	public void updateView(){
		view.printStudentDetails(model.getNombre(), model.getId());
	}*/

	public static void seleccionOpciones(Connection conn) {
		boolean correcto;
		int id;
		String nombre;
		Object[][] data;
		switch (Tarea22.Tarea22.View.menuPrincipal()) {
			case 1:
				correcto=Tarea22.Tarea22.Modelo.EliminarTablaPieza(conn);
				Tarea22.Tarea22.View.accionCorrecta(correcto);
				correcto=Tarea22.Tarea22.Modelo.CrearTablaPieza(conn);
				Tarea22.Tarea22.View.accionCorrecta(correcto);
				break;
			case 2:
				String[] nombres=new String[5];
				for (int i=0;i<nombres.length;i++) {
					nombres[i]=Tarea22.Tarea22.View.pedirNombre();
				}
				correcto=Tarea22.Tarea22.Modelo.InsertarPaquetePiezas(nombres, conn);
				Tarea22.Tarea22.View.accionCorrecta(correcto);
				break;
			case 3:
				correcto=Tarea22.Tarea22.Modelo.EliminarTablaPieza(conn);
				Tarea22.Tarea22.View.accionCorrecta(correcto);
				break;
			case 4:
				switch (Tarea22.Tarea22.View.menuCRUD()) {
					case 11:
						nombre=Tarea22.Tarea22.View.pedirNombre();
						Tarea22.Tarea22.Modelo.NuevaPieza(nombre,conn);
						break;
					case 22:
						id=Tarea22.Tarea22.View.pedirId();
						try {
							data=Tarea22.Tarea22.Modelo.getRegistro(id,conn);
							Tarea22.Tarea22.View.mostrarTabla(data);
						} catch (SQLException e) {
							Tarea22.Tarea22.View.mostrarError(e);
						}
						break;
					case 33:
						data=Tarea22.Tarea22.Modelo.getTablaPieza(conn);
						Tarea22.Tarea22.View.mostrarTabla(data);
						break;
					case 44:
						nombre=Tarea22.Tarea22.View.pedirNombre();
						id=Tarea22.Tarea22.View.pedirId();
						correcto=Tarea22.Tarea22.Modelo.modificarRegistro(nombre, id,conn);
						Tarea22.Tarea22.View.accionCorrecta(correcto);
						break;
					case 55:
						id=Tarea22.Tarea22.View.pedirId();
						Tarea22.Tarea22.Modelo.EliminarPieza(id,conn);
						break;
					default:
				}
				break;
			default:
				Tarea22.Tarea22.View.mensajeSalir();
		}
		
	}
	
	public static Connection iniciar() {
		Connection conn;
		conn=Tarea22.Tarea22.ConexionDB.conexion();
    	if (conn!=null) {
    		System.out.println("Connexión correcta");
    	}
    	else {
    		System.out.println("Connexión incorrecta");
    	}
		return conn;
	}
}
