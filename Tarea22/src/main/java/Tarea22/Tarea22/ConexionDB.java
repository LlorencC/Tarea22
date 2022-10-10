package Tarea22.Tarea22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	
	
	public static Connection conexion(){
		String bd = "piezasproveedores2";
		String login = "root";
		String password = "BlazeSQL546!";
		String url = "jdbc:mysql://localhost:3307/"+bd;
		Connection conn = null;
		
	    try {
	    	//obtenemos el driver de para mysql
	    	//Class.forName("com.mysql.jdbc.Driver");
	    	//obtenemos la conexión
	        conn = DriverManager.getConnection(url,login,password);        
	    } catch(SQLException e) {
	    	System.err.println( e.getMessage() );
	    }
//	    	catch(ClassNotFoundException e){
//	    	System.err.println( e.getMessage() );
//	    }
	    return conn;
	}
}
