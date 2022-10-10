package Tarea22.Tarea22;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo extends ConexionDB {
//	/** Constructor de clase */
//    public Modelo() {
//    }
//
    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public static Object[][] getTablaPieza(Connection conn) {
      int registros = 0;
      String[] columNames = {"Codigo","Nombre"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm=conn.prepareStatement("SELECT count(*) as total FROM piezas");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }
      catch (SQLException e) {
         System.err.println( e.getMessage() );
      }
      //se crea una matriz con tantas filas y columnas que necesite
      Object[][] data = new String[registros+1][2];
      data[0][0]=columNames[0];
      data[0][1]=columNames[1];
      try {
    	  //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
    	  PreparedStatement pstm=conn.prepareStatement("SELECT * FROM piezas");
    	  ResultSet res = pstm.executeQuery();
    	  int i=1;
    	  while (res.next()) {
    		  data[i][0] = res.getString("Codigo");
    		  data[i][1] = res.getString("Nombre");
    		  i++;
    	  }
    	  res.close();
         } catch(SQLException e) {
        	 System.err.println( e.getMessage() );
         }
      return data;
    }

    /** Registra un nuevo producto */
    public static boolean NuevaPieza(String nombre, Connection conn) {
        if (valida_datos(nombre)) {
        	//Se arma la consulta
        	String q="INSERT INTO piezas (Codigo, Nombre) VALUES ("+null+",'"+nombre+"') ";
        	//se ejecuta la consulta
            try {
                PreparedStatement pstm=conn.prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            } catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
        else {
        	return false;
        }
    }

    /** Elimina un registro dado su ID -> Llave primaria */
    public static boolean EliminarPieza(int id, Connection conn) {
    	boolean res=false;
    	//se arma la consulta
        String q = "DELETE FROM piezas WHERE  p_id=" +id+"";
        //se ejecuta la consulta
        try {
        	PreparedStatement pstm=conn.prepareStatement(q);
        	pstm.execute();
        	pstm.close();
        	res=true;
        } catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }

    /** Metodo privado para validar datos */
    private static boolean valida_datos(String nombre) {
        if(nombre.length()>0) {
        	return true;
        } else {
        	return false;
        }
    }
    
    public static boolean EliminarTablaPieza(Connection conn) {
    	boolean res=false;
    	String q="DROP TABLE IF EXISTS piezas";
    	try {
        	PreparedStatement pstm=conn.prepareStatement(q);
        	pstm.execute();
        	pstm.close();
        	res=true;
        } catch(SQLException e){
            System.err.println( e.getMessage() );
        }
    	return res;
    }
    
    public static boolean CrearTablaPieza(Connection conn) {
    	boolean res=false;
    	String q="CREATE TABLE IF NOT EXISTS piezas("
    			+ "Codigo INT PRIMARY KEY AUTO_INCREMENT,"
    			+ "Nombre NVARCHAR(100));";
    	try {
        	PreparedStatement pstm=conn.prepareStatement(q);
        	pstm.execute();
        	pstm.close();
        	res=true;
        } catch(SQLException e){
            System.err.println( e.getMessage() );
        }
    	return res;
    }
    
    public static Object[][] getRegistro(int id,Connection conn) throws SQLException {
        String[] columNames = {"Codigo","Nombre"};
        //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
        //para formar la matriz de datos
        Object[][] data = new String[2][2];
        data[0][0]=columNames[0];
        data[0][1]=columNames[1];
      	//realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
      	PreparedStatement pstm=conn.prepareStatement("SELECT * FROM piezas WHERE Codigo="+id);
      	ResultSet res=pstm.executeQuery();
        res.next();
   	    data[1][0] = res.getString(1);
      	data[1][1] = res.getString(2); 
      	res.close();
        return data;
      }
    
    public static boolean modificarRegistro(String nombre, int id, Connection conn) {
    	boolean correcto;
    	String q="UPDATE piezas SET Nombre='"+nombre+"' WHERE Codigo="+id;
    	try {
    		PreparedStatement pstm=conn.prepareStatement(q);
    		pstm.execute();
            pstm.close();
            correcto=true;
    	} catch (SQLException e) {
    		System.err.print(e);
    		correcto=false;
    	}
    	return correcto;
    }
    
    public static boolean InsertarPaquetePiezas(String[] nombres, Connection conn) {
    	boolean correcto=false;
    	for (int i=0;i<nombres.length;i++) {
    		if (valida_datos(nombres[i])) {
            	//Se arma la consulta
            	String q="INSERT INTO piezas (Codigo, Nombre) VALUES ("+null+",'"+nombres[i]+"') ";
            	//se ejecuta la consulta
                try {
                    PreparedStatement pstm=conn.prepareStatement(q);
                    pstm.execute();
                    pstm.close();
                    correcto=true;
                } catch(SQLException e){
                    System.err.println( e.getMessage() );
                }
                correcto=false;
            }
            else {
            	correcto=false;
            }
    	}
    	return correcto;
    }
}
